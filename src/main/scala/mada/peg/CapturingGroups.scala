

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


/**
 * Emulates capturing groups of regular expressions.
 */
class CapturingGroups[K, A](val group: scala.collection.mutable.Map[K, Vector[A]]) {
    def this() = this(new scala.collection.jcl.HashMap[K, Vector[A]])

    /**
     * Alias of <code>capture</code>
     */
    def apply(k: K, p: Peg[A]): Peg[A] = capture(k, p)

    /**
     * Captures matched region.
     */
    def capture(k: K, p: Peg[A]): Peg[A] = p.act({ (v, i, j) => group(k) = v(i, j) })

    /**
     * Alias of <code>backref</code>
     */
    def apply(k: K): Peg[A] = backref(k)

    /**
     * Back-reference
     */
    def backref(k: K): Peg[A] = new BackrefPeg(k)

    private class BackrefPeg(k: K) extends PegProxy[A] {
        override def self = Peg.vectorPeg(group(k))
    }
}
