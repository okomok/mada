

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package peg


/**
 * Provides capturing groups functionality.
 */
class CapturingGroups[K, A](val map: scala.collection.mutable.Map[K, sequence.Vector[A]]) {
    /**
     * Constructs from a hash-assoc.
     */
    def this() = this(new scala.collection.mutable.HashMap[K, sequence.Vector[A]])

    @annotation.aliasOf("capture")
    final def apply(k: K, p: Peg[A]): Peg[A] = capture(k, p)

    /**
     * Captures matched region.
     */
    def capture(k: K, p: Peg[A]): Peg[A] = p.act{ v => map(k) = v }

    @annotation.aliasOf("backref")
    final def apply(k: K): Peg[A] = backref(k)

    /**
     * Back-reference
     */
    def backref(k: K): Peg[A] = Backref(k)

    case class Backref(_1: K) extends Forwarder[A] {
        override def delegate = fromSequence(map(_1))
    }
}
