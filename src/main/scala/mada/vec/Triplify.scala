

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Triplify {
    def apply[A, B](f: Vector.Func[A, B]): Vector.Func3[A, B] = {
        { (v: Vector[A], i: Int, j: Int) => f(v.window(i, j)) }
    }
}

object Untriplify {
    def apply[A, B](f: Vector.Func3[A, B]): Vector.Func[A, B] = {
        { (v: Vector[A]) => f(v, 0, v.size) }
    }
}
