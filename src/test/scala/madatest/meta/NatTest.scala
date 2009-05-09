

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.meta


import mada.Meta._


class NatTest {
    def testUnmeta: Unit = {
        import junit.framework.Assert._
        assertEquals(0, unmeta[_0, scala.Int])
        assertEquals(7, unmeta[_6#increment, scala.Int])
        assertEquals(10, unmeta[_10, scala.Int])
    }

    trait testTrivial {
        assert[_0 == _0]
        assert[_0 != _1]

        assert[_1 == _1]
        assert[_1 != _2]
        assert[_1 != _3]

        assert[_1#increment == _2]
        assert[_1#increment#increment == _3]
    }

    trait testAdd {
        assert[_0 + _3 == _3]
        assert[_4 + _3 == _7]
        assert[_1 + _8 == _9]
        assert[_5 + _2 == _7]
    }

    trait testMultiply {
        /* very slow to compile.
        assert[_0#multiply[_3] == _0]
        assert[_1#multiply[_3] == _3]
        assert[_3#multiply[_1] == _3]
        assert[_3#multiply[_2] == _6]
        assert[_2#multiply[_3] == _6]
        assert[_9#multiply[_1] == _9]
        assert[_3#multiply[_3] == _9]
        assert[_4#multiply[_2] == _8]
        */
    }

    trait testPropagation {
        type plusPlus[n <: Nat] = n#increment#increment
        type id[n <: Nat] = n
        type equaL[n <: Nat, m <: Nat] = plusPlus[n] == id[m]

        assert[plusPlus[_4] == _6]
        assert[plusPlus[_7] == _9]
        assert[id[_9] == _9]
        assert[id[_7] == _7]

        assert[equaL[_3, _5]]
        assert[equaL[_4, _6]]
    }
/*
    trait testFoldLeft {
        type inc[b <: Int, a <: Int] = ++[b]
        type id[n <: Int] = n
        type qp = quote2[inc, Int, Int, Int]

        assert[qp#apply2[_8, _0] == _9]
        assertLower[_3#foldLeft[_0, qp], Int]
        assert[_3#foldLeft[_0, qp] == _3]
        assert[inc[_2, _0]#foldLeft[_2, qp] == _5]

        type callFold[n <: Int] = inc[n, _0]#foldLeft[_2, qp]
        assert[callFold[_2]#increment == _6]

        // same problem as meta-if.
        //type callFold[n <: Int] = inc[n, _0]#foldLeft[_2, qp]#increment
        //assert[callFold[_2] == _6]
    }
*/
}
