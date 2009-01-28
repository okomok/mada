

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


// `need( ("abc" ^^ byNeed(f)) >> "def" )`

/**
 * Suppresses actions until outer <code>Peg</code> is matched.
 */
class ByNeedActions[A] {
    private val queue = new java.util.ArrayDeque[(Vector.Func3[A, Any], Vector.Triple[A])]

    /**
     * Creates action which is delayed until <code>need</code> is applied.
     */
    def byNeed(f: Vector.Func3[A, Any]): Function1[Vector.Triple[A], Unit] = new Function1[Vector.Triple[A], Unit] {
        override def apply(v: Vector.Triple[A]) = queue.add((f, v))
    }

    /**
     * Alias of <code>need</code>
     */
    def apply(p: Peg[A]): Peg[A] = need(p)

    /**
     * Creates a <code>Peg</code> which triggers queued actions.
     */
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
