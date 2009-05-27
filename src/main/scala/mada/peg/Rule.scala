

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


/**
 * Helps to define recursive grammars.
 */
class Rule[A] extends Peg[A] { // essentially shall not be a forwarder.
    @volatile private var f: Function0[Peg[A]] = null

    /**
     * Assigns <code>that</code>.
     */
    final def ::=(that: => Peg[A]): Unit = { f = function.ofLazy(that) }

    override def parse(v: Vector[A], start: Int, end: Int) = f().parse(v, start, end)
    override def width = f().width
}
