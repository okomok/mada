

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package blend; package list


case class Transform[H2, T2, R](f: (H2, T2) => R) {
    def apply[H1, T1 <: List](g: H1 => H2, h: T1 => T2) = new _Transform(f, g, h)
}


class _Transform[H2, T2, R, H1, T1 <: List](val f: (H2, T2) => R, g: H1 => H2, h: T1 => T2) extends (Cons[H1, T1] => R) {
    override def apply(xs: Cons[H1, T1]): R = f(g(xs.head), h(xs.tail))
}

object _Transform {
    sealed class _Of[H2, T2, R <: T2, H1, T1 <: List](_this: _Transform[H2, T2, R, H1, T1]) {
        def ::[_H1](g: _H1 => H2): _Transform[H2, R, R, _H1, Cons[H1, T1]]  = new _Transform(_this.f, g, _this)
    }
    implicit def _of[H2, T2, R <: T2, H1, T1 <: List](_this: _Transform[H2, T2, R, H1, T1]): _Of[H2, T2, R, H1, T1] = new _Of(_this)
}
