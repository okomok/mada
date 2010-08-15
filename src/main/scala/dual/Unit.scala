

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


sealed abstract class Unit extends Any {
    type self = Unit

    final override  def asUnit = self
    final override type asUnit = self

    override  def undual: undual = ()
    override type undual = scala.Unit
    override def canEqual(that: scala.Any) = that.isInstanceOf[Unit]
}


private[dual]
object _Unit {
    val value = new Unit{}
}
