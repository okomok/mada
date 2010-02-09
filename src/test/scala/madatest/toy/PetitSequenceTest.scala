

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package toy; package petitsequencetest


import junit.framework.Assert._


import scala.annotation.unchecked.uncheckedVariance


trait Iter[+A] {
    type Self <: Iter[A]
    val self = this.asInstanceOf[Self]
    def map[B, Result](f: A => B)(implicit _map: SpecializeMap[Self, A @uncheckedVariance, B, Result]): Result = _map(self, f)
}

trait SpecializeMap[-Self <: Iter[A], +A, -B, +Result] extends Function2[Self, A => B, Result] {
    def apply(self: Self, f: A => B): Result
}

object Iter {
    implicit def specializeMap[A, B] = new SpecializeMap[Iter[A], A, B, Iter[B]] {
        override def apply(self: Iter[A], f: A => B): Iter[B] = throw new Error()
    }
}

trait Vec[A] extends Iter[A] {
    override type Self = Vec[A]
}

object Vec {
    implicit def specializeMap[A, B] = new SpecializeMap[Vec[A], A, B, Vec[B]] {
        override def apply(self: Vec[A], f: A => B): Vec[B] = throw new Error()
    }
}


class PetitSequenceTest {
    def compileMe(i: Int) = {
//        import Iter.specializeMap
        val it = new Iter[Int] { }
        val jt: Iter[String] = it.map(_.toString) //(specializeMap[Iter[Int], Int, String])
    }

    def compileMe2(i: Int) = {
        val it = new Vec[Int] { }
        val jt: Vec[String] = it.map(_.toString)
    }

}

