

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package listtest


import com.github.okomok.mada

import mada.sequence.list
import junit.framework.Assert._


class UniqueTest {

    def testUnique: Unit = {
        val tr = list.Of(5,4,4,4,3,2,2,2,2,2,1)
        val sr = tr.unique
        assertEquals(list.Of(5,4,3,2,1), sr)
        assertEquals(list.Of(5,4,3,2,1), sr) // traverse again.
    }

    def testUniqueUnique: Unit = {
        val tr = list.Of(5,5,5,4,4,4,3,2,2,2,2,2,1)
        val sr = tr.unique.unique.unique
        assertEquals(list.Of(5,4,3,2,1), sr)
        assertEquals(list.Of(5,4,3,2,1), sr) // traverse again.
    }

    def testUnique0: Unit = {
        val tr = list.empty.of[Int]
        val sr = tr.unique
        assertTrue(tr.isEmpty)
    }

    def testUnique1: Unit = {
        val tr = list.Of(9)
        val sr = tr.unique
        assertEquals(list.Of(9), sr)
        assertEquals(list.Of(9), sr) // traverse again.
    }

    def testUnique2: Unit = {
        val tr = list.Of(9,9,9,9,9,9)
        val sr = tr.unique
        assertEquals(list.Of(9), sr)
        assertEquals(list.Of(9), sr) // traverse again.
    }

}
