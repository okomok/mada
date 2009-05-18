

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.toy.minivector


import junit.framework.Assert._


trait MiniSequence[+A] {
    val tid = 1
    def ++[B >: A](that: MiniSequence[B]): MiniSequence[B] = TAppend[B](this, that)
}
trait MiniSequenceForwarder[+A] extends MiniSequence[A] {
    override def ++[B >: A](that: MiniSequence[B]): MiniSequence[B] = that
}

trait MiniVector[A] extends MiniSequence[A] {
    val vid = 2
//    override def ++[B >: A](that: MiniSequence[B]): MiniSequence[B] = that
    def ++(that: MiniVector[A]): MiniVector[A] = VAppend(this, that)
}

class MyMiniVector[A] extends MiniVector[A] {
//    override def ++[B >: A](that: MiniSequence[B]): MiniSequence[B] = { k = 1; that }
//    override def ++(that: MiniVector[A]): MiniVector[A] = { k = 2; that }
}

trait MiniVectorForwarder[A] extends MiniSequenceForwarder[A] with MiniVector[A] {
    override def ++(that: MiniVector[A]): MiniVector[A] = that
}

case class TAppend[+A](_1: MiniSequence[A], _2: MiniSequence[A]) extends MiniSequence[A] {
}

case class VAppend[A](override val _1: MiniVector[A], override val _2: MiniVector[A]) extends TAppend[A](_1, _2) with MiniVector[A] {
}


class MiniVectorTest {

    def testCompile[A](t: MiniSequence[A], v: MiniVector[A]): Unit = {
        testCompile(v ++ t, v)
        testCompile(t, v ++ v)
    }

    def testExpr: Unit = {
        val v = new MyMiniVector[Int]
        val w = (v ++ v) ++ v
        w match {
            case VAppend(VAppend(v1, v2), v3) => assertEquals(2, v1.vid)
            case _ => fail("doh")
        }
        mada.as[MiniSequence[Int]](w) match {
            case TAppend(TAppend(t1, t2), t3) => assertEquals(1, t1.tid)
            case _ => fail("doh")
        }
    }

}
