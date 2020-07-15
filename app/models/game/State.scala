package models.game

import models.schema.Tables.{GameRow, GeographicLocationRow, ParkRow, TeamRow}
import play.api.libs.functional.syntax._
import play.api.libs.json._

import scala.concurrent.ExecutionContext

case class TeamPair(road: Team, home: Team)

object TeamPair {
  implicit val teamPairReads: Reads[TeamPair] = (
    (JsPath \ "road").read[Team] and
      (JsPath \ "home").read[Team]
    )(TeamPair.apply _)
}
case class State(teams: TeamPair, park: Park, inning: Inning, stage: String)

object State {
  implicit val stateReads: Reads[State] = (json: JsValue) => {
    JsSuccess(State(
      (json \ "teams").as[TeamPair],
      (json \ "park").as[Park],
      (json \ "inning").as[Inning],
      (json \ "stage").as[String]
    ))
  }

  implicit val stateFormat: Writes[State] = (state: State) => JsObject(
    Seq(
      "teams" -> JsObject(
        Seq(
          "road" -> Json.toJson(state.teams.road),
          "home" -> Json.toJson(state.teams.home)
        ),
      ),
      "park" -> Json.toJson(state.park),
      "inning" -> Json.toJson(state.inning),
      "stage" -> JsString(state.stage)
    )
  )

  def apply(
             managerId: Int,
             roadRow: TeamRow,
             homeRow: TeamRow,
             parkRow: ParkRow,
             geoRow: GeographicLocationRow,
             month: Month,
             timeOfDay: TimeOfDay
           )(implicit executionContext: ExecutionContext): State = {
    val state = new State(
      TeamPair(
        Team(roadRow, managerId),
        Team(homeRow, managerId)
      ),
      Park(parkRow, geoRow, month, timeOfDay),
      Inning(),
      "DetermineTemperature"
    )
    if (geoRow.name == "Dome") {
      // for domes we don't need weather effects rolls
      val newPark = state.park.copy(temperature = Cool(None), cloudCover = Roof, precipitation = NoPrecipitation, wind = NoWind)
      state.copy(park = newPark, stage = "ChoosingPitchers")
    } else {
      state
    }
  }

  def apply(gameRow: GameRow): State = {
    Json.parse(gameRow.state.get).as[State]
  }
}
