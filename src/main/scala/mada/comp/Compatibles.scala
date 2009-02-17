

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.less


/**
 * Contains implicit conversions for strict weak ordering.
 */
object Compatibles {
    import Functions.{ Compare, OrderedView }
    import java.util.Comparator
    import Less._

    /**
     * Alias of <code>fromOrderedView</code>
     */
    implicit def madaLessFromOrderedView[A](from: OrderedView[A]): Compare[A] = fromOrderedView(from)

    /**
     * Alias of <code>toOrderedView</code>
     */
    implicit def madaLessToOrderedView[A](from: Compare[A]): OrderedView[A] = toOrderedView(from)

    /**
     * Alias of <code>fromOrdering</code>
     */
    implicit def madaLessFromOrdering[A](from: Ordering[A]): Compare[A] = fromOrdering(from)

    /**
     * Alias of <code>toOrdering</code>
     */
    implicit def madaLessToOrdering[A](from: Compare[A]): Ordering[A] = toOrdering(from)

    /**
     * Alias of <code>fromComparator</code>
     */
    implicit def madaLessFromComparator[A](from: Comparator[A]): Compare[A] = fromComparator(from)

    /**
     * Alias of <code>toComparator</code>
     */
    implicit def madaLessToComparator[A](from: Compare[A]): Comparator[A] = toComparator(from)
}
