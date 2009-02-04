

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Flatten {
    def apply[A](vv: Vector[Vector[A]]): Vector[A] = {
        val av = Vector.fromArray(new Array[A](FlattenSize(vv)))
        var (i, j) = (av.start, av.start)
        for (v <- vv) {
            j += v.size
            v.copyTo(av(i, j))
            i = j
        }
        av
    }
}

private[mada] object FlattenSize {
    def apply[A](vv: Vector[Vector[A]]): Int = vv.foldLeft(0)({ (c, v) => c + v.size })
}
