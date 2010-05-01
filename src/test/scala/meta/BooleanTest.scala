

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package metatest


import com.github.okomok.mada

import mada.meta._
//import junit.framework.Assert._


class BooleanTest {
    def testUnmeta: Unit = {
        import junit.framework.Assert._
        assertEquals(true, unmeta[`true`, scala.Boolean])
        assertEquals(false, unmeta[`false`, scala.Boolean])
    }

    def testTrivial: Unit = {
        assertSame[`true`, `true`]
        assert[`false` == if_Boolean[`true`, `false`, `true`]]
        assert[`false` == if_Boolean[`false`, `true`, `false`]]

        assertSame[`false`, if_Boolean[`true`, `false`, `true`]]
        assertSame[`false`, if_Boolean[`false`, `true`, `false`]]
    }

    assert[`true` == `true`]
    assert[`false` == `false`]
    assert[`true` != `false`]
    assert[`false` != `true`]

    type myNot[b <: Boolean] = b#not
    assert[myNot[`true`] != `true`]
    assert[myNot[`false`] != `false`]
    assert[myNot[`true`] == `false`]
    assert[myNot[`false`] == `true`]

    trait testOperator {
        assert[`true` && `true`]
        assert[(`false` && `true`)#not]
        assert[`false` || `true`]
        assert[`true` || `false`]
    }

    trait testPropagation {
        type incinc[n <: Nat] = if_Nat[n == _3N, n#increment, n]#increment
        assertLower[incinc[_2N], Nat]

        assert[if_Nat[_2N == _3N, _2N#increment, _2N]#increment == _3N]
        assert[incinc[_2N] == _3N]
        assert[incinc[_3N] == _5N]
    }
}
