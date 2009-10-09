

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package compare


class ToSome[-A](val _1: Compare[A]) extends Forwarder[A] {
    override protected val delegate = _1

    override def toSome = this // toSome-toSome fusion
}


object ToSome {
    implicit def madaToSomeToGetOrdered[A](from: ToSome[A]): GetOrdered[A] = from._1.toGetOrdered
    implicit def madaToSomeToOrdering[A](from: ToSome[A]): Ordering[A] = from._1.toOrdering
    implicit def madaToSomeToJComparator[A](from: ToSome[A]): java.util.Comparator[A] = from._1.toJComparator
}
