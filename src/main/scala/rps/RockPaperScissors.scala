package rps

import scala.util.Random

object RockPaperScissors extends App {

  private val aiGestureChooser = ComputerAiGestureChooser(new Random())
  private val gameRunner = GameRunner(aiGestureChooser)
  UI(gameRunner).play()
  
}
