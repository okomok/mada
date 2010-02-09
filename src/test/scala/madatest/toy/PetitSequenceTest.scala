

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package toy; package petitsequencetest


import junit.framework.Assert._


import scala.annotation.unchecked.uncheckedVariance


trait Iter[+A] {
    type Self <: Iter[A]
    val self = this.asInstanceOf[Self]
    final def map[B, Result](f: A => B)(implicit _map: OverrideMap[Self, A @uncheckedVariance, B, Result]): Result = _map(self, f)
}

trait OverrideMap[-Self <: Iter[A], +A, -B, +Result] extends Function2[Self, A => B, Result] {
    def apply(self: Self, f: A => B): Result
}

object Iter {
    implicit def overrideMap[A, B] = new OverrideMap[Iter[A], A, B, Iter[B]] {
        override def apply(self: Iter[A], f: A => B): Iter[B] = throw new Error()
    }
}

trait Vec[A] extends Iter[A] {
    override type Self = Vec[A]
}

object Vec {
    implicit def overrideMap[A, B] = new OverrideMap[Vec[A], A, B, Vec[B]] {
        override def apply(self: Vec[A], f: A => B): Vec[B] = throw new Error()
    }
}


class PetitSequenceTest {
    def compileMe(i: Int) = {
//        import Iter.overrideMap
        val it = new Iter[Int] { }
        val jt: Iter[String] = it.map(_.toString) //(overrideMap[Iter[Int], Int, String])
    }

    def compileMe2(i: Int) = {
        val it = new Vec[Int] { }
        val jt: Vec[String] = it.map(_.toString)
    }

}

