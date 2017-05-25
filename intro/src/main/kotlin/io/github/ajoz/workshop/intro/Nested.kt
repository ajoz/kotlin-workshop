package io.github.ajoz.workshop.intro

interface Callback {
    fun onSuccess()
    fun onFailure()
}

// outer class containing some nested classses:
class Outer {
    val value = 10

    //this is static class from java
    class Nested {
        fun testNested() {
            //no access to value of outer class
        }
    }

    fun takesCallback(c: Callback) {
        c.onFailure()
        c.onSuccess()
    }

    // classic java inner class - Kotlin is forcing us to remember that this class has reference to outer class
    inner class Inner {
        fun testInner() {
            println(value)
        }
    }
}