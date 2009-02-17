

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.less


import Functions.Compare


private[mada] object FromOrdering {
    def apply[A](from: Ordering[A]): Compare[A] = new OrderingLess(from)
}

private[mada] class OrderingLess[A](from: Ordering[A]) extends Compare[A] {
    override def apply(x: A, y: A) = from.lt(x, y)
}


private[mada] object ToOrdering {
    def apply[A](from: Compare[A]): Ordering[A] = new LessOrdering(from)
}

private[mada] class LessOrdering[A](from: Compare[A]) extends Ordering[A] {
    override def compare(x: A, y: A) = Compare3way(x, from, y)
}
