package com.buransky.lynnctrl.ui

import java.awt.Frame

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}

import scala.swing._

object MainWnd {
  def apply(): Behavior[Nothing] =
    Behaviors.setup[Nothing] { context =>
      //context.spawn[Nothing](Dashboard(), "dashboard")
      initFrame()
      new MainWnd(context)
    }

  private def initFrame(): MainFrame = {
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

class MainWnd(context: ActorContext[Nothing]) extends AbstractBehavior[Nothing](context) {
  override def onMessage(msg: Nothing): Behavior[Nothing] = Behaviors.unhandled
}
