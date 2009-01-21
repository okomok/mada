

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Flatten {
    def apply[A](vv: Vector[Vector[A]]): Vector[A] = {
        val av = Vector.arrayVector(new Array[A](FlattenSize(vv)))
        var (i, j) = (0, 0)
        for (v <- vv) {
            j += v.size
            v.copyTo(av.window(i, j))
            i = j
        }
        av
    }
}

object Flatten3 {
    def apply[A](vv: Vector[Vector.Triple[A]]): Vector[A] = Vector.flatten(Vector.triplesVectors(vv))
}

object FlattenSize {
    def apply[A](vv: Vector[Vector[A]]): Int = vv.foldLeft(0)({ (c, v) => c + v.size })
}
