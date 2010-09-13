

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package auto


private[auto]
class Common {

    @returnThat
    def use[A](that: Auto[A]): Auto[A] = that

    @equivalentTo("a.foreach(f)")
    def using[A](a: Auto[A])(f: A => Unit): Unit = a.foreach(f)

}
