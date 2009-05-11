

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package object auto {

    @aliasOf("Auto")
    type Type[-A] = Auto[A]

    /**
     * @return  <code>a.begin(e); try { f(e) } finally { a.end(e) }</code>.
     */
    def using[A, B](e: A)(f: A => B)(implicit a: Auto[A]): B = {
        a.begin(e)
        try {
            f(e)
        } finally {
            a.end(e)
        }
    }

}
