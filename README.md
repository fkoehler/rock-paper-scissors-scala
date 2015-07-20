# Rock Paper Scissors

This solution implements rock, paper, scissors as described in https://en.wikipedia.org/wiki/Rock-paper-scissors
It is implemented in Scala with SBT as the build tool. External libraries are only used for testing purposes.

## Run/Build Instructions

This is a common SBT project (http://www.scala-sbt.org/). Just type `sbt run` to execute the game and play it via
the console.

Use `sbt test` to run all tests which validate the functionality software.

## Thoughts about the design

I generally tend to write a simple solution first and let me guide by the requirements what else is needed. I did
built in extensibility regarding the gestures as this was a sort of requirement but I only do this if I really know
what is coming. Beside of that I stick to the "fool me once" rule.
 
### UI

For the UI the Console was chosen. It is a simple user interface which is tested end 2 end in the UiSpec.
I would like to point out how I implemented the retryUntilValidValue function in a purely functional way.
The function executes the given user function in a continuous stream until the desired input is available.
 
### Code is the documentation

I generally stick to that rule. When there are non obvious things in the code or context information that is really required
I add that to the source code

### DDD

The domain is rather simple in this case but I still tried to go with a domain driven design approach, e.g. the Gesture
trait: Every concrete `Gesture` implements how it relates to another `Gesture` according to the rules of the game. The
code lives where I feel it should live and not in a special class.

In the domain we have a `GameRunner` which knows ow a game is played, i.e. it needs the moves from the computer or 
the player and it uses the `GameEngine` to calculate a result for a right between gestures. The `UI` uses the `GameRunner`
to collect input and produce output. You could play the game without a UI in memory.

### Dependency injection

I think it is always good to do that especially for testing. You can see in the Specs how the depndencies are wired 
together or in the `RockPaperScissors` object.

### Extensibility

It is easy to extend the game with more gestures like with lizard and spock (http://en.wikipedia.org/wiki/Rock-paper-scissors-lizard-Spock).
One would just need to implement the new case object for the the. The compiler will tell what else needs to be implemented
except for the Gesture.values member for that one could use a library like Enumeratum (https://github.com/lloydmeta/enumeratum)

## Test strategy

The code is end2end test in the `UiSpec` class. There are unit tests for all other classes.
I think not more than that is needed.

I used TDD almost completely. Generally I write tests with the code, sometimes it is just easier to not TDD for me
but most of the time I stick to that rule.