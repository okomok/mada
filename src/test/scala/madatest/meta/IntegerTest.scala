

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.hetero


import mada.Meta._
// import junit.framework.Assert._


class IntegerTest {
    def testTrivial: Unit = {
        /*
        assertEquals[_0, _0]
        assertEquals[_1, _0 + _1]
        assertEquals[_1, _1 + _0]
        assertEquals[_2, _1#increment]
        assertEquals[_3, _1 + _2]
        assertEquals[_2, _1 + _1]
        assertEquals[_3, _1#plus[_1]#increment]
        */

      // illegal cyclic reference
//        assertEquals[_3, _2#plus[_1]]
      //  assertEquals[_5, _2#plus[_3]]
      //  assertEquals[_5, _2#multiply[_3]]
      ()
    }
}
