@file:JvmName(name = "FunUtils")

//allows to change the name of the class
package io.github.ajoz.workshop.intro

@JvmOverloads //functions can have default values for arguments
// we can force kotlin compiler to generate missing methods
fun outerFunction(msg: String = "JUG Lodz"): Unit {
    println(msg.length)
}

// a function can have a shorter version without all the braces
fun outerFunction2(msg: String = "JUG Lodz") = println(msg.length)

// we can extend any type (Java, Kotlin)
fun String.sayHello() {
    println("Hello $this")
}
