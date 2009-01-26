

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Range {
    def apply(start: Int, end: Int): Vector[Int] = new IntRangeVector(start, end)
    def apply(start: Long, end: Long): Vector[Long] = new LongRangeVector(start, end)
}


// Int

private[mada] class IntRangeVector(start: Int, end: Int) extends Vector[Int] {
    override def size = end - start
    override def apply(i: Int) = start + i
}


// Long

private[mada] class LongRangeVector(start: Long, end: Long) extends Vector[Long] {
    override def size = (end - start).toInt
    override def apply(i: Int) = start + i
}
