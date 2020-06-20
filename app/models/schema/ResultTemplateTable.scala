package models.schema
// AUTO-GENERATED Slick data model for table ResultTemplate
trait ResultTemplateTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
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
}
