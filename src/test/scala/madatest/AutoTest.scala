

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


import mada.Auto
import junit.framework.Assert._


object MyFile {
    implicit object myAuto extends Auto[MyFile] {
        override def dispose(x: MyFile) = x.disposed = true
    }
}

class MyFile {
    var disposed = false
    def read: Unit = { }
}


class AutoTest {
    def testTrivial: Unit = {
        val file = new MyFile
        assertFalse(file.disposed)
        Auto(file){ f =>
            f.read
        }
        assertTrue(file.disposed)
    }

    def testThrow: Unit = {
        val file = new MyFile
        var thrown = false
        assertFalse(file.disposed)
        assertFalse(thrown)
        try {
            Auto(file){ f =>
                f.read
                throw new Error("wow")
            }
        } catch {
            case _: Error => thrown = true
        }
        assertTrue(thrown)
        assertTrue(file.disposed)
    }

    def testEligibles: Unit = {
        Auto(new java.io.StringReader("abc")){ r =>
            ()
        }
    }
}
