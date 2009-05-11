

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package object compare {


    @aliasOf("Compare")
    type Type[-A] = Compare[A]

    @aliasOf("Compare")
    val compatibles: Compatibles = Compare

    @aliasOf("Function2[T, T, Boolean]")
    type Func[-T] = Function2[T, T, Boolean]

    @aliasOf("Function1[T, Ordered[T]]")
    type GetOrdered[T] = Function1[T, Ordered[T]]

    @aliasOf("ava.util.Comparator[T]")
    type Comparator[T] = java.util.Comparator[T]

    @aliasOf("fromFunc")
    def by[A](p: Func[A]): Compare[A] = fromFunc(p)


// conversions

    @returnThat
    def from[A](to: Compare[A]) = to

    @conversion
    def fromFunc[A](from: Func[A]): Compare[A] = new Compare[A] {
        override def apply(x: A, y: A) = from(x, y)
    }

    @conversion
    def fromGetOrdered[A](implicit from: GetOrdered[A]): Compare[A] = new Compare[A] {
        override def apply(x: A, y: A) = from(x).compare(y) < 0
        override def threeWay(x: A, y: A) = from(x).compare(y)
    }

    @conversion
    def fromOrdering[A](from: Ordering[A]): Compare[A] = new Compare[A] {
        override def apply(x: A, y: A) = from.lt(x, y)
        override def threeWay(x: A, y: A) = from.compare(x, y)
    }

    @conversion
    def fromComparator[A](from: Comparator[A]): Compare[A] = new Compare[A] {
        override def apply(x: A, y: A) = from.compare(x, y) < 0
        override def threeWay(x: A, y: A) = from.compare(x, y)
    }

    @conversion
    def toGetOrdered[A](implicit from: Compare[A]): GetOrdered[A] = { x =>
        new Ordered[A] {
            override def compare(y: A) = from.threeWay(x, y)
        }
    }

    @conversion
    def toOrdering[A](implicit from: Compare[A]): Ordering[A] = new Ordering[A] {
        override def compare(x: A, y: A) = from.threeWay(x, y)
    }

    @conversion
    def toComparator[A](implicit from: Compare[A]): Comparator[A] = new Comparator[A] {
        override def compare(x: A, y: A) = from.threeWay(x, y)
    }

}
