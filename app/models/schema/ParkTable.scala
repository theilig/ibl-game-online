package models.schema
// AUTO-GENERATED Slick data model for table Park
trait ParkTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
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
}
