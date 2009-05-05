

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.cmp


/**
 * Contains explicit conversions for strict weak ordering.
 */
@provider
trait Conversions { this: Compare.type =>

// from
    def fromFunc[A](from: Func[A]): Compare[A] = new Compare[A] {
        override def apply(x: A, y: A) = from(x, y)
    }

    def fromGetOrdered[A](implicit from: GetOrdered[A]): Compare[A] = new Compare[A] {
        override def apply(x: A, y: A) = from(x).compare(y) < 0
        override def threeWay(x: A, y: A) = from(x).compare(y)
    }

    def fromOrdering[A](from: Ordering[A]): Compare[A] = new Compare[A] {
        override def apply(x: A, y: A) = from.lt(x, y)
        override def threeWay(x: A, y: A) = from.compare(x, y)
    }

    def fromComparator[A](from: Comparator[A]): Compare[A] = new Compare[A] {
        override def apply(x: A, y: A) = from.compare(x, y) < 0
        override def threeWay(x: A, y: A) = from.compare(x, y)
    }

// to
    def toGetOrdered[A](implicit from: Compare[A]): GetOrdered[A] = { x =>
        new Ordered[A] {
            override def compare(y: A) = from.threeWay(x, y)
        }
    }

    def toOrdering[A](implicit from: Compare[A]): Ordering[A] = new Ordering[A] {
        override def compare(x: A, y: A) = from.threeWay(x, y)
    }

    def toComparator[A](implicit from: Compare[A]): Comparator[A] = new Comparator[A] {
        override def compare(x: A, y: A) = from.threeWay(x, y)
    }
}
