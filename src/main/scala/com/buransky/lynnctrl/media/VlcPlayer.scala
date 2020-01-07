package com.buransky.lynnctrl.media

import java.io.File

import javax.swing.JFrame
import uk.co.caprica.vlcj.factory.MediaPlayerFactory

class VlcPlayer extends AutoCloseable {
  private val factory = new MediaPlayerFactory()
  private val mediaPlayer = factory.mediaPlayers().newMediaPlayer()

  def play(frame: JFrame): Unit = {
    val audioFilePath = new File("""c:\Users\rado\OneDrive\Documents\Cubase Projects\2019-12-07 Pri sklenom dube\Audio\01_PriSklenomDube.wav""")
    val videoFilePath = new File("""c:\Users\rado\Downloads\SampleVideo_1280x720_10mb.mp4""")

    mediaPlayer.media().play(audioFilePath.getAbsolutePath)
  }

  override def close(): Unit = {
    mediaPlayer.release()
    factory.release()
  }
}
