

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package blend; package list


case class Transformer[H2, T2, R](f: (H2, T2) => R) {
    def apply[H1, T1 <: List](g: H1 => H2, h: T1 => T2) = new scala.Function1[Cons[H1, T1], R] {
        override def apply(xs: Cons[H1, T1]): R = f(g(xs.head), h(xs.tail))
    }
}
