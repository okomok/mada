

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest


import com.github.okomok.mada

import mada.{auto, Auto}
import junit.framework.Assert._
import mada.auto.use


object MyFile {
    implicit def _toAuto(from: MyFile): Auto[MyFile] = new Auto[MyFile] {
        override def get = from
        override def end = from.ended = true
    }
}
class MyFile {
    var ended = false
    def read: Unit = { }
}


class HisFile[A] extends Auto[HisFile[A]] {
    var began = false
    var ended = false
    override def begin = began = true
    override def get = this // bad way
    override def end = ended = true
    def read: Unit = { }
}


class MaybeThrow(b: Boolean) extends Auto[MaybeThrow] {
    var ended = false
    override def get = this
    override def begin = if (b) throw new Error // might throw.
    override def end = ended = true // Note `end` shall not throw.
}



class AutoTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial: Unit = {
        val file = new MyFile
        assertFalse(file.ended)
        auto.using(file){ f =>
            f.read
        }
        assertTrue(file.ended)
    }

    def testTrivialSugar: Unit = {
        val file = new MyFile
        assertFalse(file.ended)
        for {
            f <- auto.use(file)
        } {
            f.read
        }
        assertTrue(file.ended)
    }

    def testHis: Unit = {
        val file = new HisFile[Int]
        assertFalse(file.ended)
        val tmp = auto.using(file){ f =>
            f.read; 3
        }
        assertTrue(file.ended)
        assertEquals(3, tmp)
    }

    def testThrow: Unit = {
        val file = new MyFile
        var thrown = false
        assertFalse(file.ended)
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
        assertTrue(file.ended)
    }

    def testCloseable: Unit = {
        auto.using(new java.io.StringReader("abc")){ r =>
            val k: java.io.StringReader = r // keeps type.
            ()
        }
    }
/*
    def testVarArg: Unit = {
        val file1, file2, file3 = new MyFile
        auto.using(file1, file2, file3) { (f1, f2, f3) =>
            ()
        }
        assertTrue(file1.ended)
        assertTrue(file2.ended)
        assertTrue(file3.ended)
    }
*/
    def testExceptionSafe: Unit = {
        val t1, t2 = new MaybeThrow(false)
        val t3, t4, t5 = new MaybeThrow(true)
        try {
            for (e1 <- t1; e2 <- t2; e3 <- t3; e4 <- t4; e5 <- t5) {
                ()
            }
        } catch {
            case e: Error => ()
        }
        assertTrue(t1.ended)
        assertTrue(t2.ended)
    }

    def testUsedWith: Unit = {
        val file1, file2, file3 = new MyFile
        auto.using(auto.usedWith(List(file1, file2, file3), 10)) { e =>
            assertEquals(10, e)
        }
        assertTrue(file1.ended)
        assertTrue(file2.ended)
        assertTrue(file3.ended)
    }

    def testForeach: Unit = {
        val F1 = new HisFile[Int]
        val F2 = new HisFile[Double]
        val F3 = new HisFile[String]
        assertFalse(F1.began)
        assertFalse(F2.began)
        assertFalse(F3.began)
        assertFalse(F1.ended)
        assertFalse(F2.ended)
        assertFalse(F3.ended)
        for (file1 <- use(F1); file2 <- use(F2); file3 <- use(F3)) {
            assertTrue(file1.began)
            assertTrue(file2.began)
            assertTrue(file3.began)
            Tuple3(file1, file2, file3)
            ()
        }
        assertTrue(F1.get.ended)
        assertTrue(F2.get.ended)
        assertTrue(F3.get.ended)
    }

    def testForeach2: Unit = {
        val F1 = new HisFile[Int]
        val F2 = new HisFile[Double]
        val F3 = new HisFile[String]
        assertFalse(F1.began)
        assertFalse(F2.began)
        assertFalse(F3.began)
        assertFalse(F1.ended)
        assertFalse(F2.ended)
        assertFalse(F3.ended)
        val r =
            for (file1 <- use(F1); file2 <- use(F2); file3 <- use(F3)) {
                Tuple6(
                    file1.began, file2.began, file3.began,
                    file1.ended, file2.ended, file3.ended )
            }

        assertTrue(r._1)
        assertTrue(r._2)
        assertTrue(r._3)
        assertFalse(r._4)
        assertFalse(r._5)
        assertFalse(r._6)

        assertTrue(F1.ended)
        assertTrue(F2.ended)
        assertTrue(F3.ended)
    }

    def testForeachOrder: Unit = {
        val a = new java.util.ArrayList[Int]
        val x1 = new Auto[Int] {
            override def get = 1
            override def begin = a.add(1)
            override def end = a.add(9)
        }
        val x2 = new Auto[Int] {
            override def get = 2
            override def begin = a.add(2)
            override def end = a.add(8)
        }
        val x3 = new Auto[Int] {
            override def get = 3
            override def begin = a.add(3)
            override def end = a.add(7)
        }

        val r =
            for (y1 <- use(x1); y2 <- use(x2); y3 <- use(x3)) {
            }

        import mada.sequence.iterative
        assertEquals(iterative.Of(1,2,3,7,8,9), iterative.from(a))
    }

}
