

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package armtest


import com.github.okomok.mada

import mada.{arm, Arm}
import arm.{use, scope}
import junit.framework.Assert._


class CpsTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        val r1 = TrivialResource("res1")
        val r2 = TrivialResource("res2")
        val r3 = TrivialResource("res3")
        scope {
            val s1 = use(r1)
            val s2 = use(r2)
            val s3 = use(r3)
            assertEquals(s1, "res1")
            assertEquals(s2, "res2")
            assertEquals(s3, "res3")
            999
        }
        assertTrue(r1.began)
        assertTrue(r1.ended)
        assertTrue(r2.began)
        assertTrue(r2.ended)
        assertTrue(r3.began)
        assertTrue(r3.ended)
    }

    def testThrow {
        val r1 = TrivialResource("res1")
        val r2 = TrivialResource("res2")
        val r3 = TrivialResource("res3", true)
        var thrown = false

        try {
            scope {
                val s1 = use(r1)
                val s2 = use(r2)
                val s3 = use(r3)
                assertEquals(s1, "res1")
                assertEquals(s2, "res2")
                assertEquals(s3, "res3")
            }
        } catch {
            case _: Error => thrown = true
        }
        assertTrue(thrown)

        assertTrue(r1.began)
        assertTrue(r1.ended)
        assertTrue(r2.began)
        assertTrue(r2.ended)
        assertFalse(r3.began)
        assertFalse(r3.ended)
    }


    val arr = new java.util.ArrayList[Int]
    import mada.sequence.Reactive

    case class Res1[A](res: A) extends Reactive[A] {
        override def forloop(f: A => Unit, k: => Unit) = {
            arr.add(10)
            try {
                f(res)
            } finally {
                arr.add(11)
            }
        }
    }
    case class Res2[A](res: A) extends Reactive[A] {
        override def forloop(f: A => Unit, k: => Unit) = {
            arr.add(20)
            try {
                f(res)
            } finally {
                arr.add(21)
            }
        }
    }
    case class Res3[A](res: A) extends Reactive[A] {
        override def forloop(f: A => Unit, k: => Unit) = {
            arr.add(30)
            try {
                f(res)
            } finally {
                arr.add(31)
            }
        }
    }

    def testOrder {
        val r1 = Res1("res1")
        val r2 = Res2("res2")
        val r3 = Res3("res3")

        scope {
            val s1 = r1.each
            val s2 = r2.each
            val s3 = r3.each
            arr.add(1)
            arr.add(2)
            arr.add(3)
            ()
        }

        import mada.sequence.iterative
        assertEquals(iterative.Of(10,20,30,1,2,3,31,21,11), iterative.from(arr))
    }


    def testCloseable {
        val r1 = new TrivialCloseable
        val r2 = new TrivialCloseable
        val r3 = new TrivialCloseable
        scope {
            val s1 = use(r1)
            val s2 = use(r2)
            val s3 = use(r3)
            ()
        }
        assertTrue(r1.ended)
        assertTrue(r2.ended)
        assertTrue(r3.ended)
    }
}
