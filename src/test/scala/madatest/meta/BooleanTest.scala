

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.meta


import mada.Meta._
//import junit.framework.Assert._


class BooleanTest {
    def testTrivial: Unit = {
        assertSame[`true`, `true`]
        assertSame[`false`, `if`[`true`, `false`, `true`]]
        assertSame[`false`, `if`[`false`, `true`, `false`]]
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


    trait testPropagation {
        type incinc[n <: Nat] = natIf[n == _3N, n#increment, n]#increment
        assertLower[incinc[_2N], Nat]

        assert[`if`[_2N == _3N, _2N#increment, _2N]#increment == _3N]
        assert[incinc[_2N] == _3N]
        assert[incinc[_3N] == _5N]
    }


    /*
    trait N extends Object {
        type Self = N
        type feel <: Object
    }
    trait s extends N {
        type `this` = s
        override type isBoxed = `false`
    }
    trait t extends s {
        type `this` = t
        type touch <: Object
    }

    type foo[a <: N, _s <: N, _t <: _s] = `if`[a#isBoxed, _s, _t]

    type feelIt[a <: N, _s <: N, _t <: _s] = `if`[a#isBoxed, _s, _t]#feel

    def testTypeSafe: Unit = {
        nullOf[foo[s, s, t]#touch]
        assertSame[t, foo[s, s, t]]
    }
    */
}
