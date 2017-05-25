package io.github.ajoz.workshop.intro;

import kotlin.jvm.functions.Function1;
import org.junit.Test;

import java.util.List;

public class JWorkshopTest {
    // Kotlin type system and how its visible from Java
    @Test
    public void test08() throws Exception {
        final KLang lang = new KLang("Test", 2017, "Test test");
//        lang.notify(); <-- they are visible in Java but not in Kotlin
//        lang.wait();
        System.out.println(lang);
    }

    @Test
    public void test14() throws Exception {
        final Option<Integer> some = new Some<>(42);
        final Option<String> some2 = some.map(new Function1<Integer, String>() {
            @Override
            public String invoke(final Integer value) {
                return "From java: " + value;
            }
        });
        System.out.println(some2);

        final Option<String> none = None.INSTANCE;
        System.out.println(none);
    }

    @Test
    public void test16() throws Exception {
        int value = JUG.INSTANCE.getValue();
        JUG.INSTANCE.test();

        Kotlin.JUG.testCompanion();
    }
}
