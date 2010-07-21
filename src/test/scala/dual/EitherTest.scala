

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest


import com.github.okomok.mada.dual
import dual._
import junit.framework.Assert._
import nat.peano.Literal._
import nat.Peano


class EitherTest extends org.scalatest.junit.JUnit3Suite {


    def testTrivialLeft {
        type s = Left[_3]
        val s: s = Left(_3)
        meta.assert[s#isLeft]
        assertEquals(`true`, s.isLeft)
        meta.assertNot[s#isRight]
        assertEquals(`false`, s.isRight)
    }

    def testTrivialRight {
        type s = Right[_3]
        val s: s = Right(_3)
        meta.assert[s#isRight]
        assertEquals(`true`, s.isRight)
        meta.assertNot[s#isLeft]
        assertEquals(`false`, s.isLeft)
    }


    def testUndualLeft {
        type s = Left[_3]
        val s: s = Left(_3)
        meta.assertSame[scala.Left[Int, _], s#undual]
        assertEquals(scala.Left(3), s.undual)
    }

    def testUndualRight {
        type s = Right[_3]
        val s: s = Right(_3)
        meta.assertSame[scala.Right[_, Int], s#undual]
        assertEquals(scala.Right(3), s.undual)
    }


    def testMatchLeft {
        type s = Left[_3]
        val s: s = Left(_3)
        (s: Either) match {
            case Left(a) =>
            case Right(b) => fail("doh")
        }
        ()
    }

    def testMatchRight {
        type s = Right[_3]
        val s: s = Right(_3)
        (s: Either) match {
            case Left(a) => fail("doh")
            case Right(b) =>
        }
        ()
    }


    def testGetLeft {
        type s = Left[_3]
        val s: s = Left(_3)
        meta.assertSame[_3, s#get]
        val m: s#get = s.get
        assertEquals(_3, m)
    }

    def testGetRight {
        type s = Right[_3]
        val s: s = Right(_3)
        meta.assertSame[_3, s#get]
        val m: s#get = s.get
        assertEquals(_3, m)
    }


    def testSwapLeft {
        type s = Left[_3]
        val s: s = Left(_3)
        meta.assertSame[Right[_3], s#swap]
        val m: s#swap = s.swap
        assertEquals(Right(_3), m)
    }

    def testSwapRight {
        type s = Right[_3]
        val s: s = Right(_3)
        meta.assertSame[Left[_3], s#swap]
        val m: s#swap = s.swap
        assertEquals(Left(_3), m)
    }


    def testJoinLeftLeft {
        type s = Left[Right[_3]]
        val s: s = Left(Right(_3))
        meta.assertSame[Right[_3], s#joinLeft]
        val m: s#joinLeft = s.joinLeft
        assertEquals(Right(_3), m)
    }

    def testJoinLeftRight {
        type s = Right[_3]
        val s: s = Right(_3)
        meta.assertSame[Right[_3], s#joinLeft]
        val m: s#joinLeft = s.joinLeft
        assertEquals(Right(_3), m)
    }

    def testJoinRightRight {
        type s = Right[Left[_3]]
        val s: s = Right(Left(_3))
        meta.assertSame[Left[_3], s#joinRight]
        val m: s#joinRight = s.joinRight
        assertEquals(Left(_3), m)
    }

    def testJoinRightLeft {
        type s = Left[_3]
        val s: s = Left(_3)
        meta.assertSame[Left[_3], s#joinRight]
        val m: s#joinRight = s.joinRight
        assertEquals(Left(_3), m)
    }


    case class Plus1() extends Function1 {
        override def self = this
        override type self = Plus1
        override def apply[n <: Any](n: n): apply[n] = n.asInstanceOfNat.increment
        override type apply[n <: Any] = n#asInstanceOfNat#increment
    }

    case class Plus2() extends Function1 {
        override def self = this
        override type self = Plus2
        override def apply[n <: Any](n: n): apply[n] = n.asInstanceOfNat.increment.increment
        override type apply[n <: Any] = n#asInstanceOfNat#increment#increment
    }

    def testFoldLeft {
        type s = Left[_3]
        val s: s = Left(_3)
        meta.assertSame[_4, s#fold[Plus1, Plus2]]
        val m: s#fold[Plus1, Plus2] = s.fold(Plus1(), Plus2())
        assertEquals(_4, m)
    }

    def testFoldRight {
        type s = Right[_3]
        val s: s = Right(_3)
        meta.assertSame[_5, s#fold[Plus1, Plus2]]
        val m: s#fold[Plus1, Plus2] = s.fold(Plus1(), Plus2())
        assertEquals(_5, m)
    }

}
