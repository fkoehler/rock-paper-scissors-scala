package rps

import java.io.{ByteArrayOutputStream, StringReader}

import org.scalatest.{FunSpec, Matchers}

import scala.Console._

class UiSpec extends FunSpec with Matchers with ComputerAiGestureChooserMock {

  private val welcome = "Welcome to RockPaperScissors!"
  private val selectGameMode = """Please choose the game mode you want to play:
    |1) Player Vs Computer
    |2) Computer Vs Computer
    |3) Exit
    |>""".stripMargin
  private val chooseGesture = """Please choose your gesture:
    |1) Rock
    |2) Paper
    |3) Scissors
    |>""".stripMargin
  private val exit = "Thanks for playing!"

  describe("the UI") {

    it("should play a single computer vs computer game with a printed result and exit message") {
      val aiGestureChooser = computerGestureChooserWhichReturns(Rock, Scissors)
      val gameRunner = GameRunner(aiGestureChooser)
      val ui = UI(gameRunner)

      val bos = new ByteArrayOutputStream()
      withOut(bos) {
        withIn(new StringReader("2\n3\n")) {
          ui.play()
        }
      }

      bos.toString shouldBe s"""
        |$welcome
        |
        |$selectGameMode
        |Player 1 gesture: Rock
        |Player 2 gesture: Scissors
        |
        |===> Player 1 wins the game!
        |
        |$selectGameMode
        |$exit
        |""".stripMargin
    }
    it("should play a single player vs computer game with a printed result and exit message") {
      val aiGestureChooser = computerGestureChooserWhichReturns(Rock)
      val gameRunner = GameRunner(aiGestureChooser)
      val ui = UI(gameRunner)

      val bos = new ByteArrayOutputStream()
      withOut(bos) {
        withIn(new StringReader("1\n1\n3\n")) {
          ui.play()
        }
      }

      bos.toString shouldBe s"""
        |$welcome
        |
        |$selectGameMode
        |$chooseGesture
        |Player 1 gesture: Rock
        |Player 2 gesture: Rock
        |
        |===> The game is tied!
        |
        |$selectGameMode
        |$exit
        |""".stripMargin
    }
    it("should ask the user again on any invalid input during the questions") {
      val aiGestureChooser = computerGestureChooserWhichReturns(Rock)
      val gameRunner = GameRunner(aiGestureChooser)
      val ui = UI(gameRunner)

      val bos = new ByteArrayOutputStream()
      withOut(bos) {
        withIn(new StringReader("5\n1\n9\n1\n3\n")) {
          ui.play()
        }
      }

      bos.toString shouldBe s"""
        |$welcome
        |
        |$selectGameMode
        |$selectGameMode
        |$chooseGesture
        |$chooseGesture
        |Player 1 gesture: Rock
        |Player 2 gesture: Rock
        |
        |===> The game is tied!
        |
        |$selectGameMode
        |$exit
        |""".stripMargin
    }
  }

}
