

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


private[mada] object FromRandomAccessSeq {
    def apply[A](from: scala.collection.Vector[A]): Vector[A] = from match {
        case from: VectorRandomAccessSeq[_] => from.v.asInstanceOf[Vector[A]] // conversion fusion
        case _: scala.collection.mutable.Vector[_] => new MutableRandomAccessSeqVector(from.asInstanceOf[scala.collection.mutable.Vector[A]])
        case _ => new RandomAccessSeqVector(from)
    }
}

private[mada] class RandomAccessSeqVector[A](from: scala.collection.Vector[A]) extends Vector[A] {
    override def start = 0
    override def end = from.length
    override def apply(i: Int) = from(i)
}

private[mada] case class MutableRandomAccessSeqVector[A](from: scala.collection.mutable.Vector[A]) extends Vector[A] {
    override def start = 0
    override def end = from.length
    override def apply(i: Int) = from(i)
    override def update(i: Int, e: A) = from(i) = e
}


private[mada] object ToRandomAccessSeq {
    def apply[A](from: Vector[A]): scala.collection.mutable.Vector[A] = from match {
        case MutableRandomAccessSeqVector(_from) => _from // conversion fusion
        case _ => new VectorRandomAccessSeq(from)
    }
}

private[mada] class VectorRandomAccessSeq[A](val v: Vector[A]) extends scala.collection.mutable.Vector[A] {
    override def length = v.nth.size
    override def apply(i: Int) = v.nth(i)
    override def update(i: Int, e: A) = v.nth(i) = e
}
