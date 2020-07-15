package controllers.game.stage

import controllers.game.{GetRoll, GetTemperature, Message, Roll}
import dao.TeamDao
import javax.inject.Inject
import models.game.{State, Temperature, Unknown}
import models.schema.Tables.TemperatureRow
import models.{RollResult, User}
import play.api.libs.json.{JsObject, Json}
import play.api.mvc.Result
import play.api.mvc.Results.{InternalServerError, Ok}

import scala.concurrent.{ExecutionContext, Future}

class DetermineTemperature @Inject()(teamDao: TeamDao)(implicit executionContext: ExecutionContext) extends GameStage {

  def setTemperature(state: State, result: Int): Future[State] = {
    val eventualRanges = teamDao.getTempRanges(state.park.geoLocationId, state.park.month, state.park.timeOfDay)
    val eventualTemperature = eventualRanges.map(ranges => {
      @scala.annotation.tailrec
      def rangeMatch(ranges: List[TemperatureRow], remainingRoll: Int): Temperature = {
        ranges match {
          case x :: _ if x.frequency > remainingRoll => Temperature(x.temp, Some(result))
          case Nil => Unknown
          case x :: xs => rangeMatch(xs, remainingRoll - x.frequency)
        }
      }
      rangeMatch(ranges, result)
    })
    eventualTemperature.map(temperature => {

      state.copy(park = state.park.copy(temperature = temperature))
    })
  }

  override def receive(message: Message, source: User, state: State): Future[Result] = {
     message match {
       case r @ Roll(2, result) if source.userId == state.teams.home.managerId =>
         val eventualNewState = setTemperature(state, r.resultAsInt)
         eventualNewState.map(newState => {
           Ok(JsObject(Map("roll" -> Json.toJson(result), "state" -> Json.toJson(newState))))
         })
       case GetRoll(2) if state.park.temperature.roll.nonEmpty =>
         Future.successful(Ok(JsObject(Map(
           "roll" -> Json.toJson(Roll(2, state.park.temperature.roll.get)),
           "state" -> Json.toJson(state)
         ))))
       case GetTemperature =>
         val park = state.park
         val eventualRanges = teamDao.getTempRanges(park.geoLocationId, park.month, park.timeOfDay)
         eventualRanges.map(ranges => {
           val result: (List[RollResult], Int) =
             ranges.foldRight((List[RollResult](), 99))((tempRow, soFar) => {
               val (tempList, lastNumber) = soFar
               if (tempRow.frequency > 0) {
                 (RollResult(tempRow.temp, lastNumber - tempRow.frequency + 1, lastNumber) :: tempList, lastNumber - tempRow.frequency)
               } else {
                 (tempList, lastNumber)
               }
             })
           if (result._2 != -1) {
             InternalServerError("Incorrect Range")
           } else {
             Ok(Json.toJson(result._1))
           }
         })
     }
  }
}
