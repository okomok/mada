

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility types and methods operating on <code>Proxy</code>.
 */
object Proxies {


    /**
     * Typed proxy
     */
    trait ProxyOf[A] extends Proxy {
        override def self: A
    }


    /**
     * Supports pattern matching of <code>Mutable</code>.
     */
    object Mutable {
        def unapply[A](that: Mutable[A]): Option[A] = if (that.isEmptyProxy) None else Some(that.self)

        object Null {
            def unapply[A](that: Mutable[A]): Boolean = that.isEmptyProxy
        }
    }

    /**
     * Mutable proxy
     */
    trait Mutable[A] extends ProxyOf[A] {
        /**
         * Assigns <code>that</code> to <code>self</code>.
         */
        def :=(that: => A): Unit

        /**
         * Is <code>self</code> inaccessible?
         */
        def isEmptyProxy: Boolean

        /**
         * Alias of <code>:=</code>
         */
        final def ::=(that: => A): Unit = this := that

        /**
         * Alias of <code>:=</code>
         */
        final def <--(that: => A): Unit = this := that

        /**
         * Returns a vector of this proxy.
         */
        final def vectorOfProxy: Vector[A] = new Vector[A] {
            override def start = 0
            override def end = if (Mutable.this.isEmptyProxy) 0 else 1
            override def apply(i: Int) = Mutable.this.self
            override def update(i: Int, e: A) = Mutable.this := e
        }
    }


    /**
     * Trivial mutable proxy
     */
    class Ref[A](private var x: Option[A]) extends Mutable[A] {
        def this() = this(None)
        def this(that: A) = this(Some(that))

        override def self = x.get
        override def :=(that: => A) = x = Some(that)
        override def isEmptyProxy = x.isEmpty
    }


    /**
     * Trivial lazy mutable proxy; second time assignment is not evaluated.
     */
    class LazyRef[A] extends Mutable[A] {
        private val r = new java.util.concurrent.atomic.AtomicReference[ByName]

        override lazy val self = r.get.apply
        override def :=(that: => A) = { r.compareAndSet(null, new ByName(that)) }
        override def isEmptyProxy = r.get == null

        private class ByName(that: => A) {
            def apply: A = that
        }
    }
}
