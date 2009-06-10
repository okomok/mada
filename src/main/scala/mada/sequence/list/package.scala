

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


import function.ofLazy


package object list {


    @aliasOf("List")
    type Type[+A] = List[A]


// constructors

    object cons {
        /**
         * Prepends <code>x</code> to <code>xs</code> by lazy evaluation.
         */
        def apply[A](x: => A, xs: => List[A]): List[A] = Cons(ofLazy(x), ofLazy(xs))
    }

    val nil: List[Nothing] = Nil()

    /**
     * Refers a list by lazy.
     */
    def byLazy[A](xs: => List[A]): List[A] = ByLazy(function.ofLazy(xs))

    /**
     * Refers a list by name.
     */
    def byName[A](xs: => List[A]): List[A] = ByName(function.ofName(xs))


// conversion


// detail


}
