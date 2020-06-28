package models.game

import models.schema.Tables.{GeographicLocationRow, ParkRow, TeamRow}
import play.api.libs.json.{JsObject, JsString, Json, Writes}

sealed trait GameStage
case object DetermineTemperature extends GameStage
case object ChoosingPitchers extends GameStage

case class TeamPair(road: Team, home: Team)

case class State(teams: TeamPair, park: Park, inning: Inning, stage: GameStage)

object State {
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
      "stage" -> JsString(state.stage.toString)
    )
  )

  def apply(roadRow: TeamRow, homeRow: TeamRow, parkRow: ParkRow, geoRow: GeographicLocationRow, month: Month, timeOfDay: TimeOfDay): State = {
    val state = new State(TeamPair(
      Team(roadRow),
      Team(homeRow)),
      Park(parkRow, geoRow, month, timeOfDay),
      Inning(),
      DetermineTemperature
    )
    if (geoRow.name == "Dome") {
      // for domes we don't need weather effects rolls
      val newPark = state.park.copy(temperature = Cool, cloudCover = Roof, precipitation = None, wind = NoWind)
      state.copy(park = newPark, stage = ChoosingPitchers)
    } else {
      state
    }
  }
}
