

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


// `need("abc"{byNeed(f)} >> "def")`

/**
 * Suppresses actions until outer <code>Peg</code> is matched.
 */
class ByNeedActions[A] {
    private val queue = new java.util.ArrayDeque[(Peg.Action[A], Vector[A])]

    /**
     * Creates action which is delayed until <code>need</code> is applied.
     */
    def byNeed(f: Peg.Action[A]): Peg.Action[A] = new Peg.Action[A] {
        override def apply(v: Vector[A]) = queue.add((f, v))
    }

    /**
     * Alias of <code>need</code>
     */
    final def apply(p: Peg[A]): Peg[A] = need(p)

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
            val (f, v) = it.next
            f(v)
        }
    }
}
