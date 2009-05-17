

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


// not object for inheritance
case class Empty() extends Traversable[Nothing] {
    override def begin = traverser.theEnd
}
