

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package eval


trait Strategy {
    def apply[R](f: ByName[R]): Function0[R]
    def apply[R](f: => R): Function0[R] // needed: http://lampsvn.epfl.ch/trac/scala/ticket/3237
}

object Strategy {
    implicit def _toFunction[R](from: Strategy): (=> R) => Function0[R] = from.apply[R]
    implicit def _toFunctionUnit[R](from: Strategy): (=> R) => Unit = function.discard(from.apply[R])
}
