

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


sealed abstract class Unit extends Any {
    type self = Unit

    override  def asUnit = self
    override type asUnit = self

    override  def naturalOrdering: naturalOrdering = _Unit.NaturalOrdering
    override type naturalOrdering                  = _Unit.NaturalOrdering

    override  def undual: undual = ()
    override type undual         = scala.Unit
    override  def canEqual(that: scala.Any) = that.isInstanceOf[Unit]
}


private[dual]
object _Unit {
    val value = new Unit{}

    val NaturalOrdering = new NaturalOrdering
    class NaturalOrdering extends ordering.AbstractOrdering {
        type self = NaturalOrdering

        override  def equiv[x <: Any, y <: Any](x: x, y: y): equiv[x, y] = `true`
        override type equiv[x <: Any, y <: Any]                          = `true`

        override  def compare[x <: Any, y <: Any](x: x, y: y): compare[x, y] = ordering.EQ
        override type compare[x <: Any, y <: Any]                            = ordering.EQ
    }
}
