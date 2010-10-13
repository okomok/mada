

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package arm


private[arm]
class Common {

    @returnThat
    def use[A](that: Arm[A]): Arm[A] = that

    @equivalentTo("a.foreach(f)")
    def using[A](a: Arm[A])(f: A => Unit): Unit = a.foreach(f)

}
