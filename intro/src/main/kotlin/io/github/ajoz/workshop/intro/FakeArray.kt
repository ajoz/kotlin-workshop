package io.github.ajoz.workshop.intro

// we implement get and set to get [] working
class FakeArray(var value: Int) {
    operator fun get(ignore: Int): Int = value

    operator fun set(ignore: Int, value: Int): Unit {
        this.value = value
    }

    operator fun iterator(): Iterator<Int> = object : Iterator<Int> {
        override fun hasNext() = true
        override fun next() = value
    }
}