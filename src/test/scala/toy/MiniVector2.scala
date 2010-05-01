

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package toy; package minivector2


import junit.framework.Assert._


case class Append[+A](_1: MiniSequence[A], _2: MiniSequence[A]) extends MiniSequence[A]

//trait _Append[T1, T2, R] extends Function2[T1, T2, R]
trait _Append[B, T1 <: MiniSequence[B], T2 <: MiniSequence[B], R <: MiniSequence[B]] extends Function2[T1, T2, R]

trait _Map[T1 <: MiniSequence[A], A, B, R <: MiniSequence[B]] extends Function2[T1, A => B, R]
trait _Lookma[-This, -A, +That]

trait MiniSequence[+A] {
    type Element = A
    type This <: MiniSequence[A]
    def self: This = this.asInstanceOf[This]
    val tid = 1
    def filter(p: A => Boolean): This = self
//    def map[B, R <: MiniSequence[B]](f: A => B)(implicit s: _Map[This, A, B, R]): R = s(self, f)
    def lookma[B, That](f: A => B)(implicit s: _Lookma[This, A, That]): That = throw new Error
    def imSequence = ()
//    final def ++[B >: A, That <: MiniSequence[B], Result <: MiniSequence[B]](that: That)(implicit _append: _Append[B, This, That, Result]): Result = _append(self, that)
//    final def **[B >: A, That <: MiniSequence[B], Result](that: That)(implicit _append: _Append[This, That, Result]): Result = ++[B, That, Result](that)(_append)
}

object MySequence extends MiniSequence[Int]

trait MiniSequenceForwarder[+A] extends MiniSequence[A] {
 //   override type This <: MiniSequenceForwarder[A]
    def delegate: MiniSequence[A]
   // override def ++[B >: A, That <: MiniSequence[B], Result](that: That)(implicit _append: _Append[This, That, Result]): Result = delegate.++[B, That, Result](that)(_append)
}

/*
object MiniSequenceForwarder {
    implicit def appendImpl[This <: MiniSequenceForwarder[_], That <: MiniSequence[_]] = new _Append[This, That, Int] {
        override def apply(x: This, y: That) =
    }
}*/


object MiniSequence {
    implicit def seqLookmaImpl[A, B] = new _Lookma[MiniSequence[A], A, MiniSequence[B]] {
        println("Seq")
    }
/*
    sealed class OfWhat[B, This <: MiniSequence[B]](_this: This) {
        final def ++[That <: MiniSequence[B], Result <: MiniSequence[B]](that: That)(implicit _append: _Append[B, This, That, Result]) = _this._append_[B, This, That, Result](_this, that)(_append)
    }
    implicit def ofWhat[B, This <: MiniSequence[B]](_this: This): OfWhat[B, This] = new OfWhat[B, This](_this)
*/
  //  implicit def appendImpl[B, This <: MiniSequence[B], That <: MiniSequence[B]] = new _Append[B, This, That, MiniSequence[B]] {
        // override def apply(x: This, y: That) = Append[B](x, y)
  //  }

  //  implicit def seqMapImpl[A, B] = new _Map[MiniSequence[A], A, B, MiniSequence[B]] {
  //       override def apply(_this: MiniSequence[A], f: A => B) = throw new Error
  //  }
}


case class VAppend[A](_1: MiniVector[A], _2: MiniVector[A]) extends MiniVector[A]


trait MiniVector[A] extends MiniSequence[A] {
    override type This <: MiniVector[A]
    override def self = this.asInstanceOf[This]
    val vid = 2
    override def filter(p: A => Boolean): This = self

    def imVector = ()
}

object MyVector extends MiniVector[String]


object MiniVector {
/*
    implicit def appendImpl[B, This <: MiniVector[B], That <: MiniVector[B]] = new _Append[B, This, That, MiniVector[B]] {
        override def apply(x: This, y: That) = VAppend[B](x, y)
    }*/
/*
    implicit def seqMapImpl[A, B] = new _Map[MiniVector[A], A, B, MiniVector[A]] {
         override def apply(_this: MiniVector[A], f: A => B) = throw new Error
    }
*/
    implicit def seqLookmaImpl[A, B] = new _Lookma[MiniVector[A], A, MiniVector[B]] {
        println("Vec")
    }
}

class MiniVector2Test {

    def testTrivial: Unit = {
        val v = new MiniVector[Int]{}
        v.filter(_ => true).imVector
        val s = new MiniSequence[Int]{}//.asInstanceOf[MiniSequence[Int]]
        s.filter(_ => true).imSequence

//        v.map(_ => "wow").imVector
//        s.map(_ => "wow").imSequence

     //   MyVector.lookma(_ => "wow")
try{
     //   v.lookma(_ => "wow")
} catch {
    case _ => ()
}
     //   s.lookma(_ => "wow")

//        v ++ v
    //    s.++[Int, MiniSequence[Int], MiniSequence[Int]](s) // Hmm, can't infer B.
        //s ++ v
        //v ++ s
        //v ++ v

        ()
    }

}

