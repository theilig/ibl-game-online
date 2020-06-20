package models.schema
// AUTO-GENERATED Slick data model for table Distance
trait DistanceTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Distance
   *  @param distanceId Database column distance_id SqlType(INT), AutoInc, PrimaryKey
   *  @param parkId Database column park_id SqlType(INT)
   *  @param location Database column location SqlType(ENUM), Length(4,false), Default(None)
   *  @param windType Database column wind_type SqlType(ENUM), Length(8,false)
   *  @param fenceDistance Database column fence_distance SqlType(INT) */
  case class DistanceRow(distanceId: Int, parkId: Int, location: Option[String] = None, windType: String, fenceDistance: Int)
  /** GetResult implicit for fetching DistanceRow objects using plain SQL queries */
  implicit def GetResultDistanceRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[String]): GR[DistanceRow] = GR{
    prs => import prs._
    DistanceRow.tupled((<<[Int], <<[Int], <<?[String], <<[String], <<[Int]))
  }
  /** Table description of table Distance. Objects of this class serve as prototypes for rows in queries. */
  class Distance(_tableTag: Tag) extends profile.api.Table[DistanceRow](_tableTag, Some("IblGame"), "Distance") {
    def * = (distanceId, parkId, location, windType, fenceDistance) <> (DistanceRow.tupled, DistanceRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(distanceId), Rep.Some(parkId), location, Rep.Some(windType), Rep.Some(fenceDistance))).shaped.<>({r=>import r._; _1.map(_=> DistanceRow.tupled((_1.get, _2.get, _3, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column distance_id SqlType(INT), AutoInc, PrimaryKey */
    val distanceId: Rep[Int] = column[Int]("distance_id", O.AutoInc, O.PrimaryKey)
    /** Database column park_id SqlType(INT) */
    val parkId: Rep[Int] = column[Int]("park_id")
    /** Database column location SqlType(ENUM), Length(4,false), Default(None) */
    val location: Rep[Option[String]] = column[Option[String]]("location", O.Length(4,varying=false), O.Default(None))
    /** Database column wind_type SqlType(ENUM), Length(8,false) */
    val windType: Rep[String] = column[String]("wind_type", O.Length(8,varying=false))
    /** Database column fence_distance SqlType(INT) */
    val fenceDistance: Rep[Int] = column[Int]("fence_distance")

    /** Uniqueness Index over (parkId,windType,location) (database name park_wind_location) */
    val index1 = index("park_wind_location", (parkId, windType, location), unique=true)
  }
  /** Collection-like TableQuery object for table Distance */
  lazy val Distance = new TableQuery(tag => new Distance(tag))
}
