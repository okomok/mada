

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


// not object for inheritance
case class Empty() extends Sequence[Nothing] {
    override def begin = iterator.theEnd
}
