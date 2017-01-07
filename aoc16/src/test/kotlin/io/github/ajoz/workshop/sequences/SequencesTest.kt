package io.github.ajoz.workshop.sequences

import io.kotlintest.specs.WordSpec
import java.util.*

class SequencesTest : WordSpec() {
    init {
        "Empty Sequence" should {
            "return an empty sequence after scan transformation" {
                val emptySeq = emptySequence<Int>()
                val transform = { prev: Int, next: Int -> prev + next }

                emptySeq.scan(0, transform).count() shouldEqual 0
            }

            "throw exception when checked for first repeated element" {
                shouldThrow<NoSuchElementException> {
                    val emptySeq = emptySequence<Int>()
                    emptySeq.firstRepeated()
                }
            }
        }

        "One element Sequence: [1]" should {
            "map to [1] when scanned with 0 for a sum transformation" {
                val oneElemSeq = sequenceOf(1)
                val transform = { prev: Int, next: Int -> prev + next }

                oneElemSeq.scan(0, transform).last() shouldEqual 1
            }

            "throw exception when checked for first repeated element" {
                shouldThrow<NoSuchElementException> {
                    val oneElemSeq = sequenceOf(1)
                    oneElemSeq.firstRepeated()
                }
            }
        }

        "Multi element Sequence: [1, 2, 2, 3]" should {
            "map to [1, 3, 5, 8] when scanned with 0 for a sum transformation" {
                val multiElemSeq = sequenceOf(1, 2, 2, 3)
                val expectedSeq = sequenceOf(1, 3, 5, 8)
                val transform = { prev: Int, next: Int -> prev + next }


                //TODO: add Seq<T> shouldEqual Seq<T> for comparing sequences in tests
                multiElemSeq.scan(0, transform)
            }
        }

    }
}