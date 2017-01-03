package io.github.ajoz.workshop.day2.solution

import io.github.ajoz.workshop.day2.solution.SimpleKeypad.*
import io.github.ajoz.workshop.day2.solution.Symbol.Up

/**
 * We can see the solution to this puzzle in terms of a deterministic FSM (finite state machine). An input alphabet of
 * a FSM consists of a set of symbols:
 */
enum class Symbol {
    Up,
    Left,
    Right,
    Down;
}

val Char.symbol: Symbol?
    get() = when (this) {
        'U' -> Up
        'D' -> Symbol.Down
        'L' -> Symbol.Left
        'R' -> Symbol.Right
        else -> null
    }

/**
 * Set of State:
 */
enum class SimpleKeypad(val value: Char) {
    Key1('1'),
    Key2('2'),
    Key3('3'),
    Key4('4'),
    Key5('5'),
    Key6('6'),
    Key7('7'),
    Key8('8'),
    Key9('9')
}

data class Match<out T, out V>(val state: T, val symbol: V)
data class Transition<out T, out V>(val match: Match<T, V>, val state: T)

infix fun <T, V> T.after(value: V) = Match(this, value)
infix fun <T, V> Match<T, V>.to(state: T) = Transition(this, state)

fun <T, V> transitions(vararg transtions: Transition<T, V>) {

}

class Transitions<T, V> {
    val children = arrayListOf<Transition<T, V>>()
}

fun <T, V> transitions(init: Transitions<T, V>.() -> Unit) {

}

fun main(args: Array<String>) {
    val test = """
                |ULL
                |RRDDD
                |LURDL
                |UUUUD
               """.trimMargin()

    transitions(
            Key1 after Up to Key1,
            Key2 after Up to Key2,
            Key3 after Up to Key3,
            Key4 after Up to Key1,
            Key5 after Up to Key2,
            Key6 after Up to Key3,
            Key7 after Up to Key4,
            Key8 after Up to Key5,
            Key9 after Up to Key6
    )


}