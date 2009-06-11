

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


package object list {


// alias

    @aliasOf("List")
    type Type[+A] = List[A]


// constructor

    @aliasOf("Nil")
    val nil = Nil

    /**
     * A typed Nil
     */
    def nilOf[A]: List[A] = Nil

    /**
     * @return  <code>Cons(util.byLazy(x), util.byLazy(xs))</code>
     */
    def cons[A](x: => A, xs: => List[A]): List[A] = Cons(util.byLazy(x), util.byLazy(xs))


// algorithm

    /**
     * Returns the size.
     */
    def size[A](xs: List[A]): Int = {
        var r = 0
        var it = xs
        while (!it.isNil) {
            r += 1
            it = it.tail
        }
        r
    }

    def append[A](xs: => List[A], ys: => List[A]): List[A] = xs match {
        case Nil => ys
        case Cons(x, xs) => cons(x(), append(xs(), ys))
    }

    /**
     * Applies <code>f</code> to each element.
     */
    def foreach[A](xs: List[A])(f: A => Unit): Unit = {
        var it = xs
        while (!it.isNil) {
            f(it.head)
            it = it.tail
        }
    }

// conversion


// detail


}
