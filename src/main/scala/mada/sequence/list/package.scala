

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


package object list {


// alias

    @aliasOf("List")
    type Type[+A] = List[A]

    @aliasOf("Nil")
    val nil = Nil

    @aliasOf("StrictCons")
    val :: = StrictCons


// constructor

    /**
     * A typed Nil
     */
    def nilOf[A]: List[A] = Nil

    /**
     * @return  <code>Cons(util.byLazy(x), util.byLazy(xs))</code>.
     */
    def cons[A](x: => A, xs: => List[A]): List[A] = Cons(util.byLazy(x), util.byLazy(xs))


// conversion


// detail


}
