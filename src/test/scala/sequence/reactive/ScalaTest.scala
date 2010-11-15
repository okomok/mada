

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._


class ScalaTest extends org.scalatest.junit.JUnit3Suite {

    def testOption {
        reactive.block {
            val k = reactive.optional(12).each
            expect(12)(k)
        }
    }

    def testOptionEmpty {
        reactive.block {
            val k = reactive.optional(throw new Error("doh")).each
            throw new Error
        }
    }
}
