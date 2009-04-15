

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.func


/**
 * Converts a function into one taking a tuple argument, and vice versa.
 * (This trait is for exposition only: use <code>Functions</code> methods instead.)
 */
trait Fuse {
    def fuse1[T1, R](f: Function1[T1, R]): Function1[Tuple1[T1], R] = { v => f(v._1) }
    def fuse2[T1, T2, R](f: Function2[T1, T2, R]): Function1[Tuple2[T1, T2], R] = { v => f(v._1, v._2) }
    def fuse3[T1, T2, T3, R](f: Function3[T1, T2, T3, R]): Function1[Tuple3[T1, T2, T3], R] = { v => f(v._1, v._2, v._3) }

    def fuse[T1, R](f: Function1[T1, R]): Function1[Tuple1[T1], R] = fuse1(f)
    def fuse[T1, T2, R](f: Function2[T1, T2, R]): Function1[Tuple2[T1, T2], R] = fuse2(f)
    def fuse[T1, T2, T3, R](f: Function3[T1, T2, T3, R]): Function1[Tuple3[T1, T2, T3], R] = fuse3(f)

    def unfuse1[T1, R](f: Function1[Tuple1[T1], R]): Function1[T1, R] = { v1 => f(Tuple1(v1)) }
    def unfuse2[T1, T2, R](f: Function1[Tuple2[T1, T2], R]): Function2[T1, T2, R] = { (v1, v2) => f(Tuple2(v1, v2)) }
    def unfuse3[T1, T2, T3, R](f: Function1[Tuple3[T1, T2, T3], R]): Function3[T1, T2, T3, R] = { (v1, v2, v3) => f(Tuple3(v1, v2, v3)) }
}
