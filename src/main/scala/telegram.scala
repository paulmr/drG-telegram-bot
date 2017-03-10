package com.gu.drG.bot

import com.gu.drG.config

// import scala.concurrent.Future
import org.json4s.{ JValue, JBool }
import scala.concurrent.ExecutionContext
import dispatch._

case class BotException(msg: String) extends Throwable

class TelegramBot(apiKey: String = config.telegramApiKey) {

  def makeUrl(method: String) =
    host("api.telegram.org").secure / s"bot${apiKey}" / method

  def apiRequest(telegramCommand: String,queryParams: Map[String, String] = Map.empty)
    (implicit ec: ExecutionContext): Future[JValue] = {
    val req = makeUrl(telegramCommand) <<? queryParams
    Http(req OK as.json4s.Json) flatMap { res =>
      // the http request succeeded, but the response may have an error in it
      (res \\ "ok") match {
        case JBool(true) => Future.successful(res \\ "result")
        case _           => Future.failed(BotException("OK was false"))
      }
    }
  }

  def getMe()(implicit ec: ExecutionContext) = apiRequest("getMe")

  def getUpdates(timeout: Int = 20)(implicit ec: ExecutionContext) =
    apiRequest("getUpdates", Map("timeout" -> s"$timeout"))

  def shutdown(): Unit = {
    println("shutdown()")
    Http.shutdown()
  }

}
