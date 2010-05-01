

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package vector


/**
 * Creates a sequence initially containing the specified elements.
 */
object Of {
    def apply[A](from: A*): Vector[A] = iterative.fromSIterable(from).toVector
    def unapplySeq[A](from: Vector[A]): Option[Seq[A]] = Some(from.toSIndexedSeq)
}
