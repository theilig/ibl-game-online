package dao

import javax.inject.Inject
import models.schema.Tables
import models.{GameList, GameListItem}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class GameDao @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)
                        (implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  private val Games = TableQuery[Tables.Game]

  private val Teams = TableQuery[Tables.Team]

  private val Players = TableQuery[Tables.Player]

  def gameList(userName: String): Future[GameList] = {
    db.run(Games.result).map(list => {
      val allGames = list.map(game => {
        GameListItem(1, "Uo", "Tim", "Tim")
      })
      GameList(userName, allGames.toList)
    })
  }
}
