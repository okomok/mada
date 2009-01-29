

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Empty {
    def apply[A] = new EmptyVector[A]
}

private[mada] class EmptyVector[A] extends Vector[A] {
    override def start = 0
    override def end = 0
}
