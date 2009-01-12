

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Memoize {
    def apply[A](p: Peg[A]): Peg[A] = new MemoizePeg(p)
}

class MemoizePeg[A](override val self: Peg[A]) extends PegProxy[A] {
    val memoTable = new scala.collection.jcl.HashMap[TripleKey[A], Long]

    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        val key = new TripleKey(v, first, last)
        val value = memoTable.get(key)
        if (value.isEmpty) {
            val cur = self.parse(v, first, last)
            memoTable.put(key, cur)
            cur
        } else {
            value.get
        }
    }
}

/*
class Memoizer[A](w: Vector[A]) {
    def apply(p: Peg[A]): Peg[A] = memoize(p)
    def memoize(p: Peg[A]): MemoizePeg = new MemoizePeg(p)

    class MemoizePeg(override val self: Peg[A]) extends PegProxy[A] {
        val memoTable = new scala.collection.jcl.HashMap[Long, Long]

        override def parse(v: Vector[A], first: Long, last: Long): Long = {
            if (v eq w) {
                val key = first // last too is key?
                val value = memoTable.get(key)
                if (value.isEmpty) {
                    val cur = self.parse(v, first, last)
                    memoTable.put(key, cur)
                    cur
                } else {
                    value.get
                }
            } else {
                self.parse(v, first, last)
            }
        }
    }
}
*/

