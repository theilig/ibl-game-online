package models.game
import models.schema.Tables.TeamRow
import play.api.libs.json.{Json, OFormat}
case class Team(city: String, abbrev: String, mascot: String, icon: String, score: Int)

object Team {
  implicit val teamFormat: OFormat[Team] = Json.format[Team]

  def apply(row: TeamRow): Team = {
    new Team(
      row.city,
      row.abbrev,
      row.nickname,
      s"https://a1.espncdn.com/combiner/i?img=/i/teamlogos/mlb/500/scoreboard/${row.abbrev.toLowerCase}.png&h=70&w=70",
      0)
  }
}
