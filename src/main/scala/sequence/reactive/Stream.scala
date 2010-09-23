

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


/**
 * Represents a stream of elements.
 */
trait Stream[A] extends Reactive[A] with (A => Unit) {
    def write(x: A): Unit
    def writeAll(it: Iterative[A]): Unit = for (x <- it) write(x)

    @aliasOf("write")
    override def apply(x: A) = write(x)
}
