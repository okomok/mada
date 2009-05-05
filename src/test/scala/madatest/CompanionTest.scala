

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


import mada.Companion
import junit.framework.Assert._


class CompanionTest {
    trait Foo[T] extends Companion[Foo.type] {
        def companion = Foo
    }

    object Foo {
    }

    def testTrivial: Unit = {
        assertEquals(Foo, new Foo[Int]{}.companion)
    }
}
