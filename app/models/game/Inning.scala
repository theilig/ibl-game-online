package models.game

import play.api.libs.json._

sealed trait InningHalf
case object Top extends InningHalf
case object Bottom extends InningHalf

case class Inning(half: InningHalf, number: Int)

object Inning {
  def apply(): Inning = {
    new Inning(Top, 1)
  }

  implicit val inningFormat: Writes[Inning] = (inning: Inning) => JsObject(
    Seq(
      "half" -> JsString(inning.half.toString),
      "number" -> JsNumber(inning.number)
    )
  )
}
