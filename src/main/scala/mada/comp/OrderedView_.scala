

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.comp


import Compare.OrderedView


private[mada] object FromOrderedView {
    def apply[A](from: OrderedView[A]): Compare.Type[A] = new OrderedViewCompare(from)
}

private[mada] class OrderedViewCompare[A](from: OrderedView[A]) extends Compare.Type[A] {
    override def apply(x: A, y: A) = from(x) < y
}


private[mada] object ToOrderedView {
    def apply[A](from: Compare.Type[A]): OrderedView[A] = new CompareOrderedView(from)
}

private[mada] class CompareOrderedView[A](from: Compare.Type[A]) extends OrderedView[A] {
    override def apply(x: A) = new Ordered[A] {
        override def compare(y: A) = Compare3way(x, from, y)
    }
}
