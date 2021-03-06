

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


import java.util.ArrayList


/**
 * Infinite Reactive list (immutable)
 */
final class Rist[A] extends Reactive[A] {
    private[this] val xs = new ArrayList[A]
    private[this] val outs = new ArrayList[A => Unit]

    override def forloop(f: A => Unit, k: Exit => Unit) {
        for (x <- iterative.from(xs)) {
            f(x)
        }
        outs.add(f)
    }
//    override def head: A = xs.get(0)

    def add(y: A) {
        xs.add(y)
        for (out <- iterative.from(outs)) {
            out(y)
        }
    }
    def addAll(ys: Iterative[A]) {
        for (y <- ys) {
            add(y)
        }
    }
}


object Rist {
    /**
     * Constructs a Rist with initial values.
     */
    def apply[A](inits: A*): Rist[A] = {
        val that = new Rist[A]
        that.addAll(inits)
        that
    }
}
