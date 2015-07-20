package rps

import scala.collection.immutable.SortedMap

case object Gesture {
  val values: List[Gesture] = List(Rock, Paper, Scissors)

  lazy val numberToGestureName: SortedMap[Int, String] = {
    val elements = values
      .zipWithIndex
      .map { case (gesture, index) => (index + 1, gesture.toString) }
      .sortBy(_._1)

    SortedMap(elements: _*)
  }

  def findByNumber(number: Int): Gesture = values(number - 1)
}

/**
 * all gestures the game supports. extend this trait with another case object
 * to support more weapons. The compiler will tell you which implementations are missing
 */
sealed trait Gesture {
  def winsAgainst(opponentGesture: Gesture): GestureVsGestureResult
}

case object Rock extends Gesture {
  def winsAgainst(opponentGesture: Gesture): GestureVsGestureResult = opponentGesture match {
    case _: Rock.type => GestureTied
    case _: Scissors.type => Wins
    case _: Paper.type => Loses
  }
}

case object Paper extends Gesture {
  def winsAgainst(opponentGesture: Gesture): GestureVsGestureResult = opponentGesture match {
    case _: Rock.type => Wins
    case _: Scissors.type => Loses
    case _: Paper.type => GestureTied
  }
}

case object Scissors extends Gesture {
  def winsAgainst(opponentGesture: Gesture): GestureVsGestureResult = opponentGesture match {
    case _: Rock.type => Loses
    case _: Scissors.type => GestureTied
    case _: Paper.type => Wins
  }
}

sealed trait GestureVsGestureResult

case object Wins extends GestureVsGestureResult

case object Loses extends GestureVsGestureResult

case object GestureTied extends GestureVsGestureResult
