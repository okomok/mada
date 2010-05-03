

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package iterator


case class End() extends Iterator[Nothing] {
    override def isEnd = true
    override def deref = throw new NoSuchElementException("deref on end iterator")
    override def increment = throw new UnsupportedOperationException("increment on end iterator")
}
