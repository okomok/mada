

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package toy; package petitsequencetest


import junit.framework.Assert._


import scala.annotation.unchecked.uncheckedVariance


trait Iter[+A] {
    type Self <: Iter[A]
    val self = this.asInstanceOf[Self]
    def map[B, Result](f: A => B)(implicit _map: _Map[Self, A @uncheckedVariance, B, Result]): Result = _map(self, f)
    def append[That >: Self, Result](that: That)(implicit _append: _Append[Self, That, Result]): Result = _append(self, that)
}

trait _Map[-Self, +A, -B, +Result] extends Function2[Self, A => B, Result] {
    def apply(self: Self, f: A => B): Result
}

trait _Append[-Self, -That >: Self, +Result] extends Function2[Self, That, Result] {
    def apply(self: Self, that: That): Result
}

object Iter {
    implicit def _map[A, B] = new _Map[Iter[A], A, B, Iter[B]] {
        override def apply(self: Iter[A], f: A => B): Iter[B] = throw new Error()
    }

    implicit def _append[A] = new _Append[Iter[A], Iter[A], Iter[A]] {
        override def apply(self: Iter[A], that: Iter[A]): Vec[A] = throw new Error()
    }
}

trait Vec[A] extends Iter[A] {
    override type Self = Vec[A]
}

object Vec {
    implicit def _map[A, B] = new _Map[Vec[A], A, B, Vec[B]] {
        override def apply(self: Vec[A], f: A => B): Vec[B] = throw new Error()
    }

    implicit def _append[A] = new _Append[Vec[A], Vec[A], Vec[A]] {
        override def apply(self: Vec[A], that: Vec[A]): Vec[A] = throw new Error()
    }
}


class PetitSequenceTest {
    def compileMe(i: Int) = {

        val it = new Iter[Int] { }
        val jt: Iter[String] = it.map(_.toString) //(_map[Iter[Int], Int, String])

        val kt: Iter[Int] = it append it

        val iv = new Vec[Int] { }
        val jv: Vec[String] = iv.map(_.toString)

        val kv: Vec[Int] = iv append iv

        val xv: Iter[Int] = it append iv

        val yv: Iter[Int] = iv append it
    }

}

