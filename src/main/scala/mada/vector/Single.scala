

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector


private[mada] object Single {
    def apply[A](from: A): Vector[A] = new SingleVector(from)
}

private[mada] class SingleVector[A](private var from: A) extends Vector[A] {
    override def start = 0
    override def end = 1
    override def apply(i: Int) = from
    override def update(i: Int, e: A) = from = e
}