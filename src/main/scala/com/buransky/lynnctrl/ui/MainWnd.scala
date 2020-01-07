package com.buransky.lynnctrl.ui

import java.awt.Frame

import akka.actor.typed.{Behavior, PostStop, Signal}
import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}
import com.buransky.lynnctrl.media.VlcPlayer

import scala.swing._

object MainWnd {
  def apply(): Behavior[Nothing] =
    Behaviors.setup[Nothing] { context =>
      //context.spawn[Nothing](Dashboard(), "dashboard")
      val mainFrame = initFrame()
      val vlcPlayer = new VlcPlayer()
      vlcPlayer.play(mainFrame.peer)
      new MainWnd(mainFrame, context)
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

class MainWnd(mainFrame: MainFrame,
              context: ActorContext[Nothing]) extends AbstractBehavior[Nothing](context) {

  override def onMessage(msg: Nothing): Behavior[Nothing] = Behaviors.unhandled
}
