

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package arm


import scala.util.continuations


private[arm]
class Common {

    @annotation.returnThat
    def from[A](that: Arm[A]): Arm[A] = that

    @annotation.equivalentTo("a.foreach(f)")
    def using[A](a: Arm[A])(f: A => Unit): Unit = a.foreach(f)

    @annotation.equivalentTo("a.toCps")
    def use[A](a: Arm[A]): A @continuations.cpsParam[Any, Unit] = a.toCps

    @annotation.equivalentTo("continuations.reset(ctx)")
    def scope[A](ctx: => A @continuations.cpsParam[A, Any]): Unit = continuations.reset(ctx)

}
