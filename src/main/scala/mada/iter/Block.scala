

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


import Iterables.Yield
import java.lang.InterruptedException
import java.util.ArrayDeque
import java.util.concurrent.Exchanger


private[mada] object Block {
    val CAPACITY = 20
    def apply[A](op: Yield[A] => Unit): Iterable[A] = Iterables.byName(iimpl(op))
    def iimpl[A](op: Yield[A] => Unit): Iterator[A] = new BlockIterator(op)
}

private[mada] class BlockIterator[A](op: Yield[A] => Unit) extends Iterator[A] {
    private var in = new BlockData[A]
    private val x = new Exchanger[BlockData[A]]
    private val bt = new BlockThread(op, x)

    bt.start
    doExchange

    override def hasNext = !in.buf.isEmpty
    override def next = {
        val tmp = in.buf.removeFirst
        if (in.buf.isEmpty && !in.isLast) {
            doExchange
        }
        tmp
    }

    private def doExchange: Unit = {
        Assert(in.buf.isEmpty)
        in = x.exchange(in)
    }
}

private[mada] class BlockThread[A](op: Yield[A] => Unit, x: Exchanger[BlockData[A]]) extends java.lang.Thread {
    private var out = new BlockData[A]

    private val y = new Yield[A] {
        override def apply(v: A) = {
            out.buf.addLast(v)
            if (out.buf.size == Block.CAPACITY) {
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

private[mada] class BlockData[A](val buf: ArrayDeque[A], var isLast: Boolean) {
    def this() = this(new ArrayDeque[A](Block.CAPACITY), false)
}
