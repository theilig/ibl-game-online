package models.schema
// AUTO-GENERATED Slick data model for table Temperature
trait TemperatureTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Temperature
   *  @param temperatureId Database column temperature_id SqlType(INT), AutoInc, PrimaryKey
   *  @param geographicLocationId Database column geographic_location_id SqlType(INT)
   *  @param timeOfDay Database column time_of_day SqlType(ENUM), Length(5,false)
   *  @param gameMonth Database column game_month SqlType(ENUM), Length(9,false)
   *  @param temp Database column temp SqlType(ENUM), Length(4,false), Default(None)
   *  @param rank Database column rank SqlType(INT)
   *  @param frequency Database column frequency SqlType(INT) */
  case class TemperatureRow(temperatureId: Int, geographicLocationId: Int, timeOfDay: String, gameMonth: String, temp: Option[String] = None, rank: Int, frequency: Int)
  /** GetResult implicit for fetching TemperatureRow objects using plain SQL queries */
  implicit def GetResultTemperatureRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[String]]): GR[TemperatureRow] = GR{
    prs => import prs._
    TemperatureRow.tupled((<<[Int], <<[Int], <<[String], <<[String], <<?[String], <<[Int], <<[Int]))
  }
  /** Table description of table Temperature. Objects of this class serve as prototypes for rows in queries. */
  class Temperature(_tableTag: Tag) extends profile.api.Table[TemperatureRow](_tableTag, Some("IblGame"), "Temperature") {
    def * = (temperatureId, geographicLocationId, timeOfDay, gameMonth, temp, rank, frequency) <> (TemperatureRow.tupled, TemperatureRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(temperatureId), Rep.Some(geographicLocationId), Rep.Some(timeOfDay), Rep.Some(gameMonth), temp, Rep.Some(rank), Rep.Some(frequency))).shaped.<>({r=>import r._; _1.map(_=> TemperatureRow.tupled((_1.get, _2.get, _3.get, _4.get, _5, _6.get, _7.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column temperature_id SqlType(INT), AutoInc, PrimaryKey */
    val temperatureId: Rep[Int] = column[Int]("temperature_id", O.AutoInc, O.PrimaryKey)
    /** Database column geographic_location_id SqlType(INT) */
    val geographicLocationId: Rep[Int] = column[Int]("geographic_location_id")
    /** Database column time_of_day SqlType(ENUM), Length(5,false) */
    val timeOfDay: Rep[String] = column[String]("time_of_day", O.Length(5,varying=false))
    /** Database column game_month SqlType(ENUM), Length(9,false) */
    val gameMonth: Rep[String] = column[String]("game_month", O.Length(9,varying=false))
    /** Database column temp SqlType(ENUM), Length(4,false), Default(None) */
    val temp: Rep[Option[String]] = column[Option[String]]("temp", O.Length(4,varying=false), O.Default(None))
    /** Database column rank SqlType(INT) */
    val rank: Rep[Int] = column[Int]("rank")
    /** Database column frequency SqlType(INT) */
    val frequency: Rep[Int] = column[Int]("frequency")

    /** Index over (geographicLocationId,timeOfDay,gameMonth,rank) (database name loc_tod_month_ordering) */
    val index1 = index("loc_tod_month_ordering", (geographicLocationId, timeOfDay, gameMonth, rank))
    /** Uniqueness Index over (geographicLocationId,timeOfDay,gameMonth,temp) (database name loc_tod_month_temp) */
    val index2 = index("loc_tod_month_temp", (geographicLocationId, timeOfDay, gameMonth, temp), unique=true)
  }
  /** Collection-like TableQuery object for table Temperature */
  lazy val Temperature = new TableQuery(tag => new Temperature(tag))
}
