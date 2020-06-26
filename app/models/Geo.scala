package models

import play.api.libs.json.{Json, OFormat}
import schema.Tables.GeographicLocationRow

case class Geo(geoId: Int, name: String)

object Geo {
  implicit val geoFormat: OFormat[Geo] = Json.format[Geo]
  def apply(geoRow: GeographicLocationRow): Geo = new Geo(
    geoRow.geographicLocationId,
    geoRow.name
  )
}
