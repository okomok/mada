

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


class Memoizer[A](val vector: Vector[A]) {
    def apply(p: Peg[A]): Peg[A] = memoize(p)
    def memoize(p: Peg[A]): MemoizePeg = new MemoizePeg(p)

    class MemoizePeg(override val self: Peg[A]) extends PegProxy[A] {
        val table = new scala.collection.jcl.HashMap[Long, Long]

        override def parse(v: Vector[A], first: Long, last: Long): Long = {
            if (v eq vector) {
                val key = first
                val value = table.get(key)
                if (value.isEmpty) {
                    println("memo unhit")
                    val cur = self.parse(v, first, last)
                    table.put(key, cur)
                    cur
                } else {
                    println("memo hit:" + value.get)
                    value.get
                }
            } else {
                self.parse(v, first, last)
            }
        }
    }
}
