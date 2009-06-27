

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.vectortest


import mada.sequence.vector._
import mada.auto.using
import junit.framework.Assert._


class FileTest {

    def testRead: Unit = {
        val n = "./target/madatest_vector_FileTest_read.txt"

        using(new java.io.RandomAccessFile(n, "rw")) { f =>
            f.writeChars("abcde")
        }

        using(charFile(n, "r")) { v =>
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

        using(charFile(n, "rw")) { v =>
            v.nth(2) = 'C'
        }

        using(charFile(n, "r")) { v =>
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
            val f = charFile(n, "r")
            f.autoRef.map(e => e).using(f)
        }

        using(aSeq) { v =>
            assertEquals(from("abcde"), v)
        }
    }

}
