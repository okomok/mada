

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.hetero


import mada.Meta._
// import junit.framework.Assert._


class IntegerTest {
    def testTrivial: Unit = {

        assertSame[_0I, _0I]
        assertSame[_1I, _0I + _1I]
        assertSame[_1I, _1I + _0I]
        assertSame[_2I, _1I#increment]
        assertSame[_3I, _1I + _2I]
        assertSame[_2I, _1I + _1I]
        assertSame[_3I, _1I#add[_1I]#increment]


        assertSame[_3I, _2I +_1I]
        assertSame[_5I, _2I + _3I]
        assertSame[_3I, _2I +_1I]
        assertSame[_5I, _3I + _2I]
        assertSame[_5I, _4I + _1I]
        assertSame[_5I, _2I + _3I]

        assertSame[_4I, _2I#multiply[_2I]]
        assertSame[_3I, _3I#multiply[_1I]]

        assertSame[_6I, _2I#multiply[_3I]]
        assertSame[_6I, _3I#multiply[_2I]]

      ()
    }
}
