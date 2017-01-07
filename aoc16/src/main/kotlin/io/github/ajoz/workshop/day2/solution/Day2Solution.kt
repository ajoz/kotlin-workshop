package io.github.ajoz.workshop.day2.solution

import io.github.ajoz.workshop.fsm.FSM
import io.github.ajoz.workshop.fsm.Match
import io.github.ajoz.workshop.fsm.Match.Companion.matchOf
import io.github.ajoz.workshop.fsm.Symbol
import io.github.ajoz.workshop.fsm.State

/**
 * We can see the solution to this puzzle in terms of a deterministic FSM (finite state machine). An input alphabet of
 * a FSM consists of a set of symbols, states change to next states due to a transition function.
 */


data class Transitions<T, V>(val values: Map<Match<T, V>, State<T>>)

fun <T, V> transitions(vararg transitions: Transitions<T, V>): (State<T>, Symbol<V>) -> State<T> {
    val t = transitions.fold(emptyMap<Match<T, V>, State<T>>()) {
        map, trans ->
        map + trans.values
    }
    return fun(st: State<T>, sy: Symbol<V>): State<T> {
        val state = t[matchOf(st, sy)]
        return state!! //we want this implementation to die if anything bad happens :)
    }
}

val Char.symbol: Symbol<Char>
    get() = Symbol(this)

val String.head: Char
    get() = get(0)

val String.tail: String
    get() = drop(1)

tailrec fun accept(fsm: FSM<Int, Char>, instruction: String): FSM<Int, Char> = when {
    instruction.isEmpty() -> fsm
    else -> accept(fsm.accept(instruction.head.symbol), instruction.tail)
}

fun main(args: Array<String>) {
    val t = transitions(
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

    val fsm = FSM(State(5), t)
    val result = accept(fsm, "ULL")
    println(result.state)
}