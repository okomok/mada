

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterative


private
case class FromArray[A](_1: Array[A]) extends Forwarder[A] {
    override protected val delegate = Iterative.fromSIterable(_1)
}

private
case class FromSIterable[+A](_1: Iterable[A]) extends Iterative[A] {
    override def begin = Iterator.fromSIterator(_1.iterator)
}

private
case class ToSIterable[+A](_1: Iterative[A]) extends scala.collection.Iterable[A] {
    override def iterator = _1.begin.toSIterator
}

private
case class ToSeq[+A](_1: Iterative[A]) extends Seq[A] {
    override def iterator = _1.begin.toSIterator
    override def apply(n: Int) = _1.nth(n)
    override def length = _1.size
}
