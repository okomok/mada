

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest


import com.github.okomok.mada

import mada.sequence.{list,vector,iterative}
import junit.framework.Assert._


class OptionTest extends org.scalatest.junit.JUnit3Suite {

    def evenOption(i: Int): Option[Int] = if (i % 2 == 0) Some(i) else None

    // vector doesn't have flatMap and flatten. (so, delegated to iterative.)
    def testVector: Unit = {
        val evens =
            for {
                e <- vector.Of(1,2,3,4,5)
                x <- evenOption(e)
            } yield x

        assertEquals(list.Of(2,4), evens)
    }

    def testList: Unit = {
        val evens =
            for {
                e <- list.Of(1,2,3,4,5)
                x <- evenOption(e)
            } yield x

        assertEquals(list.Of(2,4), evens)
    }

    def testIterative: Unit = {
        val evens =
            for {
                e <- iterative.Of(1,2,3,4,5)
                x <- evenOption(e)
            } yield x

        assertEquals(list.Of(2,4), evens)
    }


}
