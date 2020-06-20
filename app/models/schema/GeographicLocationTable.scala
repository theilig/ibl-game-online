package models.schema
// AUTO-GENERATED Slick data model for table GeographicLocation
trait GeographicLocationTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table GeographicLocation
   *  @param geographicLocationId Database column geographic_location_id SqlType(INT), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(100,true) */
  case class GeographicLocationRow(geographicLocationId: Int, name: String)
  /** GetResult implicit for fetching GeographicLocationRow objects using plain SQL queries */
  implicit def GetResultGeographicLocationRow(implicit e0: GR[Int], e1: GR[String]): GR[GeographicLocationRow] = GR{
    prs => import prs._
    GeographicLocationRow.tupled((<<[Int], <<[String]))
  }
  /** Table description of table GeographicLocation. Objects of this class serve as prototypes for rows in queries. */
  class GeographicLocation(_tableTag: Tag) extends profile.api.Table[GeographicLocationRow](_tableTag, Some("IblGame"), "GeographicLocation") {
    def * = (geographicLocationId, name) <> (GeographicLocationRow.tupled, GeographicLocationRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(geographicLocationId), Rep.Some(name))).shaped.<>({r=>import r._; _1.map(_=> GeographicLocationRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column geographic_location_id SqlType(INT), AutoInc, PrimaryKey */
    val geographicLocationId: Rep[Int] = column[Int]("geographic_location_id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(VARCHAR), Length(100,true) */
    val name: Rep[String] = column[String]("name", O.Length(100,varying=true))
  }
  /** Collection-like TableQuery object for table GeographicLocation */
  lazy val GeographicLocation = new TableQuery(tag => new GeographicLocation(tag))
}
