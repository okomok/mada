

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector


// TODO: per-one-iteration check may be a bottleneck.


private class Breakable1[A](p: A => Boolean, ret: Boolean) extends (A => Boolean) {
    @volatile private var _breaks = false
    def breaks: Boolean = _breaks
    def break: Unit = _breaks = true
    override def apply(a: A) = if (_breaks) ret else p(a)
}

private class Breakable2[A, B](p: (A, B) => Boolean, ret: Boolean) extends ((A, B) => Boolean) {
    @volatile private var _breaks = false
    def breaks: Boolean = _breaks
    def break: Unit = _breaks = true
    override def apply(a: A, b: B) = if (_breaks) ret else p(a, b)
}
