

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object ReadMap {
    def apply[Z, A](p: Peg[A], f: Vector[Z] => Vector[A]): Peg[Z] = new ReadMapPeg(p, f)
}

class ReadMapPeg[Z, A](p: Peg[A], f: Vector[Z] => Vector[A]) extends Peg[Z] {
    override def parse(v: Vector[Z], first: Int, last: Int): Int = {
        p.parse(f(v), first, last) // f must return one-to-one view of Vector
    }

    override def length = p.length
}
