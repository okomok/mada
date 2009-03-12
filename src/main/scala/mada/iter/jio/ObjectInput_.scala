

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter.jio


import java.io.ObjectInput


private[mada] object ObjectInputToIterable {
    def apply[A](from: ObjectInput): Iterable[AnyRef] = Iterables.by(iimpl(from)) // single-pass
    def iimpl[A](from: ObjectInput): Iterator[AnyRef] = new ObjectInputIterator(from)
}


private[mada] class ObjectInputIterator(in: ObjectInput) extends Iterator[AnyRef] {
    private var cur = readNext // Note that null is a valid data.

    override def hasNext = !cur.isEmpty
    override def next = {
        val tmp = cur.get
        cur = readNext
        tmp
    }

    private def readNext: Option[AnyRef] = try {
        Some(in.readObject)
    } catch {
        case _: java.io.EOFException => None
    }
}
