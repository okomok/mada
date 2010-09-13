

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package auto


private[auto]
case class FromJCloseable[A <: java.io.Closeable](_1: A) extends Resource[A] {
    override protected val get = _1
    override protected def end = _1.close
}

private[auto]
case class FromJLock[A <: java.util.concurrent.locks.Lock](_1: A) extends Resource[A] {
    override protected val get = _1
    override protected def begin = _1.lock
    override protected def end = _1.unlock
}
