

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package autotest


import com.github.okomok.mada

import mada.auto._
import mada.Auto
import junit.framework.Assert._


class DependTest extends org.scalatest.junit.JUnit3Suite {

    class MyFile(val name: String) extends java.io.Closeable {
        var disposed = false
        override def close = {
            assertFalse("disposed twice: " + name, disposed)
            disposed = true
        }
    }

    def depends[X](f: MyFile, x: X) = f

    def testTrivial: Unit = {
        val f1 = new MyFile("f1")
        val f2 = new MyFile("f2")

        for {
            _f1 <- use(f1)
            _f2 <- use(depends(f2, _f1))
        } {
            assertSame(_f1, f1)
            assertSame(_f2, f2)
        }

        assertTrue(f1.disposed)
        assertTrue(f2.disposed)
    }

}
