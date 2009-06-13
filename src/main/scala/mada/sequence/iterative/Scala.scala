

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterative


case class FromSIterable[+A](_1: Iterable[A]) extends Iterative[A] {
    override def begin = iterator.fromSIterator(_1.iterator)
}

case class ToSeq[+A](_1: Iterative[A]) extends Seq[A] {
    override def iterator = _1.begin.toSIterator
    override def apply(n: Int) = _1.at(n)
    override def length = _1.size
}
