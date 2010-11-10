

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterative


import java.lang.InterruptedException
import java.util.ArrayDeque
import java.util.concurrent


trait Yield[-A] extends (A => Unit) {
    def flush: Unit
}


private
case class Generator[+A](_1: Yield[A] => Unit) extends Iterative[A] {
    override def begin = new _Iterator[A] {
        private[this] var in = new _Generator.Data[A]
        private[this] val x = new concurrent.Exchanger[_Generator.Data[A]]

        locally {
            val t = new _Generator.Task(_1, x)
            try {
                util.Execute{t.run}
            } catch {
                case _: concurrent.RejectedExecutionException => new Thread(t).start
            }
        }

        doExchange
        forwardExn

        override protected def _isEnd = in.buf.isEmpty
        override protected def _deref = in.buf.getFirst
        override protected def _increment {
            in.buf.removeFirst
            if (in.buf.isEmpty && !in.isLast) {
                doExchange
            }
            forwardExn
        }

        private def forwardExn {
            if (in.buf.isEmpty && in.isLast && !in.exn.isEmpty) {
                throw in.exn.get
            }
        }

        private def doExchange {
            assert(in.buf.isEmpty)
            in = x.exchange(in)
            assert(!in.buf.isEmpty || in.isLast)
        }
    }
}


private
object _Generator {

    val CAPACITY = 20

    class Task[A](op: Yield[A] => Unit, x: concurrent.Exchanger[Data[A]]) extends Runnable {
        private[this] var out = new Data[A]

        private[this] val y = new Yield[A] {
            override def apply(e: A) = {
                out.buf.addLast(e)
                if (out.buf.size == CAPACITY) {
                    doExchange
                }
            }
            override def flush = {
                if (!out.buf.isEmpty) {
                    doExchange
                }
            }
        }

        override def run {
            try {
                op(y)
            } catch {
                case t: Throwable => out.exn = Some(t)
            } finally {
                out.isLast = true
                doExchange
            }
        }

        private def doExchange {
            out = x.exchange(out)
            assert(out.buf.isEmpty)
        }
    }

    class Data[A](val buf: ArrayDeque[A], var isLast: Boolean, var exn: Option[Throwable]) {
        def this() = this(new ArrayDeque[A](CAPACITY), false, None)
    }

}
