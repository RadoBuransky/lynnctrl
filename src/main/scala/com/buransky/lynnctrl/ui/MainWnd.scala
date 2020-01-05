package com.buransky.lynnctrl.ui

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}
import scala.swing._

object MainWnd {
  def apply(): Behavior[Nothing] =
    Behaviors.setup[Nothing] { context =>
      val frame = new MainFrame()
      frame.maximize()
      frame.open()

      new MainWnd(context)
    }
}

class MainWnd(context: ActorContext[Nothing]) extends AbstractBehavior[Nothing](context) {
  override def onMessage(msg: Nothing): Behavior[Nothing] = Behaviors.unhandled
}
