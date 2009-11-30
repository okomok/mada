

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package autotest


import mada.auto._
import mada.Auto
import junit.framework.Assert._


class FilterTest {

    class MyFile(val name: String) extends java.io.Closeable {
        var disposed = false
        override def close = {
            assertFalse("disposed twice: " + name, disposed)
            disposed = true
        }
    }

    def testFilter: Unit = {
        val f1 = new MyFile("f1")
        val f2 = new MyFile("f2")

        for {
            _f1 <- use(f1) if (_f1.name == "f1")
            _f2 <- use(f2) if (_f2.name == "wow")
        } {
            assertSame(_f1, f1)
            assertSame(_f2, f2)
        }

        assertTrue(f1.disposed)
        assertFalse(f2.disposed)
    }

    def testFilterVar: Unit = {
        val f1 = use(new MyFile("f1"))
        val f2 = use(new MyFile("f2"))

        for {
            a1 <- useVar(f1) if (a1.get.name == "f1")
            a2 <- useVar(f2) if (a2.get.name == "wow")
        } {
        }

        assertTrue(f1.get.disposed)
        assertFalse(f2.get.disposed)
    }
}
