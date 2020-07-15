package controllers

import controllers.game.stage._
import controllers.game._
import dao.TeamDao
import javax.inject.Inject
import models.RollRequest
import models.game.State
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future}

class GameController @Inject()(
                                cc: ControllerComponents,
                                gameAction: GameAction,
                                teamDao: TeamDao
                              )(implicit executionContext: ExecutionContext)
  extends AbstractController(cc) {

  private def currentStageController(game: State): GameStage = {
    game.stage match {
      case "DetermineTemperature" => new DetermineTemperature(teamDao)
    }
  }

  def setGame(resource: String = ""): Action[AnyContent] =
    gameAction.async { implicit request: GameRequest[AnyContent] =>
      try {
        val stage = currentStageController(request.game)
        val message = resource match {
          case "roll" =>
            val rollRequest = request.body.asJson.get.as[RollRequest]
            Roll(rollRequest.numberOfDice)
        }
        stage.receive(message, request.userRequest.user, request.game)
      } catch {
        case _: MatchError => Future.successful(BadRequest("Unexpected message"))
      }
    }

  def getGame(resource: String = ""): Action[AnyContent] =
    gameAction.async { implicit request: GameRequest[AnyContent] =>
      try {
        val stage = currentStageController(request.game)
        val optionalMessage = resource match {
          case "roll" => Some(GetRoll(request.queryString("numberOfDice").head.toInt))
          case "temperature" => Some(GetTemperature)
          case "state" => None
        }
        optionalMessage.map(message => {
          stage.receive(message, request.userRequest.user, request.game)
        }).getOrElse(Future.successful(Ok(Json.toJson(request.game))))
      } catch {
        case _: MatchError => Future.successful(BadRequest("Unexpected message"))
      }
    }
}
