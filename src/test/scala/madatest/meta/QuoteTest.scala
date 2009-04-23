

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.hetero


import mada.Meta._
// import junit.framework.Assert._


class QuoteTest {
    type get1[a <: Obj, b <: Obj] = a
    type q1 = Quote2[get1]

    type get2[a <: Obj, b <: Obj] = b
    type q2 = Quote2[get2]

    trait SomeThing extends Obj
    final class alwaysSomeThing[a <: Obj] extends SomeThing

    def testTrivial: Unit = {
        AssertEquals[Quote1[Identity]#Apply1[String], String]
        AssertEquals[q1#Apply2[Int, String], Int]
        AssertEquals[q2#Apply2[Int, String], String]

        AssertLower[Quote1[alwaysSomeThing]#Apply1[Double], SomeThing]
    }
}
