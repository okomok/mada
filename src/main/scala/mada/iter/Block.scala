

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


private[mada] object Block {
    def apply[A](op: Function1[A, Unit] => Unit): Iterable[A] = Iterables.byName(iimpl(op))
    def iimpl[A](op: Function1[A, Unit] => Unit): Iterator[A] = new BlockIterator(op)
}

private[mada] class BlockIterator[A](op: Function1[A, Unit] => Unit) extends Iterator[A] {
    private var e: Option[A] = None
    private val x = new java.util.concurrent.Exchanger[Option[A]]
    private val bt = new BlockThread

    bt.start
    doYield

    override def hasNext = !e.isEmpty
    override def next = {
        val tmp = e.get
        doYield
        tmp
    }

    private def doYield: Unit = {
        e = x.exchange(None)
    }

    private class BlockThread extends java.lang.Thread {
        private val y = new Function1[A, Unit] {
            override def apply(v: A) = x.exchange(Some(v))
        }

        override def run() = {
            op(y)
            x.exchange(None)
        }
    }
}
