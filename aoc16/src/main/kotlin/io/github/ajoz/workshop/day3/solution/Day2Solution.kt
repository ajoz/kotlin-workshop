package io.github.ajoz.workshop.day3.solution

import java.io.File

data class Specification(val a: Int, val b: Int, val c: Int)

val Specification.isTriangle: Boolean
    get() {
        val side1 = a + b > c
        val side2 = a + c > b
        val side3 = b + c > a
        return side1 && side2 && side3
    }

fun toSpecification(list: List<Int>): Specification? = when {
    list.isEmpty() -> null
    list.size != 3 -> null
    else -> Specification(list[0], list[1], list[2])
}

val input = File("src/main/resources/day3/input")

fun main(args: Array<String>) {
    val count = input.readLines()
            .map { line ->
                line.trim()
                        .split(delimiters = " ")
                        .filter(String::isNotBlank)
                        .map(String::toInt)
            }
            .map(::toSpecification)
            .filter { spec -> spec!!.isTriangle }
            .count()

    println(count)
}