

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private[reactive]
case class Empty() extends Reactive[Nothing] {
    override def foreach(f: Nothing => Unit) = ()
}
