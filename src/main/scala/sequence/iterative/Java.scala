

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package iterative


private[mada] case class FromJIterable[A](_1: java.lang.Iterable[A]) extends Iterative[A] {
    override def begin = Iterator.fromJIterator(_1.iterator)
}

private[mada] case class ToJIterable[A](_1: Iterative[A]) extends java.lang.Iterable[A] {
    override def iterator = sequence.iterator.toJIterator(_1.begin)
}


private[mada] case class FromJObjectInput(_1: java.io.ObjectInput) extends Iterative[AnyRef] {
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


private[mada] case class FromJReader(_1: java.io.Reader) extends Iterative[Char] {
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
