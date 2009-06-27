

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.auto


case class FromJCloseable[A <: java.io.Closeable](_1: A) extends Auto[A] {
    override def autoRef = _1
    override def autoEnd = _1.close
}

case class FromJLock[A <: java.util.concurrent.locks.Lock](_1: A) extends Auto[A] {
    override def autoRef = _1
    override def autoBegin = _1.lock
    override def autoEnd = _1.unlock
}
