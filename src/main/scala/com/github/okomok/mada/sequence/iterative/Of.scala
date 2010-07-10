

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package iterative


/**
 * Creates a sequence initially containing the specified elements.
 */
object Of {
    def apply[A](from: A*): Iterative[A] = Iterative.from(from)
    def unapplySeq[A](from: Iterative[A]): Option[Seq[A]] = Some(from.toSeq)
}
