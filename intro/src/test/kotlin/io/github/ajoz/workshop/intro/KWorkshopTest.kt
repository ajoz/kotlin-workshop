package io.github.ajoz.workshop.intro

//import io.github.ajoz.workshop.intro.p2.ConflictingName --> Name is ambiguous
import io.github.ajoz.workshop.intro.p1.ConflictingName
import org.junit.Test
import io.github.ajoz.workshop.intro.p2.ConflictingName as CN2

typealias Year = Int

const val SOMECONSTANT = 10

// simple class definition
class KWorkshopTest {
    // can have functions
    @Test
    fun test01() {

    }

    // functions can have return type, normally its inferred by the compiler
    // java void is changed to Unit in Kotlin
    @Test
    fun test02(): Unit {

    }

    // its possible in kotlin for functions to have spaces in the name
    // JVM allows any name even starting with a number but javac does not!
    // JVM method name cannot contain: . ; [ / < > :
    // JVM spec: http://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.3.4
    @Test
    fun `My Class should do x when y and z happens after major thing`() {

    }

    // we can have nested functions (fun in fun), internally its changed to a field
    @Test
    fun test04() {
        fun nested(x: String): Int {
            return x.length
        }

        println(nested("JUG Lodz"))
    }

    // we can have functions that do not belong to any class (at least in Kotlin)
    @Test
    fun test05() {
        outerFunction("Intro to Kotlin 2017")
    }

    // we can have functions that extend other types: "extension functions"
    // there are some limitations:
    // -- cannot access private fields that are not have getters or setters
    // -- extensions are resolved statically
    @Test
    fun test06() {
        "JUG Lodz".sayHello()
    }

    // Value, Variable, Constant
    @Test
    fun test07() {
        val cannotBeChanged = 10
//        cannotBeChanged = 5 //ERROR
        println(cannotBeChanged)

        var canBeChaned = "Intro to Kotlin 2017"
        canBeChaned = "JUG Lodz"
        println(canBeChaned) //IDE doesn't like mutable var so it underlines them :>

        println(SOMECONSTANT) //only basic types + String
    }

    // Type system in Kotlin
    @Test
    fun test08() {
        // Types in Kotlin are placed in a hierarchy
        // there is an abstract type Any
        // it adds: equals, hashCode and toString
        var any: Any = "String"
        // any other type is derived from it
        val string: String = "Kotlin"
        any = string
        any = 10

        //Do not treat this as java Object because in the Kotlin lang its not the same
        // string.wait()
        // string.notify() <-- missing although available in java Object

        //Any type is not available in Java or I haven't tried hard enough to get it :>

        //Unlike in Java Kotlin distinguishes nullable and non-nullable types
        var str1: String = "Non-Nullable"
        var str2: String? = "Nullable"

        // its not possible to do:
        // str1 = str2
        //its possible to do:
        str2 = str1
        //Each type has its nullable equivalent (its derived from it) String has String?, Int has Int? and even
        // Any has Any?

        //There are no void functions in Kotlin like in Java or C, function always returns a value
        // a function that only is needed for its side effect returns Unit, its an object
        var u: Unit = Unit

        fun sideEffect() = println("side effect")
        val u2: Unit = sideEffect()

        // Unit is derived from Any
        any = u2
        // There is also a nullable Unit? which can have value of Unit (object) or null
        var u3: Unit? = null

        // There is a special type called Nothing (which has a nullable equivalent Nothing?)
        // it is a subtype of every type in Kotlin
        var n: Nothing //nothing has no instances
        var n2: Nothing? = null //null is the only value of Nothing? nulls type is Nothing?
        //this is why it can be assigned to any nullable type

        //an expression of type Nothing doesn't result in value
        // expression of type Unit returns said Unit, expression of type Nothing never returns anything
        // any code after such expression is unreachable
        // expressions like throw, return
//        exitProcess(9) //example of function returning Nothing
//        println("test") //unreachable code

        //very nice explanation on Nat Pryce blog
        // http://natpryce.com/articles/000818.html
    }

    //there is no octal type in kotlin
    @Test
    fun test09() {
        val decimal: Int = 42
        val long: Long = 42L
        val hex: Int = 0x2A
        val binary: Int = 0b101010
        val double: Double = 42.0
        val float: Float = 42.0F

        val withUnderscores = 0b10_10_10

        println(decimal == hex) //== used for equality
        println(binary == hex)
        println(binary == withUnderscores)

        //val javaInt: Integer //You cannot explicitly use it in Kotlin
        // there is a conversion to Int? behind the scenes
        val abs: Int = Math.abs(10)
        //Java API also is visible with Kotlin types

        val i: Int = 42
        val nullableI: Int? = i
        val nullableI2: Int? = i

        println(i == nullableI)
        println(i === nullableI) //will preserve equality
        println(nullableI === nullableI2) //does not necessary need to preserve identity

        // there are no smaller / larger numeric types in Kotlin, casts need to be explicit
        // unless they can be inferred by the compiler
        //        val d: Double = i //Does not compile Kotlin enforces explicit conversion
        val d: Double = i.toDouble()
        val d2: Double = 42.3 + i //inferred

        val c = 'j'
//        val isIt = c == 12 //Char is not Int
        val isIt = c.toInt() == 12
    }

    @Test
    fun test10() {
        //Go to Classes.kt file
    }

    @Test
    fun test11() {
        //Go to Properties.kt file
        val string = "JUG Lodz"
        println(string.head)
        println(string.tail)
    }

