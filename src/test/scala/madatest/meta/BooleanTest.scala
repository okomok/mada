

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.hetero


import mada.Meta._
//import junit.framework.Assert._


class BooleanTest {
    def testTrivial: Unit = {
        assertEquals[`true`, `true`]
        assertEquals[`false`, `if`[`true`, `false`, `true`]]
        assertEquals[`false`, `if`[`false`, `true`, `false`]]
    }

    trait N extends Object {
        type feel <: Object
    }
    trait s extends N {
        override type isBoxed[void] = `false`
    }
    trait t extends s {
        type touch <: Object
    }

    type foo[a <: N, _s <: N, _t <: _s] = `if`[a#isBoxed[void], _s, _t]

    type feelIt[a <: N, _s <: N, _t <: _s] = `if`[a#isBoxed[void], _s, _t]#feel

    def testTypeSafe: Unit = {
       // instance[foo[newObject[String]]#touch]
        instance[foo[s, s, t]#touch]
        assertEquals[s, foo[newObject[String], s, t]]
        assertEquals[t, foo[s, s, t]]
    }
}
