

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.hetero


import mada.Meta._
// import junit.framework.Assert._


class BindTest {
    type get1[a <: Obj, b <: Obj] = a
    type q1 = Quote2[get1]

    type get2[a <: Obj, b <: Obj] = b
    type q2 = Quote2[get2]

    def testTrivial: Unit = {
       // AssertEquals[Bind2[q1, _1, _1]#Apply1[Boxed[String]], Boxed[String]]
        ()
    }
}
