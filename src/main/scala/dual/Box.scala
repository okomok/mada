

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


/**
 * A boxed non-dual type
 */
final case class Box[A](unbox: A) extends Any {
    type self = Box[A]
    type unbox = A
    override  def undual: undual = unbox
    override type undual         = unbox
}
