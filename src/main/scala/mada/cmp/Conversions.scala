

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.cmp


/**
 * Contains explicit conversions for strict weak ordering.
 */
trait Conversions {
    import java.util.Comparator
    import Compare.{ Type, GetOrdered }
// from
    def fromGetOrdered[A](from: GetOrdered[A]): Type[A] = FromGetOrdered(from)
    def fromOrdering[A](from: Ordering[A]): Type[A] = FromOrdering(from)
    def fromComparator[A](from: Comparator[A]): Type[A] = FromComparator(from)
// to
    def toGetOrdered[A](from: Type[A]): GetOrdered[A] = ToGetOrdered(from)
    def toOrdering[A](from: Type[A]): Ordering[A] = ToOrdering(from)
    def toComparator[A](from: Type[A]): Comparator[A] = ToComparator(from)
}
