package models

import play.api.libs.json.{Json, OFormat}
import schema.Tables.ParkRow

case class Park(parkId: Int, name: String)

object Park {
  implicit val parkFormat: OFormat[Park] = Json.format[Park]
  def apply(parkRow: ParkRow): Park = new Park(
    parkRow.parkId,
    parkRow.name
  )
}
