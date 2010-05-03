

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package function


case class Identity[T]() extends Transform[T] {
    override def apply(x: T) = x
}
