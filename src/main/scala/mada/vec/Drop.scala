

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Drop {
    def apply[A](v: Vector[A], n: Int): Vector[A] = {
        val (x, i, j) = v.triple
        x.window(Math.min(i + n, j), j)
    }
}

private[mada] object DropWhile {
    def apply[A](v: Vector[A], p: A => Boolean): Vector[A] = {
        val (x, i, j) = v.triple
        x.window(stl.FindIf(x, i, j, Functions.not(p)), j)
    }
}
