package models.schema
// AUTO-GENERATED Slick data model for table Defense
trait DefenseTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Defense
   *  @param defenseId Database column defense_id SqlType(INT), AutoInc, PrimaryKey
   *  @param playerId Database column player_id SqlType(INT)
   *  @param position Database column position SqlType(ENUM), Length(2,false)
   *  @param primary Database column primary SqlType(BIT)
   *  @param error Database column error SqlType(INT)
   *  @param rating Database column rating SqlType(VARCHAR), Length(2,true)
   *  @param pivot Database column pivot SqlType(CHAR), Default(None)
   *  @param throws Database column throws SqlType(INT), Default(None)
   *  @param lostPitch Database column lost_pitch SqlType(ENUM), Length(10,false), Default(None)
   *  @param stopJump Database column stop_jump SqlType(INT), Default(None) */
  case class DefenseRow(defenseId: Int, playerId: Int, position: String, primary: Boolean, error: Int, rating: String, pivot: Option[Char] = None, throws: Option[Int] = None, lostPitch: Option[String] = None, stopJump: Option[Int] = None)
  /** GetResult implicit for fetching DefenseRow objects using plain SQL queries */
  implicit def GetResultDefenseRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Boolean], e3: GR[Option[Char]], e4: GR[Option[Int]], e5: GR[Option[String]]): GR[DefenseRow] = GR{
    prs => import prs._
    DefenseRow.tupled((<<[Int], <<[Int], <<[String], <<[Boolean], <<[Int], <<[String], <<?[Char], <<?[Int], <<?[String], <<?[Int]))
  }
  /** Table description of table Defense. Objects of this class serve as prototypes for rows in queries. */
  class Defense(_tableTag: Tag) extends profile.api.Table[DefenseRow](_tableTag, Some("IblGame"), "Defense") {
    def * = (defenseId, playerId, position, primary, error, rating, pivot, throws, lostPitch, stopJump) <> (DefenseRow.tupled, DefenseRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(defenseId), Rep.Some(playerId), Rep.Some(position), Rep.Some(primary), Rep.Some(error), Rep.Some(rating), pivot, throws, lostPitch, stopJump)).shaped.<>({r=>import r._; _1.map(_=> DefenseRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7, _8, _9, _10)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column defense_id SqlType(INT), AutoInc, PrimaryKey */
    val defenseId: Rep[Int] = column[Int]("defense_id", O.AutoInc, O.PrimaryKey)
    /** Database column player_id SqlType(INT) */
    val playerId: Rep[Int] = column[Int]("player_id")
    /** Database column position SqlType(ENUM), Length(2,false) */
    val position: Rep[String] = column[String]("position", O.Length(2,varying=false))
    /** Database column primary SqlType(BIT) */
    val primary: Rep[Boolean] = column[Boolean]("primary")
    /** Database column error SqlType(INT) */
    val error: Rep[Int] = column[Int]("error")
    /** Database column rating SqlType(VARCHAR), Length(2,true) */
    val rating: Rep[String] = column[String]("rating", O.Length(2,varying=true))
    /** Database column pivot SqlType(CHAR), Default(None) */
    val pivot: Rep[Option[Char]] = column[Option[Char]]("pivot", O.Default(None))
    /** Database column throws SqlType(INT), Default(None) */
    val throws: Rep[Option[Int]] = column[Option[Int]]("throws", O.Default(None))
    /** Database column lost_pitch SqlType(ENUM), Length(10,false), Default(None) */
    val lostPitch: Rep[Option[String]] = column[Option[String]]("lost_pitch", O.Length(10,varying=false), O.Default(None))
    /** Database column stop_jump SqlType(INT), Default(None) */
    val stopJump: Rep[Option[Int]] = column[Option[Int]]("stop_jump", O.Default(None))

    /** Uniqueness Index over (playerId,position) (database name player_position) */
    val index1 = index("player_position", (playerId, position), unique=true)
  }
  /** Collection-like TableQuery object for table Defense */
  lazy val Defense = new TableQuery(tag => new Defense(tag))
}
