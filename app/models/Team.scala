package models

import play.api.libs.json.{Json, OFormat}
import models.schema.Tables.TeamRow

case class Team(teamId: Int, name: String, parkId: Int, geoId: Int)

object Team {
  implicit val teamFormat: OFormat[Team] = Json.format[Team]
  def apply(teamRow: TeamRow): Team = new Team(
    teamRow.teamId,
    List(teamRow.city, teamRow.nickname).mkString(" "),
    teamRow.homeParkId, teamRow.geographicLocationId
  )
}
