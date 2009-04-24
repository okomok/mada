

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.hetero


import mada.Meta._
// import junit.framework.Assert._


class QuoteTest {
    type get1[a <: Object, b <: Object] = a
    type q1 = quote2[get1]

    type get2[a <: Object, b <: Object] = b
    type q2 = quote2[get2]

    trait SomeThing extends Object
    final class alwaysSomeThing[a <: Object] extends SomeThing

    def testTrivial: Unit = {
        assertEquals[quote1[identity]#apply1[String], String]
        assertEquals[q1#apply2[Int, String], Int]
        assertEquals[q2#apply2[Int, String], String]

        assertLower[quote1[alwaysSomeThing]#apply1[Double], SomeThing]
    }
}
