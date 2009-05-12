

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case object Empty extends Traversable[Nothing] {
    override def start = traverser.theEnd
}
