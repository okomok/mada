

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


/**
 * Provides capturing groups functionality.
 */
class CapturingGroups[K, A](val map: Maps.Mutable[K, Vector[A]]) {
    /**
     * Constructs from a hash-map.
     */
    def this() = this(new scala.collection.jcl.HashMap[K, Vector[A]])

    /**
     * Alias of <code>capture</code>
     */
    final def apply(k: K, p: Peg[A]): Peg[A] = capture(k, p)

    /**
     * Captures matched region.
     */
    def capture(k: K, p: Peg[A]): Peg[A] = p.act({ v => map(k) = v })

    /**
     * Alias of <code>backref</code>
     */
    final def apply(k: K): Peg[A] = backref(k)

    /**
     * Back-reference
     */
    def backref(k: K): Peg[A] = new BackrefPeg(k)

    private class BackrefPeg(k: K) extends PegProxy[A] {
        override def self = Peg.fromVector(map(k))
    }
}
