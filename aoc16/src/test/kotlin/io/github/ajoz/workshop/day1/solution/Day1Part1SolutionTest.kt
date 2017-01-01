package io.github.ajoz.workshop.day1.solution

import io.kotlintest.specs.WordSpec

class Day1Part1SolutionTest : WordSpec() {
    init {
        "The shortest path to Bunny HQ" should {
            "be 0 for a short all RIGHT (R) round route from start to finish" {
                getShortestPathLengthToDestination("R2, R2, R2, R2") shouldBe 0
            }

            "be 0 for a short all LEFT (L) round route from start to finish" {
                getShortestPathLengthToDestination("L2, L2, L2, L2") shouldBe 0
            }

            "be 0 for a long all RIGHT (R) round route from start to finish" {
                getShortestPathLengthToDestination("R10000, R10000, R10000, R10000") shouldBe 0
            }

            "be 0 for a long all LEFT (L) round route from start to finish" {
                getShortestPathLengthToDestination("L10000, L10000, L10000, L10000") shouldBe 0
            }

            "be 0 for a short route from start to finish" {
                getShortestPathLengthToDestination("L3, L2, R1, L1, L3, L1, R1, L2") shouldBe 0
            }

            "be 5 for a AOC'16 Example: R2, L3" {
                getShortestPathLengthToDestination("R2, L3") shouldBe 5
            }

            "be 2 for AOC'16 Example: R2, R2, R2" {
                getShortestPathLengthToDestination("R2, R2, R2") shouldBe 2
            }

            "be 12 for AOC'16 Example: R5, L5, R5, R3" {
                getShortestPathLengthToDestination("R5, L5, R5, R3") shouldBe 12
            }

            "be 8 for AOC'16 Example: R8, R4, R4, R8" {
                getShortestPathLengthToDestination("R8, R4, R4, R8") shouldBe 8
            }

            "be 243 for a AOC'16 contest puzzle input #1" {
                getShortestPathLengthToDestination("""R3, L5, R2, L1, L2, R5, L2, R2, L2, L2, L1, R2, L2, R4, R4, R1,
                L2, L3, R3, L1, R2, L2, L4, R4, R5, L3, R3, L3, L3, R4, R5, L3, R3, L5, L1, L2, R2, L1, R3, R1, L1,
                R187, L1, R2, R47, L5, L1, L2, R4, R3, L3, R3, R4, R1, R3, L1, L4, L1, R2, L1, R4, R5, L1, R77, L5, L4,
                R3, L2, R4, R5, R5, L2, L2, R2, R5, L2, R194, R5, L2, R4, L5, L4, L2, R5, L3, L2, L5, R5, R2, L3, R3,
                R1, L4, R2, L1, R5, L1, R5, L1, L1, R3, L1, R5, R2, R5, R5, L4, L5, L5, L5, R3, L2, L5, L4, R3, R1, R1,
                R4, L2, L4, R5, R5, R4, L2, L2, R5, R5, L5, L2, R4, R4, L4, R1, L3, R1, L1, L1, L1, L4, R5, R4, L4, L4,
                R5, R3, L2, L2, R3, R1, R4, L3, R1, L4, R3, L3, L2, R2, R2, R2, L1, L4, R3, R2, R2, L3, R2, L3, L2, R4,
                L2, R3, L4, R5, R4, R1, R5, R3""") shouldBe 243
            }

            "be 301 for AOC'16 contest puzzle input #2" {
                getShortestPathLengthToDestination("""L2, L3, L3, L4, R1, R2, L3, R3, R3, L1, L3, R2, R3, L3, R4, R3,
                R3, L1, L4, R4, L2, R5, R1, L5, R1, R3, L5, R2, L2, R2, R1, L1, L3, L3, R4, R5, R4, L1, L189, L2, R2,
                L5, R5, R45, L3, R4, R77, L1, R1, R194, R2, L5, L3, L2, L1, R5, L3, L3, L5, L5, L5, R2, L1, L2, L3, R2,
                R5, R4, L2, R3, R5, L2, L2, R3, L3, L2, L1, L3, R5, R4, R3, R2, L1, R2, L5, R4, L5, L4, R4, L2, R5, L3,
                L2, R4, L1, L2, R2, R3, L2, L5, R1, R1, R3, R4, R1, R2, R4, R5, L3, L5, L3, L3, R5, R4, R1, L3, R1, L3,
                R3, R3, R3, L1, R3, R4, L5, L3, L1, L5, L4, R4, R1, L4, R3, R3, R5, R4, R3, R3, L1, L2, R1, L4, L4, L3,
                L4, L3, L5, R2, R4, L2""") shouldBe 301
            }
        }
    }
}

