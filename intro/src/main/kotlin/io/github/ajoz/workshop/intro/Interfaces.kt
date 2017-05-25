package io.github.ajoz.workshop.intro

// no methods
interface Baz

// with methods:
interface Baz2 {
    fun method1()
}

// with default implementation
interface Baz3 {
    fun method1()
    fun method2() {
        println("Kotlin")
    }
}

//with properties
interface Baz4 {
    fun method1()
    fun method2() {
        println("Default implementation for Baz4")
    }

    val property: Int
}

// implementing:
class FooBar : Baz4 {
    override fun method1() {
        println("FooBar implementation")
    }

    override val property: Int
        get() = 42
}

interface A {
    fun test(): Int = 6
//    fun test2(): String <-- this will be a conflict
}

interface B {
    fun test(): Int = 7
//    fun test2(): Int
}

// we can access which ever default we want through super
class FuBar : A, B {
    override fun test(): Int {
        return super<A>.test() * super<B>.test()
    }
}


