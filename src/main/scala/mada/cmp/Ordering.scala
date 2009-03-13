

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.cmp


private[mada] object FromOrdering {
    def apply[A](from: Ordering[A]): Compare.Type[A] = new OrderingCompare(from)
}

private[mada] class OrderingCompare[A](from: Ordering[A]) extends Compare.Type[A] {
    override def apply(x: A, y: A) = from.lt(x, y)
}


private[mada] object ToOrdering {
    def apply[A](from: Compare.Type[A]): Ordering[A] = new CompareOrdering(from)
}

private[mada] class CompareOrdering[A](from: Compare.Type[A]) extends Ordering[A] {
    override def compare(x: A, y: A) = Compare3way(x, from, y)
}
