package models.schema
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.MySQLProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Array(Bunting.schema, CloudCover.schema, Defense.schema, Distance.schema, FenceHeight.schema, Game.schema, GeographicLocation.schema, Park.schema, ParkEffect.schema, Pitcher.schema, Player.schema, PlayEvolutions.schema, Precipitation.schema, ResultTemplate.schema, Roster.schema, Team.schema, Temperature.schema, User.schema, UserConfirmation.schema, WildRating.schema, Wind.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

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

  /** Entity class storing rows of table CloudCover
   *  @param cloudCoverId Database column cloud_cover_id SqlType(INT), AutoInc, PrimaryKey
   *  @param geographicLocationId Database column geographic_location_id SqlType(INT)
   *  @param gameMonth Database column game_month SqlType(ENUM), Length(9,false)
   *  @param cover Database column cover SqlType(ENUM), Length(13,false), Default(None)
   *  @param rank Database column rank SqlType(INT)
   *  @param frequency Database column frequency SqlType(INT) */
  case class CloudCoverRow(cloudCoverId: Int, geographicLocationId: Int, gameMonth: String, cover: Option[String] = None, rank: Int, frequency: Int)
  /** GetResult implicit for fetching CloudCoverRow objects using plain SQL queries */
  implicit def GetResultCloudCoverRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[String]]): GR[CloudCoverRow] = GR{
    prs => import prs._
    CloudCoverRow.tupled((<<[Int], <<[Int], <<[String], <<?[String], <<[Int], <<[Int]))
  }
  /** Table description of table CloudCover. Objects of this class serve as prototypes for rows in queries. */
  class CloudCover(_tableTag: Tag) extends profile.api.Table[CloudCoverRow](_tableTag, Some("IblGame"), "CloudCover") {
    def * = (cloudCoverId, geographicLocationId, gameMonth, cover, rank, frequency) <> (CloudCoverRow.tupled, CloudCoverRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(cloudCoverId), Rep.Some(geographicLocationId), Rep.Some(gameMonth), cover, Rep.Some(rank), Rep.Some(frequency))).shaped.<>({r=>import r._; _1.map(_=> CloudCoverRow.tupled((_1.get, _2.get, _3.get, _4, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column cloud_cover_id SqlType(INT), AutoInc, PrimaryKey */
    val cloudCoverId: Rep[Int] = column[Int]("cloud_cover_id", O.AutoInc, O.PrimaryKey)
    /** Database column geographic_location_id SqlType(INT) */
    val geographicLocationId: Rep[Int] = column[Int]("geographic_location_id")
    /** Database column game_month SqlType(ENUM), Length(9,false) */
    val gameMonth: Rep[String] = column[String]("game_month", O.Length(9,varying=false))
    /** Database column cover SqlType(ENUM), Length(13,false), Default(None) */
    val cover: Rep[Option[String]] = column[Option[String]]("cover", O.Length(13,varying=false), O.Default(None))
    /** Database column rank SqlType(INT) */
    val rank: Rep[Int] = column[Int]("rank")
    /** Database column frequency SqlType(INT) */
    val frequency: Rep[Int] = column[Int]("frequency")

    /** Uniqueness Index over (geographicLocationId,gameMonth,cover) (database name loc_month_cover) */
    val index1 = index("loc_month_cover", (geographicLocationId, gameMonth, cover), unique=true)
    /** Index over (geographicLocationId,gameMonth,rank) (database name loc_month_ordering) */
    val index2 = index("loc_month_ordering", (geographicLocationId, gameMonth, rank))
  }
  /** Collection-like TableQuery object for table CloudCover */
  lazy val CloudCover = new TableQuery(tag => new CloudCover(tag))

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

  /** Entity class storing rows of table Distance
   *  @param distanceId Database column distance_id SqlType(INT), AutoInc, PrimaryKey
   *  @param parkId Database column park_id SqlType(INT)
   *  @param location Database column location SqlType(ENUM), Length(4,false), Default(None)
   *  @param windType Database column wind_type SqlType(ENUM), Length(8,false)
   *  @param fenceDistance Database column fence_distance SqlType(INT) */
  case class DistanceRow(distanceId: Int, parkId: Int, location: Option[String] = None, windType: String, fenceDistance: Int)
  /** GetResult implicit for fetching DistanceRow objects using plain SQL queries */
  implicit def GetResultDistanceRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[String]): GR[DistanceRow] = GR{
    prs => import prs._
    DistanceRow.tupled((<<[Int], <<[Int], <<?[String], <<[String], <<[Int]))
  }
  /** Table description of table Distance. Objects of this class serve as prototypes for rows in queries. */
  class Distance(_tableTag: Tag) extends profile.api.Table[DistanceRow](_tableTag, Some("IblGame"), "Distance") {
    def * = (distanceId, parkId, location, windType, fenceDistance) <> (DistanceRow.tupled, DistanceRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(distanceId), Rep.Some(parkId), location, Rep.Some(windType), Rep.Some(fenceDistance))).shaped.<>({r=>import r._; _1.map(_=> DistanceRow.tupled((_1.get, _2.get, _3, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column distance_id SqlType(INT), AutoInc, PrimaryKey */
    val distanceId: Rep[Int] = column[Int]("distance_id", O.AutoInc, O.PrimaryKey)
    /** Database column park_id SqlType(INT) */
    val parkId: Rep[Int] = column[Int]("park_id")
    /** Database column location SqlType(ENUM), Length(4,false), Default(None) */
    val location: Rep[Option[String]] = column[Option[String]]("location", O.Length(4,varying=false), O.Default(None))
    /** Database column wind_type SqlType(ENUM), Length(8,false) */
    val windType: Rep[String] = column[String]("wind_type", O.Length(8,varying=false))
    /** Database column fence_distance SqlType(INT) */
    val fenceDistance: Rep[Int] = column[Int]("fence_distance")

    /** Uniqueness Index over (parkId,windType,location) (database name park_wind_location) */
    val index1 = index("park_wind_location", (parkId, windType, location), unique=true)
  }
  /** Collection-like TableQuery object for table Distance */
  lazy val Distance = new TableQuery(tag => new Distance(tag))

  /** Entity class storing rows of table FenceHeight
   *  @param fenceHeightId Database column fence_height_id SqlType(INT), AutoInc, PrimaryKey
   *  @param parkId Database column park_id SqlType(INT)
   *  @param location Database column location SqlType(ENUM), Length(4,false), Default(None)
   *  @param fenceHeight Database column fence_height SqlType(INT) */
  case class FenceHeightRow(fenceHeightId: Int, parkId: Int, location: Option[String] = None, fenceHeight: Int)
  /** GetResult implicit for fetching FenceHeightRow objects using plain SQL queries */
  implicit def GetResultFenceHeightRow(implicit e0: GR[Int], e1: GR[Option[String]]): GR[FenceHeightRow] = GR{
    prs => import prs._
    FenceHeightRow.tupled((<<[Int], <<[Int], <<?[String], <<[Int]))
  }
  /** Table description of table FenceHeight. Objects of this class serve as prototypes for rows in queries. */
  class FenceHeight(_tableTag: Tag) extends profile.api.Table[FenceHeightRow](_tableTag, Some("IblGame"), "FenceHeight") {
    def * = (fenceHeightId, parkId, location, fenceHeight) <> (FenceHeightRow.tupled, FenceHeightRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(fenceHeightId), Rep.Some(parkId), location, Rep.Some(fenceHeight))).shaped.<>({r=>import r._; _1.map(_=> FenceHeightRow.tupled((_1.get, _2.get, _3, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column fence_height_id SqlType(INT), AutoInc, PrimaryKey */
    val fenceHeightId: Rep[Int] = column[Int]("fence_height_id", O.AutoInc, O.PrimaryKey)
    /** Database column park_id SqlType(INT) */
    val parkId: Rep[Int] = column[Int]("park_id")
    /** Database column location SqlType(ENUM), Length(4,false), Default(None) */
    val location: Rep[Option[String]] = column[Option[String]]("location", O.Length(4,varying=false), O.Default(None))
    /** Database column fence_height SqlType(INT) */
    val fenceHeight: Rep[Int] = column[Int]("fence_height")

    /** Uniqueness Index over (parkId,fenceHeight,location) (database name park_fence_height_location) */
    val index1 = index("park_fence_height_location", (parkId, fenceHeight, location), unique=true)
  }
  /** Collection-like TableQuery object for table FenceHeight */
  lazy val FenceHeight = new TableQuery(tag => new FenceHeight(tag))

  /** Entity class storing rows of table Game
   *  @param gameId Database column game_id SqlType(INT), AutoInc, PrimaryKey
   *  @param roadTeamId Database column road_team_id SqlType(INT)
   *  @param homeTeamId Database column home_team_id SqlType(INT)
   *  @param roadScore Database column road_score SqlType(INT)
   *  @param homeScore Database column home_score SqlType(INT)
   *  @param inning Database column inning SqlType(INT), Default(None)
   *  @param halfInning Database column half_inning SqlType(ENUM), Length(6,false), Default(None)
   *  @param outs Database column outs SqlType(INT), Default(None)
   *  @param runnerOnFirst Database column runner_on_first SqlType(INT), Default(None)
   *  @param runnerOnSecond Database column runner_on_second SqlType(INT), Default(None)
   *  @param runnerOnThird Database column runner_on_third SqlType(INT), Default(None)
   *  @param batter Database column batter SqlType(INT), Default(None)
   *  @param pitcher Database column pitcher SqlType(INT), Default(None) */
  case class GameRow(gameId: Int, roadTeamId: Int, homeTeamId: Int, roadScore: Int, homeScore: Int, inning: Option[Int] = None, halfInning: Option[String] = None, outs: Option[Int] = None, runnerOnFirst: Option[Int] = None, runnerOnSecond: Option[Int] = None, runnerOnThird: Option[Int] = None, batter: Option[Int] = None, pitcher: Option[Int] = None)
  /** GetResult implicit for fetching GameRow objects using plain SQL queries */
  implicit def GetResultGameRow(implicit e0: GR[Int], e1: GR[Option[Int]], e2: GR[Option[String]]): GR[GameRow] = GR{
    prs => import prs._
    GameRow.tupled((<<[Int], <<[Int], <<[Int], <<[Int], <<[Int], <<?[Int], <<?[String], <<?[Int], <<?[Int], <<?[Int], <<?[Int], <<?[Int], <<?[Int]))
  }
  /** Table description of table Game. Objects of this class serve as prototypes for rows in queries. */
  class Game(_tableTag: Tag) extends profile.api.Table[GameRow](_tableTag, Some("IblGame"), "Game") {
    def * = (gameId, roadTeamId, homeTeamId, roadScore, homeScore, inning, halfInning, outs, runnerOnFirst, runnerOnSecond, runnerOnThird, batter, pitcher) <> (GameRow.tupled, GameRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(gameId), Rep.Some(roadTeamId), Rep.Some(homeTeamId), Rep.Some(roadScore), Rep.Some(homeScore), inning, halfInning, outs, runnerOnFirst, runnerOnSecond, runnerOnThird, batter, pitcher)).shaped.<>({r=>import r._; _1.map(_=> GameRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6, _7, _8, _9, _10, _11, _12, _13)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column game_id SqlType(INT), AutoInc, PrimaryKey */
    val gameId: Rep[Int] = column[Int]("game_id", O.AutoInc, O.PrimaryKey)
    /** Database column road_team_id SqlType(INT) */
    val roadTeamId: Rep[Int] = column[Int]("road_team_id")
    /** Database column home_team_id SqlType(INT) */
    val homeTeamId: Rep[Int] = column[Int]("home_team_id")
    /** Database column road_score SqlType(INT) */
    val roadScore: Rep[Int] = column[Int]("road_score")
    /** Database column home_score SqlType(INT) */
    val homeScore: Rep[Int] = column[Int]("home_score")
    /** Database column inning SqlType(INT), Default(None) */
    val inning: Rep[Option[Int]] = column[Option[Int]]("inning", O.Default(None))
    /** Database column half_inning SqlType(ENUM), Length(6,false), Default(None) */
    val halfInning: Rep[Option[String]] = column[Option[String]]("half_inning", O.Length(6,varying=false), O.Default(None))
    /** Database column outs SqlType(INT), Default(None) */
    val outs: Rep[Option[Int]] = column[Option[Int]]("outs", O.Default(None))
    /** Database column runner_on_first SqlType(INT), Default(None) */
    val runnerOnFirst: Rep[Option[Int]] = column[Option[Int]]("runner_on_first", O.Default(None))
    /** Database column runner_on_second SqlType(INT), Default(None) */
    val runnerOnSecond: Rep[Option[Int]] = column[Option[Int]]("runner_on_second", O.Default(None))
    /** Database column runner_on_third SqlType(INT), Default(None) */
    val runnerOnThird: Rep[Option[Int]] = column[Option[Int]]("runner_on_third", O.Default(None))
    /** Database column batter SqlType(INT), Default(None) */
    val batter: Rep[Option[Int]] = column[Option[Int]]("batter", O.Default(None))
    /** Database column pitcher SqlType(INT), Default(None) */
    val pitcher: Rep[Option[Int]] = column[Option[Int]]("pitcher", O.Default(None))
  }
  /** Collection-like TableQuery object for table Game */
  lazy val Game = new TableQuery(tag => new Game(tag))

  /** Entity class storing rows of table GeographicLocation
   *  @param geographicLocationId Database column geographic_location_id SqlType(INT), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(100,true) */
  case class GeographicLocationRow(geographicLocationId: Int, name: String)
  /** GetResult implicit for fetching GeographicLocationRow objects using plain SQL queries */
  implicit def GetResultGeographicLocationRow(implicit e0: GR[Int], e1: GR[String]): GR[GeographicLocationRow] = GR{
    prs => import prs._
    GeographicLocationRow.tupled((<<[Int], <<[String]))
  }
  /** Table description of table GeographicLocation. Objects of this class serve as prototypes for rows in queries. */
  class GeographicLocation(_tableTag: Tag) extends profile.api.Table[GeographicLocationRow](_tableTag, Some("IblGame"), "GeographicLocation") {
    def * = (geographicLocationId, name) <> (GeographicLocationRow.tupled, GeographicLocationRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(geographicLocationId), Rep.Some(name))).shaped.<>({r=>import r._; _1.map(_=> GeographicLocationRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column geographic_location_id SqlType(INT), AutoInc, PrimaryKey */
    val geographicLocationId: Rep[Int] = column[Int]("geographic_location_id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(VARCHAR), Length(100,true) */
    val name: Rep[String] = column[String]("name", O.Length(100,varying=true))
  }
  /** Collection-like TableQuery object for table GeographicLocation */
  lazy val GeographicLocation = new TableQuery(tag => new GeographicLocation(tag))

  /** Entity class storing rows of table Park
   *  @param parkId Database column park_id SqlType(INT), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(150,true)
   *  @param surface Database column surface SqlType(ENUM), Length(10,false)
   *  @param quality Database column quality SqlType(ENUM), Length(10,false)
   *  @param foulTerritory Database column foul_territory SqlType(ENUM), Length(11,false)
   *  @param ifrAdjustment Database column ifr_adjustment SqlType(INT)
   *  @param ofrAdjustment Database column ofr_adjustment SqlType(INT)
   *  @param templateId Database column template_id SqlType(INT)
   *  @param results Database column results SqlType(VARCHAR), Length(200,true) */
  case class ParkRow(parkId: Int, name: String, surface: String, quality: String, foulTerritory: String, ifrAdjustment: Int, ofrAdjustment: Int, templateId: Int, results: String)
  /** GetResult implicit for fetching ParkRow objects using plain SQL queries */
  implicit def GetResultParkRow(implicit e0: GR[Int], e1: GR[String]): GR[ParkRow] = GR{
    prs => import prs._
    ParkRow.tupled((<<[Int], <<[String], <<[String], <<[String], <<[String], <<[Int], <<[Int], <<[Int], <<[String]))
  }
  /** Table description of table Park. Objects of this class serve as prototypes for rows in queries. */
  class Park(_tableTag: Tag) extends profile.api.Table[ParkRow](_tableTag, Some("IblGame"), "Park") {
    def * = (parkId, name, surface, quality, foulTerritory, ifrAdjustment, ofrAdjustment, templateId, results) <> (ParkRow.tupled, ParkRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(parkId), Rep.Some(name), Rep.Some(surface), Rep.Some(quality), Rep.Some(foulTerritory), Rep.Some(ifrAdjustment), Rep.Some(ofrAdjustment), Rep.Some(templateId), Rep.Some(results))).shaped.<>({r=>import r._; _1.map(_=> ParkRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column park_id SqlType(INT), AutoInc, PrimaryKey */
    val parkId: Rep[Int] = column[Int]("park_id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(VARCHAR), Length(150,true) */
    val name: Rep[String] = column[String]("name", O.Length(150,varying=true))
    /** Database column surface SqlType(ENUM), Length(10,false) */
    val surface: Rep[String] = column[String]("surface", O.Length(10,varying=false))
    /** Database column quality SqlType(ENUM), Length(10,false) */
    val quality: Rep[String] = column[String]("quality", O.Length(10,varying=false))
    /** Database column foul_territory SqlType(ENUM), Length(11,false) */
    val foulTerritory: Rep[String] = column[String]("foul_territory", O.Length(11,varying=false))
    /** Database column ifr_adjustment SqlType(INT) */
    val ifrAdjustment: Rep[Int] = column[Int]("ifr_adjustment")
    /** Database column ofr_adjustment SqlType(INT) */
    val ofrAdjustment: Rep[Int] = column[Int]("ofr_adjustment")
    /** Database column template_id SqlType(INT) */
    val templateId: Rep[Int] = column[Int]("template_id")
    /** Database column results SqlType(VARCHAR), Length(200,true) */
    val results: Rep[String] = column[String]("results", O.Length(200,varying=true))
  }
  /** Collection-like TableQuery object for table Park */
  lazy val Park = new TableQuery(tag => new Park(tag))

  /** Entity class storing rows of table ParkEffect
   *  @param parkEffectId Database column park_effect_id SqlType(INT), AutoInc, PrimaryKey
   *  @param parkId Database column park_id SqlType(INT)
   *  @param effect Database column effect SqlType(ENUM), Length(9,false), Default(None) */
  case class ParkEffectRow(parkEffectId: Int, parkId: Int, effect: Option[String] = None)
  /** GetResult implicit for fetching ParkEffectRow objects using plain SQL queries */
  implicit def GetResultParkEffectRow(implicit e0: GR[Int], e1: GR[Option[String]]): GR[ParkEffectRow] = GR{
    prs => import prs._
    ParkEffectRow.tupled((<<[Int], <<[Int], <<?[String]))
  }
  /** Table description of table ParkEffect. Objects of this class serve as prototypes for rows in queries. */
  class ParkEffect(_tableTag: Tag) extends profile.api.Table[ParkEffectRow](_tableTag, Some("IblGame"), "ParkEffect") {
    def * = (parkEffectId, parkId, effect) <> (ParkEffectRow.tupled, ParkEffectRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(parkEffectId), Rep.Some(parkId), effect)).shaped.<>({r=>import r._; _1.map(_=> ParkEffectRow.tupled((_1.get, _2.get, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column park_effect_id SqlType(INT), AutoInc, PrimaryKey */
    val parkEffectId: Rep[Int] = column[Int]("park_effect_id", O.AutoInc, O.PrimaryKey)
    /** Database column park_id SqlType(INT) */
    val parkId: Rep[Int] = column[Int]("park_id")
    /** Database column effect SqlType(ENUM), Length(9,false), Default(None) */
    val effect: Rep[Option[String]] = column[Option[String]]("effect", O.Length(9,varying=false), O.Default(None))

    /** Uniqueness Index over (parkId,effect) (database name park_effect) */
    val index1 = index("park_effect", (parkId, effect), unique=true)
  }
  /** Collection-like TableQuery object for table ParkEffect */
  lazy val ParkEffect = new TableQuery(tag => new ParkEffect(tag))

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

  /** Entity class storing rows of table PlayEvolutions
   *  @param id Database column id SqlType(INT), PrimaryKey
   *  @param hash Database column hash SqlType(VARCHAR), Length(255,true)
   *  @param appliedAt Database column applied_at SqlType(TIMESTAMP)
   *  @param applyScript Database column apply_script SqlType(MEDIUMTEXT), Length(16777215,true), Default(None)
   *  @param revertScript Database column revert_script SqlType(MEDIUMTEXT), Length(16777215,true), Default(None)
   *  @param state Database column state SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param lastProblem Database column last_problem SqlType(MEDIUMTEXT), Length(16777215,true), Default(None) */
  case class PlayEvolutionsRow(id: Int, hash: String, appliedAt: java.sql.Timestamp, applyScript: Option[String] = None, revertScript: Option[String] = None, state: Option[String] = None, lastProblem: Option[String] = None)
  /** GetResult implicit for fetching PlayEvolutionsRow objects using plain SQL queries */
  implicit def GetResultPlayEvolutionsRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Option[String]]): GR[PlayEvolutionsRow] = GR{
    prs => import prs._
    PlayEvolutionsRow.tupled((<<[Int], <<[String], <<[java.sql.Timestamp], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table play_evolutions. Objects of this class serve as prototypes for rows in queries. */
  class PlayEvolutions(_tableTag: Tag) extends profile.api.Table[PlayEvolutionsRow](_tableTag, Some("IblGame"), "play_evolutions") {
    def * = (id, hash, appliedAt, applyScript, revertScript, state, lastProblem) <> (PlayEvolutionsRow.tupled, PlayEvolutionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(hash), Rep.Some(appliedAt), applyScript, revertScript, state, lastProblem)).shaped.<>({r=>import r._; _1.map(_=> PlayEvolutionsRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column hash SqlType(VARCHAR), Length(255,true) */
    val hash: Rep[String] = column[String]("hash", O.Length(255,varying=true))
    /** Database column applied_at SqlType(TIMESTAMP) */
    val appliedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("applied_at")
    /** Database column apply_script SqlType(MEDIUMTEXT), Length(16777215,true), Default(None) */
    val applyScript: Rep[Option[String]] = column[Option[String]]("apply_script", O.Length(16777215,varying=true), O.Default(None))
    /** Database column revert_script SqlType(MEDIUMTEXT), Length(16777215,true), Default(None) */
    val revertScript: Rep[Option[String]] = column[Option[String]]("revert_script", O.Length(16777215,varying=true), O.Default(None))
    /** Database column state SqlType(VARCHAR), Length(255,true), Default(None) */
    val state: Rep[Option[String]] = column[Option[String]]("state", O.Length(255,varying=true), O.Default(None))
    /** Database column last_problem SqlType(MEDIUMTEXT), Length(16777215,true), Default(None) */
    val lastProblem: Rep[Option[String]] = column[Option[String]]("last_problem", O.Length(16777215,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table PlayEvolutions */
  lazy val PlayEvolutions = new TableQuery(tag => new PlayEvolutions(tag))

  /** Entity class storing rows of table Precipitation
   *  @param precipitationId Database column precipitation_id SqlType(INT), AutoInc, PrimaryKey
   *  @param geographicLocationId Database column geographic_location_id SqlType(INT)
   *  @param gameMonth Database column game_month SqlType(ENUM), Length(9,false)
   *  @param precip Database column precip SqlType(ENUM), Length(14,false)
   *  @param rank Database column rank SqlType(INT)
   *  @param frequency Database column frequency SqlType(INT) */
  case class PrecipitationRow(precipitationId: Int, geographicLocationId: Int, gameMonth: String, precip: String, rank: Int, frequency: Int)
  /** GetResult implicit for fetching PrecipitationRow objects using plain SQL queries */
  implicit def GetResultPrecipitationRow(implicit e0: GR[Int], e1: GR[String]): GR[PrecipitationRow] = GR{
    prs => import prs._
    PrecipitationRow.tupled((<<[Int], <<[Int], <<[String], <<[String], <<[Int], <<[Int]))
  }
  /** Table description of table Precipitation. Objects of this class serve as prototypes for rows in queries. */
  class Precipitation(_tableTag: Tag) extends profile.api.Table[PrecipitationRow](_tableTag, Some("IblGame"), "Precipitation") {
    def * = (precipitationId, geographicLocationId, gameMonth, precip, rank, frequency) <> (PrecipitationRow.tupled, PrecipitationRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(precipitationId), Rep.Some(geographicLocationId), Rep.Some(gameMonth), Rep.Some(precip), Rep.Some(rank), Rep.Some(frequency))).shaped.<>({r=>import r._; _1.map(_=> PrecipitationRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column precipitation_id SqlType(INT), AutoInc, PrimaryKey */
    val precipitationId: Rep[Int] = column[Int]("precipitation_id", O.AutoInc, O.PrimaryKey)
    /** Database column geographic_location_id SqlType(INT) */
    val geographicLocationId: Rep[Int] = column[Int]("geographic_location_id")
    /** Database column game_month SqlType(ENUM), Length(9,false) */
    val gameMonth: Rep[String] = column[String]("game_month", O.Length(9,varying=false))
    /** Database column precip SqlType(ENUM), Length(14,false) */
    val precip: Rep[String] = column[String]("precip", O.Length(14,varying=false))
    /** Database column rank SqlType(INT) */
    val rank: Rep[Int] = column[Int]("rank")
    /** Database column frequency SqlType(INT) */
    val frequency: Rep[Int] = column[Int]("frequency")

    /** Index over (geographicLocationId,gameMonth,rank) (database name loc_month_ordering) */
    val index1 = index("loc_month_ordering", (geographicLocationId, gameMonth, rank))
    /** Uniqueness Index over (geographicLocationId,gameMonth,precip) (database name loc_month_precip) */
    val index2 = index("loc_month_precip", (geographicLocationId, gameMonth, precip), unique=true)
  }
  /** Collection-like TableQuery object for table Precipitation */
  lazy val Precipitation = new TableQuery(tag => new Precipitation(tag))

  /** Entity class storing rows of table ResultTemplate
   *  @param resultTemplateId Database column result_template_id SqlType(INT), AutoInc, PrimaryKey
   *  @param templateId Database column template_id SqlType(INT)
   *  @param rank Database column rank SqlType(INT)
   *  @param result Database column result SqlType(ENUM), Length(7,false)
   *  @param location Database column location SqlType(ENUM), Length(4,false), Default(None) */
  case class ResultTemplateRow(resultTemplateId: Int, templateId: Int, rank: Int, result: String, location: Option[String] = None)
  /** GetResult implicit for fetching ResultTemplateRow objects using plain SQL queries */
  implicit def GetResultResultTemplateRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[String]]): GR[ResultTemplateRow] = GR{
    prs => import prs._
    ResultTemplateRow.tupled((<<[Int], <<[Int], <<[Int], <<[String], <<?[String]))
  }
  /** Table description of table ResultTemplate. Objects of this class serve as prototypes for rows in queries. */
  class ResultTemplate(_tableTag: Tag) extends profile.api.Table[ResultTemplateRow](_tableTag, Some("IblGame"), "ResultTemplate") {
    def * = (resultTemplateId, templateId, rank, result, location) <> (ResultTemplateRow.tupled, ResultTemplateRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(resultTemplateId), Rep.Some(templateId), Rep.Some(rank), Rep.Some(result), location)).shaped.<>({r=>import r._; _1.map(_=> ResultTemplateRow.tupled((_1.get, _2.get, _3.get, _4.get, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column result_template_id SqlType(INT), AutoInc, PrimaryKey */
    val resultTemplateId: Rep[Int] = column[Int]("result_template_id", O.AutoInc, O.PrimaryKey)
    /** Database column template_id SqlType(INT) */
    val templateId: Rep[Int] = column[Int]("template_id")
    /** Database column rank SqlType(INT) */
    val rank: Rep[Int] = column[Int]("rank")
    /** Database column result SqlType(ENUM), Length(7,false) */
    val result: Rep[String] = column[String]("result", O.Length(7,varying=false))
    /** Database column location SqlType(ENUM), Length(4,false), Default(None) */
    val location: Rep[Option[String]] = column[Option[String]]("location", O.Length(4,varying=false), O.Default(None))

    /** Uniqueness Index over (templateId,rank) (database name slot) */
    val index1 = index("slot", (templateId, rank), unique=true)
  }
  /** Collection-like TableQuery object for table ResultTemplate */
  lazy val ResultTemplate = new TableQuery(tag => new ResultTemplate(tag))

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

  /** Entity class storing rows of table User
   *  @param userId Database column user_id SqlType(INT), AutoInc, PrimaryKey
   *  @param firstName Database column first_name SqlType(VARCHAR), Length(200,true)
   *  @param lastName Database column last_name SqlType(VARCHAR), Length(200,true)
   *  @param passwordHash Database column password_hash SqlType(VARCHAR), Length(200,true)
   *  @param email Database column email SqlType(VARCHAR), Length(256,true)
   *  @param confirmed Database column confirmed SqlType(BIT), Default(false) */
  case class UserRow(userId: Int, firstName: String, lastName: String, passwordHash: String, email: String, confirmed: Boolean = false)
  /** GetResult implicit for fetching UserRow objects using plain SQL queries */
  implicit def GetResultUserRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Boolean]): GR[UserRow] = GR{
    prs => import prs._
    UserRow.tupled((<<[Int], <<[String], <<[String], <<[String], <<[String], <<[Boolean]))
  }
  /** Table description of table User. Objects of this class serve as prototypes for rows in queries. */
  class User(_tableTag: Tag) extends profile.api.Table[UserRow](_tableTag, Some("IblGame"), "User") {
    def * = (userId, firstName, lastName, passwordHash, email, confirmed) <> (UserRow.tupled, UserRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(userId), Rep.Some(firstName), Rep.Some(lastName), Rep.Some(passwordHash), Rep.Some(email), Rep.Some(confirmed))).shaped.<>({r=>import r._; _1.map(_=> UserRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column user_id SqlType(INT), AutoInc, PrimaryKey */
    val userId: Rep[Int] = column[Int]("user_id", O.AutoInc, O.PrimaryKey)
    /** Database column first_name SqlType(VARCHAR), Length(200,true) */
    val firstName: Rep[String] = column[String]("first_name", O.Length(200,varying=true))
    /** Database column last_name SqlType(VARCHAR), Length(200,true) */
    val lastName: Rep[String] = column[String]("last_name", O.Length(200,varying=true))
    /** Database column password_hash SqlType(VARCHAR), Length(200,true) */
    val passwordHash: Rep[String] = column[String]("password_hash", O.Length(200,varying=true))
    /** Database column email SqlType(VARCHAR), Length(256,true) */
    val email: Rep[String] = column[String]("email", O.Length(256,varying=true))
    /** Database column confirmed SqlType(BIT), Default(false) */
    val confirmed: Rep[Boolean] = column[Boolean]("confirmed", O.Default(false))

    /** Uniqueness Index over (email) (database name user_email) */
    val index1 = index("user_email", email, unique=true)
  }
  /** Collection-like TableQuery object for table User */
  lazy val User = new TableQuery(tag => new User(tag))

  /** Entity class storing rows of table UserConfirmation
   *  @param userConfirmationId Database column user_confirmation_id SqlType(INT), AutoInc, PrimaryKey
   *  @param token Database column token SqlType(VARCHAR), Length(256,true)
   *  @param userId Database column user_id SqlType(INT) */
  case class UserConfirmationRow(userConfirmationId: Int, token: String, userId: Int)
  /** GetResult implicit for fetching UserConfirmationRow objects using plain SQL queries */
  implicit def GetResultUserConfirmationRow(implicit e0: GR[Int], e1: GR[String]): GR[UserConfirmationRow] = GR{
    prs => import prs._
    UserConfirmationRow.tupled((<<[Int], <<[String], <<[Int]))
  }
  /** Table description of table UserConfirmation. Objects of this class serve as prototypes for rows in queries. */
  class UserConfirmation(_tableTag: Tag) extends profile.api.Table[UserConfirmationRow](_tableTag, Some("IblGame"), "UserConfirmation") {
    def * = (userConfirmationId, token, userId) <> (UserConfirmationRow.tupled, UserConfirmationRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(userConfirmationId), Rep.Some(token), Rep.Some(userId))).shaped.<>({r=>import r._; _1.map(_=> UserConfirmationRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column user_confirmation_id SqlType(INT), AutoInc, PrimaryKey */
    val userConfirmationId: Rep[Int] = column[Int]("user_confirmation_id", O.AutoInc, O.PrimaryKey)
    /** Database column token SqlType(VARCHAR), Length(256,true) */
    val token: Rep[String] = column[String]("token", O.Length(256,varying=true))
    /** Database column user_id SqlType(INT) */
    val userId: Rep[Int] = column[Int]("user_id")

    /** Uniqueness Index over (token) (database name confirmation_token) */
    val index1 = index("confirmation_token", token, unique=true)
    /** Uniqueness Index over (userId) (database name confirmation_user) */
    val index2 = index("confirmation_user", userId, unique=true)
  }
  /** Collection-like TableQuery object for table UserConfirmation */
  lazy val UserConfirmation = new TableQuery(tag => new UserConfirmation(tag))

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

  /** Entity class storing rows of table Wind
   *  @param windId Database column wind_id SqlType(INT), AutoInc, PrimaryKey
   *  @param parkId Database column park_id SqlType(INT)
   *  @param gameMonth Database column game_month SqlType(ENUM), Length(9,false)
   *  @param windType Database column wind_type SqlType(ENUM), Length(8,false)
   *  @param rank Database column rank SqlType(INT)
   *  @param frequency Database column frequency SqlType(INT) */
  case class WindRow(windId: Int, parkId: Int, gameMonth: String, windType: String, rank: Int, frequency: Int)
  /** GetResult implicit for fetching WindRow objects using plain SQL queries */
  implicit def GetResultWindRow(implicit e0: GR[Int], e1: GR[String]): GR[WindRow] = GR{
    prs => import prs._
    WindRow.tupled((<<[Int], <<[Int], <<[String], <<[String], <<[Int], <<[Int]))
  }
  /** Table description of table Wind. Objects of this class serve as prototypes for rows in queries. */
  class Wind(_tableTag: Tag) extends profile.api.Table[WindRow](_tableTag, Some("IblGame"), "Wind") {
    def * = (windId, parkId, gameMonth, windType, rank, frequency) <> (WindRow.tupled, WindRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(windId), Rep.Some(parkId), Rep.Some(gameMonth), Rep.Some(windType), Rep.Some(rank), Rep.Some(frequency))).shaped.<>({r=>import r._; _1.map(_=> WindRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column wind_id SqlType(INT), AutoInc, PrimaryKey */
    val windId: Rep[Int] = column[Int]("wind_id", O.AutoInc, O.PrimaryKey)
    /** Database column park_id SqlType(INT) */
    val parkId: Rep[Int] = column[Int]("park_id")
    /** Database column game_month SqlType(ENUM), Length(9,false) */
    val gameMonth: Rep[String] = column[String]("game_month", O.Length(9,varying=false))
    /** Database column wind_type SqlType(ENUM), Length(8,false) */
    val windType: Rep[String] = column[String]("wind_type", O.Length(8,varying=false))
    /** Database column rank SqlType(INT) */
    val rank: Rep[Int] = column[Int]("rank")
    /** Database column frequency SqlType(INT) */
    val frequency: Rep[Int] = column[Int]("frequency")

    /** Index over (parkId,gameMonth,rank) (database name ordering) */
    val index1 = index("ordering", (parkId, gameMonth, rank))
    /** Uniqueness Index over (parkId,gameMonth,windType) (database name park_month_wind) */
    val index2 = index("park_month_wind", (parkId, gameMonth, windType), unique=true)
  }
  /** Collection-like TableQuery object for table Wind */
  lazy val Wind = new TableQuery(tag => new Wind(tag))
}
