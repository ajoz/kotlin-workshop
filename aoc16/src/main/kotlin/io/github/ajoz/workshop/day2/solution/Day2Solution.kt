package io.github.ajoz.workshop.day2.solution

/**
 * You arrive at Easter Bunny Headquarters under cover of darkness. However, you left in such a rush that you forgot to
 * use the bathroom! Fancy office buildings like this one usually have keypad locks on their bathrooms, so you search
 * the front desk for the code.
 *
 * "In order to improve security," the document you find says, "bathroom codes will no longer be written down. Instead,
 * please memorize and follow the procedure below to access the bathrooms."
 *
 * The document goes on to explain that each button to be pressed can be found by starting on the previous button and
 * moving to adjacent buttons on the keypad: U moves up, D moves down, L moves left, and R moves right. Each line of
 * instructions corresponds to one button, starting at the previous button (or, for the first line, the "5" button);
 * press whatever button you're on at the end of each line. If a move doesn't lead to a button, ignore it.
 *
 * You can't hold it much longer, so you decide to figure out the code as you walk to the bathroom. You picture a keypad
 * like this:
 *
 * 1 2 3
 * 4 5 6
 * 7 8 9
 *
 * Suppose your instructions are:
 *
 * ULL
 * RRDDD
 * LURDL
 * UUUUD
 *
 * - You start at "5" and move up (to "2"), left (to "1"), and left (you can't, and stay on "1"), so the first button is
 * 1.
 * - Starting from the previous button ("1"), you move right twice (to "3") and then down three times (stopping at "9"
 * after two moves and ignoring the third), ending up with 9.
 * - Continuing from "9", you move left, up, right, down, and left, ending with 8.
 * - Finally, you move up four times (stopping at "2"), then down once, ending with 5.
 *
 * So, in this example, the bathroom code is 1985.
 *
 * Your puzzle input is the instructions from the document you found at the front desk. What is the bathroom code?
 */

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