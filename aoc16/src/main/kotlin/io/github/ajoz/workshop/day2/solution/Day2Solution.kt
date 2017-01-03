package io.github.ajoz.workshop.day2.solution

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
        'U' -> Symbol.Up
        'D' -> Symbol.Down
        'L' -> Symbol.Left
        'R' -> Symbol.Right
        else -> null
    }

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

infix fun <T, V> T.with(value: V) = Match(this, value)
infix fun <T, V> Match<T, V>.transitionsTo(state: T) = Transition(this, state)

fun <T, V> transitions(vararg transtions: Transition<T, V>) {

}

fun main(args: Array<String>) {
    val test = """
                |ULL
                |RRDDD
                |LURDL
                |UUUUD
               """.trimMargin()

    transitions(
            SimpleKeypad.Key1 with Symbol.Up transitionsTo SimpleKeypad.Key1,
            SimpleKeypad.Key2 with Symbol.Up transitionsTo SimpleKeypad.Key2,
            SimpleKeypad.Key3 with Symbol.Up transitionsTo SimpleKeypad.Key3,
            SimpleKeypad.Key4 with Symbol.Up transitionsTo SimpleKeypad.Key1,
            SimpleKeypad.Key5 with Symbol.Up transitionsTo SimpleKeypad.Key2,
            SimpleKeypad.Key6 with Symbol.Up transitionsTo SimpleKeypad.Key3,
            SimpleKeypad.Key7 with Symbol.Up transitionsTo SimpleKeypad.Key4,
            SimpleKeypad.Key8 with Symbol.Up transitionsTo SimpleKeypad.Key5,
            SimpleKeypad.Key9 with Symbol.Up transitionsTo SimpleKeypad.Key6
    )


}