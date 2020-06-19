package controllers

import dao.UserDao
import javax.inject.Inject
import models.User
import play.api.mvc.Results.Redirect
import play.api.mvc._
import services.Jwt

import scala.concurrent.{ExecutionContext, Future}

class AuthenticatedAction @Inject()(parser: BodyParsers.Default, jwt: Jwt, userDao: UserDao)(implicit ec: ExecutionContext)
  extends ActionBuilder[UserRequest, AnyContent] {

  override def parser: BodyParser[AnyContent] = parser

  override protected def executionContext: ExecutionContext = ec

  private val HeaderTokenRegex = """Bearer\s+(.+?)""".r

  override def invokeBlock[A](request: Request[A], block: UserRequest[A] => Future[Result]): Future[Result] = {
    val authHeader = request.headers.get("Authorization").getOrElse("")
    val jwtToken = authHeader match {
      case HeaderTokenRegex(token) => token
      case _ => ""
    }
    val userIdLookup = jwt.decodePayload(jwtToken)
    userIdLookup match {
      case None => Future.successful(Redirect("/login"))
      case Some(userId) =>
        userDao.findById(userId).flatMap {
          case Some(row) if row.confirmed => block(UserRequest(User(row), request))
          // Send the user to login if they are no longer confirmed (this would happen if reset password was kicked off
          case Some(_) => Future.successful(Redirect("/login"))
          case None => Future.successful(Redirect("/login"))
        }
    }
  }
}
