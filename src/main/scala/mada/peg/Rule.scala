

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


/**
 * A <code>Peg</code> to support recursive grammars.
 */
class Rule[A] extends PegProxy[A] with Proxies.Mutable[Peg[A]] {
    private var p: Peg[A] = null

    override def self = p
    override def :=(that: => Peg[A]) = p = that
    override def isEmptyProxy = p eq null

    /**
     * Returns a shallow copy. (The <code>self</code> is not copied.)
     */
    override def clone: Rule[A] = {
        val that = new Rule[A]
        that := self
        that
    }
}
