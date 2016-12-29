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
}

sealed class Instruction(val numOfBlocks: Int) {
    class Right(numOfBlocks: Int) : Instruction(numOfBlocks)
    class Left(numOfBlocks: Int) : Instruction(numOfBlocks)
}

data class Coordinates(val x: Int, val y: Int)

data class PartialSolution(val direction: Direction, val coordinates: Coordinates)

fun String.toInstruction() = when (this.substring(0, 1)) {
    "R" -> Right(this.substring(1).toInt())
    "L" -> Left(this.substring(1).toInt())
    else -> throw IllegalArgumentException("Unknown instruction type: $this")
}

fun Direction.next(forAn: Instruction) = when {
    this is North && forAn is Right -> East()
    this is North && forAn is Left -> West()
    this is East && forAn is Right -> South()
    this is East && forAn is Left -> North()
    this is West && forAn is Right -> North()
    this is West && forAn is Left -> South()
    this is South && forAn is Right -> West()
    this is South && forAn is Left -> East()
    else -> throw IllegalArgumentException("Unknown direction: $this and instruction: $forAn combination")
}

fun nextCoordinates(coordinates: Coordinates, direction: Direction, instruction: Instruction) = when (direction) {
    is North -> coordinates.copy(y = coordinates.y + instruction.numOfBlocks)
    is East -> coordinates.copy(x = coordinates.x + instruction.numOfBlocks)
    is West -> coordinates.copy(x = coordinates.x - instruction.numOfBlocks)
    is South -> coordinates.copy(y = coordinates.y - instruction.numOfBlocks)
}

fun getShortestPathToDestinationLength(instructions: String): Int {
    val start = Coordinates(0, 0)
    val stop = instructions
            .splitToSequence(delimiters = ",", ignoreCase = true)
            .map(String::trim)
            .map(String::toInstruction)
            .fold(PartialSolution(North(), start), {
                solution, instruction ->
                val nextDirection = solution.direction.next(forAn = instruction)
                val partial = PartialSolution(nextDirection, nextCoordinates(solution.coordinates, nextDirection, instruction))
                println(nextCoordinates(solution.coordinates, nextDirection, instruction))
                partial
            }).coordinates

    return Math.abs(start.x - stop.x) + Math.abs(start.y - stop.y)
}

fun main(args: Array<String>) {
//    println(getShortestPathToDestinationLength("R2, L3"))
//    println(getShortestPathToDestinationLength("R2, R2, R2"))
//    println(getShortestPathToDestinationLength("R5, L5, R5, R3"))
//    println(getShortestPathToDestinationLength("R2, R2, R2, R2"))
//    println(getShortestPathToDestinationLength("R8, R4, R4, R8"))
    println(getShortestPathToDestinationLength("L2, L3, L3, L4, R1, R2, L3, R3, R3, L1, L3, R2, R3, L3, R4, R3, R3, L1, L4, R4, L2, R5, R1, L5, R1, R3, L5, R2, L2, R2, R1, L1, L3, L3, R4, R5, R4, L1, L189, L2, R2, L5, R5, R45, L3, R4, R77, L1, R1, R194, R2, L5, L3, L2, L1, R5, L3, L3, L5, L5, L5, R2, L1, L2, L3, R2, R5, R4, L2, R3, R5, L2, L2, R3, L3, L2, L1, L3, R5, R4, R3, R2, L1, R2, L5, R4, L5, L4, R4, L2, R5, L3, L2, R4, L1, L2, R2, R3, L2, L5, R1, R1, R3, R4, R1, R2, R4, R5, L3, L5, L3, L3, R5, R4, R1, L3, R1, L3, R3, R3, R3, L1, R3, R4, L5, L3, L1, L5, L4, R4, R1, L4, R3, R3, R5, R4, R3, R3, L1, L2, R1, L4, L4, L3, L4, L3, L5, R2, R4, L2"))
}