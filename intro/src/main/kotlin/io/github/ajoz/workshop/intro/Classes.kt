@file:Suppress("unused")

package io.github.ajoz.workshop.intro

// empty class
class Empty

// with primary constructor and a property
class Foo1(val value: Int)

// can be named with "constructor"
class Foo2 constructor(val value: Int)

// useful when we need to add visibility or annotations
class Foo3 @JvmOverloads constructor(val value: Int = 42)

// no primary constructor only a secondary constructor - IDE wants to help
class Foo4 {
    val value: Int

    constructor(value: Int) {
        this.value = value
    }
}

// we do not have to declare properties in primary constructor
class Foo5(arg: Int) {
    val value: Int = arg //we can access argument in the declaration

    init {
        //we can access arg init block
        for (i in 1..arg) {
            println(i) //more sophisticated init
        }
    }
}

// we can add visibility to any constructor
class Foo6 private constructor(val value: Int = 42) {
    internal constructor(x: String) : this(x.length) {
        for (i in 1..value) {
            println(x)
        }
    }
}
// public default visibility
// private its private for this class
// internal its accessible for everything in module
// protected used in open classes
// there is no package level visibility (could be added in the future)

class CannotCreate private constructor()

// all classes are final by default!
// we need to mark them as open
class Foo7(val value: Int = 42)
// class Bar() : Foo() <-- ERROR

// if there is a primary constructor base class needs to be initiated right there
open class Foo8(val value: Int = 42)
class Bar8(x: String) : Foo8(x.length)

abstract class Foo9(val value: Int = 42)
class Bar9(x: String) : Foo9(x.length)

// if no primary constructor we can use super in secondary
open class Foo10(val value: Int = 42)
class Bar10 : Foo10 {
    constructor(x: String) : super(x.length)
}

// to override a method it needs to be open
// seems like an overkill at first but its very nice
// explicitly shows what is open and what is overriden
open class Foo11(val value: Int = 42) {
    open fun test(l: List<String>) = l.size + 100
}

// we need to specify that we override
open class Bar11 : Foo11() {
    override fun test(l: List<String>): Int {
        return super.test(l) - 100
    }
}

// overriden function is open by default, need to add final to close it again
// if the whole class is open
open class Baz11 : Bar11() {
    final override fun test(l: List<String>): Int {
        return super.test(l) * 6
    }
}

// its possible to override properties but they need to be declared open in base class
open class Foo12(open val value: Int = 42)
class Bar12(override val value: Int = 56) : Foo12(23)