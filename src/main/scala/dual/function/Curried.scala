

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package function


final case class Curried2[f <: Function2](f: f) extends Function1 {
    type self = Curried2[f]
    override  def apply[v1 <: Any](v1: v1): apply[v1] = new Apply(v1)
    override type apply[v1 <: Any] = Apply[v1]

    class Apply[v1 <: Any](v1: v1) extends Function1 {
        type self = Apply[v1]
        override  def apply[v2 <: Any](v2: v2): apply[v2] = f.apply(v1, v2)
        override type apply[v2 <: Any]                    = f#apply[v1, v2]
    }
}
