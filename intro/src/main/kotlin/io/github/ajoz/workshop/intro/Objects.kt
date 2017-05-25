package io.github.ajoz.workshop.intro

// compiles down to a final class with static fields and methods
object JUG {
    val value = 42

    fun test() {
        println("JUG $value")
    }
}

class Kotlin {
    companion object JUG {
        fun testCompanion() {
            println("From companion")
        }
    }
}