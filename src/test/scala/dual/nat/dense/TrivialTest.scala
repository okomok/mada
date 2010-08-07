

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package nattest; package densetest


import com.github.okomok.mada

import mada.dual._
import mada.dual.nat.dense.Literal._
import mada.dual.nat.Dense


class TrivialTest extends org.scalatest.junit.JUnit3Suite {

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
        val x: _2#plus [_3] = _2 plus _3
        val y: _5 = x
        mada.dual.assert(x equal y)
        mada.dual.assert(x equal _5)
    }

    def testSubstractDuality {
        val x: _6# minus [_5] = _6 minus _5
        val y: _1 = x
        mada.dual.assert(x equal y)
        mada.dual.assert(x equal _1)
    }

    def testComparisonDuality {
        val a: _4#gt [_2] = _4 gt _2
        val b: `true` = a
        mada.dual.assert(a equal b)
        mada.dual.assert(a equal `true`)
    }

    trait testTrivial {
        meta.assertSame[scala.Int, _2#undual]
        meta.assert[_0# equal [_0]]

        meta.assert[_0# nequal [_1]]
        meta.assert[_1# nequal [_0]]

        meta.assert[_1# equal [_1]]

        meta.assert[_1# nequal [_2]]
        meta.assert[_1# nequal [_3]]
        meta.assert[_2# nequal [_1]]
        meta.assert[_3# nequal[_1]]

        meta.assert[_7# equal[_7]]
        meta.assert[_2# nequal[_7]]
        meta.assert[_7# nequal[_2]]
        meta.assert[_6# nequal[_7]]
        meta.assert[_7# nequal[_6]]
        meta.assert[_0# nequal[_7]]
        meta.assert[_7# nequal[_0]]
        meta.assert[_1# nequal[_7]]
        meta.assert[_7# nequal[_1]]

        meta.assert[_1#increment# equal [_2]]
        meta.assert[_1#increment#increment# equal [_3]]

        meta.assert[_1#decrement# equal [_0]]
        meta.assert[_3#decrement#decrement# equal [_1]]
        meta.assert[_4#decrement# equal [_3]]
        meta.assert[_7#increment#decrement#decrement# equal [_6]]
    }

    trait testAdd {
        meta.assert[_0#plus[_0]# equal[_0]]
        meta.assert[_0#plus[_3]# equal[_3]]
        meta.assert[_4#plus[_3]# equal[_7]]
        meta.assert[_1#plus[_8]# equal[_9]]
        meta.assert[_5#plus[_2]# equal[_7]]
    }

    trait testSubtract {
        meta.assert[_0# minus[_0]# equal[_0]]
        meta.assert[_1# minus[_1]# equal[_0]]
        meta.assert[_3# minus[_0]# equal[_3]]
        meta.assert[_4# minus[_3]# equal[_1]]
        meta.assert[_8# minus[_1]# equal[_7]]
        meta.assert[_5# minus[_2]# equal[_3]]
        meta.assert[_6# minus[_5]# equal[_1]]
        meta.assert[_5# minus[_5]# equal[_0]]
    }

    trait testComparison {
        meta.assert[_0#lt[_2]]
        meta.assert[_3#lt[_5]]
        meta.assert[_3#lteq[_3]]
        meta.assert[_5#gt[_3]]
        meta.assert[_4#gt[_0]]
        meta.assert[_4# gteq[_2]]
        meta.assert[_0#lteq[_0]]
        meta.assert[_0# gteq[_0]]
        meta.assertNot[_3#gt[_5]]
        meta.assertNot[_0#lt[_0]]
        meta.assertNot[_0#gt[_0]]
        meta.assertNot[_4# gteq[_5]]
        meta.assertNot[_4#lteq[_2]]
        meta.assertNot[_4#lt[_4]]
        meta.assertNot[_4#gt[_4]]
    }

    trait testPropagation {
        type plusPlus[n <: Dense] = n#increment#increment
        type id[n <: Dense] = n#increment#decrement
        type equaL[n <: Dense, m <: Dense] = plusPlus[n]# equal [id[m]]

        meta.assert[plusPlus[_4]# equal [_6]]
        meta.assert[plusPlus[_7]# equal [_9]]
        meta.assert[id[_9]# equal [_9]]
        meta.assert[id[_7]# equal [_7]]

        meta.assert[equaL[_3, _5]]
        meta.assert[equaL[_4, _6]]

        // Must work.
        type subsub[n <: Dense, m <: Dense] = n# minus[m]# minus[m]
        meta.assert[subsub[_9, _2]# equal [_5]]
    }

}
