

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package peg


/**
 * Helps to define recursive grammars.
 */
class Rule[A] extends Peg[A] { // essentially shall not be a forwarder.
    @volatile private[this] var f: Function0[Peg[A]] = null

    /**
     * Assigns <code>that</code>.
     */
    final def ::=(that: => Peg[A]) { f = eval.ByLazy(that) }

    override def parse(v: sequence.Vector[A], start: Int, end: Int) = f().parse(v, start, end)
    override def width = f().width
}
