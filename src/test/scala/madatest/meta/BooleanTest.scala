

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.hetero


import mada.Meta._
//import junit.framework.Assert._


class BooleanTest {
    def testTrivial: Unit = {
        assertSame[`true`, `true`]
        assertSame[`false`, `if`[Boolean, `true`, `false`, `true`]]
        assertSame[`false`, `if`[Boolean, `false`, `true`, `false`]]
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

    type foo[a <: N, _s <: N, _t <: _s] = `if`[_s, a#isBoxed, _s, _t]

    type feelIt[a <: N, _s <: N, _t <: _s] = `if`[N, a#isBoxed, _s, _t]#feel

    def testTypeSafe: Unit = {
        unmeta[foo[s, s, t]#touch]
        assertSame[t, foo[s, s, t]]
    }


    trait M extends Object {
        type walk <: Object
    }

    sealed trait fn7 extends Function0 {
        type Result0 = Object
        type apply0 = N
    }

    sealed trait fn8 extends Function0 {
        type Result0 = Object
        type apply0 = M
    }

    sealed trait fn9 extends Function0 {
        type Result0 = Object
        type apply0 = t
    }

    def testLazy: Unit = {
        unmeta[lazyIf[`true`, fn7, fn8]#apply0#feel]
        unmeta[lazyIf[`false`, fn7, fn8]#apply0#walk]
        unmeta[lazyIf[`true`, lazyIf[`false`, fn7, fn8], fn9]#apply0#walk]
        assertSame[N, lazyIf[`true`, fn7, fn8]#apply0]
        assertSame[M, lazyIf[`false`, fn7, fn8]#apply0]
    }
}
