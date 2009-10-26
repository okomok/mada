

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package vector


// Iterable

case class FromSIterable[A](_1: scala.Iterable[A]) extends Forwarder[A] {
    override protected val delegate = {
        val r = new java.util.ArrayList[A]
        for (e <- _1.view) {
            r.add(e)
        }
        fromJList(r)
    }
}


// IndexedSeq

case class FromSIndexedSeq[A](_1: scala.collection.IndexedSeq[A]) extends Forwarder[A] {
    override protected val delegate: Vector[A] = _1 match {
        case ToSIndexedSeq(from) => from // from-to fusion
        case _: scala.collection.mutable.IndexedSeq[_] => new _FromSMutableIndexedSeq(_1.asInstanceOf[scala.collection.mutable.IndexedSeq[A]])
        case _ => new _FromSIndexedSeq(_1)
    }
}

private class _FromSIndexedSeq[A](_1: scala.collection.IndexedSeq[A]) extends Vector[A] {
    override def start = 0
    override def end = _1.length
    override def apply(i: Int) = _1(i)
}

private class _FromSMutableIndexedSeq[A](_1: scala.collection.mutable.IndexedSeq[A]) extends Vector[A] {
    override def start = 0
    override def end = _1.length
    override def apply(i: Int) = _1(i)
    override def update(i: Int, e: A) = _1(i) = e

    override def toSIndexedSeq = _1 // to-from fusion
}

case class ToSIndexedSeq[A](_1: Vector[A]) extends scala.collection.mutable.IndexedSeq[A] {
    override def length = _1.nth.size
    override def apply(i: Int) = _1.nth(i)
    override def update(i: Int, e: A) = _1.nth(i) = e
}
