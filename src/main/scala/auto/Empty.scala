

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package auto


private[auto]
case class Empty() extends Auto[Nothing] {
    override def foreach(f: Nothing => Unit) = ()
}
