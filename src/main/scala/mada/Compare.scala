

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods operating on strict weak ordering.
 */
object Compare {
    import comp._
    import java.util.Comparator

    /**
     * Alias of <code>Functions.Predicate2[T, T]</code>
     */
    type Type[-T] = Functions.Predicate2[T, T]

    /**
     * Alias of <code>Function1[T, Ordered[T]]</code>
     */
    type OrderedView[T] = Function1[T, Ordered[T]]

    /**
     * Triggers implicit conversions explicitly.
     *
     * @return  <code>to</code>.
     */
    def from[A](to: Type[A]) = to

    /**
     * Converts from <code>Ordered</code> view.
     */
    def fromOrderedView[A](from: OrderedView[A]): Type[A] = FromOrderedView(from)

    /**
     * Converts to <code>Ordered</code> view.
     */
    def toOrderedView[A](from: Type[A]): OrderedView[A] = ToOrderedView(from)

    /**
     * Converts from <code>Ordering</code>.
     */
    def fromOrdering[A](from: Ordering[A]): Type[A] = FromOrdering(from)

    /**
     * Converts to <code>Ordering</code>.
     */
    def toOrdering[A](from: Type[A]): Ordering[A] = ToOrdering(from)

    /**
     * Converts from <code>java.util.Comparator</code>.
     */
    def fromComparator[A](from: Comparator[A]): Type[A] = FromComparator(from)

    /**
     * Converts to <code>java.util.Comparator</code>.
     */
    def toComparator[A](from: Type[A]): Comparator[A] = ToComparator(from)

    /**
     * Alias of <code>fromOrderedView</code>
     */
    def apply[A](implicit from: OrderedView[A]): Type[A] = fromOrderedView(from)

    /**
     * Alias of <code>comp.Compatibles</code>
     */
    val Compatibles = comp.Compatibles
}
