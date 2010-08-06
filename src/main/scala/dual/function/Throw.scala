

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package function


private[dual]
final class Throw0(what: Throwable) extends Function0 {
    type self = Throw0
    override  def apply: apply = throw what
    override type apply = Nothing
}
