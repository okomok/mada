

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


// use copyTo

object Flatten {
    def apply[A](vv: Vector[Vector[A]]): Vector[A] = {
        val len = vv.foldLeft(0L)({ (c, v) => c + v.size })
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

/*
object Flatten3 {
    def apply[A](vv: Vector[Vector.Triple[A]]): Vector[A] = {
        val len = vv.foldLeft(0L)({ (c, v) => c + v.size })
        val a = new Array[A](len.toInt)
        var i = 0
        for ((v, n, m) <- vv) {
            for (e <- Vector.tripleVector(v, n, m)) {
                a(i) = e
                i += 1
            }
        }
        Vector.arrayVector(a)
    }
}*/
