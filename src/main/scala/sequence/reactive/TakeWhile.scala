

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class TakeWhile[A, B >: A](_1: Reactive[A], _2: A => Boolean, _3: (B => Unit) => Unit = function.empty1) extends Reactive[B] {
    override def close = _1.close
    override def foreach(f: B => Unit): Unit = {
        val k = util.ByLazy{close; _3(f)}
        for (x <- _1) {
            if (_2(x)) {
                f(x)
            } else {
                k()
            }
        }
    }

    override def then(f: => Unit): Reactive[B] = TakeWhile[A, B](_1, _2, g => {_3(g);f}) // _1.onClose(f).takeWhile(_2)
    override def then_++[C >: B](that: Reactive[C]): Reactive[C] = TakeWhile[A, C](_1, _2, g => {_3(g);that.foreach(g)})
}


/*
private
case class TakeWhile[A](_1: Reactive[A], _2: A => Boolean, _3: (A => Unit) => Unit = function.empty1) extends Reactive[A] {
    override def close = _1.close
    override def foreach(f: A => Unit): Unit = {
        val k = util.ByLazy{close; _3(f)}
        for (x <- _1) {
            if (_2(x)) {
                f(x)
            } else {
                k()
            }
        }
    }

    override def then(f: => Unit): Reactive[A] = TakeWhile[A](_1, _2, _ => f) // _1.onClose(f).takeWhile(_2)
    override def then_++[B >: A](that: Reactive[B]): Reactive[B] = TakeWhileThenAppend(_1, _2, that) // TakeWhile[B](_1, _2, f => that.foreach(f))
}

private
case class TakeWhileThenAppend[A, B >: A](_1: Reactive[A], _2: A => Boolean, _3: Reactive[B]) extends Reactive[B] {
    override def close = _1.close
    override def foreach(f: B => Unit): Unit = {
        val k = util.ByLazy{close; _3.foreach(f)}
        for (x <- _1) {
            if (_2(x)) {
                f(x)
            } else {
                k()
            }
        }
    }
}
*/
