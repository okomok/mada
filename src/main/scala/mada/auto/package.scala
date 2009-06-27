

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
        def apply[A1, B](a1: Auto[A1])(f: A1 => B): B = a1.usedBy(f)

        def apply[A1, A2, B](a1: Auto[A1], a2: Auto[A2])(f: (A1, A2) => B): B = {
            using(a1) { e1 =>
                using(a2) { e2 =>
                    f(e1, e2)
                }
            }
        }

        def apply[A1, A2, A3, B](a1: Auto[A1], a2: Auto[A2], a3: Auto[A3])(f: (A1, A2, A3) => B): B = {
            using(a1) { e1 =>
                using(a2, a3) { (e2, e3) =>
                    f(e1, e2, e3)
                }
            }
        }

        def apply[A1, A2, A3, A4, B](a1: Auto[A1], a2: Auto[A2], a3: Auto[A3], a4: Auto[A4])(f: (A1, A2, A3, A4) => B): B = {
            using(a1) { e1 =>
                using(a2, a3, a4) { (e2, e3, e4) =>
                    f(e1, e2, e3, e4)
                }
            }
        }

        def apply[A1, A2, A3, A4, A5, B](a1: Auto[A1], a2: Auto[A2], a3: Auto[A3], a4: Auto[A4], a5: Auto[A5])(f: (A1, A2, A3, A4, A5) => B): B = {
            using(a1) { e1 =>
                using(a2, a3, a4, a5) { (e2, e3, e4, e5) =>
                    f(e1, e2, e3, e4, e5)
                }
            }
        }

    }


    /**
     * Returns an auto reference where <code>as</code> is used by <code>x</code>.
     */
    def usedWith[A](as: Seq[Auto[_]], e: A): Auto[A] = UsedWith(as, e)


// conversion

    @compatibleConversion
    def fromJCloseable[A <: java.io.Closeable](from: A): Auto[A] = FromJCloseable(from)

    @compatibleConversion
    def fromJLock[A <: java.util.concurrent.locks.Lock](from: A): Auto[A] = FromJLock(from)

}
