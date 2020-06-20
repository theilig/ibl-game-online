package models.schema
// AUTO-GENERATED Slick data model for table Wind
trait WindTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Wind
   *  @param windId Database column wind_id SqlType(INT), AutoInc, PrimaryKey
   *  @param parkId Database column park_id SqlType(INT)
   *  @param gameMonth Database column game_month SqlType(ENUM), Length(9,false)
   *  @param windType Database column wind_type SqlType(ENUM), Length(8,false)
   *  @param rank Database column rank SqlType(INT)
   *  @param frequency Database column frequency SqlType(INT) */
  case class WindRow(windId: Int, parkId: Int, gameMonth: String, windType: String, rank: Int, frequency: Int)
  /** GetResult implicit for fetching WindRow objects using plain SQL queries */
  implicit def GetResultWindRow(implicit e0: GR[Int], e1: GR[String]): GR[WindRow] = GR{
    prs => import prs._
    WindRow.tupled((<<[Int], <<[Int], <<[String], <<[String], <<[Int], <<[Int]))
  }
  /** Table description of table Wind. Objects of this class serve as prototypes for rows in queries. */
  class Wind(_tableTag: Tag) extends profile.api.Table[WindRow](_tableTag, Some("IblGame"), "Wind") {
    def * = (windId, parkId, gameMonth, windType, rank, frequency) <> (WindRow.tupled, WindRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(windId), Rep.Some(parkId), Rep.Some(gameMonth), Rep.Some(windType), Rep.Some(rank), Rep.Some(frequency))).shaped.<>({r=>import r._; _1.map(_=> WindRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column wind_id SqlType(INT), AutoInc, PrimaryKey */
    val windId: Rep[Int] = column[Int]("wind_id", O.AutoInc, O.PrimaryKey)
    /** Database column park_id SqlType(INT) */
    val parkId: Rep[Int] = column[Int]("park_id")
    /** Database column game_month SqlType(ENUM), Length(9,false) */
    val gameMonth: Rep[String] = column[String]("game_month", O.Length(9,varying=false))
    /** Database column wind_type SqlType(ENUM), Length(8,false) */
    val windType: Rep[String] = column[String]("wind_type", O.Length(8,varying=false))
    /** Database column rank SqlType(INT) */
    val rank: Rep[Int] = column[Int]("rank")
    /** Database column frequency SqlType(INT) */
    val frequency: Rep[Int] = column[Int]("frequency")

    /** Index over (parkId,gameMonth,rank) (database name ordering) */
    val index1 = index("ordering", (parkId, gameMonth, rank))
    /** Uniqueness Index over (parkId,gameMonth,windType) (database name park_month_wind) */
    val index2 = index("park_month_wind", (parkId, gameMonth, windType), unique=true)
  }
  /** Collection-like TableQuery object for table Wind */
  lazy val Wind = new TableQuery(tag => new Wind(tag))
}
