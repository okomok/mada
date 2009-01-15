

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


// ` capture(3, "abc" >> "efg") >> backref(3)

class Captures[A](val map: scala.collection.mutable.Map[Int, Vector[A]]) {
    def this() = this(new scala.collection.jcl.HashMap[Int, Vector[A]])

    def apply(i: Int, p: Peg[A]): Peg[A] = capture(i, p)
    def capture(i: Int, p: Peg[A]): Peg[A] = p.action({ (v: Vector[A]) => map(i) = v })
    def backref(i: Int): Peg[A] = Peg.vectorPeg(map(i))
}