    @Test
    fun test12() {
        // Go to Interfaces.kt file
    }

    @Test
    fun test13() {
        // For data classes go to JLang and KLang
        val lang = KLang("Kotlin", 2017, "JUG")
        // destructure thanks to component* methods
        val (name, year, developer) = lang
        // we can ommit the unwanted ones
        val (name2, year2, _) = lang
        println(name2)
        //we can create a copy
        val lang2 = lang.copy(year = 2018)
        println(lang2)
    }

    @Test
    fun test14() {
        //For sealed classes go to Sealeds.kt
        val some: Option<Int> = Some(1)
        val none = None
        var some2: Option<Any> = None

        some.map {
            value ->
            "Value inside $value"
        }

        println(some)
    }

    @Test
    fun test15() {
        // Go to file: Nested.kt
        val outer = Outer()
        val nested = Outer.Nested()
        val inner = Outer().Inner()

        //anonymous implementation
        outer.takesCallback(object : Callback {
            override fun onSuccess() {

            }

            override fun onFailure() {

            }

        })
    }

    @Test
    fun test16() {
        // Go to file Objects.kt
        val unit: Unit = JUG.test()
        println(JUG.value)

        Kotlin.testCompanion()
    }

    @Test
    fun test17() {
        val derived = Derived(ExtraTest())
        derived.test()
    }

    @Test
    fun test18() {
        val wdp = WithDelegatedProperties(
                mapOf(
                        "fromMap1" to 42,
                        "fromMap2" to "JUG"
                )
        )

        println(wdp.value1)
        println(wdp.variable)
        wdp.observableValue = 2017
        wdp.observableValue = 42
        println(wdp.lazyValue)
        println(wdp.fromMap1)
        println(wdp.fromMap2)
    }

    @Test
    fun test19() {
        //empty array
        val arr1 = emptyArray<String>()
        //we can specify type explicitly
        val arr2: Array<String> = emptyArray()
        //int array
        val arr3: IntArray = intArrayOf(1, 2, 3)
        //custom array
        val arr4: Array<String> = Array(10) {
            index ->
            "element: $index"
        }

        //simple iteration
        for (s: String in arr1) {
            println("simple iteration: $s")
        }

        // withIndex returns an indexed result which is a data class that can be destructured
        for ((index, s) in arr2.withIndex()) {
            println("indexed iteration: $index -> $s")
        }

        //for(i in 0 .. arr3.size) would cause OutOfBoundsException because Ranges in kotlin are inclusive by default
        for (i in 0 until arr3.size) {
//            "${arr3.get(i)}"
            println("closed range iteration: ${arr3[i]}")
        }

        // we can also specify the step
        for (i: Int in 0 until arr4.size step 2) {
            println("Step iteration: ${arr4[i]}")
        }

        // we can iterate it in declarative way
        arr4.forEach({ item -> println("forEach iteration: $item") })

        // you can modify array
        val arr5 = intArrayOf(1, 2, 3)
        arr5[0] = 10 // to implement operator [] you need get and set
        arr5[1] = 20 // operator fun get(index: Int): T
        arr5[2] = 30 // operator fun set(index: Int, value: T): Unit
    }

    //we can define our own operators
    @Test
    fun test20() {
        // index operator
        val fake = FakeArray(42)
        println(fake[0])
        fake[100] = 50
        println(fake[33])

        for (i in fake) {
            println(i)
        }
        //there are other operators to overload:
        // unuary:
        // unuaryPlus, unuaryMinus, not, inc, dec
        // binary:
        // plus, minus, times, div, rem, rangeTo
        // contains [in]
        // invoke operator for function calls
    }

    @Test
    fun test21() {
        val list1 = emptyList<String>()
        val list2 = listOf(1, 2, 3)

        //both return the same type of list
        //kotlin ArrayList == java ArrayList
        //its just a type alias
        val list3: ArrayList<Int> = arrayListOf(1, 2, 3)
        val list4 = ArrayList<Int>() //java list
        list4.add(10)
        //to define a typealias we need to do it out of scope of class or object
        //lets do a type alias for Int
        //typealias Year = Int
        val year: Year = 2017
        list3.add(year)
    }

    @Test
    fun test22() {
        val langs = JLangs.getLangs()
        // separation of concerns (non lazy)
        val filteredNonLazy = langs
                .filter { it.year > 1969 }     /*Filtering*/
                .map { it.name.toUpperCase() } /*Mapping*/
                .sorted()                      /*Sorting*/
                .take(3)                       /*Taking 3 or less*/
        println(filteredNonLazy)

        val filteredLazy = langs
                .asSequence()
                .onEach { println("Before Filtering: $it") }
                .filter { it.year > 1969 }                     /*Filtering*/
                .onEach { println("After Filtering: $it") }
                .map { it.name.toUpperCase() }                 /*Mapping*/
                .onEach { println("Mapping: $it") }
                .sorted()                                      /*Sorting*/
                .onEach { println("Sorting: $it") }
                .take(n = 3)                                   /*Taking 3 or less*/
        println(filteredLazy.toList())
    }

    @Test
    fun test23() {
        //sometimes there are name conflicts in the imports
        val cn = ConflictingName()
        println(cn.value)
        //IDE doesn't always help you with the name you are looking for
        val cn2: CN2 = CN2() //we do not have to specify type explicitly
        println(cn2.value)
    }

    
}