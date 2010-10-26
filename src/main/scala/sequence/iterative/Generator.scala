

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterative


import java.lang.InterruptedException
import java.util.ArrayDeque
import java.util.concurrent.Exchanger


trait Yield[-A] extends (A => Unit) {
    def flush: Unit
}


private
case class Generator[+A](_1: Yield[A] => Unit) extends Iterative[A] {
    override def begin = new Iterator[A] {
        private[this] var in = new _Generator.Data[A]
        private[this] val x = new Exchanger[_Generator.Data[A]]

        util.Parallel{new _Generator.Task(_1, x).run}
        doExchange

        override def isEnd = in.buf.isEmpty
        override def deref = {
            preDeref
            in.buf.getFirst
        }
        override def increment = {
            preIncrement
            in.buf.removeFirst
            if (in.buf.isEmpty && !in.isLast) {
                doExchange
            }
        }

        private def doExchange: Unit = {
            assert(in.buf.isEmpty)
            in = x.exchange(in)
        }
    }
}


private
object _Generator {

    val CAPACITY = 20

    class Task[A](op: Yield[A] => Unit, x: Exchanger[Data[A]]) {
        private[this] var out = new Data[A]

        private[this] val y = new Yield[A] {
            override def apply(e: A) = {
                out.buf.addLast(e)
                if (out.buf.size == CAPACITY) {
                    doExchange
                }
            }
            override def flush = {
                if (out.buf.size != 0) {
                    doExchange
                }
            }
        }

        def run: Unit = {
            try {
                op(y)
            } finally {
                out.isLast = true
                doExchange
            }
        }

        private def doExchange: Unit = {
            out = x.exchange(out)
            assert(out.buf.isEmpty)
        }
    }

    class Data[A](val buf: ArrayDeque[A], var isLast: Boolean) {
        def this() = this(new ArrayDeque[A](CAPACITY), false)
    }

}
