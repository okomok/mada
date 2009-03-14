

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.toy.implicitresolution


import junit.framework.Assert._


trait K[A] {
    def i: Int
}

object K {
    implicit def forAny[A]: K[A] = new K[A] { def i = 1 }
}

class V[A]

object V {
    implicit def forK[A]: K[V[A]] = new K[V[A]] { def i = 2 }
}


class ImplicitResolutionTest {

    def makeK[A](x: A)(expected: Int)(implicit p: K[A]): Unit = {
        assertEquals(expected, p.i)
    }

    def testTrivial: Unit = {
        makeK(999)(1)
        makeK(new V[Int])(2)
    }
}
