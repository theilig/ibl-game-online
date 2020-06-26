package dao

import javax.inject.Inject
import models.GameListItem
import models.schema.Tables
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class GameDao @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)
                        (implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  private val Games = TableQuery[Tables.Game]

  private val Users = TableQuery[Tables.User]

  def gameList: Future[List[GameListItem]] = {
    val allGames = for {
      ((game, roadManager), homeManager) <- (Games join Users on (_.roadManager === _.userId)) join Users on (_._1.homeManager === _.userId)
    } yield (game, roadManager.firstName, homeManager.firstName)
    db.run(allGames.result).map { list =>
        list.map {
          case (gameRow, road, home) => GameListItem(gameRow, road, home)
        }
    }.map(x => x.toList)
  }
}
