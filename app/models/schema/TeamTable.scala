package models.schema
// AUTO-GENERATED Slick data model for table Team
trait TeamTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Team
   *  @param teamId Database column team_id SqlType(INT), AutoInc, PrimaryKey
   *  @param city Database column city SqlType(VARCHAR), Length(100,true)
   *  @param nickname Database column nickname SqlType(VARCHAR), Length(100,true)
   *  @param geographicLocationId Database column geographic_location_id SqlType(INT)
   *  @param homeParkId Database column home_park_id SqlType(INT) */
  case class TeamRow(teamId: Int, city: String, nickname: String, geographicLocationId: Int, homeParkId: Int)
  /** GetResult implicit for fetching TeamRow objects using plain SQL queries */
  implicit def GetResultTeamRow(implicit e0: GR[Int], e1: GR[String]): GR[TeamRow] = GR{
    prs => import prs._
    TeamRow.tupled((<<[Int], <<[String], <<[String], <<[Int], <<[Int]))
  }
  /** Table description of table Team. Objects of this class serve as prototypes for rows in queries. */
  class Team(_tableTag: Tag) extends profile.api.Table[TeamRow](_tableTag, Some("IblGame"), "Team") {
    def * = (teamId, city, nickname, geographicLocationId, homeParkId) <> (TeamRow.tupled, TeamRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(teamId), Rep.Some(city), Rep.Some(nickname), Rep.Some(geographicLocationId), Rep.Some(homeParkId))).shaped.<>({r=>import r._; _1.map(_=> TeamRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column team_id SqlType(INT), AutoInc, PrimaryKey */
    val teamId: Rep[Int] = column[Int]("team_id", O.AutoInc, O.PrimaryKey)
    /** Database column city SqlType(VARCHAR), Length(100,true) */
    val city: Rep[String] = column[String]("city", O.Length(100,varying=true))
    /** Database column nickname SqlType(VARCHAR), Length(100,true) */
    val nickname: Rep[String] = column[String]("nickname", O.Length(100,varying=true))
    /** Database column geographic_location_id SqlType(INT) */
    val geographicLocationId: Rep[Int] = column[Int]("geographic_location_id")
    /** Database column home_park_id SqlType(INT) */
    val homeParkId: Rep[Int] = column[Int]("home_park_id")
  }
  /** Collection-like TableQuery object for table Team */
  lazy val Team = new TableQuery(tag => new Team(tag))
}
