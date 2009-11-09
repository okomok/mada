

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


trait Forwarder[+A] extends Reactive[A] with SequenceForwarder[A] {
    override protected def delegate: Reactive[A]

    protected def around[B](that: => Reactive[B]): Reactive[B] = that
    protected def around2[B, C](that: => (Reactive[B], Reactive[C])): (Reactive[B], Reactive[C]) = {
        val (l, r) = that
        (around(l), around(r))
    }


    def subscribe(k: Reactor[A]): Unit = delegate.subscribe(k)
    // TODO...

}
