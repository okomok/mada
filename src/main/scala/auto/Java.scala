

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package auto


private[auto]
case class FromJCloseable[A <: java.io.Closeable](_1: A) extends Auto[A] {
    override def foreach(f: A => Unit) = {
        try {
            f(_1)
        } finally {
            _1.close
        }
    }
}

private[auto]
case class FromJLock[A <: java.util.concurrent.locks.Lock](_1: A) extends Auto[A] {
    override def foreach(f: A => Unit) = {
        _1.lock
        try {
            f(_1)
        } finally {
            _1.unlock
        }
    }
}
