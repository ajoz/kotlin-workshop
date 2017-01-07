package io.github.ajoz.workshop.fsm

import io.kotlintest.specs.WordSpec

class StateTest : WordSpec() {
    init {
        "State" should {
            "use shorter form of toString" {
                State(1).toString() shouldBe "State(1)"
                State('A').toString() shouldBe "State(A)"
                State("TestState").toString() shouldBe "State(TestState)"
            }

            "return a StateSet when called or with another State" {
                val set = State(1) or State(2)
                set.values should contain(State(1))
                set.values should contain(State(2))
            }

            "return a Match when called after with a Symbol" {
                val match = State(1) after Symbol('R')
                match.state shouldBe State(1)
                match.symbol shouldBe Symbol('R')
            }
        }
    }
}