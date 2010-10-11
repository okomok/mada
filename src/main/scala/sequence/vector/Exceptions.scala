

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector


/**
 * Thrown if vector is not readable.
 */
case class NotReadableException[A](_1: Vector[A]) extends UnsupportedOperationException("not readable")

/**
 * Thrown if vector is not writable.
 */
case class NotWritableException[A](_1: Vector[A]) extends UnsupportedOperationException("not writable")
