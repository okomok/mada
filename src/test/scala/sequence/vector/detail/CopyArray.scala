

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest; package detail


import junit.framework.Assert._


object CopyArray {
    def apply[A: ClassManifest](a: Array[A]): Array[A] = {
        val b = new Array[A](a.length)
        var i = 0
        for (e <- a) { b(i) = e; i = i + 1 }
        b
    }
}


object CopyReverseArray {
    def apply[A: ClassManifest](a: Array[A]): Array[A] = {
        val b = new Array[A](a.length)
        a.toList.reverse.iterator.copyToArray(b, 0)
        b
    }
}


class CopyArrayTest extends junit.framework.TestCase {
    def sample = Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4)

    def testCopy {
        val a = CopyArray(sample)
        assertNotSame(sample, a)
        assertTrue(arrayEquals(sample, a))
    }

    def testCopyReverse {
        val a = CopyReverseArray(sample)
        assertNotSame(sample, a)
        assertTrue(arrayEquals(Array(4,11,15,12,0,23,4,6,13,8,19,17,14,18,0), a))
    }

    def testArrayEquals {
        val a = Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4)
        assertNotSame(sample, a)
        assertTrue(arrayEquals(sample, a))
        val b = Array(0,18,14,17,19, 8,13,99, 4,23, 0,12,15,11, 4)
        assertFalse(arrayEquals(sample, b))
    }

    private def arrayEquals[A](a1: Array[A], a2: Array[A]): Boolean = {
        if (a1.length != a2.length)
            return false

        var i = 0
        while (i != a1.length) {
            if (a1(i) != a2(i)) { return false }
            i = i + 1
        }

        true
    }
}
