package models.schema
// AUTO-GENERATED Slick data model for table Precipitation
trait PrecipitationTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Precipitation
   *  @param precipitationId Database column precipitation_id SqlType(INT), AutoInc, PrimaryKey
   *  @param geographicLocationId Database column geographic_location_id SqlType(INT)
   *  @param gameMonth Database column game_month SqlType(ENUM), Length(9,false)
   *  @param precip Database column precip SqlType(ENUM), Length(14,false)
   *  @param rank Database column rank SqlType(INT)
   *  @param frequency Database column frequency SqlType(INT) */
  case class PrecipitationRow(precipitationId: Int, geographicLocationId: Int, gameMonth: String, precip: String, rank: Int, frequency: Int)
  /** GetResult implicit for fetching PrecipitationRow objects using plain SQL queries */
  implicit def GetResultPrecipitationRow(implicit e0: GR[Int], e1: GR[String]): GR[PrecipitationRow] = GR{
    prs => import prs._
    PrecipitationRow.tupled((<<[Int], <<[Int], <<[String], <<[String], <<[Int], <<[Int]))
  }
  /** Table description of table Precipitation. Objects of this class serve as prototypes for rows in queries. */
  class Precipitation(_tableTag: Tag) extends profile.api.Table[PrecipitationRow](_tableTag, Some("IblGame"), "Precipitation") {
    def * = (precipitationId, geographicLocationId, gameMonth, precip, rank, frequency) <> (PrecipitationRow.tupled, PrecipitationRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(precipitationId), Rep.Some(geographicLocationId), Rep.Some(gameMonth), Rep.Some(precip), Rep.Some(rank), Rep.Some(frequency))).shaped.<>({r=>import r._; _1.map(_=> PrecipitationRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column precipitation_id SqlType(INT), AutoInc, PrimaryKey */
    val precipitationId: Rep[Int] = column[Int]("precipitation_id", O.AutoInc, O.PrimaryKey)
    /** Database column geographic_location_id SqlType(INT) */
    val geographicLocationId: Rep[Int] = column[Int]("geographic_location_id")
    /** Database column game_month SqlType(ENUM), Length(9,false) */
    val gameMonth: Rep[String] = column[String]("game_month", O.Length(9,varying=false))
    /** Database column precip SqlType(ENUM), Length(14,false) */
    val precip: Rep[String] = column[String]("precip", O.Length(14,varying=false))
    /** Database column rank SqlType(INT) */
    val rank: Rep[Int] = column[Int]("rank")
    /** Database column frequency SqlType(INT) */
    val frequency: Rep[Int] = column[Int]("frequency")

    /** Index over (geographicLocationId,gameMonth,rank) (database name loc_month_ordering) */
    val index1 = index("loc_month_ordering", (geographicLocationId, gameMonth, rank))
    /** Uniqueness Index over (geographicLocationId,gameMonth,precip) (database name loc_month_precip) */
    val index2 = index("loc_month_precip", (geographicLocationId, gameMonth, precip), unique=true)
  }
  /** Collection-like TableQuery object for table Precipitation */
  lazy val Precipitation = new TableQuery(tag => new Precipitation(tag))
}
