

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class Range(_1: Int, _2: Int) extends Vector[Int] {
    override val start = _1
    override val end = _2
    override def apply(i: Int) = i
}
