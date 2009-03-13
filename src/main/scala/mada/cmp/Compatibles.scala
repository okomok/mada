

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.cmp


/**
 * Contains implicit conversions for strict weak ordering.
 */
trait Compatibles {
    import Compare._
// from
    implicit def madaCompareFromPredicate[A](from: Predicate[A]): Compare[A] = fromPredicate(from)
    implicit def madaCompareFromGetOrdered[A](implicit from: GetOrdered[A]): Compare[A] = fromGetOrdered(from)
    implicit def madaCompareFromOrdering[A](from: Ordering[A]): Compare[A] = fromOrdering(from)
    implicit def madaCompareFromComparator[A](from: Comparator[A]): Compare[A] = fromComparator(from)
// to
    implicit def madaCompareToGetOrdered[A](implicit from: Compare[A]): GetOrdered[A] = toGetOrdered(from)
    implicit def madaCompareToOrdering[A](implicit from: Compare[A]): Ordering[A] = toOrdering(from)
    implicit def madaCompareToComparator[A](implicit from: Compare[A]): Comparator[A] = toComparator(from)
}
