

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


import java.util.{ArrayList => JArrayList}


/**
 * Canonical mutable sequence
 */
final class ArrayList[A] extends Stream[A] {
    private val xs = new JArrayList[A]
    private val outs = new JArrayList[A => Unit]

    override def foreach(f: A => Unit) = {
        for (x <- iterative.from(xs)) f(x)
        outs.add(f)
    }

    @aliasOf("write")
    def add(x: A): Unit = write(x)

    override def head: A = xs.get(0)
    override def write(x: A) = {
        xs.add(x)
        for (out <- iterative.from(outs)) out(x)
    }
}


object ArrayList {
    /**
     * Constructs an ArrayList with initial values.
     */
    def apply[A](inits: A*): ArrayList[A] = {
        val that = new ArrayList[A]
        that.writeAll(inits)
        that
    }
}
