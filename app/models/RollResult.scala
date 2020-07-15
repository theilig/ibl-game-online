package models

import play.api.libs.json.{Json, OFormat}

case class RollResult(result: String, lowRange: Int, highRange: Int)

object RollResult {
  implicit val rollFormat: OFormat[RollResult] = Json.format[RollResult]
}
