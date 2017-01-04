package io.github.ajoz.workshop.day2.solution

import io.github.ajoz.workshop.day2.solution.InstructionSymbol.*
import io.github.ajoz.workshop.day2.solution.SimpleKeypad.*

/**
 * We can see the solution to this puzzle in terms of a deterministic FSM (finite state machine). An input alphabet of
 * a FSM consists of a set of symbols:
 */
enum class InstructionSymbol {
    Up,
    Left,
    Right,
    Down;
}

val Char.symbol: InstructionSymbol?
    get() = when (this) {
        'U' -> Up
        'D' -> Down
        'L' -> Left
        'R' -> Right
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

data class SymbolSet<out T>(val states: List<T>)

infix fun <T> T.or(value: T) = SymbolSet(listOf(this, value))
infix fun <T> SymbolSet<T>.or(value: T) = SymbolSet(states + value)
infix fun <T, V> SymbolSet<T>.cyclesAfter(symbol: V) {

}

infix fun <T, V> T.after(value: V) = Match(this, value)
infix fun <T, V> Match<T, V>.to(state: T) = Transition(this, state)

fun <T, V> transitions(vararg transtions: Transition<T, V>) {

}

fun getSimpleKeypadCode(instructions: String) : Int {

    return 0
}

fun main(args: Array<String>) {

    Key1 or Key2 or Key3 cyclesAfter Up

    transitions(
            //cycles need a better syntax State or State or State cyclesAfter Symbol (harder to implement 4 transitions)
            //or State cyclesAfter Symbol or Symbol or Symbol (easier to implement gives (8 transitions)
            Key1 after Up to Key1,
            Key2 after Up to Key2,
            Key3 after Up to Key3,

            Key1 after Left to Key1,
            Key4 after Left to Key4,
            Key7 after Left to Key7,

            Key3 after Right to Key3,
            Key6 after Right to Key6,
            Key9 after Right to Key9,

            Key7 after Down to Key7,
            Key8 after Down to Key8,
            Key9 after Down to Key9,

            //non cycles:
            Key1 after Right to Key2,
            Key1 after Down to Key4,

            Key2 after Left to Key1,
            Key2 after Right to Key3,
            Key2 after Down to Key5,

            Key3 after Left to Key2,
            Key3 after Down to Key6,

            Key4 after Up to Key1,
            Key4 after Right to Key5,
            Key4 after Down to Key7,

            Key5 after Up to Key2,
            Key5 after Left to Key4,
            Key5 after Right to Key6,
            Key5 after Down to Key8,

            Key6 after Up to Key3,
            Key6 after Left to Key5,
            Key6 after Down to Key9,

            Key7 after Up to Key4,
            Key7 after Right to Key8,

            Key8 after Up to Key5,
            Key8 after Left to Key7,
            Key8 after Right to Key9,

            Key9 after Up to Key6,
            Key9 after Left to Key8
    )

}