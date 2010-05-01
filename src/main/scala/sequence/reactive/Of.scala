

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package reactive


/**
 * Creates a sequence initially containing the specified elements.
 */
object Of {
    def apply[A](from: A*): Reactive[A] = fromSIterable(from)
}
