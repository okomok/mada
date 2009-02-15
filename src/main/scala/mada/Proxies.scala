

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility types and methods operating on <code>Proxy</code>.
 */
object Proxies {


    /**
     * Typed proxy just changing return type of <code>self</code>.
     */
    trait ProxyOf[A] extends Proxy {
        override def self: A
    }


    /**
     * Supports pattern matching of <code>Mutable</code>.
     */
    object Mutable {
        /**
         * @return  <code>if (that.isEmptyProxy) None else Some(that.self)</code>.
         */
        def unapply[A](that: Mutable[A]): Option[A] = if (that.isEmptyProxy) None else Some(that.self)

        object Empty {
            /**
             * @return  <code>that.isEmptyProxy</code>.
             */
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
         * Swaps <code>self</code> and <code>that.self</code>.
         */
        final def swapProxy(that: Mutable[A]): Unit = {
            val tmp = self
            this := that.self
            that := tmp
        }

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
    class Var[A] private (private var x: Option[A]) extends Mutable[A] {
        def this() = this(None)
        def this(that: A) = this(Some(that))

        override def self = x.get
        override def :=(that: => A) = x = Some(that)
        override def isEmptyProxy = x.isEmpty

        /**
         * Returns a shallow copy. (The <code>self</code> is not copied.)
         */
        override def clone: Var[A] = new Var(x)
    }


    /**
     * Trivial lazy mutable proxy; second time assignment is not evaluated.
     */
    class LazyVar[A] extends Mutable[A] {
        private val r = new java.util.concurrent.atomic.AtomicReference[Function0[A]]

        override lazy val self = r.get.apply
        override def :=(that: => A) = { r.compareAndSet(null, Functions.byName(that)) }
        override def isEmptyProxy = null == r.get

        /**
         * Returns a shallow copy. (The <code>self</code> is not copied.)
         */
        override def clone: LazyVar[A] = {
            val v = new LazyVar[A]
            if (!isEmptyProxy) {
                v := self
            }
            v
        }
    }
}
