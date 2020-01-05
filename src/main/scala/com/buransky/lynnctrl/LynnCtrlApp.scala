package com.buransky.lynnctrl

import akka.actor.typed.ActorSystem
import ch.qos.logback.classic.LoggerContext
import com.buransky.lynnctrl.config.LynnCtrlModule
import com.buransky.lynnctrl.util.Logging
import com.google.inject.Guice
import org.slf4j.LoggerFactory

/**
 * Application entry point.
 */
object LynnCtrlApp extends Logging {
  def main(args: Array[String]): Unit = {
    globalLogging {
      val injector = Guice.createInjector(LynnCtrlModule)
      ActorSystem[Nothing](LynnCtrlSupervisor(), "lynnctrl")
    }
  }

  private def globalLogging(body: => Unit): Unit = {
    logger.info("App started.")
    try {
      addShutdownHook()
      body
    }
    catch {
      case t: Throwable =>
        logger.error("App crashed!", t)
        throw t
    }
  }

  private def addShutdownHook(): Unit = {
    Runtime.getRuntime.addShutdownHook(new Thread() {
      override def run(): Unit = {
        logger.info("App stopped.")
        val loggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
        loggerContext.stop()
      }
    })
  }
}