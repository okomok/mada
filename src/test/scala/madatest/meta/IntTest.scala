

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.meta


import mada.Meta._


class IntTest {
    def testUnmeta: Unit = {
        import junit.framework.Assert._
        assertEquals(0, unmeta[_0I, scala.Int])
        assertEquals(7, unmeta[++[_6I], scala.Int])
        assertEquals(10, unmeta[_10I, scala.Int])
    }

    trait testTrivial {
        assert[_0I == _0I]
        assert[_0I != _1I]

        assert[_1I == _1I]
        assert[_1I != _2I]
        assert[_1I != _3I]

        assert[_1I#increment == _2I]
        assert[++[_1I] == _2I]

        assert[_1I#increment#increment == _3I]
        assert[_2I#increment#decrement == _2I]

        assert[_5I#decrement#decrement#decrement == _2I]
        assert[_8I == _7I#increment#increment#decrement]
        assert[_7I#decrement#increment#increment == _7I#increment#increment#decrement]

        assert[++[++[_2I]] == _4I]
        assert[--[++[_3I]] == _3I]
    }

    trait testAdd {
        assert[_0I + _3I == _3I]
        assert[_4I + _3I == _7I]
        assert[_1I + _8I == _9I]
        assert[_5I + _2I == _7I]
    }
/*
    trait testSub {
        assert[_3I - _0I == _3I]
        assert[_4I - _3I == _1I]
        assert[_8I - _1I == _7I]
        assert[_5I - _2I == _3I]
    }
*/
    trait testPropagation {
        type plusPlus[n <: Int] = ++[++[n]]
        type id[n <: Int] = ++[--[n]]#increment#decrement
        type equaL[n <: Int, m <: Int] = plusPlus[n] == id[m]

        assert[plusPlus[_4I] == _6I]
        assert[plusPlus[_7I] == _9I]
        assert[id[_9I] == _9I]
        assert[id[_7I] == _7I]

        assert[equaL[_3I, _5I]]
        assert[equaL[_4I, _6I]]
    }

    trait testFoldLeft {
        type inc[b <: Int, a <: Int] = ++[b]
        type id[n <: Int] = --[++[n]]
        type qp = quote2[inc, Int, Int, Int]

        assert[qp#apply2[_8I, _0I] == _9I]
        assertLower[_3I#foldLeft[_0I, qp], Int]
        assert[_3I#foldLeft[_0I, qp] == _3I]
        assert[inc[_2I, _0I]#foldLeft[_2I, qp] == _5I]

        type callFold[n <: Int] = inc[n, _0I]#foldLeft[_2I, qp]
        assert[callFold[_2I]#increment == _6I]

        // same problem as meta-if.
        //type callFold[n <: Int] = inc[n, _0I]#foldLeft[_2I, qp]#increment
        //assert[callFold[_2I] == _6I]
    }
}
