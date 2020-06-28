package dao

import javax.inject.Inject
import models.schema.Tables
import models.{Geo, Park, Team}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.lifted

import scala.concurrent.{ExecutionContext, Future}

class TeamDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
                       (implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  private val Teams = lifted.TableQuery[Tables.Team]

  private val Parks = lifted.TableQuery[Tables.Park]

  private val Geos = lifted.TableQuery[Tables.GeographicLocation]

  def teamList: Future[List[Team]] = {
    db.run(Teams.result).map(teams => teams.toList.map(x => Team(x)))
  }

  def parkList: Future[List[Park]] = {
    db.run(Parks.result).map(parks => parks.toList.map(x => Park(x)))
  }

  def geoList: Future[List[Geo]] = {
    db.run(Geos.result).map(geos => geos.toList.map(x => Geo(x)))
  }

  def findTeamById(teamId: Int): Future[Option[Tables.TeamRow]] = {
    db.run(Teams.filter(_.teamId === teamId).result.headOption)
  }

  def findParkById(parkId: Int): Future[Option[Tables.ParkRow]] = {
    db.run(Parks.filter(_.parkId === parkId).result.headOption)
  }

  def findGeoById(geoId: Int): Future[Option[Tables.GeographicLocationRow]] = {
    db.run(Geos.filter(_.geographicLocationId === geoId).result.headOption)
  }

}
