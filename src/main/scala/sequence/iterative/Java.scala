

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterative


private
case class FromJIterable[A](_1: java.lang.Iterable[A]) extends Iterative[A] {
    override def begin = Iterator.fromJIterator(_1.iterator)
}

private
case class ToJIterable[A](_1: Iterative[A]) extends java.lang.Iterable[A] {
    override def iterator = sequence.iterator.toJIterator(_1.begin)
}


private
case class FromJObjectInput(_1: java.io.ObjectInput) extends Iterative[AnyRef] {
    override def begin = new _Iterator[AnyRef] {
        private[this] var e = ready // Note that null is a valid data.

        override protected def _isEnd = e.isEmpty
        override protected def _deref = e.get
        override protected def _increment = e = ready

        private def ready: Option[AnyRef] = {
            try {
                Some(_1.readObject)
            } catch {
                case _: java.io.EOFException => None
            }
        }
    }
}


private
case class FromJReader(_1: java.io.Reader) extends Iterative[Char] {
    override def begin = new _Iterator[Char] {
        _1.reset
        private[this] var e = ready

        override protected def _isEnd = e.isEmpty
        override protected def _deref = e.get
        override protected def _increment = e = ready

        private def ready: Option[Char] = {
            val c = _1.read
            if (c == -1) None else Some(c.toChar)
        }
    }
}
