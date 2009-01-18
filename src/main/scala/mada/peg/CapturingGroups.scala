

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


class CapturingGroups[A](val map: scala.collection.mutable.Map[Int, Vector[A]]) {
    def this() = this(new scala.collection.jcl.HashMap[Int, Vector[A]])

    object group {
        def apply(i: Int): Vector[A] = map(i)
        def update(i: Int, v: Vector[A]): Unit = map(i) = v
    }

    def apply(i: Int, p: Peg[A]): Peg[A] = capture(i, p)
    def capture(i: Int, p: Peg[A]): Peg[A] = p.act({ (v, m, n) => group(i) = v(m, n) })

    def apply(i: Int): Peg[A] = backref(i)
    def backref(i: Int): Peg[A] = new BackrefPeg(i)

    class BackrefPeg(i: Int) extends PegProxy[A] {
        override def self = Peg.vectorPeg(group(i))
    }
}
