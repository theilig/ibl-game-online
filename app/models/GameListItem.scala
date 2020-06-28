package models

import models.schema.Tables.GameRow
import play.api.libs.json._

case class GameListItem(
                         gameId: Int,
                         state: JsValue
                       )

object GameListItem {
  implicit val gameListItemFormat: OFormat[GameListItem] = Json.format[GameListItem]
  implicit val gameListItemWrites: OWrites[GameListItem] = Json.writes[GameListItem]

  def apply(gameRow: GameRow, roadManager: String, homeManager: String): GameListItem = {
    def projection(gameState: JsValue): JsObject = {
      def teamInfo(team: JsValue, manager: String): JsObject = {
        JsObject(Seq(
          "name" -> (team \ "abbrev").get,
          "icon" -> (team \ "icon").get,
          "score" -> (team \ "score").get,
          "manager" -> JsString(manager)
        ))
      }
      JsObject(Seq(
        "road" -> teamInfo((gameState \ "teams" \ "road").get, roadManager),
        "home" -> teamInfo((gameState \ "teams" \ "home").get, homeManager),
        "inning" -> (gameState \ "inning").get
      ))
    }

    new GameListItem(gameRow.gameId, projection(Json.parse(gameRow.state.getOrElse(""))))
  }


}
