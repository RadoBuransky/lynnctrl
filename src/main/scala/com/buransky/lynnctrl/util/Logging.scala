package com.buransky.lynnctrl.util

import org.slf4j.{Logger, LoggerFactory}

private[lynnctrl] trait Logging {
  protected def logger: Logger = LoggerFactory.getLogger(this.getClass)
}
