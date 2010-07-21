

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package function


private[mada] case class Fuse1[-T1, +R](_1: Function1[T1, R]) extends Function1[Tuple1[T1], R] {
    override def apply(t: Tuple1[T1]) = _1(t._1)
}

private[mada] case class Fuse2[-T1, -T2, +R](_1: Function2[T1, T2, R]) extends Function1[Tuple2[T1, T2], R] {
    override def apply(t: Tuple2[T1, T2]) = _1(t._1, t._2)
}

private[mada] case class Fuse3[-T1, -T2, -T3, +R](_1: Function3[T1, T2, T3, R]) extends Function1[Tuple3[T1, T2, T3], R] {
    override def apply(t: Tuple3[T1, T2, T3]) = _1(t._1, t._2, t._3)
}


private[mada] case class Unfuse1[-T1, +R](_1: Function1[Tuple1[T1], R]) extends Function1[T1, R] {
    override def apply(v1: T1) = _1(Tuple1(v1))
}

private[mada] case class Unfuse2[-T1, -T2, +R](_1: Function1[Tuple2[T1, T2], R]) extends Function2[T1, T2, R] {
    override def apply(v1: T1, v2: T2) = _1(Tuple2(v1, v2))
}

private[mada] case class Unfuse3[-T1, -T2, -T3, +R](_1: Function1[Tuple3[T1, T2, T3], R]) extends Function3[T1, T2, T3, R] {
    override def apply(v1: T1, v2: T2, v3: T3) = _1(Tuple3(v1, v2, v3))
}
