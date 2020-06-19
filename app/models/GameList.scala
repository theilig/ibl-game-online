package models

import play.api.libs.json.{Json, OFormat, OWrites}

case class GameList(games: List[GameListItem])

object GameList {
  implicit val gameListFormat: OFormat[GameList] = Json.format[GameList]
  implicit val gameListWrites: OWrites[GameList] = Json.writes[GameList]
}
