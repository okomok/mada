

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


/**
 * Mixin for a Closeable Reactive.
 */
trait Closeable[+A] extends Reactive[A] with java.io.Closeable {
    def used: Reactive[A] = using(this)
}
