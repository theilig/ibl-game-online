package models.schema
// AUTO-GENERATED Slick data model for table FenceHeight
trait FenceHeightTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
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
}
