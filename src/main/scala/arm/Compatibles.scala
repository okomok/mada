

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package arm


@compatibles
trait Compatibles {
    implicit def fromJCloseable[A <: java.io.Closeable](from: A): Arm[A] = FromJCloseable(from)
    implicit def fromJLock[A <: java.util.concurrent.locks.Lock](from: A): Arm[A] = FromJLock(from)
}
