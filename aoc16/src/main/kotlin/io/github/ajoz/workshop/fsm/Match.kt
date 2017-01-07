package io.github.ajoz.workshop.fsm

import io.github.ajoz.workshop.day2.solution.Transitions

/**
 *
 */
data class Match<T, V>(val state: State<T>, val symbol: Symbol<V>) {
    override fun toString() = "Match(state=${state.value}, symbol=${symbol.value})"

    infix fun transitionsTo(state: State<T>) = Transitions(mapOf(this to state))

    companion object {
        /**
         *
         */
        fun <T, V> matchOf(state: T, symbol: V) = Match(State(state), Symbol(symbol))

        /**
         *
         */
        fun <T, V> matchOf(state: State<T>, symbol: Symbol<V>) = Match(state, symbol)
    }
}