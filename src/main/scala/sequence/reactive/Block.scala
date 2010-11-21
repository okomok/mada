

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


import scala.util.continuations.{cpsParam, shift}


trait BlockContext {

    def each[A](xs: Reactive[A]): A @cpsParam[Any, Any] = shift { (k: A => Any) => xs.foreach(function.discard(k)) }

    def head[A](xs: Reactive[A]): A @cpsParam[Any, Any] = each(xs.take(1))

    def nth[A](xs: Reactive[A])(n: Int): A @cpsParam[Any, Any] = each(xs.drop(n).take(1))

    def find[A](xs: Reactive[A])(p: A => Boolean): A @cpsParam[Any, Any] = each(xs.dropWhile(function.not(p)).take(1))

    def forUntil[A](xs: Reactive[A], ys: Reactive[_])(_f: A => Unit): Unit @cpsParam[Any, Any] = {
        each { new Reactive[Unit] {
            override def forloop(f: Unit => Unit, k: Exit => Unit) {
                xs.takeUntil(ys).onExit(_ => f()).foreach(_f)
            }
        } }
    }

    def until[A](xs: Reactive[A], ys: Reactive[_]) = new {
        def foreach(f: A => Unit): Unit @cpsParam[Any, Any] = forUntil(xs, ys)(f)
    }

}

object BlockContext {
    val default: BlockContext = new BlockContext{}
}
