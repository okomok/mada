

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility types and methods operating on <code>Proxy</code>.
 */
object Proxies {

    /**
     * Mutable proxy
     */
    trait Mutable[A] extends Proxy {
        override def self: A

        /**
         * Mutates <code>self</code>.
         */
        def :=(that: A): Unit
    }


    /**
     * Supports pattern matching of <code>Ref</code>.
     */
    object Ref {
        def apply[A](that: A): Ref[A] = new Ref[A](that)
        def unapply[A](that: Ref[A]): Option[A] = Some(that.self)
    }

    /**
     * A trivial <code>Mutable</code> proxy
     */
    class Ref[A](private var r: A) extends Mutable[A] {
        override def self = r
        override def :=(that: A) = r = that
    }


    /**
     * Supports pattern matching of <code>LazyRef</code>.
     */
    object LazyRef {
        def unapply[A](that: LazyRef[A]): Option[A] = Some(that.self)
    }

    /**
     * A lazy <code>Mutable</code> proxy; second time assignment is ignored.
     */
    class LazyRef[A] extends Mutable[A] {
        private var r = new java.util.concurrent.atomic.AtomicReference[A]
        override def self = r.get
        override def :=(that: A) = r.compareAndSet(null.asInstanceOf[A], that)
    }
}
