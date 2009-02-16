

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods operating on "less-than" predicate.
 */
object Less {
    import Functions.{ Compare, OrderedView }
    import java.util.Comparator

    private def compareToInt[A](x: A, lt: Compare[A], y: A): Int = {
        if (lt(x, y)) -1 else if (lt(y, x)) 1 else 0
    }

    /**
     * Triggers implicit conversions explicitly.
     *
     * @return  <code>to</code>.
     */
    def from[A](to: Compare[A]) = to

    /**
     * Converts from <code>Ordered</code> view.
     */
    def fromOrderedView[A](from: OrderedView[A]): Compare[A] = { (x, y) => from(x) < y }

    /**
     * Converts to <code>Ordered</code> view.
     */
    def toOrderedView[A](from: Compare[A]): OrderedView[A] = { x => new Ordered[A] {
        override def compare(y: A) = compareToInt(x, from, y)
    } }

    /**
     * Converts from <code>java.util.Comparator</code>.
     */
    def fromComparator[A](from: Comparator[A]): Compare[A] = { (x, y) => from.compare(x, y) < 0 }

    /**
     * Converts to <code>java.util.Comparator</code>.
     */
    def toComparator[A](from: Compare[A]): Comparator[A] = new Comparator[A] {
        override def compare(x: A, y: A) = compareToInt(x, from, y)
    }

    /**
     * Alias of <code>fromOrderedView</code>
     */
    def apply[A](implicit from: OrderedView[A]): Compare[A] = fromOrderedView(from)

    /**
     * Contains implicit conversions.
     */
    object Compatibles {
        /**
         * Alias of <code>fromOrderedView</code>
         */
        implicit def madaLessFromOrderedView[A](from: OrderedView[A]): Compare[A] = fromOrderedView(from)

        /**
         * Alias of <code>toOrderedView</code>
         */
        implicit def madaLessToOrderedView[A](from: Compare[A]): OrderedView[A] = toOrderedView(from)

        /**
         * Alias of <code>fromComparator</code>
         */
        implicit def madaLessFromComparator[A](from: Comparator[A]): Compare[A] = fromComparator(from)

        /**
         * Alias of <code>toComparator</code>
         */
        implicit def madaLessToComparator[A](from: Compare[A]): Comparator[A] = toComparator(from)
    }
}
