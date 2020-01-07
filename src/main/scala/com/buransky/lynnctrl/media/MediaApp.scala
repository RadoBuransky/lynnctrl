package com.buransky.lynnctrl.media

import java.io.File

import com.buransky.lynnctrl.util.Logging
import javafx.application.Application
import javafx.scene.media.{Media, MediaPlayer, MediaView}
import javafx.scene.{Group, Scene}
import javafx.stage.Stage
import javafx.util.Duration

/**
 * Main JavaFX class.
 */
class MediaApp extends Application with Logging {
  override def start(primaryStage: Stage): Unit = {
    val audioFilePath = new File("""c:\Users\rado\OneDrive\Documents\Cubase Projects\2019-12-07 Pri sklenom dube\Audio\01_PriSklenomDube.wav""")
    val audioMedia = new Media(audioFilePath.toURI.toString) // replace this with your own audio file
    val audioPlayer = new MediaPlayer(audioMedia)
    val audioMediaView = new MediaView(audioPlayer)

    val videoFilePath = new File("""c:\Users\rado\Downloads\SampleVideo_1280x720_10mb.mp4""")
    val videoMedia = new Media(videoFilePath.toURI.toString)
    val videoPlayer = new MediaPlayer(videoMedia)
    val videoMediaView = new MediaView(videoPlayer)
    videoPlayer.setMute(true)

    // Add to scene
    val root = new Group(audioMediaView, videoMediaView)
    val scene = new Scene(root, 500, 200)

    // Show the stage
    primaryStage.setTitle("Lynn video")
    primaryStage.setScene(scene)
    primaryStage.setFullScreen(true)
    primaryStage.show()

    audioPlayer.setStartTime(Duration.seconds(10.0))
    audioPlayer.setStopTime(Duration.seconds(13.0))
    audioPlayer.setCycleCount(Integer.MAX_VALUE)
    videoPlayer.setStartTime(Duration.seconds(10.0))
    videoPlayer.setStopTime(Duration.seconds(13.0))
    videoPlayer.setCycleCount(Integer.MAX_VALUE)

    // Play the media once the stage is shown
    audioPlayer.play()
    videoPlayer.play()
  }
}
