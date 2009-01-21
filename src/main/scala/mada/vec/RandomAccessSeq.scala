

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object RandomAccessSeqVector {
    def apply[A](u: RandomAccessSeq[A]): Vector[A] = u match {
        case _: RandomAccessSeq.Mutable[_] => new MutableRandomAccessSeqVector(u.asInstanceOf[RandomAccessSeq.Mutable[A]])
        case _ => new RandomAccessSeqVector(u)
    }
}

class RandomAccessSeqVector[A](val seq: RandomAccessSeq[A]) extends Vector[A] {
    override def size = seq.length
    override def apply(i: Int) = seq(i)
}

class MutableRandomAccessSeqVector[A](val seq: RandomAccessSeq.Mutable[A]) extends Vector[A] {
    override def size = seq.length
    override def apply(i: Int) = seq(i)
    override def update(i: Int, e: A) = seq(i) = e

    override def randomAccessSeq = seq // conversion fusion
}


object VectorRandomAccessSeq {
    def apply[A](v: Vector[A]): RandomAccessSeq.Mutable[A] = new VectorRandomAccessSeq(v)
}

class VectorRandomAccessSeq[A](v: Vector[A]) extends RandomAccessSeq.Mutable[A] {
    override def length = v.size
    override def apply(i: Int) = v(i)
    override def update(i: Int, e: A) = v(i) = e
}
