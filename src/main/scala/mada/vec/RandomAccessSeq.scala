

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object RandomAccessSeqVector {
    def apply[A](from: RandomAccessSeq[A]): Vector[A] = from match {
        case from: VectorRandomAccessSeq[_] => from.f.asInstanceOf[Vector[A]] // conversion fusion
        case _: RandomAccessSeq.Mutable[_] => new MutableRandomAccessSeqVector(from.asInstanceOf[RandomAccessSeq.Mutable[A]])
        case _ => new RandomAccessSeqVector(from)
    }
}

private[mada] class RandomAccessSeqVector[A](from: RandomAccessSeq[A]) extends Vector[A] {
    override def size = from.length
    override def apply(i: Int) = from(i)
}

private[mada] class MutableRandomAccessSeqVector[A](from: RandomAccessSeq.Mutable[A]) extends Vector[A] {
    override def size = from.length
    override def apply(i: Int) = from(i)
    override def update(i: Int, e: A) = from(i) = e

    override def randomAccessSeq = from // conversion fusion
}


private[mada] object VectorRandomAccessSeq {
    def apply[A](v: Vector[A]): RandomAccessSeq.Mutable[A] = new VectorRandomAccessSeq(v)
}

private[mada] class VectorRandomAccessSeq[A](val f: Vector[A]) extends RandomAccessSeq.Mutable[A] {
    override def length = f.size
    override def apply(i: Int) = f(i)
    override def update(i: Int, e: A) = f(i) = e
}
