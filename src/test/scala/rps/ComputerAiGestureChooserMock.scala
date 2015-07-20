package rps

import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar

trait ComputerAiGestureChooserMock extends MockitoSugar  {

  /**
   * builds a gesture chooser which produces the given gestures
   */
  def computerGestureChooserWhichReturns(gestures: Gesture*): ComputerAiGestureChooser = {
    val aiGestureChooser = mock[ComputerAiGestureChooser]
    when(aiGestureChooser.nextGesture()).thenReturn(gestures.head, gestures.tail: _*)
    aiGestureChooser
  }

}
