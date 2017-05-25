package io.github.ajoz.workshop.intro

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

// we have an interface
interface Test {
    fun test(): Int
}

// and its known implementation
open class ExtraTest : Test {
    override fun test(): Int {
        return 42
    }
}

// we already have an important base class that we need to derive
abstract class Base {
    abstract fun veryImportant(): Int
}

// we can compose our Test implementation through the usage of delegation
// such delegation can be done only through interfaces :(
class Derived(et: Test) : Base(), Test by et {
    override fun veryImportant(): Int {
        return 2017
    }
}
// we can delegate properties handling both var and val
class WithDelegatedProperties(val map: Map<String, Any?>) {
    // need to implement getValue and setValue functions / extension functions
    val value1: Int by CustomValDelegate()
    var variable: String by CustomVarDelegate()

    // by default its synchronized
    val lazyValue: String by lazy {
        "A very intensive operation that returns a String"
    }

    var observableValue: Int by Delegates.observable(42) {
        prop, old, new ->
        println("Observable $prop : $old -> $new")
    }

    // we can delegate from map it will explode if it doesn't find the value
    val fromMap1: Int by map
    val fromMap2: String by map
}

class CustomValDelegate {
    operator fun  getValue(thisRef: WithDelegatedProperties, property: KProperty<*>): Int {
        println("getValue $thisRef has delegated property: ${property.name}")
        return 12
    }
}

class CustomVarDelegate

private operator fun CustomVarDelegate.setValue(thisRef: WithDelegatedProperties, property: KProperty<*>, s: String) {
    println("setValue $thisRef has delegated property: ${property.name} and sets value: $s")
}

private operator fun CustomVarDelegate.getValue(thisRef: WithDelegatedProperties, property: KProperty<*>): String {
    println("getValue $thisRef has delegated property: ${property.name}")
    return "JUG Lodz"
}


