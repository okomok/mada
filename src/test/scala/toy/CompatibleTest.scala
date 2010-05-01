

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package toy; package compatibletest


import com.github.okomok.mada

import junit.framework.Assert._


trait B[+T] {
    def imB: Unit = ()
}
class ToSome[+T](val _1: A[T])
object ToSome {
    implicit def toB[T](from: ToSome[T]): B[T] = from._1.toB // A --> B
}
trait A[+T] {
    def imA: Unit = ()

    def toB[T]: B[T] = new B[T]{}
    def toSome: ToSome[T]  = new ToSome(this)

    def youA[U >: T](a: A[U]): Unit = ()
}
object A {
    implicit def fromB[T](b: B[T]): A[T] = new A[T]{} // B --> A
}
object ap {
    def from[T](to: A[T]): A[T] = to
}


trait K[T] {
    def imK: Unit = ()
}
class ToSome_[T](override val _1: A_[T]) extends ToSome[T](_1)
object ToSome_ {
    implicit def toB[T](from: ToSome_[T]): K[T] = from._1.toK // A --> K
}
trait A_[T] extends A[T] {
    def toK: K[T] = new K[T]{}
    override def toSome : ToSome_[T] = new ToSome_(this)
}



class CompatibleTest {

    def testTrivial: Unit = {
        val a = new A[Int]{}
        val b = new B[Int]{}
        // a.imB // should not compile.
        a.toSome.imB
        a.youA(b)
        ap.from(b)
        ()
    }

    def testInheritance: Unit = {
        val a = new A_[Int]{}
        val b = new B[Int]{}
        // a.imB // should not compile.
        a.toSome.imB
        a.toSome.imK
        ()
    }
}
