

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
        override def begin(e: Interface) = e.autoBegin
        override def end(e: Interface) = e.autoEnd
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
    def apply[A1, B](e1: A1)(f: A1 => B)(implicit a1: Auto[A1]): B = using(e1)(f)(a1)
    @aliasOf("auto.using")
    def apply[A1, A2, B](e1: A1, e2: A2)(f: (A1, A2) => B)(implicit a1: Auto[A1], a2: Auto[A2]): B = using(e1, e2)(f)(a1, a2)
    @aliasOf("auto.using")
    def apply[A1, A2, A3, B](e1: A1, e2: A2, e3: A3)(f: (A1, A2, A3) => B)(implicit a1: Auto[A1], a2: Auto[A2], a3: Auto[A3]): B = using(e1, e2, e3)(f)(a1, a2, a3)
    @aliasOf("auto.using")
    def apply[A1, A2, A3, A4, B](e1: A1, e2: A2, e3: A3, e4: A4)(f: (A1, A2, A3, A4) => B)(implicit a1: Auto[A1], a2: Auto[A2], a3: Auto[A3], a4: Auto[A4]): B = using(e1, e2, e3, e4)(f)(a1, a2, a3, a4)
    @aliasOf("auto.using")
    def apply[A1, A2, A3, A4, A5, B](e1: A1, e2: A2, e3: A3, e4: A4, e5: A5)(f: (A1, A2, A3, A4, A5) => B)(implicit a1: Auto[A1], a2: Auto[A2], a3: Auto[A3], a4: Auto[A4], a5: Auto[A5]): B = using(e1, e2, e3, e4, e5)(f)(a1, a2, a3, a4, a5)

}
