

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


/**
 * A <code>Peg</code> to support recursive pegs
 */
class Rule[A] private (private var p: Peg[A]) extends PegProxy[A] {
    /**
     * Creates a <code>Rule</code> in which <code>self</code> is <code>null</code>.
     */
    def this() = this(null)

    /**
     * @return  a <code>Peg</code> assigned using <code>::=</code>;
     *          <code>null</code> in case <code>::=<code> is not called yet.
     */
    override def self = p

    /**
     * Assigns <code>that</code>.
     */
    def ::=(that: Peg[A]): Unit = { p = that }

    /**
     * @return  <code>new Rule[A](self)</code>.
     */
    override def clone: Rule[A] = new Rule[A](p)
}
