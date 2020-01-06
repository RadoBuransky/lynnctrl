package com.buransky.lynnctrl

import java.io.File

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}
import com.buransky.lynnctrl.media.audio.AudioPlayer
import com.buransky.lynnctrl.ui.MainWnd

object LynnCtrlSupervisor {
  def apply(): Behavior[Nothing] =
    Behaviors.setup[Nothing] { context =>
      context.spawn[Nothing](MainWnd(), "mainwnd")
      val audioPlayer = context.spawn(AudioPlayer(), "audioplayer")
      audioPlayer ! AudioPlayer.Play(new File("""c:\Users\rado\OneDrive\Documents\Cubase Projects\2019-12-07 Pri sklenom dube\Audio\01_PriSklenomDube.wav"""))
      new LynnCtrlSupervisor(context)
    }
}

class LynnCtrlSupervisor(context: ActorContext[Nothing]) extends AbstractBehavior[Nothing](context) {
  override def onMessage(msg: Nothing): Behavior[Nothing] = Behaviors.unhandled
}
