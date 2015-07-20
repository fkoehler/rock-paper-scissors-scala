package rps

import org.scalatest.prop.PropertyChecks
import org.scalatest.{FunSpec, Matchers}

class GameEngineSpec extends FunSpec with Matchers with PropertyChecks {

  describe("the game engine") {
    describe("some games") {
      val examples = Table(
        ("expected result", "when", "player 1 gesture", "plays against", "player 2 gesture"),

        (Player1Wins, "when", Scissors, "plays against", Paper),
        (Player2Wins, "when", Scissors, "plays against", Rock),
        (GameTied, "when", Scissors, "plays against", Scissors),

        (GameTied, "when", Paper, "plays against", Paper),
        (Player1Wins, "when", Paper, "plays against", Rock),
        (Player2Wins, "when", Paper, "plays against", Scissors),

        (Player2Wins, "when", Rock, "plays against", Paper),
        (GameTied, "when", Rock, "plays against", Rock),
        (Player1Wins, "when", Rock, "plays against", Scissors)
      )

      forAll(examples) { (expectedResult, _, player1Gesture, _, player2Gesture) =>
        it(s"expected: $expectedResult when $player1Gesture plays against $player2Gesture") {
          GameEngine.play(player1Gesture, player2Gesture) shouldBe expectedResult
        }
      }
    }
  }

}
