

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


class CapturingGroups[K, A](val map: scala.collection.mutable.Map[K, Vector[A]]) {
    def this() = this(new scala.collection.jcl.HashMap[K, Vector[A]])

    object group {
        def apply(k: K): Vector[A] = map(k)
        def update(k: K, v: Vector[A]): Unit = map(k) = v
    }

    def apply(k: K, p: Peg[A]): Peg[A] = capture(k, p)
    def capture(k: K, p: Peg[A]): Peg[A] = p.act({ (v, i, j) => group(k) = v(i, j) })

    def apply(k: K): Peg[A] = backref(k)
    def backref(k: K): Peg[A] = new BackrefPeg(k)

    class BackrefPeg(k: K) extends PegProxy[A] {
        override def self = Peg.vectorPeg(group(k))
    }
}
