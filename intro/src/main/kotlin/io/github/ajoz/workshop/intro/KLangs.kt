package io.github.ajoz.workshop.intro

object KLangs {
    // This is the longest possible notation for this small snippet of code
    // Check in IDEA:
    // - Convert to expression body
    // - Remove explicit type specification
    fun getLangs(): List<KLang> {
        return listOf(
                KLang("Plankalkul", 1943, "Konrad Zuse"),
                KLang("ENIAC Coding System", 1943, "John von Neumann"),
                KLang("Intermediate Programming Language", 1951, "Arthur Burks"),
                KLang("Fortran", 1954, "John W. Backus"),
                KLang("LISP", 1956, "John McCarthy"),
                KLang("BASIC", 1964, "John George Kemeny"),
                KLang("Pascal", 1970, "Niklaus Wirth"),
                KLang("Smalltalk", 1972, "Alan Key"),
                KLang("C", 1972, "Dennis Ritchie"),
                KLang("Prolog", 1972, "Alain Colmerauer"),
                KLang("Objective-C", 1983, "Brad Cox"),
                KLang("C++", 1983, "Bjarne Stroustrup"),
                KLang("Perl", 1987, "Larry Wall"),
                KLang("Erlang", 1987, "Joe Armstrong"),
                KLang("Bash", 1989, "Brian Fox"),
                KLang("Haskell", 1990, "-"),
                KLang("Python", 1991, "Guido van Rossum"),
                KLang("Java", 1995, "James Gosling"),
                KLang("Scala", 2003, "Martin Odersky"),
                KLang("Kotlin", 2011, "JetBrains"),
                KLang("Swift", 2014, "Apple")
        )
    }
}