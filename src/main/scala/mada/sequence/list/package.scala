

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


package object list {


// alias

    @aliasOf("List")
    type Type[+A] = List[A]


// trivial

    /**
     * @return  <code>Nil</code>.
     */
    val empty: List[Nothing] = Nil

    @aliasOf("NilOf")
    def emptyOf[A] = NilOf[A]

    /**
     * @return  <code>x :: Nil</code>.
     */
    def single[A](x: A): List[A] = x :: Nil


// algorithm

    /**
     * Unfolds right-associative.
     */
    def unfoldRight[A, B](x: A)(f: A => Option[(B, A)]): List[B] = f(x) match {
        case None => Nil
        case Some((y, x)) => y :: unfoldRight(x)(f)
    }

    /**
     * An infinite list of repeated applications of <code>f</code> to <code>x</code>.
     */
    def iterate[A](x: A)(f: A => A): List[A] = x :: iterate(f(x))(f)

    /**
     * An infinite list, with <code>x</code> the value of every element.
     */
    def repeat[A](x: A): List[A] = {
        lazy val xs: List[A] = x :: xs
        xs
    }

    /**
     * A list of length <code>n</code> with <code>x</code> the value of every element.
     */
    def replicate[A](n: Int, x: A): List[A] = repeat(x).take(n)

}
