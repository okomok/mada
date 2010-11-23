

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._


class BlockTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        val a = new java.util.ArrayList[Int]
        reactive.block { * =>
            for (x <- *(reactive.Of(1,2,3))) {
                a.add(x); ()
            }
            a.add(99); ()
        }
        expect(Vector(1,2,3,99))(Vector.from(a))
    }

    def testValueDiscarding {
        val a = new java.util.ArrayList[Int]
        reactive.block { * =>
            for (x <- *(reactive.Of(1,2,3))) {
                a.add(x)
                "discard me"
            }
            a.add(99)
            "discard me"
        }
        expect(Vector(1,2,3,99))(Vector.from(a))
    }

    def testNested {
        val a = new java.util.ArrayList[Int]
        reactive.block { * =>
            for (x <- *(reactive.Of(1,2,3))) {
                a.add(x)
                for (y <- *(reactive.Of(10+x,20+x))) {
                    a.add(y); ()
                }
                a.add(98); ()
            }
            a.add(99); ()
        }
        expect(Vector(1,11,21,98,2,12,22,98,3,13,23,98,99))(Vector.from(a))
    }

    def testNestedValueDiscarding {
        val a = new java.util.ArrayList[Int]
        reactive.block { * =>
            for (x <- *(reactive.Of(1,2,3))) {
                a.add(x)
                for (y <- *(reactive.Of(10+x,20+x))) {
                    a.add(y)
                    "discard me"
                }
                a.add(98)
                "discard me"
            }
            a.add(99)
            "discard me"
        }
        expect(Vector(1,11,21,98,2,12,22,98,3,13,23,98,99))(Vector.from(a))
    }

}
