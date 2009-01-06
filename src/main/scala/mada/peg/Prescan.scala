

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Prescan {
    def apply[Z, A](p: Peg[A], f: Vector[Z] => Vector[A]): Peg[Z] = new PrescanPeg(p, f)
}

class PrescanPeg[Z, A](p: Peg[A], f: Vector[Z] => Vector[A]) extends Peg[Z] {
    override def parse(v: Vector[Z], first: Long, last: Long): Long = {
        p.parse(f(v), first, last) // f must return one-to-one view of Vector
    }

    override def length = p.length
}
