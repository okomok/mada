

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package compare


case class FromOrdering[A](_1: Ordering[A]) extends Forwarder[A] {
    override protected val delegate: Compare[A] = _1 match {
        case ToOrdering(from) => from // from-to fusion
        case _ => new _FromOrdering(_1)
    }
}

private class _FromOrdering[A](val _1: Ordering[A]) extends Compare[A] {
    override def apply(x: A, y: A) = _1.lt(x, y)
    override def threeWay(x: A, y: A) = _1.compare(x, y)

    override def _toOrdering[B](_this: Compare[B]) = _this.asInstanceOf[_FromOrdering[B]]._1 // to-from fusion
}


case class ToOrdering[A](_1: Compare[A]) extends Ordering[A] {
    override def compare(x: A, y: A) = _1.threeWay(x, y)
}
