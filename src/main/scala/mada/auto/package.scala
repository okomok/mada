

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package object auto {


    @aliasOf("Auto")
    type Type[-A] = Auto[A]


    /**
     * Defines scope using the automatic reference.
     */
    @packageObjectBrokenOverload
    object using {
        def apply[A1, B](e1: A1)(f: A1 => B)(implicit a1: Auto[A1]): B = {
            a1.begin(e1)
            try {
                f(e1)
            } finally {
                a1.end(e1)
            }
        }

        def apply[A1, A2, B](e1: A1, e2: A2)(f: (A1, A2) => B)(implicit a1: Auto[A1], a2: Auto[A2]): B = {
            a1.begin(e1)
            a2.begin(e2)
            try {
                f(e1, e2)
            } finally {
                a2.end(e2)
                a1.end(e1)
            }
        }

        def apply[A1, A2, A3, B](e1: A1, e2: A2, e3: A3)(f: (A1, A2, A3) => B)(implicit a1: Auto[A1], a2: Auto[A2], a3: Auto[A3]): B = {
            a1.begin(e1)
            a2.begin(e2)
            a3.begin(e3)
            try {
                f(e1, e2, e3)
            } finally {
                a3.end(e3)
                a2.end(e2)
                a1.end(e1)
            }
        }

        def apply[A1, A2, A3, A4, B](e1: A1, e2: A2, e3: A3, e4: A4)(f: (A1, A2, A3, A4) => B)(implicit a1: Auto[A1], a2: Auto[A2], a3: Auto[A3], a4: Auto[A4]): B = {
            a1.begin(e1)
            a2.begin(e2)
            a3.begin(e3)
            a4.begin(e4)
            try {
                f(e1, e2, e3, e4)
            } finally {
                a4.end(e4)
                a3.end(e3)
                a2.end(e2)
                a1.end(e1)
            }
        }

        def apply[A1, A2, A3, A4, A5, B](e1: A1, e2: A2, e3: A3, e4: A4, e5: A5)(f: (A1, A2, A3, A4, A5) => B)(implicit a1: Auto[A1], a2: Auto[A2], a3: Auto[A3], a4: Auto[A4], a5: Auto[A5]): B = {
            a1.begin(e1)
            a2.begin(e2)
            a3.begin(e3)
            a4.begin(e4)
            a5.begin(e5)
            try {
                f(e1, e2, e3, e4, e5)
            } finally {
                a5.end(e5)
                a4.end(e4)
                a3.end(e3)
                a2.end(e2)
                a1.end(e1)
            }
        }
    }

}
