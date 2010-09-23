

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


import java.util.LinkedList


/**
 * Trait for stream Reactive
 */
trait Stream[A] extends Reactive[A] with (A => Unit) {
    def write(x: A): Unit
    def writeAll(it: Iterative[A]): Unit = for (x <- it) write(x)

    @aliasOf("write")
    override def apply(x: A) = write(x)
}


object Stream {

    /**
     * Constructs a canonical Stream with initial values.
     */
    def apply[A](inits: A*): Stream[A] = {
        val xs = new LinkedList[A]
        for (x <- inits) xs.add(x)
        new Trivial(xs)
    }

    private
    final class Trivial[A](xs: LinkedList[A]) extends Stream[A] {
        private var outs = new LinkedList[A => Unit]

        override def foreach(f: A => Unit) = {
            for (x <- iterative.from(xs)) f(x)
            outs.add(f)
        }

        override def write(x: A) = {
            xs.add(x)
            for (out <- iterative.from(outs)) out(x)
        }

        override def head: A = xs.peek
    }

}
