package dao

import javax.inject.Inject
import models.{Geo, Park, Team}
import models.schema.Tables
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class TeamDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
                       (implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  private val Teams = TableQuery[Tables.Team]

  private val Parks = TableQuery[Tables.Park]

  private val Geos = TableQuery[Tables.GeographicLocation]

  def teamList: Future[List[Team]] = {
    db.run(Teams.result).map(teams => teams.toList.map(x => Team(x)))
  }

  def parkList: Future[List[Park]] = {
    db.run(Parks.result).map(parks => parks.toList.map(x => Park(x)))
  }

  def geoList: Future[List[Geo]] = {
    db.run(Geos.result).map(geos => geos.toList.map(x => Geo(x)))
  }

}
