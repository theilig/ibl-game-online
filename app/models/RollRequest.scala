package models

import play.api.libs.json.{Json, OFormat}

case class RollRequest(gameId: Int, numberOfDice: Int)

object RollRequest {
  implicit val rollFormat: OFormat[RollRequest] = Json.format[RollRequest]

}
