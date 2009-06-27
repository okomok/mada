

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import auto._


/**
 * Trait for "automatic reference"
 */
trait Auto[+A] {

    /**
     * Returns the associated reference.
     */
    def get: A

    /**
     * Called when block begins.
     */
    def begin: Unit = ()

    /**
     * Called when block ends.
     */
    def end: Unit = ()

}


object Auto {


// compatibles

    implicit def _fromJCloseable[A <: java.io.Closeable](from: A): Auto[A] = fromJCloseable(from)
    implicit def _fromJLock[A <: java.util.concurrent.locks.Lock](from: A): Auto[A] = fromJLock(from)


// apply

    @aliasOf("auto.using")
    def apply[A1, B](a1: Auto[A1])(f: A1 => B): B = using(a1)(f)
    @aliasOf("auto.using")
    def apply[A1, A2, B](a1: Auto[A1], a2: Auto[A2])(f: (A1, A2) => B): B = using(a1, a2)(f)
    @aliasOf("auto.using")
    def apply[A1, A2, A3, B](a1: Auto[A1], a2: Auto[A2], a3: Auto[A3])(f: (A1, A2, A3) => B): B = using(a1, a2, a3)(f)
    @aliasOf("auto.using")
    def apply[A1, A2, A3, A4, B](a1: Auto[A1], a2: Auto[A2], a3: Auto[A3], a4: Auto[A4])(f: (A1, A2, A3, A4) => B): B = using(a1, a2, a3, a4)(f)
    @aliasOf("auto.using")
    def apply[A1, A2, A3, A4, A5, B](a1: Auto[A1], a2: Auto[A2], a3: Auto[A3], a4: Auto[A4], a5: Auto[A5])(f: (A1, A2, A3, A4, A5) => B): B = using(a1, a2, a3, a4, a5)(f)

}
