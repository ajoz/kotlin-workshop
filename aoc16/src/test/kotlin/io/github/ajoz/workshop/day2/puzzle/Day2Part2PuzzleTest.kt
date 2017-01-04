package io.github.ajoz.workshop.day2.puzzle

import io.kotlintest.specs.WordSpec

class Day2Part2PuzzleTest : WordSpec() {
    init {
        "Code to the toilet in the Bunny HQ" should {
            "be 5DB3 for the given short instructions" {
                val instructions = """
                |ULL
                |RRDDD
                |LURDL
                |UUUUD""".trimMargin()

                getDay2Part2Solution(instructions) shouldBe "5DB3"
            }
        }
    }
}