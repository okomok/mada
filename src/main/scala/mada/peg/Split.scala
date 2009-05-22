

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


case class Split[A](_1: Peg[A], _2: Vector[A]) extends Sequence[Vector[A]] {
    override def begin = new sequence.Iterator[Vector[A]] {
        private val u = new RepeatAtMostUntilPeg(any, Math.MAX_INT, peg.end | _1)
        private var (k, b, l) = u.parseImpl(_2, _2.start, _2.end)

        override def isEnd = k == l
        override def deref = {
            preDeref
            new vector.Region(_2, k, b)
        }
        override def increment = {
            preIncrement
            k_b_l_assign(u.parseImpl(_2, l, _2.end))
        }

        private def k_b_l_assign(r: (Int, Int, Int)): Unit = {
            k = r._1; b = r._2; l = r._3;
        }
    }
}
