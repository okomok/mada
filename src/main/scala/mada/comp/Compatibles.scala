

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.comp


/**
 * Contains implicit conversions for strict weak ordering.
 */
object Compatibles {
    import java.util.Comparator
    import Compare._

    /**
     * Alias of <code>fromOrderedView</code>
     */
    implicit def madaCompareFromOrderedView[A](from: OrderedView[A]): Type[A] = fromOrderedView(from)

    /**
     * Alias of <code>toOrderedView</code>
     */
    implicit def madaCompareToOrderedView[A](from: Type[A]): OrderedView[A] = toOrderedView(from)

    /**
     * Alias of <code>fromOrdering</code>
     */
    implicit def madaCompareFromOrdering[A](from: Ordering[A]): Type[A] = fromOrdering(from)

    /**
     * Alias of <code>toOrdering</code>
     */
    implicit def madaCompareToOrdering[A](from: Type[A]): Ordering[A] = toOrdering(from)

    /**
     * Alias of <code>fromComparator</code>
     */
    implicit def madaCompareFromComparator[A](from: Comparator[A]): Type[A] = fromComparator(from)

    /**
     * Alias of <code>toComparator</code>
     */
    implicit def madaCompareToComparator[A](from: Type[A]): Comparator[A] = toComparator(from)
}
