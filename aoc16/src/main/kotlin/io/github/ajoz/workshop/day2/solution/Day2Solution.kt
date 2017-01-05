package io.github.ajoz.workshop.day2.solution

/**
 * We can see the solution to this puzzle in terms of a deterministic FSM (finite state machine). An input alphabet of
 * a FSM consists of a set of symbols, states change to next states due to a transition function.
 */

data class State<T>(val value: T) {
    override fun toString() = "State($value)"

    infix fun or(other: State<T>) = States(setOf(this, other))

    infix fun <V> after(symbol: Symbol<V>) = Match(this, symbol)

    infix fun <V> after(symbols: Set<Symbol<V>>) = symbols.map { Match(this, it) }.toSet()
}

data class States<T>(val values: Set<State<T>>) {
    infix fun or(state: State<T>) = States(values + state)

    infix fun <V> cyclesAfter(symbol: Symbol<V>): Transitions<T, V> {
        val transitions = values
                .map { state -> Match(state, symbol) }
                .map { match -> Pair(match, match.state) }
                .toMap()
        return Transitions(transitions)
    }

    infix fun <V> cyclesAfter(symbols: Set<Symbol<V>>): Transitions<T, V> {
        val transitions = symbols
                .map { symbol -> this.cyclesAfter(symbol) }
                .flatMap { it.values.toList() }
                .toMap()

        return Transitions(transitions)
    }
}

data class Symbol<T>(val value: T) {
    override fun toString() = "Symbol($value)"

    infix fun or(other: Symbol<T>) = Symbols(setOf(this, other))
}

data class Symbols<T>(val values: Set<Symbol<T>>) {
    infix fun or(symbol: Symbol<T>) = Symbols(values + symbol)
}

data class Match<T, V>(val state: State<T>, val symbol: Symbol<V>) {
    override fun toString() = "Match(state=${state.value}, symbol=${symbol.value})"

    infix fun transitionsTo(state: State<T>) = Transitions(mapOf(this to state))
}

data class Transitions<T, V>(val values: Map<Match<T, V>, State<T>>)

fun <T, V> transitions(vararg transitions: Transitions<T, V>) {

}

class FSM<T, V>(val state: State<T>, transition: (State<T>, Symbol<V>) -> State<T>) {
    val transition = transition

    fun next(symbol: Symbol<V>): FSM<T, V> {
        val nextState = transition(state, symbol)
        return FSM(nextState, transition)
    }
}

val Char.symbol: Symbol<Char>
    get() = Symbol(this)

fun <T, V> accept(state: State<T>, transition: (State<T>, Symbol<V>) -> State<T>) {

}

fun main(args: Array<String>) {
    val transitions = transitions(
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

}
