

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.hetero


import mada.Meta._
//import junit.framework.Assert._


class BooleanTest {
    def testTrivial: Unit = {
        assertSame[`true`, `true`]
        assertSame[`false`, `if`[`true`, `false`, `true`]]
        assertSame[`false`, `if`[`false`, `true`, `false`]]
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

    type foo[a <: N, _s <: N, _t <: _s] = `if`[a#isBoxed, _s, _t]

    type feelIt[a <: N, _s <: N, _t <: _s] = `if`[a#isBoxed, _s, _t]#feel

    def testTypeSafe: Unit = {
       // instance[foo[newObject[String]]#touch]
        unmeta[foo[s, s, t]#touch]
        assertSame[s, foo[newObject[String], s, t]]
        assertSame[t, foo[s, s, t]]
    }
}
