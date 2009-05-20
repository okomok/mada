

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.compare


case class FromJComparator[A](_1: java.util.Comparator[A]) extends Forwarder[A] {
    override protected val delegate: Compare[A] = _1 match {
        case ToJComparator(from) => from // from-to fusion
        case _ => new _FromJComparator(_1)
    }
}

private class _FromJComparator[A](val _1: java.util.Comparator[A]) extends Compare[A] {
    override def apply(x: A, y: A) = _1.compare(x, y) < 0
    override def threeWay(x: A, y: A) = _1.compare(x, y)

    override def _toJComparator[B](_this: Compare[B]) = _this.asInstanceOf[_FromJComparator[B]]._1 // to-from fusion
}


case class ToJComparator[A](_1: Compare[A]) extends java.util.Comparator[A] {
    override def compare(x: A, y: A) = _1.threeWay(x, y)
}
