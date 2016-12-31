package io.github.ajoz.workshop.day1.solution

import io.github.ajoz.workshop.sequences.scan

/**
 * Describes all possible directions that we can take in a taxi cab geometry.
 *
 * @property numOfBlocks number of blocks we need to move in the given direction.
 */
private sealed class Direction(val numOfBlocks: Int) {
    class North(numOfBlocks: Int) : Direction(numOfBlocks)
    class East(numOfBlocks: Int) : Direction(numOfBlocks)
    class West(numOfBlocks: Int) : Direction(numOfBlocks)
    class South(numOfBlocks: Int) : Direction(numOfBlocks)
}

/**
 * Allows to generate a next direction from the given instruction. In the puzzle it is stated that we can have only two
 * types of instructions: 'R' or 'L'. There is no theoretical limit to the size of the instruction or if those
 * instructions can be incorrect (wrong letter or more letters, no number of blocks, negative number of blocks etc).
 * After looking at the input data given, I assumed:
 * - instruction has only one letter {'R', 'L'}
 * - instruction has only a positive number of blocks
 * - number of blocks will never reach maximum of integer ;)
 *
 * To calculate next direction we need previous direction and the single instruction. For example let's say that the
 * current direction is East and instruction is 'L', then if we are pointing east and we look on the left our next
 * direction will be North.
 *
 * I've implemented it as an extension function to the Direction type to allow shorter notation in lambdas.
 */
private fun Direction.next(instruction: String): Direction {
    val turn = instruction.substring(0, 1)
    val numOfBlocks = instruction.substring(1).toInt()

    return when (turn) {
        "R" -> when (this) {
            is Direction.North -> Direction.East(numOfBlocks)
            is Direction.East -> Direction.South(numOfBlocks)
            is Direction.West -> Direction.North(numOfBlocks)
            is Direction.South -> Direction.West(numOfBlocks)
        }
        "L" -> when (this) {
            is Direction.North -> Direction.West(numOfBlocks)
            is Direction.East -> Direction.North(numOfBlocks)
            is Direction.West -> Direction.South(numOfBlocks)
            is Direction.South -> Direction.East(numOfBlocks)
        }
        else -> throw IllegalArgumentException("Unknown direction for instruction: $instruction")
    }
}

/**
 * Class represents calculated coordinates of each step of the route to Bunny HQ.
 */
private data class Coordinates(val x: Int, val y: Int)

/**
 * Allows to generate coordinates of the next step of the route. As we can go only in any of the four directions: North,
 * East, West or South then Coordinates of next step will either differ in the x or y -axis to the previous one.
 *
 * I used a copy method that helps to create a new object based on the given one (also change only set of its
 * properties)
 */
private fun Coordinates.next(forADirection: Direction) = when (forADirection) {
    is Direction.North -> this.copy(y = this.y + forADirection.numOfBlocks)
    is Direction.East -> this.copy(x = this.x + forADirection.numOfBlocks)
    is Direction.West -> this.copy(x = this.x - forADirection.numOfBlocks)
    is Direction.South -> this.copy(y = this.y - forADirection.numOfBlocks)
}

/**
 * Produces a sequence of [Coordinates] from the given initial [Direction], initial [Coordinates] and instructions
 * represented as a single String. Please note that kotlin doesn't have a [scan] function defined for Sequence<T> it was
 * created to help make the code more concise (the name scan was taken from RxJava where there exists an operator that
 * works in similar way)
 */
private fun getCoordinates(direction: Direction,
                           coordinates: Coordinates,
                           instructions: String): Sequence<Coordinates> = instructions
        .splitToSequence(delimiters = ",", ignoreCase = true)
        .map(String::trim)
        .scan(direction, Direction::next)
        .scan(coordinates, Coordinates::next)

/**
 * Calculates the shortest path from the start coordinates (0, 0) to the coordinates calculated by processing the given
 * instruction.
 */
fun getShortestPathToDestinationLength(instructions: String): Int {
    val startDirection = Direction.North(0)
    val startCoords = Coordinates(0, 0)
    val stopCoords = getCoordinates(startDirection, startCoords, instructions).last()
    return Math.abs(startCoords.x - stopCoords.x) + Math.abs(startCoords.y - stopCoords.y)
}
