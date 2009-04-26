

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.meta


import mada.Meta._
// import junit.framework.Assert._


class AlwaysTest {

    trait Strong extends Object
    final class so extends Strong

    def testTrivial: Unit = {
        type k = always[box[String]]#apply1[so]
        assertSame[box[String], k]
        assertSame[box[String], always[box[String]]#apply0[_]]
        assertSame[box[String], always[box[String]]#apply1[so]]
        assertSame[box[String], always[box[String]]#apply2[box[scala.Int], so]]
        assertSame[box[String], always[box[String]]#apply3[box[scala.Int], so, so]]
        ()
    }
}
