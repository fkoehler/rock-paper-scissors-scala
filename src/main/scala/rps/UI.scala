package rps

import scala.io.StdIn
import scala.util.Try

case class UI(gameRunner: GameRunner) {

  def play(): Unit = {
    println("\nWelcome to RockPaperScissors!")
    playOnce()
  }

  private def playOnce(): Unit = {
    val chooseModeMsg = """
      |Please choose the game mode you want to play:
      |1) Player Vs Computer
      |2) Computer Vs Computer
      |3) Exit
      |>""".stripMargin

    retryUntilValidValue(letPlayerChooseIntValueWithMsg(chooseModeMsg), 1, 2, 3) match {
      case Some(gameMode) if gameMode == 1 =>
        playerVsComputer()
        playOnce()
      case Some(gameMode) if gameMode == 2 =>
        computerVsComputer()
        playOnce()
      case Some(gameMode) if gameMode == 3 =>
        println("\nThanks for playing!")
      case None =>
        println("\nInvalid input. Exiting program.")
    }
  }

  private def computerVsComputer(): Unit = printGameResult(gameRunner.playComputerVsComputer())

  private def playerVsComputer(): Unit = {
    val numberToGestureNames = Gesture.numberToGestureName.foldLeft("") { case (result, (number, gestureName)) =>
      result + s"\n$number) $gestureName"
    }
    val chooseGestureMsg = s"""
          |Please choose your gesture:$numberToGestureNames
          |>""".stripMargin

    retryUntilValidValue(letPlayerChooseIntValueWithMsg(chooseGestureMsg), Gesture.numberToGestureName.keySet.toSeq: _*) match {
      case Some(player1Gesture) => printGameResult(gameRunner.playPlayerVsComputer(Gesture.findByNumber(player1Gesture)))
      case None => println("\nInvalid input. Exiting program.")
    }
  }

  /**
   * retries the given user function until one of the valid values are returned
   */
  private def retryUntilValidValue[T](userInputFunc: => Option[T], validValues: T*): Option[T] = {
    Stream.continually(userInputFunc)
      .flatten // only keep user inputs which got something in them
      .find(userInput => validValues contains userInput) // only keep valid values
  }

  private def letPlayerChooseIntValueWithMsg(msg: String): Option[Int] = {
    print(msg)

    for {
      userInput <- Option(StdIn.readLine())
      input <- Try(userInput.toInt).toOption
    } yield input
  }

  private def printGameResult(result: (GameResult, Gesture, Gesture)): Unit = {
    val (gameResult, player1Gesture, player2Gesture) = result
    println(s"""
        |Player 1 gesture: $player1Gesture
        |Player 2 gesture: $player2Gesture
        |
        |===> $gameResult""".stripMargin)
  }

}
