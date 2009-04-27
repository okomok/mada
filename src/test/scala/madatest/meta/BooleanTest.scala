

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.meta


import mada.Meta._
//import junit.framework.Assert._


class BooleanTest {
    def testTrivial: Unit = {
        assertSame[`true`, `true`]
        assertSame[`false`, `if`[`true`, `false`, `true`, Boolean]]
        assertSame[`false`, `if`[`false`, `true`, `false`, Boolean]]
    }

    trait N extends Object {
        type feel <: Object
    }
    trait s extends N {
        override type isBoxed = `false`
    }
    trait t extends s {
        type touch <: Object
    }

    type foo[a <: N, _s <: N, _t <: _s] = `if`[a#isBoxed, _s, _t, _s]

    type feelIt[a <: N, _s <: N, _t <: _s] = `if`[a#isBoxed, _s, _t, N]#feel

    def testTypeSafe: Unit = {
        unmeta[foo[s, s, t]#touch]
        assertSame[t, foo[s, s, t]]
    }


    trait M extends Object {
        type walk <: Object
    }

    trait fn7 extends Function0 {
        type apply0 = N
    }

    trait fn8 extends Function0 {
        type apply0 = M
    }

    trait fn9 extends Function0 {
        type apply0 = t
    }
}
