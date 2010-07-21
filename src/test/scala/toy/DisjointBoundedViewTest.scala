

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package toy; package disjointboundedview


import junit.framework.Assert._


class DisjointBoundedView[A,B](_1: Option[A], _2: Option[B]) {
    def a = { println("a access"); _1 }
    def b = { println("b access"); _2 }
}

object DisjointBoundedView {
  // namespace pollution?  Maybe use "||" or "OrType"
  type or[A,B] = DisjointBoundedView[A,B]

  // convenience of definition functions
   def da[A,B](a: A): or[A,B] = { new DisjointBoundedView(Some(a),None) }
  private def db[A,B](b: B): or[A,B] = { new DisjointBoundedView(None,Some(b)) }
  private def na[A,B](n: Nothing): or[A,B] = { new DisjointBoundedView(None, None) }

  // implicit defs - stuttering-or
  implicit def noneToOr2[A,B](n: Nothing): or[A,B] =
    { na(n) }
  implicit def aToOr2[A,B](a: A): or[A,B] =
    { da(a) }
  implicit def bToOr2[A,B](b: B): or[A,B] =
    { db(b) }
  implicit def aToOr3[A,B,C](a: A): or[or[A,B],C] =
    { da(da(a)) }
  implicit def bToOr3[A,B,C](b: B): or[or[A,B],C] =
    { da(db(b)) }
  implicit def aToOr4[A,B,C,D](a: A): or[or[or[A,B],C],D] =
    { da(da(da(a))) }
  implicit def bToOr4[A,B,C,D](b: B): or[or[or[A,B],C],D] =
    { da(da(db(b))) }
  implicit def aToOr5[A,B,C,D,E](a: A): or[or[or[or[A,B],C],D],E] =
    { da(da(da(da(a)))) }
  implicit def bToOr5[A,B,C,D,E](b: B): or[or[or[or[A,B],C],D],E] =
    { da(da(da(db(b)))) }
  // more? ...

}

import DisjointBoundedView._

class Foo {

  def bar[T <% Int or String or Double](t: Option[T]) = {
    t match {
      case Some(x: Int) => println("processing Int: " + x)
      case Some(x: String) => println("processing String: " + x)
      case Some(x: Double) => println("processing Double: " + x)
      case None => println("empty and I don't care the type")
    }
  }
/*
  def baz[T <% String or Int](t: List[T]) = {
    for (x <- t) x match {
      case x: String => println("String  list item: " + x)
      case x: Int => println("Int list item: " + x)
    }
  }*/

  def baz[T](t: List[T])(implicit v: T => String or Int) = {
    for (x <- t) x match {
      case x: String => println("String list item: " + x)
      case x: Int => println("Int list item: " + x)
    }
  }

}

class DisjointBoundedViewTezt {
    def testTrivial(off: Int): Unit = {
        val f = new Foo

        f.bar(None)
        f.bar(Some(1))
        f.bar(Some("blah"))
        f.bar(Some(3.45))
        // f.bar(Some(Some(2))) // compiler error
        // f.bar(Some(Set("x", "y"))) // compiler error

        f.baz(List(1,1,2,3,5,8,13))
        f.baz(List("boogie", "woogie"))(i => {println("  view"); da[String, Int](i)})
        // f.baz(List(3.4, 3.14)) // compiler error
        // f.baz(List(1,"one")) // compiler error
        // f.baz(Some(1)) // compiler error
    }
}


