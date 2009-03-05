

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


import mada.Auto
import junit.framework.Assert._


class MyFile extends Auto {
    var disposed = false
    override def dispose = { disposed = true }
    def read: Unit = { }
}

class YourFile {
    var disposed = false
    def read: Unit = { }
}

class AutoTest {
    def testTrivial: Unit = {
        val file = new MyFile
        assertFalse(file.disposed)
        // Predef.identity is coming.
        Auto(file){ f =>
            f.read
        }
        assertTrue(file.disposed)
    }

    implicit def yourFile2Auto(f: YourFile): Auto = new Auto {
        override def dispose = { f.disposed = true }
    }

    def testConversion: Unit = {
        val file = new YourFile
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
}
