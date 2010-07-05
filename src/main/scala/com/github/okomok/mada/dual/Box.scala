

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


final case class Box[A](e: A) extends Any {
     def unbox: unbox = e
    type unbox = A
}
