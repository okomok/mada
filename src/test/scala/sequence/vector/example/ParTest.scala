

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest; package example


    import com.github.okomok.mada.sequence._

    class ParTest extends org.scalatest.junit.JUnit3Suite {
        def testTrivial {
            val v = Vector(0,1,2,3,4).par
            v.map(_ + 10).seek(_ == 13) match {
                case Some(e) => expect(13)(e)
                case None => fail("doh")
            }

            val i = new java.util.concurrent.atomic.AtomicInteger(0)
            v.each {
                _ => i.incrementAndGet
            }
            expect(5)(i.get)
        }
    }
