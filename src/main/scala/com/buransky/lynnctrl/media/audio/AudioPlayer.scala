package com.buransky.lynnctrl.media.audio

import java.io.File

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}
import com.buransky.lynnctrl.media.VlcPlayer
import com.buransky.lynnctrl.media.audio.AudioPlayer.Command

object AudioPlayer {
  def apply(): Behavior[Command] =
    Behaviors.setup[Command] { context =>
      new AudioPlayer(context)
    }

  sealed trait Command
  final case class Play(path: File) extends Command
}

class AudioPlayer(context: ActorContext[Command]) extends AbstractBehavior[Command](context) {
  private val vlcPlayer = new VlcPlayer()
  override def onMessage(msg: Command): Behavior[Command] = msg match {
    case AudioPlayer.Play(path) =>
//      Application.launch(classOf[MediaApp], null)
      this
  }
}
