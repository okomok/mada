

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


import scala.util.continuations


/**
 * Trivial mixin for single-element sequence
 */
trait ReactiveSingle[+A] extends Reactive[A] {
    @equivalentTo("head")
    final def get: A @continuations.cpsParam[Any, Any] = head
}

object ReactiveSingle {
    implicit def _toIterative[A](from: ReactiveSingle[A]): Iterative[A] = from.toIterative
}
