package models.game

import models.schema.Tables.{GeographicLocationRow, ParkRow}
import play.api.libs.json._

trait Rollable {
  val roll: Option[Int]
  def format: JsObject = {
    roll match {
      case Some(number) =>
        JsObject(Map("result" -> JsString(getClass.getSimpleName), "number" -> JsNumber(number)))
      case None =>
        JsObject(Map("result" -> JsString(getClass.getSimpleName)))
    }
  }
}

sealed trait Temperature extends Rollable
case class Cold(roll: Option[Int]) extends Temperature
case class Cool(roll: Option[Int]) extends Temperature
case class Hot(roll: Option[Int]) extends Temperature
case class Warm(roll: Option[Int]) extends Temperature

object Temperature  {
  def apply(s: String, roll: Option[Int]): Temperature = {
    List(Cold(roll), Cool(roll), Hot(roll), Warm(roll)).find(t => t.getClass.getSimpleName == s).getOrElse(Unknown)
  }

  implicit val temperatureFormat: Writes[Temperature] = (temperature: Temperature) => {
    temperature.format
  }

  implicit val temperatureRead: Reads[Temperature] = (json: JsValue) => {
    JsSuccess(
      apply((json \ "result").as[String], (json \ "roll").toOption.map(x => x.as[Int]))
    )
  }
}

sealed trait Wind
case object Cross extends Wind
case object NoWind extends Wind
case object Straight extends Wind

object Wind {
  def apply(s: String): Wind = {
    List(Cross, NoWind, Straight).find(t => t.toString == s).getOrElse(Unknown)
  }
}

sealed trait CloudCover
case object Clear extends CloudCover
case object Cloudy extends CloudCover
case object PartlyCloudy extends CloudCover
case object Roof extends CloudCover

object CloudCover {
  def apply(s: String): CloudCover = {
    List(Clear, Cloudy, PartlyCloudy, Roof).find(t => t.toString == s).getOrElse(Unknown)
  }
}

sealed trait Precipitation
case object NoPrecipitation extends Precipitation
case object Snow extends Precipitation
case object Showers extends Precipitation
case object Thunderstorms extends Precipitation

object Precipitation {
  def apply(s: String): Precipitation = {
    List(NoPrecipitation, Snow, Showers, Thunderstorms).find(t => t.toString == s).getOrElse(Unknown)
  }
}
sealed trait Month
case object April extends Month
case object May extends Month
case object June extends Month
case object July extends Month
case object August extends Month
case object September extends Month
case object October extends Month

case object Unknown extends Rollable with Temperature with Wind with CloudCover with Precipitation {
  override val roll: Option[Int] = None
}

object Month {
  def apply(s: String): Month =
    List(April, May, June, July, August, September, October).find(_.toString == s).get
}
sealed trait TimeOfDay
case object Day extends TimeOfDay
case object Night extends TimeOfDay

object TimeOfDay {
  def apply(s: String): TimeOfDay = List(Day, Night).find(_.toString == s).get
}
case class Park(
               name: String,
               parkId: Int,
               temperature: Temperature,
               cloudCover: CloudCover,
               precipitation: Precipitation,
               wind: Wind,
               geoLocationId: Int,
               month: Month,
               timeOfDay: TimeOfDay
               )

object Park {
  implicit val parkFormat: Writes[Park] = (park: Park) => JsObject(
    Seq(
      "name" -> JsString(park.name),
      "parkId" -> JsNumber(park.parkId),
      "temperature" -> Json.toJson(park.temperature),
      "cloudCover" -> JsString(park.cloudCover.toString),
      "precipitation" -> JsString(park.precipitation.toString),
      "wind" -> JsString(park.wind.toString),
      "geoId" -> JsNumber(park.geoLocationId),
      "month" -> JsString(park.month.toString),
      "timeOfDay" -> JsString(park.timeOfDay.toString)
    )
  )

  implicit val parkRead: Reads[Park] = (json: JsValue) => {
    JsSuccess(Park(
      (json \ "name").as[String],
      (json \ "parkId").as[Int],
      (json \ "temperature").as[Temperature],
      CloudCover((json \ "cloudCover").as[String]),
      Precipitation((json \ "precipitation").as[String]),
      Wind((json \ "wind").as[String]),
      (json \ "geoId").as[Int],
      Month((json \ "month").as[String]),
      TimeOfDay((json \ "timeOfDay").as[String])
    ))
  }

  def apply(parkRow: ParkRow, geoRow: GeographicLocationRow, month: Month, timeOfDay: TimeOfDay): Park = {
    new Park(
      parkRow.name,
      parkRow.parkId,
      Unknown,
      Unknown,
      Unknown,
      Unknown,
      geoRow.geographicLocationId,
      month,
      timeOfDay)
  }
}
