

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package function


case class ToOrdering[T](_1: Predicate2[T, T]) extends Ordering[T] {
    override def compare(x: T, y: T) = if (_1(x, y)) -1 else if (_1(y, x)) 1 else 0
}
