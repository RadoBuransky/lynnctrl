package com.buransky.lynnctrl.media

import java.io.File

import com.buransky.lynnctrl.util.Logging
import javax.swing.JFrame
import uk.co.caprica.vlcj.factory.MediaPlayerFactory
import uk.co.caprica.vlcj.media.{Media, MediaEventAdapter, MediaParsedStatus}
import uk.co.caprica.vlcj.player.component.{AudioPlayerComponent, EmbeddedMediaPlayerComponent}

class VlcPlayer extends AutoCloseable with Logging {
  private val videoPlayer = new EmbeddedMediaPlayerComponent()
  private val audioPlayer = new AudioPlayerComponent()

  def play(frame: JFrame): Unit = {
    val audioFilePath = new File("""c:\Users\rado\OneDrive\Documents\Cubase Projects\2019-12-07 Pri sklenom dube\Audio\01_PriSklenomDube.wav""")
    val videoFilePath = new File("""c:\Users\rado\Downloads\SampleVideo_1280x720_10mb.mp4""")

    frame.setContentPane(videoPlayer)
    frame.setVisible(true)
    videoPlayer.mediaPlayer().media().prepare(videoFilePath.getAbsolutePath)
    audioPlayer.mediaPlayer().media().prepare(encodeFileToVLCString(audioFilePath))

    videoPlayer.mediaPlayer().media().events().addMediaEventListener(new MediaEventAdapter() {
      override def mediaParsedChanged(media: Media, newStatus: MediaParsedStatus): Unit = {
        if (MediaParsedStatus.DONE == newStatus) {
          logger.info("Parsing done.")
          videoPlayer.mediaPlayerFactory().submit(new Runnable {
            override def run(): Unit = {
              videoPlayer.mediaPlayer().audio().setTrack(-1)
            }
          })
        }
      }
    })

    videoPlayer.mediaPlayer().media().parsing().parse()
    audioPlayer.mediaPlayer().media().parsing().parse()

    videoPlayer.mediaPlayer().controls().play()
    audioPlayer.mediaPlayer().controls().play()
  }

  override def close(): Unit = {
    audioPlayer.release()
    videoPlayer.release()
  }

  private def encodeFileToVLCString(filename: File): String =
    filename.toURI.toASCIIString.replaceFirst("file:/", "file:///")
}
