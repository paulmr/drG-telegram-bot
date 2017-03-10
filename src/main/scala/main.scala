package com.gu.drG
import bot._
import com.gu.contentapi.client.model.ItemQuery
import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App {
  // val testId =
  //   "housing-network/2017/mar/08/what-do-you-know-about-uk-housing-take-our-quiz"

  // val item = capi.client.getResponse(
  //     ItemQuery(testId)
  //   ) andThen { case _ => capi.client.shutdown() }

  // item.foreach { item => println(item.content) }

  val telegram = new TelegramBot()
  val req = telegram.getUpdates() map { response =>
      println(response)
    }

  req onComplete { _ => telegram.shutdown() }
}
