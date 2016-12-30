package io.github.ajoz.workshop.day1

import io.github.ajoz.workshop.day1.Direction.*

/**
Day 1: No Time for a Taxicab

You're airdropped near Easter Bunny Headquarters in a city somewhere. "Near", unfortunately, is as close as you can get
- the instructions on the Easter Bunny Recruiting Document the Elves intercepted start here, and nobody had time to work
them out further.

The Document indicates that you should start at the given coordinates (where you just landed) and face North. Then,
follow the provided sequence: either turn left (L) or right (R) 90 degrees, then walk forward the given number of
blocks, ending at a new intersection.

There's no time to follow such ridiculous instructions on foot, though, so you take a moment and work out the
destination. Given that you can only walk on the street grid of the city, how far is the shortest path to the
destination?

http://adventofcode.com/2016/day/1
 */


sealed class Direction(val numOfBlocks: Int) {
    class North(numOfBlocks: Int) : Direction(numOfBlocks)
    class East(numOfBlocks: Int) : Direction(numOfBlocks)
    class West(numOfBlocks: Int) : Direction(numOfBlocks)
    class South(numOfBlocks: Int) : Direction(numOfBlocks)
}

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

data class Coordinates(val x: Int, val y: Int)

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

fun main(args: Array<String>) {
    println(getShortestPathToDestinationLength("R2, L3"))
    println(getShortestPathToDestinationLength("R2, R2, R2"))
    println(getShortestPathToDestinationLength("R5, L5, R5, R3"))
    println(getShortestPathToDestinationLength("R2, R2, R2, R2"))
    println(getShortestPathToDestinationLength("R8, R4, R4, R8"))

    println(getShortestPathToDestinationLength("""R3, L5, R2, L1, L2, R5, L2, R2, L2, L2,
 L1, R2, L2, R4, R4, R1, L2, L3, R3, L1, R2, L2, L4, R4, R5, L3, R3, L3, L3, R4, R5,
 L3, R3, L5, L1, L2, R2, L1, R3, R1, L1, R187, L1, R2, R47, L5, L1, L2, R4, R3, L3,
 R3, R4, R1, R3, L1, L4, L1, R2, L1, R4, R5, L1, R77, L5, L4, R3, L2, R4, R5, R5, L2,
 L2, R2, R5, L2, R194, R5, L2, R4, L5, L4, L2, R5, L3, L2, L5, R5, R2, L3, R3, R1, L4,
 R2, L1, R5, L1, R5, L1, L1, R3, L1, R5, R2, R5, R5, L4, L5, L5, L5, R3, L2, L5, L4, R3,
 R1, R1, R4, L2, L4, R5, R5, R4, L2, L2, R5, R5, L5, L2, R4, R4, L4, R1, L3, R1, L1, L1, L1, L4, R5, R4,
 L4, L4, R5, R3, L2, L2, R3, R1, R4, L3, R1, L4, R3, L3, L2, R2, R2, R2, L1, L4, R3, R2, R2, L3, R2, L3,
 L2, R4, L2, R3, L4, R5, R4, R1, R5, R3"""))
    
    println(getShortestPathToDestinationLength("""L2, L3, L3, L4, R1, R2, L3, R3, R3, L1, L3, R2, R3, L3, R4, R3, R3,
 L1, L4, R4, L2, R5, R1, L5, R1, R3, L5, R2, L2, R2, R1, L1, L3, L3, R4, R5, R4, L1, L189, L2, R2, L5, R5, R45, L3,
 R4, R77, L1, R1, R194, R2, L5, L3, L2, L1, R5, L3, L3, L5, L5, L5, R2, L1, L2, L3, R2, R5, R4, L2, R3, R5, L2, L2,
 R3, L3, L2, L1, L3, R5, R4, R3, R2, L1, R2, L5, R4, L5, L4, R4, L2, R5, L3, L2, R4, L1, L2, R2, R3, L2, L5, R1, R1,
 R3, R4, R1, R2, R4, R5, L3, L5, L3, L3, R5, R4, R1, L3, R1, L3, R3, R3, R3, L1, R3, R4, L5, L3, L1, L5, L4, R4, R1,
 L4, R3, R3, R5, R4, R3, R3, L1, L2, R1, L4, L4, L3, L4, L3, L5, R2, R4, L2"""))
}