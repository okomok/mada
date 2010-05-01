

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package function


case class Fix[T, R](_1: (T => R) => T => R) extends (T => R) {
    override def apply(v: T) = impl(_1)(v)
    private def impl(g: (T => R) => T => R)(v: T): R = g(impl(g))(v)
}
