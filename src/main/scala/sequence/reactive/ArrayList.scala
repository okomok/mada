

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


import java.util.{ArrayList => JArrayList}


/**
 * Canonical mutable sequence
 */
final class ArrayList[A] extends Reactive[A] with (A => Unit) {
    private val xs = new JArrayList[A]
    private val outs = new JArrayList[A => Unit]

    override def foreach(f: A => Unit) = {
        for (x <- iterative.from(xs)) f(x)
        outs.add(f)
    }
    override def head: A = xs.get(0)

    def add(y: A): Unit = {
        xs.add(y)
        for (out <- iterative.from(outs)) out(y)
    }
    def addAll(ys: Iterative[A]): Unit = for (y <- ys) add(y)

    @aliasOf("add")
    override def apply(y: A) = add(y)
}


object ArrayList {
    /**
     * Constructs an ArrayList with initial values.
     */
    def apply[A](inits: A*): ArrayList[A] = {
        val that = new ArrayList[A]
        that.addAll(inits)
        that
    }
}
