

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


import mada.{auto, Auto}
import junit.framework.Assert._


object MyFile {
    implicit def _toAuto(from: MyFile): Auto[MyFile] = new Auto[MyFile] {
        override def autoRef = from
        override def autoEnd = from.disposed = true
    }
}
class MyFile {
    var disposed = false
    def read: Unit = { }
}


class HisFile[A] extends Auto[HisFile[A]] {
    var disposed = false
    override def autoRef = this // bad way
    override def autoEnd = disposed = true
    def read: Unit = { }
}


class AutoTest {
    def testTrivial: Unit = {
        val file = new MyFile
        assertFalse(file.disposed)
        auto.using(file){ f =>
            f.read
        }
        assertTrue(file.disposed)
    }

    def testHis: Unit = {
        val file = new HisFile[Int]
        assertFalse(file.disposed)
        val tmp = auto.using(file){ f =>
            f.read; 3
        }
        assertTrue(file.disposed)
        assertEquals(3, tmp)
    }

    def testThrow: Unit = {
        val file = new MyFile
        var thrown = false
        assertFalse(file.disposed)
        assertFalse(thrown)
        try {
            auto.using(file){ f =>
                f.read
                throw new Error("wow")
            }
        } catch {
            case _: Error => thrown = true
        }
        assertTrue(thrown)
        assertTrue(file.disposed)
    }

    def testCloseable: Unit = {
        auto.using(new java.io.StringReader("abc")){ r =>
            val k: java.io.StringReader = r // keeps type.
            ()
        }
    }

    def testVarArg: Unit = {
        val file1, file2, file3 = new MyFile
        auto.using(file1, file2, file3) { (f1, f2, f3) =>
            ()
        }
        assertTrue(file1.disposed)
        assertTrue(file2.disposed)
        assertTrue(file3.disposed)
    }
}
