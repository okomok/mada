

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.auto


/**
 * Contains eligible objects for <code>Auto</code>.
 */
trait Eligibles { this: Auto.type =>
    import java.io.Closeable
    import java.util.concurrent.locks.Lock

    implicit object ofInterface extends Auto[Interface] {
        override def begin(e: Interface) = e.begin
        override def end(e: Interface) = e.end
    }

    implicit object ofCloseable extends Auto[Closeable] {
        override def end(e: Closeable) = e.close
    }

    implicit object ofLock extends Auto[Lock] {
        override def begin(e: Lock) = e.lock
        override def end(e: Lock) = e.unlock
    }
}
