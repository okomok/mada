

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package list


@annotation.compatibles
trait Compatibles {
    implicit def fromOption[A](from: Option[A]): List[A] = if (from.isEmpty) empty else single(from.get)
}
