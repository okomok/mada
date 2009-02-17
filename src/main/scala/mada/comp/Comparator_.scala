

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.less


import Functions.Compare
import java.util.Comparator


private[mada] object FromComparator {
    def apply[A](from: Comparator[A]): Compare[A] = new ComparatorLess(from)
}

private[mada] class ComparatorLess[A](from: Comparator[A]) extends Compare[A] {
    override def apply(x: A, y: A) = from.compare(x, y) < 0
}


private[mada] object ToComparator {
    def apply[A](from: Compare[A]): Comparator[A] = new LessComparator(from)
}

private[mada] class LessComparator[A](from: Compare[A]) extends Comparator[A] {
    override def compare(x: A, y: A) = Compare3way(x, from, y)
}
