

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import auto._


/**
 * Trait for "automatic variable"
 */
trait Auto[-A] {

    /**
     * Called when block begins.
     */
    def begin(e: A): Unit = ()

    /**
     * Called when block ends.
     */
    def end(e: A): Unit = ()

}


object Auto {


// eligibles

    import java.io.Closeable
    import java.util.concurrent.locks.Lock

    implicit object _ofInterface extends Auto[Interface] {
        override def begin(e: Interface) = e.begin
        override def end(e: Interface) = e.end
    }

    implicit object _ofCloseable extends Auto[Closeable] {
        override def end(e: Closeable) = e.close
    }

    implicit object _ofLock extends Auto[Lock] {
        override def begin(e: Lock) = e.lock
        override def end(e: Lock) = e.unlock
    }


// apply

    @aliasOf("auto.using")
    def apply[A, B](e: A)(f: A => B)(implicit a: Auto[A]): B = using(e)(f)(a)

}
