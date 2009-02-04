

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object FromRandomAccessSeq {
    def apply[A](from: RandomAccessSeq[A]): Vector[A] = from match {
        case from: VectorRandomAccessSeq[_] => from.v.asInstanceOf[Vector[A]] // conversion fusion
        case _: RandomAccessSeq.Mutable[_] => new MutableRandomAccessSeqVector(from.asInstanceOf[RandomAccessSeq.Mutable[A]])
        case _ => new RandomAccessSeqVector(from)
    }
}

private[mada] class RandomAccessSeqVector[A](from: RandomAccessSeq[A]) extends Vector[A] {
    override def start = 0
    override def end = from.length
    override def apply(i: Int) = from(i)
}

private[mada] case class MutableRandomAccessSeqVector[A](from: RandomAccessSeq.Mutable[A]) extends Vector[A] {
    override def start = 0
    override def end = from.length
    override def apply(i: Int) = from(i)
    override def update(i: Int, e: A) = from(i) = e
}


private[mada] object ToRandomAccessSeq {
    def apply[A](v: Vector[A]): RandomAccessSeq.Mutable[A] = v match {
        case MutableRandomAccessSeqVector(_from) => _from // conversion fusion
        case _ => new VectorRandomAccessSeq(v)
    }
}

private[mada] class VectorRandomAccessSeq[A](val v: Vector[A]) extends RandomAccessSeq.Mutable[A] {
    override def length = v.nth.size
    override def apply(i: Int) = v.nth(i)
    override def update(i: Int, e: A) = v.nth(i) = e
}
