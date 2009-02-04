

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Triplify {
    def apply[A, B](f: Vectors.Func[A, B]): Vectors.Func3[A, B] = {
        { (v: Vector[A], start: Int, end: Int) => f(v(start, end)) }
    }
}

private[mada] object Untriplify {
    def apply[A, B](f: Vectors.Func3[A, B]): Vectors.Func[A, B] = {
        { (v: Vector[A]) => f(v, v.start, v.end) }
    }
}
