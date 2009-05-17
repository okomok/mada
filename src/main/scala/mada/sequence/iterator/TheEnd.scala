

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterator


case object TheEnd extends Iterator[Nothing] {
    override def isEnd = true
    override def deref = throw new NoSuchElementException("deref on end iterator")
    override def increment = throw new UnsupportedOperationException("increment on end iterator")
}