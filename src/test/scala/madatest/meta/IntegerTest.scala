

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.hetero


import mada.Meta._
// import junit.framework.Assert._


class IntegerTest {
    def testTrivial: Unit = {

        assertEquals[_0I, _0I]
        assertEquals[_1I, _0I + _1I]
        assertEquals[_1I, _1I + _0I]
        assertEquals[_2I, _1I#increment]
        assertEquals[_3I, _1I + _2I]
        assertEquals[_2I, _1I + _1I]
        assertEquals[_3I, _1I#add[_1I]#increment]


        assertEquals[_3I, _2I +_1I]
        assertEquals[_5I, _2I + _3I]
        assertEquals[_3I, _2I +_1I]
        assertEquals[_5I, _3I + _2I]
        assertEquals[_5I, _4I + _1I]
        assertEquals[_5I, _2I + _3I]

        assertEquals[_4I, _2I#multiply[_2I]]
        assertEquals[_3I, _3I#multiply[_1I]]

        assertEquals[_6I, _2I#multiply[_3I]]
        assertEquals[_6I, _3I#multiply[_2I]]

      ()
    }
}
