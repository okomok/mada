

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traverser


case object TheEnd extends Traverser[Nothing] {
    override def isEnd = true
    override def deref = throw new NoSuchElementException("deref on end traverser")
    override def increment = throw new UnsupportedOperationException("increment on end traverser")
}
