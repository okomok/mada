

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package detail


private[dual]
object Assert {
      def apply[c <: Boolean](c: c): apply[c] = `if`(c, const0(Unit), Else(c)).apply
     type apply[c <: Boolean]                 = `if`[c, const0[Unit], Else[c]]#apply

     case class Else[c <: Boolean](c: c) extends Function0 {
         type self = Else[c]
         override  def apply: apply = throw new AssertionError("dual.assert")
         override type apply        = Nothing // Assert.apply[c]
     }
}
