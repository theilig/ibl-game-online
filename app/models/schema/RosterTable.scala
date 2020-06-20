package models.schema
// AUTO-GENERATED Slick data model for table Roster
trait RosterTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Roster
   *  @param rosterId Database column roster_id SqlType(INT), AutoInc, PrimaryKey
   *  @param teamId Database column team_id SqlType(INT)
   *  @param playerId Database column player_id SqlType(INT) */
  case class RosterRow(rosterId: Int, teamId: Int, playerId: Int)
  /** GetResult implicit for fetching RosterRow objects using plain SQL queries */
  implicit def GetResultRosterRow(implicit e0: GR[Int]): GR[RosterRow] = GR{
    prs => import prs._
    RosterRow.tupled((<<[Int], <<[Int], <<[Int]))
  }
  /** Table description of table Roster. Objects of this class serve as prototypes for rows in queries. */
  class Roster(_tableTag: Tag) extends profile.api.Table[RosterRow](_tableTag, Some("IblGame"), "Roster") {
    def * = (rosterId, teamId, playerId) <> (RosterRow.tupled, RosterRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(rosterId), Rep.Some(teamId), Rep.Some(playerId))).shaped.<>({r=>import r._; _1.map(_=> RosterRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column roster_id SqlType(INT), AutoInc, PrimaryKey */
    val rosterId: Rep[Int] = column[Int]("roster_id", O.AutoInc, O.PrimaryKey)
    /** Database column team_id SqlType(INT) */
    val teamId: Rep[Int] = column[Int]("team_id")
    /** Database column player_id SqlType(INT) */
    val playerId: Rep[Int] = column[Int]("player_id")

    /** Uniqueness Index over (teamId,playerId) (database name team_player) */
    val index1 = index("team_player", (teamId, playerId), unique=true)
  }
  /** Collection-like TableQuery object for table Roster */
  lazy val Roster = new TableQuery(tag => new Roster(tag))
}
