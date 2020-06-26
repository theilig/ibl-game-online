package controllers

import dao.{GameDao, TeamDao}
import javax.inject.Inject
import models.{Geo, Park, Team}
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future}

class SetupController @Inject()(
                               gameDao: GameDao,
                               teamDao: TeamDao,
                               cc: ControllerComponents,
                               authenticatedAction: AuthenticatedAction
                               )
                               (implicit executionContext: ExecutionContext)
    extends AbstractController(cc) {
  def showGameList(): Action[AnyContent] = authenticatedAction.async { implicit request: UserRequest[AnyContent] =>
    gameDao.gameList.map(list => Ok(Json.toJson(Map("games" -> list))))
  }

  def newGame(): Action[AnyContent] = ???

  def teamData(): Action[AnyContent] = authenticatedAction.async { implicit request: UserRequest[AnyContent] =>
    val eventualTeams = teamDao.teamList
    val eventualParks = teamDao.parkList
    val eventualGeos = teamDao.geoList

    Future.sequence(Seq(eventualTeams, eventualParks, eventualGeos)).map {
      case Seq(teams: List[Team], parks: List[Park], geos: List[Geo]) => models.TeamData(teams, parks, geos)
    }.map(data => Ok(Json.toJson(data)))
  }
}
