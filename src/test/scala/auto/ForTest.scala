

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package autotest


import com.github.okomok.mada

import mada.auto.use
import mada.Auto
import junit.framework.Assert._


class ForTest extends org.scalatest.junit.JUnit3Suite {

    class MyFile(val name: String) extends java.io.Closeable {
        var disposed = false
        override def close = {
            assertFalse("disposed twice: " + name, disposed)
            disposed = true
        }
    }

    def identity[X](x: X) = x

    def testGocha: Unit = {
        val fs = new java.util.ArrayList[MyFile]
        for {
            name <- Array("foo", "bar", "buz")
            file <- use(new MyFile(name))
        } {
            fs.add(file)
        }

        assertEquals(3, fs.size)
        for (file <- mada.sequence.vector.from(fs)) {
            assertTrue(file.disposed)
        }
    }

}
