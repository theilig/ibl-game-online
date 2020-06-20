package models.schema
// AUTO-GENERATED Slick data model for table Bunting
trait BuntingTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Bunting
   *  @param buntingId Database column bunting_id SqlType(INT), AutoInc, PrimaryKey
   *  @param playerId Database column player_id SqlType(INT)
   *  @param inPlay Database column in_play SqlType(INT)
   *  @param placement Database column placement SqlType(ENUM), Length(10,false)
   *  @param speed Database column speed SqlType(INT) */
  case class BuntingRow(buntingId: Int, playerId: Int, inPlay: Int, placement: String, speed: Int)
  /** GetResult implicit for fetching BuntingRow objects using plain SQL queries */
  implicit def GetResultBuntingRow(implicit e0: GR[Int], e1: GR[String]): GR[BuntingRow] = GR{
    prs => import prs._
    BuntingRow.tupled((<<[Int], <<[Int], <<[Int], <<[String], <<[Int]))
  }
  /** Table description of table Bunting. Objects of this class serve as prototypes for rows in queries. */
  class Bunting(_tableTag: Tag) extends profile.api.Table[BuntingRow](_tableTag, Some("IblGame"), "Bunting") {
    def * = (buntingId, playerId, inPlay, placement, speed) <> (BuntingRow.tupled, BuntingRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(buntingId), Rep.Some(playerId), Rep.Some(inPlay), Rep.Some(placement), Rep.Some(speed))).shaped.<>({r=>import r._; _1.map(_=> BuntingRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column bunting_id SqlType(INT), AutoInc, PrimaryKey */
    val buntingId: Rep[Int] = column[Int]("bunting_id", O.AutoInc, O.PrimaryKey)
    /** Database column player_id SqlType(INT) */
    val playerId: Rep[Int] = column[Int]("player_id")
    /** Database column in_play SqlType(INT) */
    val inPlay: Rep[Int] = column[Int]("in_play")
    /** Database column placement SqlType(ENUM), Length(10,false) */
    val placement: Rep[String] = column[String]("placement", O.Length(10,varying=false))
    /** Database column speed SqlType(INT) */
    val speed: Rep[Int] = column[Int]("speed")

    /** Uniqueness Index over (playerId) (database name player_bunts) */
    val index1 = index("player_bunts", playerId, unique=true)
  }
  /** Collection-like TableQuery object for table Bunting */
  lazy val Bunting = new TableQuery(tag => new Bunting(tag))
}
