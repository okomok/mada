

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.meta


import mada.Meta._
//import junit.framework.Assert._


class BooleanTest {
    def testUnmeta: Unit = {
        import junit.framework.Assert._
        assertEquals(true, unmeta[`true`, scala.Boolean])
        assertEquals(false, unmeta[`false`, scala.Boolean])
    }

    def testTrivial: Unit = {
        assertSame[`true`, `true`]
        assert[`false` == `if`[`true`, `false`, `true`]]
        assert[`false` == `if`[`false`, `true`, `false`]]

        //assertSame[`false`, `if`[`true`, `false`, `true`]] // wow, error
        //assertSame[`false`, `if`[`false`, `true`, `false`]] // wow, error
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
        type incinc[n <: Nat] = natIf[n == _3, n#increment, n]#increment
        assertLower[incinc[_2], Nat]

//        assert[`if`[_2 == _3, _2#increment, _2]#increment == _3]
        assert[incinc[_2] == _3]
        assert[incinc[_3] == _5]
    }
}
