

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


sealed abstract class Unit extends Any {
    override  def self = this
    override type self = Unit

    override  def asInstanceOfUnit = this
    override type asInstanceOfUnit = Unit

    override  def undual = ()
    override type undual = scala.Unit
}

object _Unit {
    private[mada] val value = new Unit{}
}
