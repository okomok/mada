

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


import java.lang.InterruptedException
import java.util.ArrayDeque
import java.util.concurrent.Exchanger


case class Block[+A](_1: Yield[A] => Unit) extends Sequence[A] {
    override def begin = new Iterator[A] {
        private var in = new _Block._Data[A]
        private val x = new Exchanger[_Block._Data[A]]

        new _Block._Thread(_1, x).start
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
            Assert(in.buf.isEmpty)
            in = x.exchange(in)
        }
    }
}


private object _Block {

    val CAPACITY = 20

    class _Thread[A](op: Yield[A] => Unit, x: Exchanger[_Data[A]]) extends java.lang.Thread {
        private var out = new _Data[A]

        private val y = new Yield[A] {
            override def apply(e: A) = {
                out.buf.addLast(e)
                if (out.buf.size == CAPACITY) {
                    doExchange
                }
            }
        }

        override def run() = {
            try {
                op(y)
            } finally {
                out.isLast = true
                doExchange
            }
        }

        private def doExchange: Unit = {
            out = x.exchange(out)
            Assert(out.buf.isEmpty)
        }
    }

    class _Data[A](val buf: ArrayDeque[A], var isLast: Boolean) {
        def this() = this(new ArrayDeque[A](CAPACITY), false)
    }

}
