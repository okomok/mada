

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest


import com.github.okomok.mada

import mada.dual._


class NatTest extends junit.framework.TestCase {

    def testUndual {
        import junit.framework.Assert._
        assertEquals(0, _0N.undual)
        assertEquals(7, _6N.increment.undual)
        assertEquals(10, _10N.undual)

        assertEquals(_2N, _2N)
        AssertNotEquals(_2N, _3N)
    }

    def testAddDuality {
        val x: _2N# + [_3N] = _2N + _3N
        val y: _5N = x
        assert(x === y)
        assert(x === _5N)
    }

    def testSubstractDuality {
        val x: _6N# - [_5N] = _6N - _5N
        val y: _1N = x
        assert(x === y)
        assert(x === _1N)
    }

    def testMultiplyDuality {
        val a: _4N# ** [_2N] = _4N ** _2N
        val b: _8N = a
        assert(a === b)
        assert(a === _8N)
    }

    def testComparisonDuality {
        val a: _4N# > [_2N] = _4N > _2N
        val b: `true` = a
        assert(a === b)
        assert(a === `true`)
    }

    trait testTrivial {
        meta.assertSame[scala.Int, _2N#undual]
        meta.assert[_0N# === [_0N]]

        meta.assert[_0N# !== [_1N]]
        meta.assert[_1N# !== [_0N]]

        meta.assert[_1N# === [_1N]]

        meta.assert[_1N# !== [_2N]]
        meta.assert[_1N# !== [_3N]]
        meta.assert[_2N# !== [_1N]]
        meta.assert[_3N# !==[_1N]]

        meta.assert[_7N# ===[_7N]]
        meta.assert[_2N# !==[_7N]]
        meta.assert[_7N# !==[_2N]]
        meta.assert[_6N# !==[_7N]]
        meta.assert[_7N# !==[_6N]]
        meta.assert[_0N# !==[_7N]]
        meta.assert[_7N# !==[_0N]]
        meta.assert[_1N# !==[_7N]]
        meta.assert[_7N# !==[_1N]]

        meta.assert[_1N#increment# === [_2N]]
        meta.assert[_1N#increment#increment# === [_3N]]

        meta.assert[_1N#decrement# === [_0N]]
        meta.assert[_3N#decrement#decrement# === [_1N]]
        meta.assert[_4N#decrement# === [_3N]]
        meta.assert[_7N#increment#decrement#decrement# === [_6N]]
    }

    trait testAdd {
        meta.assert[_0N# +[_0N]# ===[_0N]]
        meta.assert[_0N# +[_3N]# ===[_3N]]
        meta.assert[_4N# +[_3N]# ===[_7N]]
        meta.assert[_1N# +[_8N]# ===[_9N]]
        meta.assert[_5N# +[_2N]# ===[_7N]]
    }
    trait testSubtract {
        meta.assert[_0N# -[_0N]# ===[_0N]]
        meta.assert[_3N# -[_0N]# ===[_3N]]
        meta.assert[_4N# -[_3N]# ===[_1N]]
        meta.assert[_8N# -[_1N]# ===[_7N]]
        meta.assert[_5N# -[_2N]# ===[_3N]]
    }

    trait testMultiply {
        meta.assert[_3N# **[_2N]# ===[_6N]]
        meta.assert[_0N# **[_3N]# ===[_0N]]
        meta.assert[_1N# **[_3N]# ===[_3N]]
        meta.assert[_3N# **[_1N]# ===[_3N]]
        meta.assert[_2N# **[_3N]# ===[_6N]]
        meta.assert[_9N# **[_1N]# ===[_9N]]
        meta.assert[_3N# **[_3N]# ===[_9N]]
        meta.assert[_4N# **[_2N]# ===[_8N]]
    }

    trait testComparison {
        meta.assert[_0N# <[_2N]]
        meta.assert[_3N# <[_5N]]
        meta.assert[_3N# <=[_3N]]
        meta.assert[_5N# >[_3N]]
        meta.assert[_4N# >[_0N]]
        meta.assert[_4N# >=[_2N]]
        meta.assert[_0N# <=[_0N]]
        meta.assert[_0N# >=[_0N]]
        meta.assertNot[_3N# >[_5N]]
        meta.assertNot[_0N# <[_0N]]
        meta.assertNot[_0N# >[_0N]]
        meta.assertNot[_4N# >=[_5N]]
        meta.assertNot[_4N# <=[_2N]]
        meta.assertNot[_4N# <[_4N]]
        meta.assertNot[_4N# >[_4N]]
    }


    trait testPropagation {
        type plusPlus[n <: Nat] = n#increment#increment
        type id[n <: Nat] = n#increment#decrement
        type equaL[n <: Nat, m <: Nat] = plusPlus[n]# === [id[m]]

        meta.assert[plusPlus[_4N]# === [_6N]]
        meta.assert[plusPlus[_7N]# === [_9N]]
        meta.assert[id[_9N]# === [_9N]]
        meta.assert[id[_7N]# === [_7N]]

        meta.assert[equaL[_3N, _5N]]
        meta.assert[equaL[_4N, _6N]]

        // Must work.
        type subsub[n <: Nat, m <: Nat] = n# -[m]# -[m]
        meta.assert[subsub[_9N, _2N]# === [_5N]]
    }

}
