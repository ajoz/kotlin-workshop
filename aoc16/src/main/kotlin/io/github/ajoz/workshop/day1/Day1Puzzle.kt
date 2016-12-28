package io.github.ajoz.workshop.day1

import io.github.ajoz.workshop.day1.Direction.*
import io.github.ajoz.workshop.day1.Instruction.Left
import io.github.ajoz.workshop.day1.Instruction.Right

/**
Day 1: No Time for a Taxicab

You're airdropped near Easter Bunny Headquarters in a city somewhere. "Near", unfortunately, is as close as you can get
- the instructions on the Easter Bunny Recruiting Document the Elves intercepted start here, and nobody had time to
work them out further.

The Document indicates that you should start at the given coordinates (where you just landed) and face North. Then,
follow the provided sequence: either turn left (L) or right (R) 90 degrees, then walk forward the given number of
blocks, ending at a new intersection.

There's no time to follow such ridiculous instructions on foot, though, so you take a moment and work out the
destination. Given that you can only walk on the street grid of the city, how far is the shortest path to the
destination?

http://adventofcode.com/2016/day/1
 */

sealed class Direction {
    class North : Direction()
    class East : Direction()
    class West : Direction()
    class South : Direction()

    override fun toString() = when (this) {
        is North -> "North"
        is East -> "East"
        is West -> "West"
        is South -> "South"
    }
}

sealed class Instruction(val numOfBlocks: Int) {
    class Right(numOfBlocks: Int) : Instruction(numOfBlocks)
    class Left(numOfBlocks: Int) : Instruction(numOfBlocks)

    override fun toString() = when (this) {
        is Right -> "Right(${this.numOfBlocks})"
        is Left -> "Left(${this.numOfBlocks})"
    }
}

fun String.toInstruction() = when (this.substring(0, 1)) {
    "R" -> Right(this.substring(1).toInt())
    "L" -> Left(this.substring(1).toInt())
    else -> throw IllegalArgumentException("Unknown instruction type: $this")
}

fun getDirectionForInstruction(direction: Direction, instruction: Instruction): Direction {
    return when {
        direction is North && instruction is Right -> East()
        direction is North && instruction is Left -> West()
        direction is East && instruction is Right -> South()
        direction is East && instruction is Left -> North()
        direction is West && instruction is Right -> North()
        direction is West && instruction is Left -> South()
        direction is South && instruction is Right -> East()
        direction is South && instruction is Left -> West()
        else -> North()
    }
}

fun getOperator(direction: Direction): (Int, Int) -> Int {
    return when (direction) {
        is North -> fun(x: Int, y: Int) = x + y
        is East -> fun(x: Int, y: Int) = x + y
        is West -> fun(x: Int, y: Int) = x - y
        is South -> fun(x: Int, y: Int) = x - y
    }
}

fun getShortestPathToDestinationLength(instruction: String): Int {
    return instruction.splitToSequence(delimiters = ",", ignoreCase = true)
            .map(String::trim)
            .map(String::toInstruction)
            .fold(Pair<Direction, Int>(North(), 0), {
                prev, elem ->
                print(prev.first)
                print("   ")
                print(elem)
                println()
                Pair(getDirectionForInstruction(prev.first, elem),
                        getOperator(prev.first)(prev.second, elem.numOfBlocks))
            }).second
}

fun main(args: Array<String>) {
    println(getShortestPathToDestinationLength("R2, L3"))
//    println(getShortestPathToDestinationLength("R2, R2, R2"))
//    println(getShortestPathToDestinationLength("R5, L5, R5, R3"))
}