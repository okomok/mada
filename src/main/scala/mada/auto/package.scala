

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package object auto {


    @aliasOf("Auto")
    type Type[+A] = Auto[A]


    /**
     * Defines scope using the automatic reference.
     */
    @packageObjectBrokenOverload
    object using {
        def apply[A1, B](a1: Auto[A1])(f: A1 => B): B = {
            a1.autoBegin
            try {
                f(a1.autoRef)
            } finally {
                a1.autoEnd
            }
        }

        def apply[A1, A2, B](a1: Auto[A1], a2: Auto[A2])(f: (A1, A2) => B): B = {
            a1.autoBegin
            a2.autoBegin
            try {
                f(a1.autoRef, a2.autoRef)
            } finally {
                a2.autoEnd
                a1.autoEnd
            }
        }

        def apply[A1, A2, A3, B](a1: Auto[A1], a2: Auto[A2], a3: Auto[A3])(f: (A1, A2, A3) => B): B = {
            a1.autoBegin
            a2.autoBegin
            a3.autoBegin
            try {
                f(a1.autoRef, a2.autoRef, a3.autoRef)
            } finally {
                a3.autoEnd
                a2.autoEnd
                a1.autoEnd
            }
        }

        def apply[A1, A2, A3, A4, B](a1: Auto[A1], a2: Auto[A2], a3: Auto[A3], a4: Auto[A4])(f: (A1, A2, A3, A4) => B): B = {
            a1.autoBegin
            a2.autoBegin
            a3.autoBegin
            a4.autoBegin
            try {
                f(a1.autoRef, a2.autoRef, a3.autoRef, a4.autoRef)
            } finally {
                a4.autoEnd
                a3.autoEnd
                a2.autoEnd
                a1.autoEnd
            }
        }

        def apply[A1, A2, A3, A4, A5, B](a1: Auto[A1], a2: Auto[A2], a3: Auto[A3], a4: Auto[A4], a5: Auto[A5])(f: (A1, A2, A3, A4, A5) => B): B = {
            a1.autoBegin
            a2.autoBegin
            a3.autoBegin
            a4.autoBegin
            a5.autoBegin
            try {
                f(a1.autoRef, a2.autoRef, a3.autoRef, a4.autoRef, a5.autoRef)
            } finally {
                a5.autoEnd
                a4.autoEnd
                a3.autoEnd
                a2.autoEnd
                a1.autoEnd
            }
        }
    }


// conversion

    @compatibleConversion
    def fromJCloseable[A <: java.io.Closeable](from: A): Auto[A] = FromJCloseable(from)

    @compatibleConversion
    def fromJLock[A <: java.util.concurrent.locks.Lock](from: A): Auto[A] = FromJLock(from)

}
