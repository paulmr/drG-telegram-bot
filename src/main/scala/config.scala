package com.gu.drG

import com.typesafe.config.ConfigFactory

object config {
  lazy val conf = ConfigFactory.load()
  lazy val capiApiKey = conf.getString("capi.apiKey")
  lazy val telegramApiKey = conf.getString("telegram.apiKey")
}
