

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package metatest


import mada.meta._


class NatTest {
    def testUnmeta: Unit = {
        import junit.framework.Assert._
        assertEquals(0, unmeta[_0N, scala.Int])
        assertEquals(7, unmeta[_6N#increment, scala.Int])
        assertEquals(10, unmeta[_10N, scala.Int])
    }

    trait testTrivial {
        assert[_0N == _0N]

        assert[_0N != _1N]
        assert[_1N != _0N]

        assert[_1N == _1N]

        assert[_1N != _2N]
        assert[_1N != _3N]
        assert[_2N != _1N]
        assert[_3N != _1N]

        assert[_7N == _7N]
        assert[_2N != _7N]
        assert[_7N != _2N]
        assert[_6N != _7N]
        assert[_7N != _6N]
        assert[_0N != _7N]
        assert[_7N != _0N]
        assert[_1N != _7N]
        assert[_7N != _1N]

        assert[_1N#increment == _2N]
        assert[_1N#increment#increment == _3N]

        assert[_1N#decrement == _0N]
        assert[_3N#decrement#decrement == _1N]
        assert[_4N#decrement == _3N]
        assert[_7N#increment#decrement#decrement == _6N]
    }

    trait testAdd {
        assert[_0N + _0N == _0N]
        assert[_0N + _3N == _3N]
        assert[_4N + _3N == _7N]
        assert[_1N + _8N == _9N]
        assert[_5N + _2N == _7N]
    }

    trait testSubtract {
        assert[_0N - _0N == _0N]
        assert[_3N - _0N == _3N]
        assert[_4N - _3N == _1N]
        assert[_8N - _1N == _7N]
        assert[_5N - _2N == _3N]
    }

    trait testMultiply {
        assert[_3N#multiply[_2N] == _6N]
        /* very slow to compile.
        assert[_0N#multiply[_3N] == _0N]
        assert[_1N#multiply[_3N] == _3N]
        assert[_3N#multiply[_1N] == _3N]
        assert[_2N#multiply[_3N] == _6N]
        assert[_9N#multiply[_1N] == _9N]
        assert[_3N#multiply[_3N] == _9N]
        assert[_4N#multiply[_2N] == _8N]
        */
    }

    trait testPropagation {
        type plusPlus[n <: Nat] = n#increment#increment
        type id[n <: Nat] = n#increment#decrement
        type equaL[n <: Nat, m <: Nat] = plusPlus[n] == id[m]

        assert[plusPlus[_4N] == _6N]
        assert[plusPlus[_7N] == _9N]
        assert[id[_9N] == _9N]
        assert[id[_7N] == _7N]

        assert[equaL[_3N, _5N]]
        assert[equaL[_4N, _6N]]
    }
/*
    trait testFoldLeft {
        type inc[b <: Int, a <: Int] = ++[b]
        type id[n <: Int] = n
        type qp = quote2[inc, Int, Int, Int]

        assert[qp#apply2[_8N, _0N] == _9N]
        assertLower[_3N#foldLeft[_0N, qp], Int]
        assert[_3N#foldLeft[_0N, qp] == _3N]
        assert[inc[_2N, _0N]#foldLeft[_2N, qp] == _5N]

        type callFold[n <: Int] = inc[n, _0N]#foldLeft[_2N, qp]
        assert[callFold[_2N]#increment == _6N]

        // same problem as meta-if.
        //type callFold[n <: Int] = inc[n, _0N]#foldLeft[_2N, qp]#increment
        //assert[callFold[_2N] == _6N]
    }
*/
}
