

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.comp


import java.util.Comparator


private[mada] object FromComparator {
    def apply[A](from: Comparator[A]): Compare.Type[A] = new ComparatorCompare(from)
}

private[mada] class ComparatorCompare[A](from: Comparator[A]) extends Compare.Type[A] {
    override def apply(x: A, y: A) = from.compare(x, y) < 0
}


private[mada] object ToComparator {
    def apply[A](from: Compare.Type[A]): Comparator[A] = new CompareComparator(from)
}

private[mada] class CompareComparator[A](from: Compare.Type[A]) extends Comparator[A] {
    override def compare(x: A, y: A) = Compare3way(x, from, y)
}
