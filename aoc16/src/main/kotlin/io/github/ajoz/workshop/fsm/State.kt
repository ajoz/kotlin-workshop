package io.github.ajoz.workshop.fsm

/**
 *
 */
data class State<T>(val value: T) {
    /**
     *
     */
    override fun toString() = "State($value)"

    /**
     *
     */
    infix fun or(other: State<T>) = StateSet(setOf(this, other))

    /**
     *
     */
    infix fun <V> after(symbol: Symbol<V>) = Match(this, symbol)
}