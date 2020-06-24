package controllers

import javax.inject.Inject
import models.RollRequest
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import scala.concurrent.ExecutionContext
import scala.util.Random

class GameController @Inject()(
                                cc: ControllerComponents,
                                authenticatedAction: AuthenticatedAction
                              )(implicit executionContext: ExecutionContext)
  extends AbstractController(cc) {

  def roll: Action[AnyContent] = authenticatedAction { implicit request: UserRequest[AnyContent] =>
    try {
      val rollRequest = request.body.asJson.get.as[RollRequest]
      Ok(Json.toJson((0 until rollRequest.numberOfDice).map(_ => Random.between(0, 10))))
    } catch {
      case _: Throwable => BadRequest("Invalid Json")
    }
  }
}
