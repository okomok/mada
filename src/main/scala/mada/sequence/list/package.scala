

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


package object list {


    @aliasOf("List")
    type Type[+A] = List[A]


// constructors

    object cons {
        /**
         * Prepends <code>h</code> to <code>t</code> by lazy evaluation.
         */
        def apply[A](h: => A, t: => List[A]): List[A] = Cons(function.ofLazy(h), function.ofLazy(t))
    }

    val nil: List[Nothing] = Nil()


// conversions


// detail


}
