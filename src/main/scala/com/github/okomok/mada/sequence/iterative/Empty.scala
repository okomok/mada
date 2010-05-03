

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package iterative


// not object for inheritance
case class Empty() extends Iterative[Nothing] {
    override def begin = iterator.end
}
