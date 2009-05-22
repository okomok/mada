

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.function


case class Synchronize1[-T1, +R](_1: Function1[T1, R]) extends Function1[T1, R] {
    override def apply(v1: T1) = synchronized { _1(v1) }
}

case class Synchronize2[-T1, -T2, +R](_1: Function2[T1, T2, R]) extends Function2[T1, T2, R] {
    override def apply(v1: T1, v2: T2) = synchronized { _1(v1, v2) }
}

case class Synchronize3[-T1, -T2, -T3, +R](_1: Function3[T1, T2, T3, R]) extends Function3[T1, T2, T3, R] {
    override def apply(v1: T1, v2: T2, v3: T3) = synchronized { _1(v1, v2, v3) }
}
