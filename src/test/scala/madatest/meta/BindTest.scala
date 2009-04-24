

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.hetero


import mada.Meta._
// import junit.framework.Assert._


class BindTest {
    type get1[a <: Object, b <: Object] = a
    type q1 = quote2[get1]

    type get2[a <: Object, b <: Object] = b
    type q2 = quote2[get2]

    def testTrivial: Unit = {
       // assertEquals[Bind2[q1, _1, _1]#apply1[Boxed[String]], Boxed[String]]
        ()
    }
}
