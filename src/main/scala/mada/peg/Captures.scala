

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


// ` capture(3, "abc" >> "efg") >> backref(3)

class Captures[A](val map: scala.collection.mutable.Map[Int, Vector[A]]) {
    def this() = this(new scala.collection.jcl.HashMap[Int, Vector[A]])

    def apply(i: Int, p: Peg[A]): Peg[A] = capture(i, p)
    def capture(i: Int, p: Peg[A]): Peg[A] = p.act({ (v: Vector[A], first: Long, last: Long) => map(i) = v.window(first, last) })

    def apply(i: Int): Peg[A] = backref(i)
    def backref(i: Int): Peg[A] = new BackrefPeg(i)

    class BackrefPeg(i: Int) extends PegProxy[A] {
        override def self = Peg.vectorPeg(map(i))
    }
}
