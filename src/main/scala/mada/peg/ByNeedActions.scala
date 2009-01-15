

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


// `need( ("abc" ^^ byNeed(f)) >> "def" )`

class ByNeedActions[A] {
    private val queue = new java.util.ArrayDeque[((Vector[A], Long, Long) => Any, (Vector[A], Long, Long))]

    def byNeed(f: Vector[A] => Any)(v: (Vector[A], Long, Long)): Unit = byNeed(Vector.triplify(f))(v)
    def byNeed(f: (Vector[A], Long, Long) => Any)(v: (Vector[A], Long, Long)): Unit = queue.add((f, v))

    def apply(p: Peg[A]): Peg[A] = need(p)
    def need(p: Peg[A]): Peg[A] = new NeedPeg(p)

    class NeedPeg(override val self: Peg[A]) extends PegProxy[A] {
        override def parse(v: Vector[A], first: Long, last: Long): Long = {
            val cur = self.parse(v, first, last)
            if (cur != FAILURE) {
                fireActions
            }
            queue.clear
            cur
        }
    }

    private def fireActions: Unit = {
        val it = queue.iterator
        while (it.hasNext) {
            val (f, (v, i, j)) = it.next
            f(v, i, j)
        }
    }
}
