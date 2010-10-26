

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Generate[+A](_1: Reactive[_], _2: Iterative[A], _3: (A => Unit) => Unit = function.empty1) extends Reactive[A] {
    override def close = _1.close
    override def foreach(f: A => Unit) {
        val it = _2.begin
        val k = util.ByName{close;_3(f)}
        if (!it) {
            k()
        } else {
            for (_ <- _1) {
                if (it) {
                    f(~it)
                    it.++
                    if (!it) {
                        k()
                    }
                }
            }
        }
    }

    override def then(f: => Unit): Reactive[A] = Generate[A](_1, _2, g => {_3(g);f}) // _1.onClose(f).generate(_2)
    override def then_++[B >: A](that: => Reactive[B]): Reactive[B] = Generate[B](_1, _2, g => {_3(g);that.foreach(g)})
}
