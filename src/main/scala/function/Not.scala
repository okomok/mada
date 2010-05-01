

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package function


case class Not1[-T1](_1: Predicate1[T1]) extends Predicate1[T1] {
    override def apply(v1: T1) = !_1(v1)
}

case class Not2[-T1, -T2](_1: Predicate2[T1, T2]) extends Predicate2[T1, T2] {
    override def apply(v1: T1, v2: T2) = !_1(v1, v2)
}

case class Not3[-T1, -T2, -T3](_1: Predicate3[T1, T2, T3]) extends Predicate3[T1, T2, T3] {
    override def apply(v1: T1, v2: T2, v3: T3) = !_1(v1, v2, v3)
}
