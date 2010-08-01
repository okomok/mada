

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package function


final case class Tupled2[f <: Function2](f: f) extends Function1 {
    type self = Tupled2[f]
    override  def apply[v1 <: Any](v1: v1): apply[v1] = pass(v1.asInstanceOfProduct2)
    override type apply[v1 <: Any]                    = pass[v1#asInstanceOfProduct2]

    private  def pass[p <: Product2](p: p): pass[p] = f.apply(p._1, p._2)
    private type pass[p <: Product2]                = f#apply[p#_1, p#_2]
}
