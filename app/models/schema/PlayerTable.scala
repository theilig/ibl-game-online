package models.schema
// AUTO-GENERATED Slick data model for table Player
trait PlayerTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Player
   *  @param playerId Database column player_id SqlType(INT), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(100,true)
   *  @param pitcherId Database column pitcher_id SqlType(INT), Default(None)
   *  @param bats Database column bats SqlType(ENUM), Length(5,false)
   *  @param durability Database column durability SqlType(INT)
   *  @param injuryDays Database column injury_days SqlType(INT)
   *  @param running Database column running SqlType(INT)
   *  @param stealing Database column stealing SqlType(INT)
   *  @param slashLeft Database column slash_left SqlType(VARCHAR), Length(40,true)
   *  @param slashRight Database column slash_right SqlType(VARCHAR), Length(40,true)
   *  @param templateIdLeft Database column template_id_left SqlType(INT)
   *  @param templateIdRight Database column template_id_right SqlType(INT)
   *  @param resultsLeft Database column results_left SqlType(VARCHAR), Length(200,true)
   *  @param resultsRight Database column results_right SqlType(VARCHAR), Length(200,true)
   *  @param ifrLocation Database column ifr_location SqlType(VARCHAR), Length(30,true)
   *  @param ofrLocation Database column ofr_location SqlType(VARCHAR), Length(30,true)
   *  @param dfLocation Database column df_location SqlType(VARCHAR), Length(30,true)
   *  @param power Database column power SqlType(VARCHAR), Length(30,true)
   *  @param jump Database column jump SqlType(INT)
   *  @param clutch Database column clutch SqlType(ENUM), Length(6,false), Default(Normal)
   *  @param usage Database column usage SqlType(VARCHAR), Length(100,true) */
  case class PlayerRow(playerId: Int, name: String, pitcherId: Option[Int] = None, bats: String, durability: Int, injuryDays: Int, running: Int, stealing: Int, slashLeft: String, slashRight: String, templateIdLeft: Int, templateIdRight: Int, resultsLeft: String, resultsRight: String, ifrLocation: String, ofrLocation: String, dfLocation: String, power: String, jump: Int, clutch: String = "Normal", usage: String)
  /** GetResult implicit for fetching PlayerRow objects using plain SQL queries */
  implicit def GetResultPlayerRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[Int]]): GR[PlayerRow] = GR{
    prs => import prs._
    PlayerRow.tupled((<<[Int], <<[String], <<?[Int], <<[String], <<[Int], <<[Int], <<[Int], <<[Int], <<[String], <<[String], <<[Int], <<[Int], <<[String], <<[String], <<[String], <<[String], <<[String], <<[String], <<[Int], <<[String], <<[String]))
  }
  /** Table description of table Player. Objects of this class serve as prototypes for rows in queries. */
  class Player(_tableTag: Tag) extends profile.api.Table[PlayerRow](_tableTag, Some("IblGame"), "Player") {
    def * = (playerId, name, pitcherId, bats, durability, injuryDays, running, stealing, slashLeft, slashRight, templateIdLeft, templateIdRight, resultsLeft, resultsRight, ifrLocation, ofrLocation, dfLocation, power, jump, clutch, usage) <> (PlayerRow.tupled, PlayerRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(playerId), Rep.Some(name), pitcherId, Rep.Some(bats), Rep.Some(durability), Rep.Some(injuryDays), Rep.Some(running), Rep.Some(stealing), Rep.Some(slashLeft), Rep.Some(slashRight), Rep.Some(templateIdLeft), Rep.Some(templateIdRight), Rep.Some(resultsLeft), Rep.Some(resultsRight), Rep.Some(ifrLocation), Rep.Some(ofrLocation), Rep.Some(dfLocation), Rep.Some(power), Rep.Some(jump), Rep.Some(clutch), Rep.Some(usage))).shaped.<>({r=>import r._; _1.map(_=> PlayerRow.tupled((_1.get, _2.get, _3, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10.get, _11.get, _12.get, _13.get, _14.get, _15.get, _16.get, _17.get, _18.get, _19.get, _20.get, _21.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column player_id SqlType(INT), AutoInc, PrimaryKey */
    val playerId: Rep[Int] = column[Int]("player_id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(VARCHAR), Length(100,true) */
    val name: Rep[String] = column[String]("name", O.Length(100,varying=true))
    /** Database column pitcher_id SqlType(INT), Default(None) */
    val pitcherId: Rep[Option[Int]] = column[Option[Int]]("pitcher_id", O.Default(None))
    /** Database column bats SqlType(ENUM), Length(5,false) */
    val bats: Rep[String] = column[String]("bats", O.Length(5,varying=false))
    /** Database column durability SqlType(INT) */
    val durability: Rep[Int] = column[Int]("durability")
    /** Database column injury_days SqlType(INT) */
    val injuryDays: Rep[Int] = column[Int]("injury_days")
    /** Database column running SqlType(INT) */
    val running: Rep[Int] = column[Int]("running")
    /** Database column stealing SqlType(INT) */
    val stealing: Rep[Int] = column[Int]("stealing")
    /** Database column slash_left SqlType(VARCHAR), Length(40,true) */
    val slashLeft: Rep[String] = column[String]("slash_left", O.Length(40,varying=true))
    /** Database column slash_right SqlType(VARCHAR), Length(40,true) */
    val slashRight: Rep[String] = column[String]("slash_right", O.Length(40,varying=true))
    /** Database column template_id_left SqlType(INT) */
    val templateIdLeft: Rep[Int] = column[Int]("template_id_left")
    /** Database column template_id_right SqlType(INT) */
    val templateIdRight: Rep[Int] = column[Int]("template_id_right")
    /** Database column results_left SqlType(VARCHAR), Length(200,true) */
    val resultsLeft: Rep[String] = column[String]("results_left", O.Length(200,varying=true))
    /** Database column results_right SqlType(VARCHAR), Length(200,true) */
    val resultsRight: Rep[String] = column[String]("results_right", O.Length(200,varying=true))
    /** Database column ifr_location SqlType(VARCHAR), Length(30,true) */
    val ifrLocation: Rep[String] = column[String]("ifr_location", O.Length(30,varying=true))
    /** Database column ofr_location SqlType(VARCHAR), Length(30,true) */
    val ofrLocation: Rep[String] = column[String]("ofr_location", O.Length(30,varying=true))
    /** Database column df_location SqlType(VARCHAR), Length(30,true) */
    val dfLocation: Rep[String] = column[String]("df_location", O.Length(30,varying=true))
    /** Database column power SqlType(VARCHAR), Length(30,true) */
    val power: Rep[String] = column[String]("power", O.Length(30,varying=true))
    /** Database column jump SqlType(INT) */
    val jump: Rep[Int] = column[Int]("jump")
    /** Database column clutch SqlType(ENUM), Length(6,false), Default(Normal) */
    val clutch: Rep[String] = column[String]("clutch", O.Length(6,varying=false), O.Default("Normal"))
    /** Database column usage SqlType(VARCHAR), Length(100,true) */
    val usage: Rep[String] = column[String]("usage", O.Length(100,varying=true))

    /** Uniqueness Index over (name) (database name player_name) */
    val index1 = index("player_name", name, unique=true)
  }
  /** Collection-like TableQuery object for table Player */
  lazy val Player = new TableQuery(tag => new Player(tag))
}
