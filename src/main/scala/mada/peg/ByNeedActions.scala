

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


/**
 * Suppresses actions until outer <code>Peg</code> is matched.
 */
class ByNeedActions[A] {
    private val queue = new java.util.ArrayDeque[(Action[A], Vector[A])]

    @aliasOf("byNeed")
    final def apply(f: Action[A]): Action[A] = byNeed(f)

    /**
     * Creates action which is delayed until <code>need</code> is applied.
     */
    def byNeed(f: Action[A]): Action[A] = new Action[A] {
        override def apply(v: Vector[A]) = queue.add((f, v))
    }

    /**
     * Creates a <code>Peg</code> which triggers queued actions.
     */
    def need(p: Peg[A]): Peg[A] = new NeedPeg(p)

    private class NeedPeg(override val delegate: Peg[A]) extends Forwarder[A] {
        override def parse(v: Vector[A], start: Int, end: Int) = {
            val cur = delegate.parse(v, start, end)
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
            val (f, v) = it.next
            f(v)
        }
    }
}
