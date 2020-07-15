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

  implicit val inningReads: Reads[Inning] = (json: JsValue) => {
    val half = (json \ "half").as[String] match {
      case "Top" => Top
      case "Bottom" => Bottom
    }
    val number = (json \ "number").as[Int]
    JsSuccess(Inning(half, number))
  }

  implicit val inningFormat: Writes[Inning] = (inning: Inning) => JsObject(
    Seq(
      "half" -> JsString(inning.half.toString),
      "number" -> JsNumber(inning.number)
    )
  )
}
