package com.buransky.lynnctrl.media.audio

import java.io.File

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}
import com.buransky.lynnctrl.media.MediaApp
import com.buransky.lynnctrl.media.audio.AudioPlayer.Command
import com.xuggle.xuggler.{ICodec, IContainer}
import javafx.application.Application

object AudioPlayer {
  def apply(): Behavior[Command] =
    Behaviors.setup[Command] { context =>
      new AudioPlayer(context)
    }

  sealed trait Command
  final case class Play(path: File) extends Command
}

class AudioPlayer(context: ActorContext[Command]) extends AbstractBehavior[Command](context) {
  private val mediaApp = new MediaApp()
  override def onMessage(msg: Command): Behavior[Command] = msg match {
    case AudioPlayer.Play(path) =>
//      play(path)
      Application.launch(classOf[MediaApp], null)
      this
  }

  private def play(path: File): Unit = {
    context.log.info(s"Playing audio: $path")

    val containerAudio = IContainer.make()
    if (containerAudio.open(path.toURI.toString, IContainer.Type.READ, null) < 0)
      throw new RuntimeException("Container open failed!")
    val streamIndex = audioStremIndex(containerAudio)
    val coder = containerAudio.getStream(streamIndex).getStreamCoder()
    if (coder.open() < 0)
      throw new RuntimeException("Coder oper failed!")
  }

  private def audioStremIndex(containerAudio: IContainer): Int = {
    val numStreamAudio = containerAudio.getNumStreams()
    (0 to numStreamAudio).find { i =>
      val stream = containerAudio.getStream(i)
      val coder = stream.getStreamCoder()
      coder.getCodecType == ICodec.Type.CODEC_TYPE_AUDIO
    }.getOrElse(throw new RuntimeException("No audio stream found!"))
  }
}
