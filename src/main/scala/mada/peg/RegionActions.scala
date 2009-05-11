

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


/**
 * Associates actions where Peg can't be placed.
 */
class RegionActions[A] {
    private val stack = new java.util.ArrayDeque[Int]

    /**
     * Marks starting point of actions.
     */
    val startAt: Peg[A] = new StartAtPeg

    private class StartAtPeg extends Peg[A] with ZeroWidth[A] {
        override def parse(v: Vector[A], start: Int, end: Int) = {
            stack.push(start)
            start
        }
    }

    @aliasOf("endWith")
    final def apply(f: Peg.Action[A]): Peg[A] = endWith(f)

    /**
     * Triggers the action.
     */
    def endWith(f: Peg.Action[A]): Peg[A] = new EndWithPeg(f)

    private class EndWithPeg(f: Peg.Action[A]) extends Peg[A] with ZeroWidth[A] {
        override def parse(v: Vector[A], start: Int, end: Int) = {
            f(v(stack.pop, start))
            start
        }
    }
}
