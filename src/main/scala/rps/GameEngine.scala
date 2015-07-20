package rps

object GameEngine {
  def play(player1Gesture: Gesture, player2Gesture: Gesture): GameResult =
    player1Gesture.winsAgainst(player2Gesture) match {
      case _: Wins.type => Player1Wins
      case _: Loses.type => Player2Wins
      case _: GestureTied.type => GameTied
    }
}

sealed trait GameResult

case object Player1Wins extends GameResult {
  override def toString: String = "Player 1 wins the game!"
}

case object Player2Wins extends GameResult {
  override def toString: String = "Player 2 wins the game!"
}

case object GameTied extends GameResult {
  override def toString: String = "The game is tied!"
}
