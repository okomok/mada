
// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


/**
 * Trait for addable sequence
 */
trait Addable[A] extends Reactive[A] with (A => Unit) {
    def add(e: A): Unit
    def addAll(it: Iterative[A]): Unit
}


object Addable {

    /**
     * Constructs a canonical Addable.
     */
    def apply[A](from: A*): Addable[A] = new Trivial(from)

    private
    final class Trivial[A](from: Iterative[A]) extends Addable[A] {
        @volatile private var out: A => Unit = null
        override def foreach(f: A => Unit) = {
            out = f
            addAll(from)
        }

        override def add(e: A) = out(e)
        override def addAll(it: Iterative[A]) = for (x <- it) out(x)

        @equivalentTo("add(e)")
        override def apply(e: A) = add(e)
    }

}
