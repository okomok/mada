

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package toy; package methodtest


import junit.framework.Assert._


object foo {
    def apply[A](e: A) = e
}


class MethodTest {

    def testTrivial: Unit = {
        assertEquals(3, (foo(_: Int))(3))
    }

}
