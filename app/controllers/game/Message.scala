package controllers.game

import play.api.libs.json.{Json, OFormat}

import scala.util.Random

sealed trait Message

case class Roll(numberOfDice: Int, result: List[Int]) extends Message {
  val resultAsInt: Int = result.foldLeft(0)(_ * 10 + _)
}

object Roll {
  implicit val rollFormat: OFormat[Roll] = Json.format[Roll]
  def apply(numberOfDice: Int, resultAsInt: Int): Roll = {
    def intToResult(dice: Int, number: Int): List[Int] = dice match {
      case d if d > 1 => number % 10 :: intToResult(d - 1, number / 10)
      case _ => number :: Nil
    }
    new Roll(numberOfDice, intToResult(numberOfDice, resultAsInt).reverse)
  }
  def apply(numberOfDice: Int): Roll = {
    new Roll(numberOfDice, (0 until numberOfDice).map(_ => Random.between(0, 10)).toList)
  }
}

case class GetRoll(numberOfDice: Int) extends Message
case object GetTemperature extends Message
