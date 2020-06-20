package models.schema
// AUTO-GENERATED Slick data model for table Pitcher
trait PitcherTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Pitcher
   *  @param pitcherId Database column pitcher_id SqlType(INT), AutoInc, PrimaryKey
   *  @param throws Database column throws SqlType(ENUM), Length(5,false)
   *  @param slashLeft Database column slash_left SqlType(VARCHAR), Length(40,true)
   *  @param slashRight Database column slash_right SqlType(VARCHAR), Length(40,true)
   *  @param templateIdLeft Database column template_id_left SqlType(INT)
   *  @param templateIdRight Database column template_id_right SqlType(INT)
   *  @param resultsLeft Database column results_left SqlType(VARCHAR), Length(200,true)
   *  @param resultsRight Database column results_right SqlType(VARCHAR), Length(200,true)
   *  @param fatigueResultsLeft Database column fatigue_results_left SqlType(VARCHAR), Length(200,true)
   *  @param fatigueResultsRight Database column fatigue_results_right SqlType(VARCHAR), Length(200,true)
   *  @param balk Database column balk SqlType(ENUM), Length(8,false)
   *  @param hold Database column hold SqlType(ENUM), Length(10,false)
   *  @param staminaSlash Database column stamina_slash SqlType(VARCHAR), Length(50,true)
   *  @param staminaStarting Database column stamina_starting SqlType(INT)
   *  @param staminaRelieving Database column stamina_relieving SqlType(INT)
   *  @param staminaOld Database column stamina_old SqlType(VARCHAR), Length(50,true)
   *  @param durability Database column durability SqlType(INT)
   *  @param injuryDays Database column injury_days SqlType(INT)
   *  @param usage Database column usage SqlType(VARCHAR), Length(60,true) */
  case class PitcherRow(pitcherId: Int, throws: String, slashLeft: String, slashRight: String, templateIdLeft: Int, templateIdRight: Int, resultsLeft: String, resultsRight: String, fatigueResultsLeft: String, fatigueResultsRight: String, balk: String, hold: String, staminaSlash: String, staminaStarting: Int, staminaRelieving: Int, staminaOld: String, durability: Int, injuryDays: Int, usage: String)
  /** GetResult implicit for fetching PitcherRow objects using plain SQL queries */
  implicit def GetResultPitcherRow(implicit e0: GR[Int], e1: GR[String]): GR[PitcherRow] = GR{
    prs => import prs._
    PitcherRow.tupled((<<[Int], <<[String], <<[String], <<[String], <<[Int], <<[Int], <<[String], <<[String], <<[String], <<[String], <<[String], <<[String], <<[String], <<[Int], <<[Int], <<[String], <<[Int], <<[Int], <<[String]))
  }
  /** Table description of table Pitcher. Objects of this class serve as prototypes for rows in queries. */
  class Pitcher(_tableTag: Tag) extends profile.api.Table[PitcherRow](_tableTag, Some("IblGame"), "Pitcher") {
    def * = (pitcherId, throws, slashLeft, slashRight, templateIdLeft, templateIdRight, resultsLeft, resultsRight, fatigueResultsLeft, fatigueResultsRight, balk, hold, staminaSlash, staminaStarting, staminaRelieving, staminaOld, durability, injuryDays, usage) <> (PitcherRow.tupled, PitcherRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(pitcherId), Rep.Some(throws), Rep.Some(slashLeft), Rep.Some(slashRight), Rep.Some(templateIdLeft), Rep.Some(templateIdRight), Rep.Some(resultsLeft), Rep.Some(resultsRight), Rep.Some(fatigueResultsLeft), Rep.Some(fatigueResultsRight), Rep.Some(balk), Rep.Some(hold), Rep.Some(staminaSlash), Rep.Some(staminaStarting), Rep.Some(staminaRelieving), Rep.Some(staminaOld), Rep.Some(durability), Rep.Some(injuryDays), Rep.Some(usage))).shaped.<>({r=>import r._; _1.map(_=> PitcherRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10.get, _11.get, _12.get, _13.get, _14.get, _15.get, _16.get, _17.get, _18.get, _19.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column pitcher_id SqlType(INT), AutoInc, PrimaryKey */
    val pitcherId: Rep[Int] = column[Int]("pitcher_id", O.AutoInc, O.PrimaryKey)
    /** Database column throws SqlType(ENUM), Length(5,false) */
    val throws: Rep[String] = column[String]("throws", O.Length(5,varying=false))
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
    /** Database column fatigue_results_left SqlType(VARCHAR), Length(200,true) */
    val fatigueResultsLeft: Rep[String] = column[String]("fatigue_results_left", O.Length(200,varying=true))
    /** Database column fatigue_results_right SqlType(VARCHAR), Length(200,true) */
    val fatigueResultsRight: Rep[String] = column[String]("fatigue_results_right", O.Length(200,varying=true))
    /** Database column balk SqlType(ENUM), Length(8,false) */
    val balk: Rep[String] = column[String]("balk", O.Length(8,varying=false))
    /** Database column hold SqlType(ENUM), Length(10,false) */
    val hold: Rep[String] = column[String]("hold", O.Length(10,varying=false))
    /** Database column stamina_slash SqlType(VARCHAR), Length(50,true) */
    val staminaSlash: Rep[String] = column[String]("stamina_slash", O.Length(50,varying=true))
    /** Database column stamina_starting SqlType(INT) */
    val staminaStarting: Rep[Int] = column[Int]("stamina_starting")
    /** Database column stamina_relieving SqlType(INT) */
    val staminaRelieving: Rep[Int] = column[Int]("stamina_relieving")
    /** Database column stamina_old SqlType(VARCHAR), Length(50,true) */
    val staminaOld: Rep[String] = column[String]("stamina_old", O.Length(50,varying=true))
    /** Database column durability SqlType(INT) */
    val durability: Rep[Int] = column[Int]("durability")
    /** Database column injury_days SqlType(INT) */
    val injuryDays: Rep[Int] = column[Int]("injury_days")
    /** Database column usage SqlType(VARCHAR), Length(60,true) */
    val usage: Rep[String] = column[String]("usage", O.Length(60,varying=true))
  }
  /** Collection-like TableQuery object for table Pitcher */
  lazy val Pitcher = new TableQuery(tag => new Pitcher(tag))
}
