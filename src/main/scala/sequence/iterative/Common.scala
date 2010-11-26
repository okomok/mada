

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterative


import scala.util.continuations.suspendable


private[iterative]
class Common {

    @annotation.returnThat
    def from[A](to: Iterative[A]): Iterative[A] = to

    @annotation.aliasOf("Of.apply")
    def apply[A](from: A*): Iterative[A] = Of.apply(from: _*)

    @annotation.aliasOf("Of.unapplySeq")
    def unapplySeq[A](from: Iterative[A]): Option[Seq[A]] = Of.unapplySeq(from)

    /**
     * The empty sequence
     */
    val empty: Iterative[Nothing] = Empty()

    /**
     * A sequence with a single element.
     */
    def single[A](e: A): Iterative[A] = Single(e)

    /**
     * A sequence with a single element.
     */
    def lazySingle[A](e: => A): Iterative[A] = LazySingle(e)

    /**
     * Refers a sequence by lazy.
     */
    def `lazy`[A](it: => Iterative[A]): Iterative[A] = Lazy(it)

    /**
     * Refers a sequence by name.
     */
    def byName[A](it: => Iterative[A]): Iterative[A] = ByName(it)

    /**
     * Creates an infinite sequence repeatedly applying <code>op</code>.
     */
    def iterate[A](z: A)(op: A => A): Iterative[A] = Iterate(z, op)

    /**
     * Repeats <code>e</code> infinitely.
     */
    def repeat[A](e: A): Iterative[A] = Repeat(e)

    /**
     * Unfolds right-associative.
     */
    def unfoldRight[A, B](z: A)(op: A => Option[(B, A)]): Iterative[B] = UnfoldRight(z, op)

    /**
     * Creates a sequence starting from <code>it<code>.
     */
    def bind[A](it: Iterator[A]): Iterative[A] = Bind(it)

    /**
     * Creates a sequence starting from <code>it<code>, which is evaluated by-name.
     */
    def bindName[A](it: => Iterator[A]): Iterative[A] = BindName(it)

    /**
     * Constructs a sequence from `Yield` expressions.
     */
    def generator[A](op: Yield[A] => Unit): Iterative[A] = Generator(op)

    /**
     * Constructs a sequence from cps expressions.
     */
    def block[A](op: (A => Unit @suspendable) => Unit @suspendable): Iterative[A] = Block(op)

    /**
     * Creates a sequence initially containing the specified elements.
     */
    object Of {
        def apply[A](from: A*): Iterative[A] = Iterative.from(from)
        def unapplySeq[A](from: Iterative[A]): Option[Seq[A]] = Some(from.toSeq)
    }

    /*
     * Zips Iteratives.
    def zipAll(v: Vector[Sequence[Any]]): Iterative[Vector[Any]] = ZipAll(v)
    def zip[T1, T2](v1: Iterative[T1], v2: Iterative[T2]): Iterative[(T1, T2)] = v1.zip(v2)
    def zip[T1, T2, T3](v1: Iterative[T1], v2: Iterative[T2], v3: Iterative[T3]): Iterative[(T1, T2, T3)] = {
        ZipAll(Vector(v1, v2, v3)).map(v => (v.nth(0).asInstanceOf[T1], v.nth(1).asInstanceOf[T2], v.nth(2).asInstanceOf[T3]))
    }
    def zip[T1, T2, T3, T4](v1: Iterative[T1], v2: Iterative[T2], v3: Iterative[T3], v4: Iterative[T4]): Iterative[(T1, T2, T3, T4)] = {
        ZipAll(Vector(v1, v2, v3, v4)).map(v => (v.nth(0).asInstanceOf[T1], v.nth(1).asInstanceOf[T2], v.nth(2).asInstanceOf[T3], v.nth(3).asInstanceOf[T4]))
    }
    def zip[T1, T2, T3, T4, T5](v1: Iterative[T1], v2: Iterative[T2], v3: Iterative[T3], v4: Iterative[T4], v5: Iterative[T5]): Iterative[(T1, T2, T3, T4, T5)] = {
        ZipAll(Vector(v1, v2, v3, v4, v5)).map(v => (v.nth(0).asInstanceOf[T1], v.nth(1).asInstanceOf[T2], v.nth(2).asInstanceOf[T3], v.nth(3).asInstanceOf[T4], v.nth(4).asInstanceOf[T5]))
    }
     */
}
