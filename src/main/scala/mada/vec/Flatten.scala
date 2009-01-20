

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Flatten {
    // use copyTo?
    def apply[A](vv: Vector[Vector[A]]): Vector[A] = {
        val len = FlattenSize(vv)
        val a = new Array[A](len.toInt)
        var i = 0
        for (v <- vv) {
            for (e <- v) {
                a(i) = e
                i += 1
            }
        }
        Vector.arrayVector(a)
    }
}

object FlattenSize {
    def apply[A](vv: Vector[Vector[A]]): Long = vv.foldLeft(0L)({ (c, v) => c + v.size })
}
