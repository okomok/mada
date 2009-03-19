

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


private[mada] object Block {
    private val DEFAULT_CAPACITY = 8
    def apply[A](op: Function1[A, Unit] => Unit): Iterable[A] = apply(op, DEFAULT_CAPACITY)
    def apply[A](op: Function1[A, Unit] => Unit, n: Int): Iterable[A] = Iterables.byName(iimpl(op, n))
    def iimpl[A](op: Function1[A, Unit] => Unit, n: Int): Iterator[A] = new BlockIterator(op,n )
}

private[mada] class BlockIterator[A](op: Function1[A, Unit] => Unit, capacity: Int) extends Iterator[A] {
    if (capacity <= 0) {
        throw new IllegalArgumentException("block buffer size: " + capacity)
    }

    import java.util.ArrayDeque

    private class Data(val buf: ArrayDeque[A], var isLast: Boolean) {
        def this() = this(new ArrayDeque[A](capacity), false)
    }

    private var in = new Data
    private val x = new java.util.concurrent.Exchanger[Data]

    new BlockThread().start
    in = x.exchange(in)

    override def hasNext = !in.buf.isEmpty
    override def next = {
        val tmp = in.buf.removeFirst
        if (in.buf.isEmpty && !in.isLast) {
            in = x.exchange(in)
        }
        tmp
    }

    private class BlockThread extends java.lang.Thread {
        private var out = new Data

        private val y = new Function1[A, Unit] {
            override def apply(v: A) = {
                out.buf.addLast(v)
                if (out.buf.size == capacity) {
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
}
