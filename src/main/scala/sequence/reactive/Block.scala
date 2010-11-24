

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


import scala.util.continuations.{cpsParam, reset, shift}


object BlockEnv {

    def each[A](xs: Reactive[A]): A @cpsParam[Any, Unit] = xs.toCps

    def head[A](xs: Reactive[A]): A @cpsParam[Any, Unit] = xs.take(1).toCps

    def nth[A](xs: Reactive[A])(n: Int): A @cpsParam[Any, Unit] = xs.drop(n).take(1).toCps

    def find[A](xs: Reactive[A])(p: A => Boolean): A @cpsParam[Any, Unit] = xs.dropWhile(function.not(p)).take(1).toCps

    def apply[A](xs: Reactive[A]): Forloop[A] = new Forloop(xs)

    sealed class Forloop[A](xs: Reactive[A]) {
        def foreach(g: A => Any @cpsParam[Unit, Unit]): Exit @cpsParam[Any, Unit] = new Reactive[Exit] {
            override def forloop(cp: Exit => Unit, k: Exit => Unit) {
                xs.onExit(q => cp(q)).forloop(x => reset{g(x);()}, k)
            }
        } toCps
    }

}
