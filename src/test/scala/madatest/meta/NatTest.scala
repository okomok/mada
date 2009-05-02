

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.meta


import mada.Meta._
// import junit.framework.Assert._


class NatTest {
    def testNone: Unit = ()

    trait testTrivial {
        assert[_0N == _0N]
        assert[_0N != _1N]

        assert[_1N == _1N]
        assert[_1N != _2N]
        assert[_1N != _3N]

        assert[_1N#increment == _2N]
        assert[++[_1N] == _2N]

        assert[_1N#increment#increment == _3N]
        assert[_2N#increment#decrement == _2N]

        assert[_5N#decrement#decrement#decrement == _2N]
        assert[_8N == _7N#increment#increment#decrement]
        assert[_7N#decrement#increment#increment == _7N#increment#increment#decrement]

        assert[++[++[_2N]] == _4N]
        assert[--[++[_3N]] == _3N]
    }

    trait testPropagation {
        type plusPlus[n <: Nat] = ++[++[n]]
        type id[n <: Nat] = ++[--[n]]#increment#decrement
        type equaL[n <: Nat, m <: Nat] = plusPlus[n] == id[m]

        assert[plusPlus[_4N] == _6N]
        assert[plusPlus[_7N] == _9N]
        assert[id[_9N] == _9N]
        assert[id[_7N] == _7N]

        assert[equaL[_3N, _5N]]
        assert[equaL[_4N, _6N]]
    }
}