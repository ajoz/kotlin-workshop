package io.github.ajoz.workshop.intro

// this will get inlined no Function1.invoke() call
inline fun inlined(body: () -> Unit) {
    body()
}

// inline the whole function and its lambdas
// we can specify to not inline something: noinline
// or if a lambda is passed as an argument to another function: crossinline
inline fun noinlined(first: () -> Unit, noinline second: () -> Unit) {
    first()
    second()
}

fun main(args: Array<String>) {
    inlined {
        println("Inline test!")
    }

    noinlined({ println("first inlined!") }) {
        println("second not inlined!")
    }
}