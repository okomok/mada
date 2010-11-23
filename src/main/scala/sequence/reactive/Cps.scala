

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


import scala.util.continuations


private
object ToCps {
    def apply[A](from: Reactive[A]): A @continuations.cpsParam[Any, Unit] = {
        continuations.shift {
            (cont: A => Any) => from.foreach(function.discard(cont))
        }
    }
}

private
class FromCps[A](from: => A @continuations.suspendable) extends Reactive[A] {
    override def forloop(f: A => Unit, k: Exit => Unit) {
        continuations.reset {
            f(from)
        }
    }
}
