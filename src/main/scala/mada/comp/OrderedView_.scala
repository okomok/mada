

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.less


import Functions.{ Compare, OrderedView }


private[mada] object FromOrderedView {
    def apply[A](from: OrderedView[A]): Compare[A] = new OrderedViewLess(from)
}

private[mada] class OrderedViewLess[A](from: OrderedView[A]) extends Compare[A] {
    override def apply(x: A, y: A) = from(x) < y
}


private[mada] object ToOrderedView {
    def apply[A](from: Compare[A]): OrderedView[A] = new LessOrderedView(from)
}

private[mada] class LessOrderedView[A](from: Compare[A]) extends OrderedView[A] {
    override def apply(x: A) = new Ordered[A] {
        override def compare(y: A) = Compare3way(x, from, y)
    }
}
