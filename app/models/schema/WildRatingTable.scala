package models.schema
// AUTO-GENERATED Slick data model for table WildRating
trait WildRatingTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table WildRating
   *  @param wildRatingId Database column wild_rating_id SqlType(INT), AutoInc, PrimaryKey
   *  @param playerId Database column player_id SqlType(INT)
   *  @param rating Database column rating SqlType(ENUM), Length(6,false)
   *  @param games Database column games SqlType(INT), Default(None) */
  case class WildRatingRow(wildRatingId: Int, playerId: Int, rating: String, games: Option[Int] = None)
  /** GetResult implicit for fetching WildRatingRow objects using plain SQL queries */
  implicit def GetResultWildRatingRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[Int]]): GR[WildRatingRow] = GR{
    prs => import prs._
    WildRatingRow.tupled((<<[Int], <<[Int], <<[String], <<?[Int]))
  }
  /** Table description of table WildRating. Objects of this class serve as prototypes for rows in queries. */
  class WildRating(_tableTag: Tag) extends profile.api.Table[WildRatingRow](_tableTag, Some("IblGame"), "WildRating") {
    def * = (wildRatingId, playerId, rating, games) <> (WildRatingRow.tupled, WildRatingRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(wildRatingId), Rep.Some(playerId), Rep.Some(rating), games)).shaped.<>({r=>import r._; _1.map(_=> WildRatingRow.tupled((_1.get, _2.get, _3.get, _4)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column wild_rating_id SqlType(INT), AutoInc, PrimaryKey */
    val wildRatingId: Rep[Int] = column[Int]("wild_rating_id", O.AutoInc, O.PrimaryKey)
    /** Database column player_id SqlType(INT) */
    val playerId: Rep[Int] = column[Int]("player_id")
    /** Database column rating SqlType(ENUM), Length(6,false) */
    val rating: Rep[String] = column[String]("rating", O.Length(6,varying=false))
    /** Database column games SqlType(INT), Default(None) */
    val games: Rep[Option[Int]] = column[Option[Int]]("games", O.Default(None))

    /** Uniqueness Index over (playerId,rating) (database name player_rating) */
    val index1 = index("player_rating", (playerId, rating), unique=true)
  }
  /** Collection-like TableQuery object for table WildRating */
  lazy val WildRating = new TableQuery(tag => new WildRating(tag))
}
