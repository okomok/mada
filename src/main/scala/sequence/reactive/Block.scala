

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


import scala.util.continuations.{cpsParam, reset, shift}


object BlockContext {

    def each[A](xs: Reactive[A]): A @cpsParam[Any, Any] = shift { (k: A => Any) => xs.foreach(function.discard(k)) }

    def head[A](xs: Reactive[A]): A @cpsParam[Any, Any] = each(xs.take(1))

    def nth[A](xs: Reactive[A])(n: Int): A @cpsParam[Any, Any] = each(xs.drop(n).take(1))

    def find[A](xs: Reactive[A])(p: A => Boolean): A @cpsParam[Any, Any] = each(xs.dropWhile(function.not(p)).take(1))

    def apply[A](xs: Reactive[A]): Forloop[A] = new Forloop(xs)

    class Forloop[A](xs: Reactive[A]) {
        def foreach(g: A => Any @cpsParam[Any, Any]): Exit @cpsParam[Any, Any] = each {
            new Reactive[Exit] {
                override def forloop(cp: Exit => Unit, k: Exit => Unit) {
                    xs.onExit(q => cp(q)).forloop(x => reset{g(x);()}, k)
                }
            }
        }
    }

}
