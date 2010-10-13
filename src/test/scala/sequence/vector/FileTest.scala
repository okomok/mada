

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest


import com.github.okomok.mada

import mada.sequence.vector._
import mada.arm.using
import junit.framework.Assert._


class FileTest extends org.scalatest.junit.JUnit3Suite {

    def testRead: Unit = {
        val n = "./target/madatest_vector_FileTest_read.txt"

        using(new java.io.RandomAccessFile(n, "rw")) { f =>
            f.writeChars("abcde")
        }

        using(file[Char](n, "r")) { v =>
            assertEquals('c', v.nth(2))
            assertEquals(from("abcde"), v)

            // File resouce should NOT be owned by iterator. (F# is wrong, IMO.)
            assertEquals(from("abcdeabcdea"), v.asIterative.seal.cycle.take(11))
        }
    }

    def testWrite: Unit = {
        val n = "./target/madatest_vector_FileTest_write.txt"

        using(new java.io.RandomAccessFile(n, "rw")) { f =>
            f.writeChars("abcde")
        }

        using(file[Char](n, "rw")) { v =>
            v.nth(2) = 'C'
        }

        using(file[Char](n, "r")) { v =>
            assertEquals('C', v.nth(2))
            assertEquals(from("abCde"), v)
        }
    }

    def testOwnerShip: Unit = {
        val n = "./target/madatest_vector_FileTest_ownership.txt"

        using(new java.io.RandomAccessFile(n, "rw")) { f =>
            f.writeChars("abcde")
        }

        def aSeq = {
            val f: mada.Arm[Vector[Char]] = file[Char](n, "r")
            f//f.map(e => e).using(f) // owns myself?
        }

        using(aSeq) { v =>
            assertEquals(from("abcde"), v)
        }
    }

}
