package models.schema
// AUTO-GENERATED Slick data model for table CloudCover
trait CloudCoverTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table CloudCover
   *  @param cloudCoverId Database column cloud_cover_id SqlType(INT), AutoInc, PrimaryKey
   *  @param geographicLocationId Database column geographic_location_id SqlType(INT)
   *  @param gameMonth Database column game_month SqlType(ENUM), Length(9,false)
   *  @param cover Database column cover SqlType(ENUM), Length(13,false), Default(None)
   *  @param rank Database column rank SqlType(INT)
   *  @param frequency Database column frequency SqlType(INT) */
  case class CloudCoverRow(cloudCoverId: Int, geographicLocationId: Int, gameMonth: String, cover: Option[String] = None, rank: Int, frequency: Int)
  /** GetResult implicit for fetching CloudCoverRow objects using plain SQL queries */
  implicit def GetResultCloudCoverRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[String]]): GR[CloudCoverRow] = GR{
    prs => import prs._
    CloudCoverRow.tupled((<<[Int], <<[Int], <<[String], <<?[String], <<[Int], <<[Int]))
  }
  /** Table description of table CloudCover. Objects of this class serve as prototypes for rows in queries. */
  class CloudCover(_tableTag: Tag) extends profile.api.Table[CloudCoverRow](_tableTag, Some("IblGame"), "CloudCover") {
    def * = (cloudCoverId, geographicLocationId, gameMonth, cover, rank, frequency) <> (CloudCoverRow.tupled, CloudCoverRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(cloudCoverId), Rep.Some(geographicLocationId), Rep.Some(gameMonth), cover, Rep.Some(rank), Rep.Some(frequency))).shaped.<>({r=>import r._; _1.map(_=> CloudCoverRow.tupled((_1.get, _2.get, _3.get, _4, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column cloud_cover_id SqlType(INT), AutoInc, PrimaryKey */
    val cloudCoverId: Rep[Int] = column[Int]("cloud_cover_id", O.AutoInc, O.PrimaryKey)
    /** Database column geographic_location_id SqlType(INT) */
    val geographicLocationId: Rep[Int] = column[Int]("geographic_location_id")
    /** Database column game_month SqlType(ENUM), Length(9,false) */
    val gameMonth: Rep[String] = column[String]("game_month", O.Length(9,varying=false))
    /** Database column cover SqlType(ENUM), Length(13,false), Default(None) */
    val cover: Rep[Option[String]] = column[Option[String]]("cover", O.Length(13,varying=false), O.Default(None))
    /** Database column rank SqlType(INT) */
    val rank: Rep[Int] = column[Int]("rank")
    /** Database column frequency SqlType(INT) */
    val frequency: Rep[Int] = column[Int]("frequency")

    /** Uniqueness Index over (geographicLocationId,gameMonth,cover) (database name loc_month_cover) */
    val index1 = index("loc_month_cover", (geographicLocationId, gameMonth, cover), unique=true)
    /** Index over (geographicLocationId,gameMonth,rank) (database name loc_month_ordering) */
    val index2 = index("loc_month_ordering", (geographicLocationId, gameMonth, rank))
  }
  /** Collection-like TableQuery object for table CloudCover */
  lazy val CloudCover = new TableQuery(tag => new CloudCover(tag))
}
