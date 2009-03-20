

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


import Iterables.Yield
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

    new BlockThread(op, x).start
    in = x.exchange(in)

    override def hasNext = !in.buf.isEmpty
    override def next = {
        val tmp = in.buf.removeFirst
        if (in.buf.isEmpty && !in.isLast) {
            in = x.exchange(in)
        }
        tmp
    }
}

private[mada] class BlockThread[A](op: Yield[A] => Unit, x: Exchanger[BlockData[A]]) extends java.lang.Thread {
    private var out = new BlockData[A]

    private val y = new Yield[A] {
        override def apply(v: A) = {
            out.buf.addLast(v)
            if (out.buf.size == Block.CAPACITY) {
                out = x.exchange(out)
                Assert(out.buf.isEmpty)
            }
        }
    }

    override def run() = {
        op(y)
        out.isLast = true
        out = x.exchange(out)
        Assert(out.buf.isEmpty)
    }
}

private[mada] class BlockData[A](val buf: ArrayDeque[A], var isLast: Boolean) {
    def this() = this(new ArrayDeque[A](Block.CAPACITY), false)
}
