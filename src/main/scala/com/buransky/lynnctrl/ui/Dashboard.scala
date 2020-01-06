package com.buransky.lynnctrl.ui

import java.awt.Color

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}

import scala.swing._

object Dashboard {
  def apply(): Behavior[Nothing] =
    Behaviors.setup[Nothing] { context =>
      initFrame()
      new Dashboard(context)
    }

  private def initFrame(): Frame = {
    val frame = new Frame {
      title = "Dashboard"
      peer.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH)
      peer.setUndecorated(true)
      open()
    }

    val panel = new Panel {
      override protected def paintComponent(g: Graphics2D): Unit = {
        super.paintComponent(g)
        g.setColor(Color.RED)
        g.drawRect(10, 10, 100, 100)
      }
    }
    panel.peer.setBounds(frame.bounds)
    frame.peer.add(panel.peer)
    frame.repaint()

    frame
  }
}

class Dashboard(context: ActorContext[Nothing]) extends AbstractBehavior[Nothing](context) {
  override def onMessage(msg: Nothing): Behavior[Nothing] = Behaviors.unhandled
}
