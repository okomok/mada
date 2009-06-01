

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.list


case class Nil() extends List[Nothing] {
    override def isEmpty = true
    override def head = throw new NoSuchElementException("head on nil")
    override def tail =  throw new UnsupportedOperationException("tail on nil")
}
