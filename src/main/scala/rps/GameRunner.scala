package rps

import scala.util.Random

/**
 * actually runs the variations (player vs player and computer vs computer) of the game by using
 * the GameEngine
 */
case class GameRunner(computerAiGestureChooser: ComputerAiGestureChooser) {
  def playComputerVsComputer(): (GameResult, Gesture, Gesture) = {
    val player1Gesture = computerAiGestureChooser.nextGesture()
    val player2Gesture = computerAiGestureChooser.nextGesture()

    (GameEngine.play(player1Gesture, player2Gesture), player1Gesture, player2Gesture)
  }

  def playPlayerVsComputer(player1Gesture: Gesture): (GameResult, Gesture, Gesture) = {
    val player2Gesture = computerAiGestureChooser.nextGesture()

    (GameEngine.play(player1Gesture, player2Gesture), player1Gesture, player2Gesture)
  }
}

case class ComputerAiGestureChooser(random: Random) {
  def nextGesture(): Gesture = random.shuffle(Gesture.values).head
}