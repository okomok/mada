

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


// `need( ("abc" ^^ byNeed(f)) >> "def" )`

class ByNeedActions[A] {
    private val queue = new java.util.ArrayDeque[(Vector.Func3[A, Any], Vector.Triple[A])]

    def byNeed(f: Vector.Func3[A, Any])(v: Vector.Triple[A]): Unit = queue.add((f, v))

    def apply(p: Peg[A]): Peg[A] = need(p)
    def need(p: Peg[A]): Peg[A] = new NeedPeg(p)

    private class NeedPeg(override val self: Peg[A]) extends PegProxy[A] {
        override def parse(v: Vector[A], start: Int, end: Int) = {
            val cur = self.parse(v, start, end)
            if (cur != Peg.FAILURE) {
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
