

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter.jio


import java.io.Reader


private[mada] object ReaderToIterable {
    def apply[A](from: Reader): Iterable[Char] = new ReaderIterable(from)
}


private[mada] class ReaderIterable(in: Reader) extends Iterable/*.Projection*/[Char] {
    override def elements = {
        in.reset
        new ReaderIterator(in)
    }
}

private[mada] class ReaderIterator(in: Reader) extends Iterator[Char] {
    private var cur = readNext

    override def hasNext = !cur.isEmpty
    override def next = {
        val tmp = cur.get
        cur = readNext
        tmp
    }

    private def readNext: Option[Char] = {
        val c = in.read
        if (c == -1) None else Some(c.toChar)
    }
}
