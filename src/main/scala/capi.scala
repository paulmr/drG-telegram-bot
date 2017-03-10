package com.gu.drG

import com.gu.contentapi.client.GuardianContentClient

object capi {
  val client = new GuardianContentClient(config.capiApiKey)
}
