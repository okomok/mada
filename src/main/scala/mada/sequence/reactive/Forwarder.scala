

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


trait Forwarder[+A] extends Reactive[A] with util.Forwarder {
    override protected def delegate: Reactive[A]

    protected def around[B](that: => Reactive[B]): Reactive[B] = that
    protected def around2[B, C](that: => (Reactive[B], Reactive[C])): (Reactive[B], Reactive[C]) = {
        val _that = that
        (around(_that._1), around(_that._2))
    }

    // TODO:

    def subscribe(k: Reactor[A]): Unit = delegate.subscribe(k)

}
