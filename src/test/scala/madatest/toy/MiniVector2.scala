

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.toy.minivector2


import junit.framework.Assert._


trait _Append[T1, T2, R] {
    def apply(x: T1, y: T2): R
}

trait MiniSequence[+A] {
    type This <: MiniSequence[A]
    val tid = 1
    def ++[B >: A, That <: MiniSequence[B], Result](that: That)(implicit _append: _Append[This, That, Result]): Result = _append(this.asInstanceOf[This], that)
}

object MiniSequence {
    implicit def appendImpl[This <: MiniSequence[_], That <: MiniSequence[_]] = new _Append[This, That, Int] {
        override def apply(x: This, y: That) = 3
    }
}


trait MiniVector[A] extends MiniSequence[A] {
    override type This <: MiniVector[A]
    val vid = 2
}

object MiniVector {
    implicit def appendImpl[This <: MiniVector[_], That <: MiniSequence[_]] = new _Append[This, That, Int] {
        override def apply(x: This, y: That) = 4
    }
}

class MiniVector2Test {

    def testTrivial: Unit = {
        val v = new MiniVector[Int]{}
        val w = new MiniSequence[Int]{}
        assertEquals(4, v ++ w)
        assertEquals(3, w ++ w)
    }

}
