package rps

import org.scalatest.prop.PropertyChecks
import org.scalatest.{FunSpec, Matchers}

class GameRunnerSpec extends FunSpec with Matchers with PropertyChecks with ComputerAiGestureChooserMock {

  describe("the two different game modes") {
    describe("player vs computer game") {
      it("player should be able to play against a computer with some simple variations") {
        GameRunner(computerGestureChooserWhichReturns(Paper)).playPlayerVsComputer(player1Gesture = Scissors)._1 shouldBe Player1Wins
        GameRunner(computerGestureChooserWhichReturns(Rock)).playPlayerVsComputer(player1Gesture = Rock)._1 shouldBe GameTied
        GameRunner(computerGestureChooserWhichReturns(Scissors)).playPlayerVsComputer(player1Gesture = Rock)._1 shouldBe Player1Wins
      }
    }
    describe("computer vs computer game") {
      it("computer should be able to play against itself computer with some simple variations") {
        GameRunner(computerGestureChooserWhichReturns(Paper, Scissors)).playComputerVsComputer()._1 shouldBe Player2Wins
        GameRunner(computerGestureChooserWhichReturns(Rock, Rock)).playComputerVsComputer()._1 shouldBe GameTied
        GameRunner(computerGestureChooserWhichReturns(Scissors, Rock)).playComputerVsComputer()._1 shouldBe Player2Wins
      }
    }
  }

}
