

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package auto


private
case class FromJCloseable[A <: java.io.Closeable](_1: A) extends Auto[A] {
    override val open = _1
    override def close = _1.close
}

private
case class FromJLock[A <: java.util.concurrent.locks.Lock](_1: A) extends Auto[A] {
    override def open = { _1.lock; _1 }
    override def close = _1.unlock
}
