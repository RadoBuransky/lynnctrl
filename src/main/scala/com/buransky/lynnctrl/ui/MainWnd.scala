package com.buransky.lynnctrl.ui

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}

import scala.swing._

object MainWnd {
  def apply(): Behavior[Nothing] =
    Behaviors.setup[Nothing] { context =>
      val frame = initFrame()
      new MainWnd(frame, context)
    }

  private def initFrame(): Frame = {
    new MainFrame {
      title = "Lynn Controller"
      menuBar = initMenuBar()
      maximize()
      open()
    }
  }

  private def initMenuBar(): MenuBar = {
    new MenuBar {
      contents += new Menu("File") {
        contents += new MenuItem(Action("Exit") {
          System.exit(0)
        })
      }
    }
  }
}

class MainWnd(frame: Frame, context: ActorContext[Nothing]) extends AbstractBehavior[Nothing](context) {
  override def onMessage(msg: Nothing): Behavior[Nothing] = Behaviors.unhandled
}
