

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


import java.util.LinkedList


/**
 * Trait for addable sequence
 */
trait Addable[A] extends Reactive[A] with (A => Unit) {
    def add(x: A): Unit
    def addAll(it: Iterative[A]): Unit
}


object Addable {

    /**
     * Constructs a canonical Addable.
     */
    def apply[A](from: A*): Addable[A] = {
        val xs = new LinkedList[A]
        for (x <- from) xs.add(x)
        new Trivial(xs)
    }

    private
    final class Trivial[A](xs: LinkedList[A]) extends Addable[A] {
        private var outs = new LinkedList[A => Unit]

        override def foreach(f: A => Unit) = {
            for (x <- iterative.from(xs)) f(x)
            outs.add(f)
        }

        override def add(x: A) = {
            xs.add(x)
            for (out <- iterative.from(outs)) out(x)
        }
        override def addAll(it: Iterative[A]) = {
            for (x <- it) {
                xs.add(x)
                for (out <- iterative.from(outs)) out(x)
            }
        }

        @equivalentTo("add(e)")
        override def apply(x: A) = add(x)
        override def head: A = xs.peek
    }

}
