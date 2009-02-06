

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Where {
    def apply[A](f: Vector.Func[A, Int]): Peg[A] = Where3(Vector.triplify(f))
}

private[mada] object Where3 {
    def apply[A](f: Vector.Func3[A, Int]): Peg[A] = new Where3Peg[A](f)
}

private[mada] class Where3Peg[A](f: Vector.Func3[A, Int]) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        if (start == f(v, start, end)) {
            start
        } else {
            Peg.FAILURE
        }
    }

    override def width = 0
}
