package models

import play.api.libs.json.{Json, OFormat}

case class TeamData(teams: List[Team], parks: List[Park], geos: List[Geo])

object TeamData {
  implicit val teamDataFormat: OFormat[TeamData] = Json.format[TeamData]
}
