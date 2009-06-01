

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


package object list {


    @aliasOf("Recursive")
    type Type[+A] = List[A]


// constructors

/*
    object cons {
        /**
         * Prepends <code>h</code> to <code>t</code> by lazy evaluation.
         */
        def apply[A](h: => A, t: => List[A]): Recursive[A] = new Cons(h, t)
    }*/

    val nil: List[Nothing] = Nil()


// conversions


// detail


}
