

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._


class SingleTest extends org.scalatest.junit.JUnit3Suite {

    def testVal {
        val rx = reactive.Val(12)
        expect(iterative.Of(12))(iterative.from(rx))
    }

    def testValCps {
        reactive.block {
            val x = reactive.Val(12).get
            expect(12)(x)
        }
    }

    def testLazyVal {
        val rx = reactive.LazyVal(12)
        expect(iterative.Of(12))(iterative.from(rx))
    }

    def testLazyValCps {
        reactive.block {
            val x = reactive.LazyVal(12).get
            expect(12)(x)
        }
    }

    def testVar {
        val rx = reactive.Var(12)
        expect(iterative.Of(12))(iterative.from(rx))
    }

    def testVarCps {
        reactive.block {
            val x = reactive.Var(12).get
            expect(12)(x)
        }
    }

}
