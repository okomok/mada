

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Flatten {
    def apply[A](v: Vector[Vector[A]]): Vector[A] = new FlattenVector(v)
}

class FlattenVector[A](vv: Vector[Vector[A]]) extends Adapter[A, A] with NotWritable[A] {
    override val * = {
        val len = vv.foldLeft(0L, { (c: Long, v: Vector[A]) => c + v.size })
        val a = new Array[A](len.toInt)
        var i = 0
        vv.foreach({ (v: Vector[A]) => v.foreach({ (e: A) => a(i) = e; i += 1 })})
        FromArray(a)
    }
}


object FlatMap {
    def apply[A, B](v: Vector[A], f: A => Vector[B]): Vector[B] = Flatten(v.map(f))
}
