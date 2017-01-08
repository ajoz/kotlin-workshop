package io.github.ajoz.workshop.day2.solution

import io.github.ajoz.workshop.fsm.FSM
import io.github.ajoz.workshop.fsm.State
import io.github.ajoz.workshop.fsm.Symbol
import io.github.ajoz.workshop.fsm.Transitions.Companion.transitions
import io.github.ajoz.workshop.sequences.scan
import io.github.ajoz.workshop.strings.head
import io.github.ajoz.workshop.strings.tail

/**
 * We can see the solution to this puzzle in terms of a deterministic FSM (finite state machine). An input alphabet of
 * a FSM consists of a set of symbols, states change to next states due to a transition function. Each function can be
 * easily expressed just as a map of some value to another value. We can define FSM transition function in terms of a
 * mapping (State, Symbol) -> State. This can be expressed as a Map<Pair<State, Symbol>, State>. We can now prepare
 * a transition function with a DSL made just for this puzzle.
 *
 * Our set of possible states is subset of Int: 1, 2, 3, 4, 5, 6, 7, 8, 9
 * Our set of possible symbols is subset of Char: U, R, L, D
 *
 * We want to have a DSL expressive enough to allow writing like:
 *
 * 1 or 2 or 3 cycles after U
 *
 * In the fsm package you can find a simple implementation of such DSL.
 */

val Char.symbol: Symbol<Char>
    get() = Symbol(this)

tailrec fun accept(fsm: FSM<Int, Char>, instruction: String): FSM<Int, Char> = when {
    instruction.isEmpty() -> fsm
    else -> accept(fsm.accept(instruction.head.symbol), instruction.tail)
}

val day2part1Transition = transitions(
        (State(1) or State(2) or State(3)) cyclesAfter Symbol('U'),
        (State(1) or State(4) or State(7)) cyclesAfter Symbol('L'),
        (State(3) or State(6) or State(9)) cyclesAfter Symbol('R'),
        (State(7) or State(8) or State(9)) cyclesAfter Symbol('D'),
        State(1) after Symbol('R') transitionsTo State(2),
        State(1) after Symbol('D') transitionsTo State(4),
        State(2) after Symbol('L') transitionsTo State(1),
        State(2) after Symbol('R') transitionsTo State(3),
        State(2) after Symbol('D') transitionsTo State(5),
        State(3) after Symbol('L') transitionsTo State(2),
        State(3) after Symbol('D') transitionsTo State(6),
        State(4) after Symbol('U') transitionsTo State(1),
        State(4) after Symbol('R') transitionsTo State(5),
        State(4) after Symbol('D') transitionsTo State(7),
        State(5) after Symbol('U') transitionsTo State(2),
        State(5) after Symbol('L') transitionsTo State(4),
        State(5) after Symbol('R') transitionsTo State(6),
        State(5) after Symbol('D') transitionsTo State(8),
        State(6) after Symbol('U') transitionsTo State(3),
        State(6) after Symbol('L') transitionsTo State(5),
        State(6) after Symbol('D') transitionsTo State(9),
        State(7) after Symbol('U') transitionsTo State(4),
        State(7) after Symbol('R') transitionsTo State(8),
        State(8) after Symbol('U') transitionsTo State(5),
        State(8) after Symbol('L') transitionsTo State(7),
        State(8) after Symbol('R') transitionsTo State(9),
        State(9) after Symbol('U') transitionsTo State(6),
        State(9) after Symbol('L') transitionsTo State(8)
)

fun getPart1BathroomAccessCode(instructions: String): Int {
    val fsm = FSM(State(5), day2part1Transition)
    return instructions.splitToSequence(delimiters = '\n')
            .scan(fsm, ::accept)
            .map { fsm -> fsm.state.value }
            .fold("") {
                str, value ->
                str + value
            }.toInt()
}