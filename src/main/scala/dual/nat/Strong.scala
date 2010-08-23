

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat


abstract class Strong[n <: Nat](final override protected val delegate: n) extends Forwarder {
    final override protected type delegate = n

    final override  def asNat: asNat = self
    final override type asNat        = self

    final override  def asNatDense: asNatDense = delegate.asNatDense
    final override type asNatDense             = delegate#asNatDense

    final override  def asNatPeano: asNatPeano = delegate.asNatPeano
    final override type asNatPeano             = delegate#asNatPeano
}
