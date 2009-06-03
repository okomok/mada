

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


private[mada] object Range {
    def apply(start: Int, end: Int): Vector[Int] = new RangeVector(start, end)
}

private[mada] class RangeVector(override val start: Int, override val end: Int) extends Vector[Int] {
    override def apply(i: Int) = i
}
