

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


/**
 * Thrown if vector is not readable.
 */
class NotReadableError[A](val vector: Vector[A]) extends Error

/**
 * Thrown if vector is not writable.
 */
class NotWritableError[A](val vector: Vector[A]) extends Error
