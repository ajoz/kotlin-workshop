package io.github.ajoz.workshop.day1

import io.github.ajoz.workshop.day1.Direction.*

/**
 *
 */
sealed class Direction(val numOfBlocks: Int) {
    class North(numOfBlocks: Int) : Direction(numOfBlocks)
    class East(numOfBlocks: Int) : Direction(numOfBlocks)
    class West(numOfBlocks: Int) : Direction(numOfBlocks)
    class South(numOfBlocks: Int) : Direction(numOfBlocks)
}

/**
 *
 */
fun Direction.next(instruction: String): Direction {
    val turn = instruction.substring(0, 1)
    val numOfBlocks = instruction.substring(1).toInt()

    return when (turn) {
        "R" -> when (this) {
            is North -> East(numOfBlocks)
            is East -> South(numOfBlocks)
            is West -> North(numOfBlocks)
            is South -> West(numOfBlocks)
        }
        "L" -> when (this) {
            is North -> West(numOfBlocks)
            is East -> North(numOfBlocks)
            is West -> South(numOfBlocks)
            is South -> East(numOfBlocks)
        }
        else -> throw IllegalArgumentException("Unknown direction for instruction: $instruction")
    }
}

/**
 *
 */
data class Coordinates(val x: Int, val y: Int)

/**
 *
 */
fun Coordinates.next(forADirection: Direction) = when (forADirection) {
    is North -> this.copy(y = this.y + forADirection.numOfBlocks)
    is East -> this.copy(x = this.x + forADirection.numOfBlocks)
    is West -> this.copy(x = this.x - forADirection.numOfBlocks)
    is South -> this.copy(y = this.y - forADirection.numOfBlocks)
}

/**
 *
 */
fun <T, R> Sequence<T>.slide(initial: R, transform: (R, T) -> R): Sequence<R> {
    return TransformingSlideSequence(this, initial, transform)
}

/**
 *
 */
class TransformingSlideSequence<T, R>
constructor(private val sequence: Sequence<T>,
            private val initial: R,
            private val transformer: (R, T) -> R) : Sequence<R> {
    override fun iterator(): Iterator<R> = object : Iterator<R> {
        val iterator = sequence.iterator()
        var previous = initial
        override fun next(): R {
            val mapped = transformer(previous, iterator.next())
            previous = mapped
            return mapped
        }

        override fun hasNext(): Boolean {
            return iterator.hasNext()
        }
    }
}

/**
 *
 */
fun getCoordinates(direction: Direction,
                   coordinates: Coordinates,
                   instructions: String): Sequence<Coordinates> = instructions
        .splitToSequence(delimiters = ",", ignoreCase = true)
        .map(String::trim)
        .slide(direction, Direction::next)
        .slide(coordinates, Coordinates::next)

/**
 *
 */
fun getShortestPathToDestinationLength(instructions: String): Int {
    val start = Coordinates(0, 0)
    val stop = getCoordinates(North(0), start, instructions).last()
    return Math.abs(start.x - stop.x) + Math.abs(start.y - stop.y)
}
