package models.schema
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.MySQLProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.)
    Each generated XXXXTable trait is mixed in this trait hence allowing access to all the TableQuery lazy vals.
  */
trait Tables extends CloudCoverTable with TemperatureTable with BuntingTable with UserConfirmationTable with ParkEffectTable with GameTable with ParkTable with DistanceTable with GeographicLocationTable with PrecipitationTable with PitcherTable with WildRatingTable with WindTable with PlayEvolutionsTable with UserTable with RosterTable with PlayerTable with ResultTemplateTable with TeamTable with FenceHeightTable with DefenseTable {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Array(Bunting.schema, CloudCover.schema, Defense.schema, Distance.schema, FenceHeight.schema, Game.schema, GeographicLocation.schema, Park.schema, ParkEffect.schema, Pitcher.schema, Player.schema, PlayEvolutions.schema, Precipitation.schema, ResultTemplate.schema, Roster.schema, Team.schema, Temperature.schema, User.schema, UserConfirmation.schema, WildRating.schema, Wind.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

}
