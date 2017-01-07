package io.github.ajoz.workshop.fsm

/**
 *
 */
data class Symbol<T>(val value: T) {
    override fun toString() = "Symbol($value)"

    infix fun or(other: Symbol<T>) = SymbolSet(setOf(this, other))
}