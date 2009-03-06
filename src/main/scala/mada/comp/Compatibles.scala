

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.comp


/**
 * Contains implicit conversions for strict weak ordering.
 */
object Compatibles {
    import java.util.Comparator
    import Compare._

    implicit def madaCompareFromGetOrdered[A](from: GetOrdered[A]): Type[A] = fromGetOrdered(from)
    implicit def madaCompareFromOrdering[A](from: Ordering[A]): Type[A] = fromOrdering(from)
    implicit def madaCompareFromComparator[A](from: Comparator[A]): Type[A] = fromComparator(from)

    implicit def madaCompareToGetOrdered[A](from: Type[A]): GetOrdered[A] = toGetOrdered(from)
    implicit def madaCompareToOrdering[A](from: Type[A]): Ordering[A] = toOrdering(from)
    implicit def madaCompareToComparator[A](from: Type[A]): Comparator[A] = toComparator(from)
}
