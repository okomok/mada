

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence.reactive
import junit.framework.Assert._
import mada.sequence.iterative


class ReactTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        val a = reactive.Of(1,2,3,2,5)
        val out = new java.util.ArrayList[Int]
        a react {
            case 2 => out.add(20)
            case 3 => out.add(30)
        } start;
        assertEquals(iterative.Of(20,30,20), iterative.from(out))
    }

    def testTotal {
        val a = reactive.Of(1,2,3,4,5)
        val out = new java.util.ArrayList[Int]
        a react {
            case x => out.add(x)
        } start;
        assertEquals(iterative.Of(1,2,3,4,5), iterative.from(out))
    }


    def testTotal2 {
        val a = reactive.Of(1,2,3,4,5)
        val out = new java.util.ArrayList[Int]
        a reactTotal {
            x => out.add(x)
        } take {
            3
        } start;
        assertEquals(iterative.Of(1,2,3,4,5), iterative.from(out))
    }

}
