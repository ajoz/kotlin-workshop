package io.github.ajoz.workshop.day2.solution

/**
 * We can see the solution to this puzzle in terms of a deterministic FSM (finite state machine). An input alphabet of
 * a FSM consists of a set of symbols, states change to next states due to a transition function.
 */

data class State<T>(val value: T) {
    override fun toString() = "State($value)"

    infix fun or(other: State<T>) = setOf(this, other)

    infix fun <V> after(symbol: Symbol<V>) = Match(this, symbol)

    infix fun <V> after(symbols: Set<Symbol<V>>) = symbols.map { Match(this, it) }.toSet()
}

data class Symbol<T>(val value: T) {
    override fun toString() = "Symbol($value)"

    infix fun or(other: Symbol<T>) = setOf(this, other)
}

data class Match<T, V>(val state: State<T>, val symbol: Symbol<V>) {
    override fun toString() = "Match(state=${state.value}, symbol=${symbol.value})"

    infix fun transitionsTo(state: State<T>) = Transitions(mapOf(this to state))
}

data class Transitions<T, V>(val values: Map<Match<T, V>, State<T>>)

infix fun <T> Set<State<T>>.or(other: State<T>) = this + other
infix fun <T> Set<Symbol<T>>.or(other: Symbol<T>) = this + other

infix fun <T, V> Set<State<T>>.cyclesAfter(other: Symbol<V>): Transitions<T, V> {
    val transitions = this
            .map { Match(it, other) }
            .map { Pair(it, it.state) }
            .toMap()
    return Transitions(transitions)
}

infix fun <T, V> Set<State<T>>.cyclesAfter(other: Set<Symbol<V>>): Transitions<T, V> {
    val transitions = other
            .map { symbol -> this.cyclesAfter(symbol) }
            .flatMap { it.values.toList() }
            .toMap()

    return Transitions(transitions)
}

fun <T, V> matchOf(state: T, symbol: V) = Match(State(state), Symbol(symbol))

val Char.symbol: Symbol<Char>
    get() = Symbol(this)


fun main(args: Array<String>) {
    (State(1) or State(2) or State(3)) cyclesAfter Symbol('U')
    (State(1) or State(4) or State(7)) cyclesAfter Symbol('L')
    (State(3) or State(6) or State(9)) cyclesAfter Symbol('R')
    (State(7) or State(8) or State(9)) cyclesAfter Symbol('D')

    State(1) after Symbol('R') transitionsTo State(2)
    State(1) after Symbol('D') transitionsTo State(4)

    State(2) after Symbol('L') transitionsTo State(1)
    State(2) after Symbol('R') transitionsTo State(3)
    State(2) after Symbol('D') transitionsTo State(5)

    State(3) after Symbol('L') transitionsTo State(2)
    State(3) after Symbol('D') transitionsTo State(6)

    State(4) after Symbol('U') transitionsTo State(1)
    State(4) after Symbol('R') transitionsTo State(5)
    State(4) after Symbol('D') transitionsTo State(7)

    State(5) after Symbol('U') transitionsTo State(2)
    State(5) after Symbol('L') transitionsTo State(4)
    State(5) after Symbol('R') transitionsTo State(6)
    State(5) after Symbol('D') transitionsTo State(8)

    State(6) after Symbol('U') transitionsTo State(3)
    State(6) after Symbol('L') transitionsTo State(5)
    State(6) after Symbol('D') transitionsTo State(9)

    State(7) after Symbol('U') transitionsTo State(4)
    State(7) after Symbol('R') transitionsTo State(8)

    State(8) after Symbol('U') transitionsTo State(5)
    State(8) after Symbol('L') transitionsTo State(7)
    State(8) after Symbol('R') transitionsTo State(9)

    State(9) after Symbol('U') transitionsTo State(6)
    State(9) after Symbol('L') transitionsTo State(8)
}
