@file:Suppress("ConvertSecondaryConstructorToPrimary", "unused")

package io.github.ajoz.workshop.intro

class Foo {
    val prop1: Int = 10
    val prop2: String
        get() {
            return "JUG Lodz"
        }

    val prop3: String
        get() = "Kotlin"

    var prop4: Int
        get(): Int = field
        set(value) {
            field = value
        }

    constructor() {
        prop4 = 100
    }

    // only in classes
    // only vars
    // accessing before initialized will result in special exception
    lateinit var lateProp: String

    fun setup() {
        lateProp = "a very late setup!"
    }
}

val String.head
    get() = if (isNotEmpty()) this[0] else throw kotlin.NoSuchElementException()

val String.tail
    get() = if (isNotEmpty()) subSequence(1, length) else throw kotlin.NoSuchElementException()
