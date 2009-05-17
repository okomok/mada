

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


case class FromJIterable[A](_1: java.lang.Iterable[A]) extends Sequence[A] {
    override def begin = iterator.fromJIterator(_1.iterator)
}

case class ToJIterable[A](_1: Sequence[A]) extends java.lang.Iterable[A] {
    override def iterator = sequence.iterator.toJIterator(_1.begin)
}


case class FromJObjectInput(_1: java.io.ObjectInput) extends Sequence[AnyRef] {
    override def begin = new Iterator[AnyRef] {
        private var e = ready // Note that null is a valid data.

        override def isEnd = e.isEmpty
        override def deref = { preDeref; e.get }
        override def increment = { preIncrement; e = ready }

        private def ready: Option[AnyRef] = {
            try {
                Some(_1.readObject)
            } catch {
                case _: java.io.EOFException => None
            }
        }
    }
}


case class FromJReader(_1: java.io.Reader) extends Sequence[Char] {
    override def begin = new Iterator[Char] {
        _1.reset
        private var e = ready

        override def isEnd = e.isEmpty
        override def deref = { preDeref; e.get }
        override def increment = { preIncrement; e = ready }

        private def ready: Option[Char] = {
            val c = _1.read
            if (c == -1) None else Some(c.toChar)
        }
    }
}
