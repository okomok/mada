

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class FromJioObjectInput[A, B](_1: java.io.ObjectInput) extends Traversable[AnyRef] {
    override def start = new Traverser[AnyRef] {
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


case class FromJioReader[A, B](_1: java.io.Reader) extends Traversable[Char] {
    override def start = new Traverser[Char] {
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
