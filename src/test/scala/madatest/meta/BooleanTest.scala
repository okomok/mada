

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
        assert[![`false` && `true`]]
        assert[`false` || `true`]
        assert[`true` || `false`]
    }

    trait testPropagation {
        type incinc[n <: Int] = natIf[n == _3I, n#increment, n]#increment
        assertLower[incinc[_2I], Int]

        assert[`if`[_2I == _3I, _2I#increment, _2I]#increment == _3I]
        assert[incinc[_2I] == _3I]
        assert[incinc[_3I] == _5I]
    }

    trait testPropagation2 {
/*
        type incinc[n <: Int] = ifThen[n == _3I, n#increment]#increment
        assert[incinc[_3I] == _5I]
*/
    }


    /*
    trait N {
        type Self = N
        type feel
    }
    trait s extends N {
        type `this` = s
        override type isBoxed = `false`
    }
    trait t extends s {
        type `this` = t
        type touch
    }

    type foo[a <: N, _s <: N, _t <: _s] = `if`[a#isBoxed, _s, _t]

    type feelIt[a <: N, _s <: N, _t <: _s] = `if`[a#isBoxed, _s, _t]#feel

    def testTypeSafe: Unit = {
        nullOf[foo[s, s, t]#touch]
        assertSame[t, foo[s, s, t]]
    }
    */
}
