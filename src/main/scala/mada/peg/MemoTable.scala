

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


class MemoTable[A](val vector: Vector[A]) {
    private val table = new scala.collection.jcl.HashMap[PairVal, Long]

    def apply(p: Peg[A]): Peg[A] = new MemoTablePeg(p)

    class MemoTablePeg(override val self: Peg[A]) extends PegProxy[A] {
        override def parse(v: Vector[A], first: Long, last: Long): Long = {
            if (vector eq v) {
                val key = PairVal(first, last)
                val value = table.get(key)
                if (value.isEmpty) {
                    val cur = self.parse(v, first, last)
                    table.put(key, cur)
                    cur
                } else {
                    value.get
                }
            } else {
                self.parse(v, first, last)
            }
        }
    }

    case class PairVal(_1: Long, _2: Long) {
        override def equals(that: Any) = that match {
            case that: PairVal => _1 == that._1 && _2 == that._2
            case _ => false
        }
        override def hashCode = LongHashCode(_1) * 7 + LongHashCode(_2) * 41
    }
}
