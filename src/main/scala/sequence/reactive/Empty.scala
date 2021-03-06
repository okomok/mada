

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Empty() extends Reactive[Nothing] {
    override def forloop(f: Nothing => Unit, k: Exit => Unit) = k(End)
}
