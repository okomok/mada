

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


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

case class ToSIterable[A](_1: Vector[A]) extends scala.Iterable[A] {
    override def iterator: scala.Iterator[A] = new _SIterableIterator(_1)
}

private class _SIterableIterator[A](v: Vector[A]) extends scala.Iterator[A] {
    private var i = v.start
    override def hasNext = i != v.end
    override def next = { val tmp = v(i); i += 1; tmp }
}


// Vector

case class FromSVector[A](_1: scala.collection.Vector[A]) extends Forwarder[A] {
    override protected val delegate: Vector[A] = _1 match {
        case ToSVector(from) => from // from-to fusion
        case _: scala.collection.mutable.Vector[_] => new _FromSMutableVector(_1.asInstanceOf[scala.collection.mutable.Vector[A]])
        case _ => new _FromSVector(_1)
    }
}

private class _FromSVector[A](_1: scala.collection.Vector[A]) extends Vector[A] {
    override def start = 0
    override def end = _1.length
    override def apply(i: Int) = _1(i)
}

private class _FromSMutableVector[A](_1: scala.collection.mutable.Vector[A]) extends Vector[A] {
    override def start = 0
    override def end = _1.length
    override def apply(i: Int) = _1(i)
    override def update(i: Int, e: A) = _1(i) = e

    override def toSVector = _1 // to-from fusion
}

case class ToSVector[A](_1: Vector[A]) extends scala.collection.mutable.Vector[A] {
    override def length = _1.nth.size
    override def apply(i: Int) = _1.nth(i)
    override def update(i: Int, e: A) = _1.nth(i) = e
}
