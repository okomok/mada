

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat


private[mada] trait AbstractNat extends Nat {
    final override  def !==[that <: Nat](that: that): !==[that] = ===(that).not
    final override type !==[that <: Nat]                        = ===[that]#not

    final override  def /[that <: Nat](that: that): /[that] = divMod(that)._1.asInstanceOfNat
    final override type /[that <: Nat]                      = divMod[that]#_1#asInstanceOfNat

    final override  def %[that <: Nat](that: that): %[that] = divMod(that)._2.asInstanceOfNat
    final override type %[that <: Nat]                      = divMod[that]#_2#asInstanceOfNat

    final override  def >[that <: Nat](that: that): >[that] = <=(that).not //that < self
    final override type >[that <: Nat]                      = <=[that]#not //that# <[self]

    final override  def >=[that <: Nat](that: that): >=[that] = <(that).not //that <= self
    final override type >=[that <: Nat]                       = <[that]#not //that# <=[self]
}
