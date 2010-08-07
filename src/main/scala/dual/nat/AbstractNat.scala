

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat


private[dual]
trait AbstractNat extends Nat {
    final override  def nequal[that <: Nat](that: that): nequal[that] = equal(that).not
    final override type nequal[that <: Nat]                           = equal[that]#not

    final override  def div[that <: Nat](that: that): div[that] = divMod(that)._1.asInstanceOfNat
    final override type div[that <: Nat]                        = divMod[that]#_1#asInstanceOfNat

    final override  def mod[that <: Nat](that: that): mod[that] = divMod(that)._2.asInstanceOfNat
    final override type mod[that <: Nat]                        = divMod[that]#_2#asInstanceOfNat

    final override  def gt[that <: Nat](that: that): gt[that] = that.lt(self)
    final override type gt[that <: Nat]                       = that#lt[self]

    final override  def gteq[that <: Nat](that: that): gteq[that] = that.lteq(self)
    final override type gteq[that <: Nat]                         = that#lteq[self]
}
