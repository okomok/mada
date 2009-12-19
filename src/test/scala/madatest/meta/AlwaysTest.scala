

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package metatest


import mada.meta._
// import junit.framework.Assert._


class AlwaysTest {

    trait Strong
    trait Strung
    final class so extends Strong
    final class su extends Strung

    def testTrivial: Unit = {
        type k = always1[Strung, su]#apply[so]
        assertSame[su, k]
        assertSame[su, always0[Strung, su]#apply]
        assertSame[su, always0[Strung, su]#apply]
        assertSame[su, always1[Strung, su]#apply[so]]
        assertSame[su, always2[Strung, su]#apply[scala.Int, so]]
        assertSame[su, always3[Strung, su]#apply[scala.Int, so, so]]
        ()
    }
}
