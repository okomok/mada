

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package nattest; package densetest


import com.github.okomok.mada

import mada.dual._
import mada.dual.nat.dense.Literal._
import mada.dual.nat.Dense


class TrivialTest extends junit.framework.TestCase {

    def testUndual {
        import junit.framework.Assert._
        assertEquals(0, _0.undual)
        assertEquals(7, _6.increment.undual)
        assertEquals(10, _10.undual)

        assertEquals(_2, _2)
        AssertNotEquals(_2, _3)
    }

    def testIncDecDuarity {
        val x: _4 = _3.increment
        val y: _3 = _4.decrement
        ()
    }

    def testAddDuality {
        val x: _2# + [_3] = _2 + _3
        val y: _5 = x
        assert(x === y)
        assert(x === _5)
    }

    def testSubstractDuality {
        val x: _6# - [_5] = _6 - _5
        val y: _1 = x
        assert(x === y)
        assert(x === _1)
    }
/*
    def testMultiplyDuality {
        val a: _4# ** [_2] = _4 ** _2
        val b: _8 = a
        assert(a === b)
        assert(a === _8)
    }
*/
    def testComparisonDuality {
        val a: _4# > [_2] = _4 > _2
        val b: `true` = a
        assert(a === b)
        assert(a === `true`)
    }

    trait testTrivial {
        meta.assertSame[scala.Int, _2#undual]
        meta.assert[_0# === [_0]]

        meta.assert[_0# !== [_1]]
        meta.assert[_1# !== [_0]]

        meta.assert[_1# === [_1]]

        meta.assert[_1# !== [_2]]
        meta.assert[_1# !== [_3]]
        meta.assert[_2# !== [_1]]
        meta.assert[_3# !==[_1]]

        meta.assert[_7# ===[_7]]
        meta.assert[_2# !==[_7]]
        meta.assert[_7# !==[_2]]
        meta.assert[_6# !==[_7]]
        meta.assert[_7# !==[_6]]
        meta.assert[_0# !==[_7]]
        meta.assert[_7# !==[_0]]
        meta.assert[_1# !==[_7]]
        meta.assert[_7# !==[_1]]

        meta.assert[_1#increment# === [_2]]
        meta.assert[_1#increment#increment# === [_3]]

        meta.assert[_1#decrement# === [_0]]
        meta.assert[_3#decrement#decrement# === [_1]]
        meta.assert[_4#decrement# === [_3]]
        meta.assert[_7#increment#decrement#decrement# === [_6]]
    }
    trait testAdd {
        meta.assert[_0# +[_0]# ===[_0]]
        meta.assert[_0# +[_3]# ===[_3]]
        meta.assert[_4# +[_3]# ===[_7]]
        meta.assert[_1# +[_8]# ===[_9]]
        meta.assert[_5# +[_2]# ===[_7]]
    }
    trait testSubtract {
        meta.assert[_0# -[_0]# ===[_0]]
        meta.assert[_1# -[_1]# ===[_0]]
        meta.assert[_3# -[_0]# ===[_3]]
        meta.assert[_4# -[_3]# ===[_1]]
        meta.assert[_8# -[_1]# ===[_7]]
        meta.assert[_5# -[_2]# ===[_3]]
        meta.assert[_6# -[_5]# ===[_1]]
        meta.assert[_5# -[_5]# ===[_0]]
    }
/*
    trait testMultiply {
        meta.assert[_3# **[_2]# ===[_6]]
        meta.assert[_0# **[_3]# ===[_0]]
        meta.assert[_1# **[_3]# ===[_3]]
        meta.assert[_3# **[_1]# ===[_3]]
        meta.assert[_2# **[_3]# ===[_6]]
        meta.assert[_9# **[_1]# ===[_9]]
        meta.assert[_3# **[_3]# ===[_9]]
        meta.assert[_4# **[_2]# ===[_8]]
    }
*/
    trait testComparison {
        meta.assert[_0# <[_2]]
        meta.assert[_3# <[_5]]
        meta.assert[_3# <=[_3]]
        meta.assert[_5# >[_3]]
        meta.assert[_4# >[_0]]
        meta.assert[_4# >=[_2]]
        meta.assert[_0# <=[_0]]
        meta.assert[_0# >=[_0]]
        meta.assertNot[_3# >[_5]]
        meta.assertNot[_0# <[_0]]
        meta.assertNot[_0# >[_0]]
        meta.assertNot[_4# >=[_5]]
        meta.assertNot[_4# <=[_2]]
        meta.assertNot[_4# <[_4]]
        meta.assertNot[_4# >[_4]]
    }

    trait testPropagation {
        type plusPlus[n <: Dense] = n#increment#increment
        type id[n <: Dense] = n#increment#decrement
        type equaL[n <: Dense, m <: Dense] = plusPlus[n]# === [id[m]]

        meta.assert[plusPlus[_4]# === [_6]]
        meta.assert[plusPlus[_7]# === [_9]]
        meta.assert[id[_9]# === [_9]]
        meta.assert[id[_7]# === [_7]]

        meta.assert[equaL[_3, _5]]
        meta.assert[equaL[_4, _6]]

        // Must work.
        type subsub[n <: Dense, m <: Dense] = n# -[m]# -[m]
        meta.assert[subsub[_9, _2]# === [_5]]
    }

}
