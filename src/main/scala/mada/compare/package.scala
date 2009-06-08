

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package object compare {


    @aliasOf("Compare")
    type Type[-A] = Compare[A]

    @aliasOf("Function2[T, T, Boolean]")
    type Func[-T] = Function2[T, T, Boolean]

    @aliasOf("Function1[T, Ordered[T]]")
    type GetOrdered[T] = Function1[T, Ordered[T]]

    @aliasOf("fromFunc")
    def by[A](p: Func[A]): Compare[A] = fromFunc(p)


// conversion

    @returnThat
    def from[A](to: Compare[A]): Compare[A] = to

    @compatibleConversion
    def fromFunc[A](from: Func[A]): Compare[A] = FromFunc(from)

    @compatibleConversion
    def fromGetOrdered[A](implicit from: GetOrdered[A]): Compare[A] = FromGetOrdered(from)

    @compatibleConversion
    def fromOrdering[A](from: Ordering[A]): Compare[A] = FromOrdering(from)

    @compatibleConversion
    def fromJComparator[A](from: java.util.Comparator[A]): Compare[A] = FromJComparator(from)

}
