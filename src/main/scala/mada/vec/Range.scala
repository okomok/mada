

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Range {
    def apply(start: Int, end: Int): Vector[Int] = new RangeVector(start, end)
}

private[mada] class RangeVector(start: Int, end: Int) extends Vector[Int] {
    override def size = end - start
    override def apply(i: Int) = start + i
}
