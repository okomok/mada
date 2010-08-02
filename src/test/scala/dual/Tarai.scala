

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest


import com.github.okomok.mada
import mada.dual._


object Tarai {

     def tarai[x <: Nat, y <: Nat, z <: Nat](x: x, y: y, z: z): tarai[x, y, z] =
        `if`(x.isZero  || y.isZero   || z.isZero   ||  x  <= y,   Const0(y), TaraiElse(x, y, z)).apply.asInstanceOfNat.asInstanceOf[tarai[x, y, z]]
    type tarai[x <: Nat, y <: Nat, z <: Nat] =
        `if`[x#isZero# ||[y#isZero]# ||[z#isZero]# || [x# <=[y]], Const0[y], TaraiElse[x, y, z]]#apply#asInstanceOfNat

    case class TaraiElse[x <: Nat, y <: Nat, z <: Nat](x: x, y: y, z: z) extends Function0 {
        type self = TaraiElse[x, y, z]
        override  def apply: apply = tarai(tarai(x.decrement, y, z), tarai(y.decrement, z, x), tarai(z.decrement, x, y))
        override type apply        = tarai[tarai[x#decrement, y, z], tarai[y#decrement, z, x], tarai[z#decrement, x, y]]
    }

     def untarai[x <: Nat, y <: Nat, z <: Nat](x: x, y: y, z: z): untarai[x, y, z] =
        `if`(x <=  y,  Const0(y), `if`((x >  y)  && (y  <= z),  Const0(z), Const0(x))).apply.asInstanceOfNat.asInstanceOf[untarai[x, y, z]]
    type untarai[x <: Nat, y <: Nat, z <: Nat] =
        `if`[x# <=[y], Const0[y], `if`[ x# >[y]# && [y# <=[z]], Const0[z], Const0[x]]]#apply#asInstanceOfNat

     def runtarai(x: Int, y: Int, z: Int): Int = if (x * y * z == 0 || x <= y) y else runtarai(runtarai(x-1, y, z), runtarai(y-1, z, x), runtarai(z-1, x, y))

}


