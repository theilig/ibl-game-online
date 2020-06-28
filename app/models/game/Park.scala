package models.game

import models.schema.Tables.{GeographicLocationRow, ParkRow}
import play.api.libs.json.{JsNumber, JsObject, JsString, Writes}

sealed trait Temperature
case object Cold extends Temperature
case object Cool extends Temperature
case object Hot extends Temperature
case object Warm extends Temperature

sealed trait Wind
case object Cross extends Wind
case object NoWind extends Wind
case object Straight extends Wind

sealed trait CloudCover
case object Clear extends CloudCover
case object Cloudy extends CloudCover
case object PartlyCloudy extends CloudCover
case object Roof extends CloudCover

sealed trait Precipitation
case object None extends Precipitation
case object Snow extends Precipitation
case object Showers extends Precipitation
case object Thunderstorms extends Precipitation

case object Unknown extends Temperature with Wind with CloudCover with Precipitation

sealed trait Month
case object April extends Month
case object May extends Month
case object June extends Month
case object July extends Month
case object August extends Month
case object September extends Month
case object October extends Month

object Month {
  def fromString(s: String): Month =
    List(April, May, June, July, August, September, October).find(_.toString == s).get
}
sealed trait TimeOfDay
case object Day extends TimeOfDay
case object Night extends TimeOfDay

object TimeOfDay {
  def fromString(s: String): TimeOfDay = List(Day, Night).find(_.toString == s).get
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
      "temperature" -> JsString(park.temperature.toString),
      "cloudCover" -> JsString(park.cloudCover.toString),
      "precipitation" -> JsString(park.precipitation.toString),
      "wind" -> JsString(park.wind.toString),
      "geoId" -> JsNumber(park.geoLocationId),
      "month" -> JsString(park.month.toString),
      "timeOfDay" -> JsString(park.timeOfDay.toString)
    )
  )

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
