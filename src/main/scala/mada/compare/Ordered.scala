

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.compare


case class FromGetOrdered[A](_1: GetOrdered[A]) extends Forwarder[A] {
    override protected val delegate: Compare[A] = _1 match {
        case ToGetOrdered(from) => from // from-to fusion
        case _ => new _FromGetOrdered(_1)
    }
}

private class _FromGetOrdered[A](val _1: GetOrdered[A]) extends Compare[A] {
    override def apply(x: A, y: A) = _1(x).compare(y) < 0
    override def threeWay(x: A, y: A) = _1(x).compare(y)

    override def _toGetOrdered[B](_this: Compare[B]) = _this.asInstanceOf[_FromGetOrdered[B]]._1 // to-from fusion
}


case class ToGetOrdered[A](_1: Compare[A]) extends GetOrdered[A] {
    override def apply(x: A) = new Ordered[A] {
        override def compare(y: A) = _1.threeWay(x, y)
    }
}
