package controllers

import dao.GameDao
import javax.inject.Inject
import models.Global
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}

import scala.concurrent.ExecutionContext

class SetupController @Inject()(
                               gameDao: GameDao,
                               cc: ControllerComponents,
                               authenticatedAction: AuthenticatedAction
                               )
                               (implicit executionContext: ExecutionContext)
    extends AbstractController(cc) {
  def showGameList(): Action[AnyContent] = authenticatedAction.async { implicit request: UserRequest[AnyContent] =>
    gameDao.gameList(request.user.firstName).map(list => Ok(Json.toJson(list)))
  }
  def createGame(): Action[AnyContent] = authenticatedAction { implicit request: Request[AnyContent] =>
    Ok(views.html.createGame(request.session(Global.SessionUsernameKey)))
  }
  def newGame(): Action[AnyContent] = ???
}
