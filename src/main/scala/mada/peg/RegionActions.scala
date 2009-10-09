

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package peg


/**
 * Associates actions where Peg can't be placed.
 */
class RegionActions[A] {
    private val stack = new java.util.ArrayDeque[Int]

    /**
     * Marks starting point of actions.
     */
    val startAt: Peg[A] = StartAt()

    case class StartAt() extends Peg[A] with ZeroWidth[A] {
        override def parse(v: sequence.Vector[A], start: Int, end: Int) = {
            stack.push(start)
            start
        }
    }

    @aliasOf("endWith")
    final def apply(f: Action[A]): Peg[A] = endWith(f)

    /**
     * Triggers the action.
     */
    def endWith(f: Action[A]): Peg[A] = EndWith(f)

    case class EndWith(_1: Action[A]) extends Peg[A] with ZeroWidth[A] {
        override def parse(v: sequence.Vector[A], start: Int, end: Int) = {
            _1(v(stack.pop, start))
            start
        }
    }
}
