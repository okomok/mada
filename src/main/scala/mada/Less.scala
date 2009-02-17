

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods operating on strict weak ordering.
 */
object Less {
    import less._
    import Functions.{ Compare, OrderedView }
    import java.util.Comparator

    /**
     * Triggers implicit conversions explicitly.
     *
     * @return  <code>to</code>.
     */
    def from[A](to: Compare[A]) = to

    /**
     * Converts from <code>Ordered</code> view.
     */
    def fromOrderedView[A](from: OrderedView[A]): Compare[A] = FromOrderedView(from)

    /**
     * Converts to <code>Ordered</code> view.
     */
    def toOrderedView[A](from: Compare[A]): OrderedView[A] = ToOrderedView(from)

    /**
     * Converts from <code>Ordering</code>.
     */
    def fromOrdering[A](from: Ordering[A]): Compare[A] = FromOrdering(from)

    /**
     * Converts to <code>Ordering</code>.
     */
    def toOrdering[A](from: Compare[A]): Ordering[A] = ToOrdering(from)

    /**
     * Converts from <code>java.util.Comparator</code>.
     */
    def fromComparator[A](from: Comparator[A]): Compare[A] = FromComparator(from)

    /**
     * Converts to <code>java.util.Comparator</code>.
     */
    def toComparator[A](from: Compare[A]): Comparator[A] = ToComparator(from)

    /**
     * Alias of <code>fromOrderedView</code>
     */
    def apply[A](implicit from: OrderedView[A]): Compare[A] = fromOrderedView(from)

    /**
     * Alias of <code>less.Compatibles</code>
     */
    val Compatibles = less.Compatibles
}
