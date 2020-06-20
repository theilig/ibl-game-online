package models.schema
// AUTO-GENERATED Slick data model for table Game
trait GameTable {

  self:Tables  =>

  import profile.api._
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Game
   *  @param gameId Database column game_id SqlType(INT), AutoInc, PrimaryKey
   *  @param completed Database column completed SqlType(BIT), Default(false)
   *  @param state Database column state SqlType(TEXT), Default(None)
   *  @param roadManager Database column road_manager SqlType(INT)
   *  @param homeManager Database column home_manager SqlType(INT) */
  case class GameRow(gameId: Int, completed: Boolean = false, state: Option[String] = None, roadManager: Int, homeManager: Int)
  /** GetResult implicit for fetching GameRow objects using plain SQL queries */
  implicit def GetResultGameRow(implicit e0: GR[Int], e1: GR[Boolean], e2: GR[Option[String]]): GR[GameRow] = GR{
    prs => import prs._
    GameRow.tupled((<<[Int], <<[Boolean], <<?[String], <<[Int], <<[Int]))
  }
  /** Table description of table Game. Objects of this class serve as prototypes for rows in queries. */
  class Game(_tableTag: Tag) extends profile.api.Table[GameRow](_tableTag, Some("IblGame"), "Game") {
    def * = (gameId, completed, state, roadManager, homeManager) <> (GameRow.tupled, GameRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(gameId), Rep.Some(completed), state, Rep.Some(roadManager), Rep.Some(homeManager))).shaped.<>({r=>import r._; _1.map(_=> GameRow.tupled((_1.get, _2.get, _3, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column game_id SqlType(INT), AutoInc, PrimaryKey */
    val gameId: Rep[Int] = column[Int]("game_id", O.AutoInc, O.PrimaryKey)
    /** Database column completed SqlType(BIT), Default(false) */
    val completed: Rep[Boolean] = column[Boolean]("completed", O.Default(false))
    /** Database column state SqlType(TEXT), Default(None) */
    val state: Rep[Option[String]] = column[Option[String]]("state", O.Default(None))
    /** Database column road_manager SqlType(INT) */
    val roadManager: Rep[Int] = column[Int]("road_manager")
    /** Database column home_manager SqlType(INT) */
    val homeManager: Rep[Int] = column[Int]("home_manager")

    /** Index over (completed) (database name completed) */
    val index1 = index("completed", completed)
    /** Index over (homeManager) (database name home_manager) */
    val index2 = index("home_manager", homeManager)
    /** Index over (roadManager) (database name road_manager) */
    val index3 = index("road_manager", roadManager)
  }
  /** Collection-like TableQuery object for table Game */
  lazy val Game = new TableQuery(tag => new Game(tag))
}
