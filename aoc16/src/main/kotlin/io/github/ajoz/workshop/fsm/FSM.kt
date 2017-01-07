package io.github.ajoz.workshop.fsm

/**
 *
 */
class FSM<T, V>(val state: State<T>, transition: (State<T>, Symbol<V>) -> State<T>) {
    val transition = transition

    /**
     *
     */
    fun accept(symbol: Symbol<V>): FSM<T, V> {
        val nextState = transition(state, symbol)
        return FSM(nextState, transition)
    }
}