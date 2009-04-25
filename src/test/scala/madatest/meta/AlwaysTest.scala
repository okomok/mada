

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.hetero


import mada.Meta._
// import junit.framework.Assert._


class AlwaysTest {

    trait Strong extends Object
    final class so extends Strong

    def testTrivial: Unit = {
        assertEquals[newObject[String], always[newObject[String]]#apply0]
        assertEquals[newObject[String], always[newObject[String]]#apply1[so]]
        assertEquals[newObject[String], always[newObject[String]]#apply2[newObject[scala.Int], so]]
        assertEquals[newObject[String], always[newObject[String]]#apply3[newObject[scala.Int], so, so]]
    }
}
