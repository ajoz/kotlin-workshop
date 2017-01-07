package io.github.ajoz.workshop.fsm

import io.github.ajoz.workshop.day2.solution.Transitions

/**
 *
 */
data class StateSet<T>(val values: Set<State<T>>) {
    /**
     *
     */
    infix fun or(state: State<T>) = StateSet(values + state)

    /**
     *
     */
    infix fun <V> cyclesAfter(symbol: Symbol<V>): Transitions<T, V> {
        val transitions = values
                .map { state -> Match(state, symbol) }
                .map { match -> Pair(match, match.state) }
                .toMap()
        return Transitions(transitions)
    }

    /**
     *
     */
    infix fun <V> cyclesAfter(symbols: Set<Symbol<V>>): Transitions<T, V> {
        val transitions = symbols
                .map { symbol -> this.cyclesAfter(symbol) }
                .flatMap { it.values.toList() }
                .toMap()

        return Transitions(transitions)
    }
}