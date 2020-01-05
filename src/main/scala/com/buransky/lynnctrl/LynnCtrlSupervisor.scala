package com.buransky.lynnctrl

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}
import com.buransky.lynnctrl.ui.MainWnd

object LynnCtrlSupervisor {
  def apply(): Behavior[Nothing] =
    Behaviors.setup[Nothing] { context =>
      context.spawn[Nothing](MainWnd(), "mainwnd")
      new LynnCtrlSupervisor(context)
    }
}

class LynnCtrlSupervisor(context: ActorContext[Nothing]) extends AbstractBehavior[Nothing](context) {
  override def onMessage(msg: Nothing): Behavior[Nothing] = Behaviors.unhandled
}
