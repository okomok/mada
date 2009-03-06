

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.comp


import Compare.GetOrdered


private[mada] object FromGetOrdered {
    def apply[A](from: GetOrdered[A]): Compare.Type[A] = new GetOrderedCompare(from)
}

private[mada] class GetOrderedCompare[A](from: GetOrdered[A]) extends Compare.Type[A] {
    override def apply(x: A, y: A) = from(x) < y
}


private[mada] object ToGetOrdered {
    def apply[A](from: Compare.Type[A]): GetOrdered[A] = new CompareGetOrdered(from)
}

private[mada] class CompareGetOrdered[A](from: Compare.Type[A]) extends GetOrdered[A] {
    override def apply(x: A) = new Ordered[A] {
        override def compare(y: A) = Compare3way(x, from, y)
    }
}
