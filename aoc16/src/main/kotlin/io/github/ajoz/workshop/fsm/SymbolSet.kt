package io.github.ajoz.workshop.fsm

/**
 *
 */
data class SymbolSet<T>(val values: Set<Symbol<T>>) {
    /**
     *
     */
    infix fun or(symbol: Symbol<T>) = SymbolSet(values + symbol)
}