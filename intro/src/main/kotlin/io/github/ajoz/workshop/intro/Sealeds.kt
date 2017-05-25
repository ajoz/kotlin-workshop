package io.github.ajoz.workshop.intro

sealed class Option<out T> {
    fun <R> map(f: (T) -> R): Option<R> = when (this) {
        is None -> None
        is Some -> Some(f(value))
    }

    abstract fun isEmpty(): Boolean
    abstract fun isDefined(): Boolean
}

data class Some<out T>(val value: T) : Option<T>() {
    override fun isDefined() = true
    override fun isEmpty() = false

}

object None : Option<Nothing>() {
    override fun isDefined() = false
    override fun isEmpty() = true
}