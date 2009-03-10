

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter.jcl


private[mada] object ObjectInputToIterable {
    def apply[A](from: java.io.ObjectInput): Iterable[AnyRef] = Iterables.make(iimpl(from)) // single-pass

    def iimpl[A](in: java.io.ObjectInput): Iterator[AnyRef] = new Iterator[AnyRef] {
        private var cur: Option[AnyRef] = readNext // Note that null is a valid data.

        override def hasNext = !cur.isEmpty
        override def next = {
            val tmp = cur.get
            cur = readNext
            tmp
        }

        private def readNext = try {
            Some(in.readObject)
        } catch {
            case _: java.io.EOFException => None
        }
    }
}
