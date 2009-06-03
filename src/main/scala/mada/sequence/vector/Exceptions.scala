

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


/**
 * Thrown if vector is not readable.
 */
class NotReadableException[A](val vector: Vector[A]) extends RuntimeException

/**
 * Thrown if vector is not writable.
 */
class NotWritableException[A](val vector: Vector[A]) extends RuntimeException
