

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


// Int

object IntRange {
    def apply(start: Int, end: Int): Vector[Int] = new IntRangeVector(start, end)
}

class IntRangeVector(start: Int, end: Int) extends Vector[Int] {
    override def size = end - start
    override def apply(i: Long) = (start + i).toInt
}


// Long

object LongRange {
    def apply(start: Long, end: Long): Vector[Long] = new LongRangeVector(start, end)
}

class LongRangeVector(start: Long, end: Long) extends Vector[Long] {
    override def size = end - start
    override def apply(i: Long) = start + i
}
