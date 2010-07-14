

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


final case class Box[A](unbox: A) extends Any {
    override  val self = this
    override type self = Box[A]

    override  def asInstanceOfBox = self
    override type asInstanceOfBox = self

    type unbox = A

    override  def undual: undual = unbox
    override type undual = unbox
}
