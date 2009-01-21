

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Single {
    def apply[A](u: A): Vector[A] = new SingleVector(u)
}

class SingleVector[A](var single: A) extends Vector[A] {
    override def size = 1
    override def apply(i: Int) = single
    override def update(i: Int, e: A) = single = e
}
