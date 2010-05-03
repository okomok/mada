

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package function


// See: That about wraps it up --- Using FIX to handle errors without exceptions, and other programming tricks (1997)
//      at http://citeseer.ist.psu.edu/51062.html


case class Memoize[T, R](_1: (T => R) => T => R) extends (T => R) {
    private val f = {
        val m = new java.util.concurrent.ConcurrentHashMap[T, () => R]
        val wrap = { (fixed: (T => R)) => (v: T) => assoc.lazyGet(m)(v){ _1(fixed)(v) } }
        fix(wrap)
    }
    override def apply(v: T) = f(v)
}
