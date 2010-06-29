

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest


import com.github.okomok.mada

import mada.dual._

class NatTest extends junit.framework.TestCase {

    def testToSInt {
        import junit.framework.Assert._
        assertEquals(0, _0N.toSInt)
        assertEquals(7, _6N.increment.toSInt)
        assertEquals(10, _10N.toSInt)
    }

    trait testTrivial {
        assertSame[scala.Int, _2N#toSInt]
        assert[_0N === _0N]

        assert[_0N !== _1N]
        assert[_1N !== _0N]

        assert[_1N === _1N]

        assert[_1N !== _2N]
        assert[_1N !== _3N]
        assert[_2N !== _1N]
        assert[_3N !== _1N]

        assert[_7N === _7N]
        assert[_2N !== _7N]
        assert[_7N !== _2N]
        assert[_6N !== _7N]
        assert[_7N !== _6N]
        assert[_0N !== _7N]
        assert[_7N !== _0N]
        assert[_1N !== _7N]
        assert[_7N !== _1N]

        assert[_1N#increment === _2N]
        assert[_1N#increment#increment === _3N]

        assert[_1N#decrement === _0N]
        assert[_3N#decrement#decrement === _1N]
        assert[_4N#decrement === _3N]
        assert[_7N#increment#decrement#decrement === _6N]
    }

    trait testAdd {
        assert[_0N + _0N === _0N]
        assert[_0N + _3N === _3N]
        assert[_4N + _3N === _7N]
        assert[_1N + _8N === _9N]
        assert[_5N + _2N === _7N]
    }

    trait testSubtract {
        assert[_0N - _0N === _0N]
        assert[_3N - _0N === _3N]
        assert[_4N - _3N === _1N]
        assert[_8N - _1N === _7N]
        assert[_5N - _2N === _3N]
    }

    trait testMultiply {
        assert[(_3N x _2N) === _6N]
        assert[(_0N x _3N) === _0N]
        assert[(_1N x _3N) === _3N]
        assert[(_3N x _1N) === _3N]
        assert[(_2N x _3N) === _6N]
        assert[(_9N x _1N) === _9N]
        assert[(_3N x _3N) === _9N]
        assert[(_4N x _2N) === _8N]
    }

    trait testComparison {
        assert[_0N < _2N]
        assert[_3N < _5N]
        assert[_3N <= _3N]
        assert[_5N > _3N]
        assert[_4N > _0N]
        assert[_4N >= _2N]
        assert[_0N <= _0N]
        assert[_0N >= _0N]
        assertNot[_3N > _5N]
        assertNot[_0N < _0N]
        assertNot[_0N > _0N]
        assertNot[_4N >= _5N]
        assertNot[_4N <= _2N]
        assertNot[_4N < _4N]
        assertNot[_4N > _4N]
    }

    trait testPropagation {
        type plusPlus[n <: Nat] = n#increment#increment
        type id[n <: Nat] = n#increment#decrement
        type equaL[n <: Nat, m <: Nat] = plusPlus[n] === id[m]

        assert[plusPlus[_4N] === _6N]
        assert[plusPlus[_7N] === _9N]
        assert[id[_9N] === _9N]
        assert[id[_7N] === _7N]

        assert[equaL[_3N, _5N]]
        assert[equaL[_4N, _6N]]

        // Must work.
        type subsub[n <: Nat, m <: Nat] = n# -[m]# -[m]
        assert[subsub[_9N, _2N] === _5N]
    }
}
