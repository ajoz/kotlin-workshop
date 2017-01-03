package io.github.ajoz.workshop.day2.solution

import io.github.ajoz.workshop.sequences.scan

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
sealed class Symbol {
    class Up : Symbol()
    class Left : Symbol()
    class Right : Symbol()
    class Down : Symbol()

    override fun toString() = when (this) {
        is Symbol.Up -> "Up"
        is Symbol.Down -> "Down"
        is Symbol.Left -> "Left"
        is Symbol.Right -> "Right"
    }
}

val Char.symbol: Symbol?
    get() = when (this) {
        'U' -> Symbol.Up()
        'D' -> Symbol.Down()
        'L' -> Symbol.Left()
        'R' -> Symbol.Right()
        else -> null
    }

sealed class Part1State(val value: Char) {
    class Key1 : Part1State('1')
    class Key2 : Part1State('2')
    class Key3 : Part1State('3')
    class Key4 : Part1State('4')
    class Key5 : Part1State('5')
    class Key6 : Part1State('6')
    class Key7 : Part1State('7')
    class Key8 : Part1State('8')
    class Key9 : Part1State('9')
}



fun main(args: Array<String>) {
    val test = """
                |ULL
                |RRDDD
                |LURDL
                |UUUUD
               """.trimMargin()


}