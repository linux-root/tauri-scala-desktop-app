package com.w47s0n.util

import java.util.UUID

object ElementIdGenerator:
  def generate(prefix: String): String = s"$prefix-${UUID.randomUUID().toString}"
