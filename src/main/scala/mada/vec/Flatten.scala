

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Flatten {
    def apply[A](vv: Vector[Vector[A]]): Vector[A] = {
        val len = vv.foldLeft(0L)({ (c: Long, v: Vector[A]) => c + v.size })
        val a = new Array[A](len.toInt)
        var i = 0
        vv.foreach({ (v: Vector[A]) => v.foreach({ (e: A) => a(i) = e; i += 1 })})
        Vector.arrayVector(a)
    }
}
