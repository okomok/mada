

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


package object list {


    @aliasOf("List")
    type Type[+A] = List[A]


// constructors

    object cons {
        /**
         * Prepends <code>x</code> to <code>xs</code> by lazy evaluation.
         */
        def apply[A](x: => A, xs: => List[A]): List[A] = Cons(util.byLazy(x), util.byLazy(xs))

        // TODO
        // unapply?, but can be lazy?: cons(x, _) // _ is evaluated?
    }

    /**
     * The universal nil
     */
    val nil: List[Nothing] = Nil()

    /**
     * A typed nil
     */
    def nilOf[A]: List[A] = Nil()

    /**
     * Refers a list by lazy.
     */
    def byLazy[A](xs: => List[A]): List[A] = ByLazy(util.byLazy(xs))

    /**
     * Refers a list by name.
     */
    def byName[A](xs: => List[A]): List[A] = ByName(util.byName(xs))


// conversion


// detail


}
