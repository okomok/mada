

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.compare


trait Forwarder[-A] extends util.Forwarder with Compare[A] {
    override protected def delegate: Compare[A]

    override def apply(x: A, y: A): Boolean = delegate.apply(x, y)
    override def threeWay(x: A, y: A): Int = delegate.threeWay(x, y)

    override def toSome: ToSome[A] = delegate.toSome
    override def _toGetOrdered[B](_this: Compare[B]): GetOrdered[B] = delegate.asInstanceOf[Compare[B]].toGetOrdered
    override def _toOrdering[B](_this: Compare[B]): Ordering[B] = delegate.asInstanceOf[Compare[B]].toOrdering
    override def _toJComparator[B](_this: Compare[B]): java.util.Comparator[B] = delegate.asInstanceOf[Compare[B]].toJComparator
}
