package controllers

import dao.{GameDao, TeamDao}
import javax.inject.Inject
import models.game.{Month, State, TimeOfDay}
import models.{GameCreation, Geo, Park, Team}
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

  def newGame(): Action[AnyContent] = authenticatedAction.async { implicit request: UserRequest[AnyContent] =>
    try {
      val gameCreation = request.body.asJson.get.as[GameCreation]
      val eventualRoadTeam = teamDao.findTeamById(gameCreation.visitingTeamId)
      val eventualHomeTeam = teamDao.findTeamById(gameCreation.homeTeamId)
      val eventualPark = teamDao.findParkById(gameCreation.parkId)
      val eventualGeoLocation = teamDao.findGeoById(gameCreation.geoId)
      val currentUser = request.user
      (for {
        roadTeam <- eventualRoadTeam
        homeTeam <- eventualHomeTeam
        park <- eventualPark
        geo <- eventualGeoLocation
      } yield State(
        roadTeam.get,
        homeTeam.get,
        park.get,
        geo.get,
        Month.fromString(gameCreation.month),
        TimeOfDay.fromString(gameCreation.tod)
      )).flatMap(state => {
        gameDao.insertGame(currentUser, state)
      }).map(row => Ok(Json.toJson(models.GameListItem(row, currentUser.firstName, currentUser.firstName))))
    } catch {
      case e: Throwable =>
        Future.successful(BadRequest("Invalid Json"))
    }
  }

  def teamData(): Action[AnyContent] = authenticatedAction.async { implicit request: UserRequest[AnyContent] =>
    val eventualTeams = teamDao.teamList
    val eventualParks = teamDao.parkList
    val eventualGeos = teamDao.geoList

    Future.sequence(Seq(eventualTeams, eventualParks, eventualGeos)).map {
      case Seq(teams: List[Team], parks: List[Park], geos: List[Geo]) => models.TeamData(teams, parks, geos)
    }.map(data => Ok(Json.toJson(data)))
  }
}
