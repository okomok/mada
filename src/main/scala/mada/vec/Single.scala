

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Single {
    def apply[A](from: A): Vector[A] = new SingleVector(from)
}

private[mada] class SingleVector[A](private var from: A) extends Vector[A] {
    override def size = 1
    override def apply(i: Int) = from
    override def update(i: Int, e: A) = from = e
}
