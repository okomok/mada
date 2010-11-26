

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package list


private[list]
class Common {


// trivial

    @annotation.aliasOf("Nil")
    val empty: List[Nothing] = Nil

    @annotation.equivalentTo("x :: Nil")
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
     * A list of range.
     */
    def range(n: Int, m: Int): List[Int] = {
        Precondition.range(n, m, "list.range")
        if (n == m) Nil else n :: range(n + 1, m)
    }

    /**
     * A list of length <code>n</code> with <code>x</code> the value of every element.
     */
    def replicate[A](n: Int, x: A): List[A] = repeat(x).take(n)


// pattern matching

    @annotation.aliasOf("Of.apply")
    def apply[A](from: A*): List[A] = Of.apply(from: _*)

    @annotation.aliasOf("Of.unapplySeq")
    def unapplySeq[A](from: List[A]): Option[Seq[A]] = Of.unapplySeq(from)

    /**
     * Creates a sequence initially containing the specified elements.
     */
    object Of {
        def apply[A](from: A*): List[A] = Iterative.from(from).toList
        def unapplySeq[A](from: List[A]): Option[Seq[A]] = Some(from.toSeq)
    }

}
