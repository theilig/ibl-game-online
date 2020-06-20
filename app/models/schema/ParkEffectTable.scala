package models.schema
// AUTO-GENERATED Slick data model for table ParkEffect
trait ParkEffectTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
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
}
