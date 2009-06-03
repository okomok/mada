

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


/**
 * Suppresses actions until outer <code>Peg</code> is matched.
 */
class ByNeedActions[A] {
    private val queue = new java.util.ArrayDeque[(Action[A], sequence.Vector[A])]

    @aliasOf("byNeed")
    final def apply(f: Action[A]): Action[A] = byNeed(f)

    /**
     * Creates action which is delayed until <code>need</code> is applied.
     */
    def byNeed(f: Action[A]): Action[A] = new Action[A] {
        override def apply(v: sequence.Vector[A]) = queue.add((f, v))
    }

    /**
     * Creates a <code>Peg</code> which triggers queued actions.
     */
    def need(p: Peg[A]): Peg[A] = Need(p)

    case class Need(_1: Peg[A]) extends Peg[A] {
        override def parse(v: sequence.Vector[A], start: Int, end: Int) = {
            val cur = _1.parse(v, start, end)
            if (cur != FAILURE) {
                fireActions
            }
            queue.clear
            cur
        }
        override def width = _1.width
    }

    private def fireActions: Unit = {
        val it = queue.iterator
        while (it.hasNext) {
            val (f, v) = it.next
            f(v)
        }
    }
}
