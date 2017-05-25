package io.github.ajoz.workshop.intro;

import java.util.List;

import static java.util.Arrays.asList;

public final class JLangs {
    public static List<JLang> getLangs() {
        return asList(
                new JLang("Plankalkul", 1943, "Konrad Zuse"),
                new JLang("ENIAC Coding System", 1943, "John von Neumann"),
                new JLang("Intermediate Programming Language", 1951, "Arthur Burks"),
                new JLang("Fortran", 1954, "John W. Backus"),
                new JLang("LISP", 1956, "John McCarthy"),
                new JLang("BASIC", 1964, "John George Kemeny"),
                new JLang("Pascal", 1970, "Niklaus Wirth"),
                new JLang("Smalltalk", 1972, "Alan Key"),
                new JLang("C", 1972, "Dennis Ritchie"),
                new JLang("Prolog", 1972, "Alain Colmerauer"),
                new JLang("Objective-C", 1983, "Brad Cox"),
                new JLang("C++", 1983, "Bjarne Stroustrup"),
                new JLang("Perl", 1987, "Larry Wall"),
                new JLang("Erlang", 1987, "Joe Armstrong"),
                new JLang("Bash", 1989, "Brian Fox"),
                new JLang("Haskell", 1990, "-"),
                new JLang("Python", 1991, "Guido van Rossum"),
                new JLang("Java", 1995, "James Gosling"),
                new JLang("Scala", 2003, "Martin Odersky"),
                new JLang("Kotlin", 2011, "JetBrains"),
                new JLang("Swift", 2014, "Apple")
        );
    }
}
