package models

import play.api.libs.json.{Json, OFormat, OWrites}

case class GameListItem(gameId: Int, state: String, roadManagerName: String, homeManagerName: String)

object GameListItem {
  implicit val gameListItemFormat: OFormat[GameListItem] = Json.format[GameListItem]
  implicit val gameListItemWrites: OWrites[GameListItem] = Json.writes[GameListItem]
}
