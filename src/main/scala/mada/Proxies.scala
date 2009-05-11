

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
     * Supports pattern matching of null proxy.
     * Also this can be used to set proxy to null.
     */
    object Null {
        /**
         * @return  <code>that.isNull</code>.
         */
        def unapply[A](that: Mutable[A]): Boolean = that.isNull
    }


    /**
     * Supports pattern matching of <code>Mutable</code>.
     */
    object Mutable {
        /**
         * @return  <code>if (that.isNull) None else Some(that.self)</code>.
         */
        def unapply[A](that: Mutable[A]): Option[A] = if (that.isNull) None else Some(that.self)
    }

    /**
     * Mutable proxy
     */
    trait Mutable[A] extends ProxyOf[A] { ^ =>
        /**
         * Assigns <code>that</code> to <code>self</code>.
         */
        def assign(that: => A): Unit

        /**
         * Makes <code>self</code> null.
         */
        def resign: Unit

        /**
         * Is <code>self</code> null?
         */
        def isNull: Boolean

        /**
         * Swaps <code>self</code> and <code>that.self</code>.
         */
        final def proxySwap(that: Mutable[A]): Unit = {
            val tmp = self
            this.assign(that.self)
            that.assign(tmp)
        }

        /**
         * Returns a vector of this proxy.
         */
        final def proxyToVector: Vector[A] = new Vector[A] {
            override def start = 0
            override def end = if (^.isNull) 0 else 1
            override def apply(i: Int) = ^.self
            override def update(i: Int, e: A) = ^.assign(e)
        }
    }


    /**
     * Mixins alias methods.
     */
    trait MutableAliasMethods[A] { this: Mutable[A] =>
        /**
         * Alias of <code>assign</code>
         */
        final def :=(that: => A): Unit = assign(that)

        /**
         * Alias of <code>resign</code>
         */
        final def :=(that: Null.type): Unit = resign

        /**
         * Alias of <code>isNull</code>
         */
        final def unary_! : Boolean = isNull
    }


    /**
     * Trivial mutable proxy (lightweight <code>Option</code>)
     */
    class Var[A](@volatile private var x: A) extends Mutable[A] with MutableAliasMethods[A] {
        /**
         * Constructs null proxy.
         */
        def this() = this(null.asInstanceOf[A])

        override def self = x
        override def assign(that: => A) = x = that
        override def resign = x = null.asInstanceOf[A]
        override def isNull = null == x

        /**
         * Alias of <code>:=</code> (not by-name parameter to be efficient.)
         */
        final def set(that: A): Unit = x = that

        /**
         * Returns a shallow copy. (The <code>self</code> is not copied.)
         */
        override def clone: Var[A] = new Var(x)
    }


    /**
     * Trivial lazy mutable proxy; second time assignment is not evaluated.
     */
    class LazyVar[A] extends Mutable[A] with MutableAliasMethods[A] {
        private val r = new java.util.concurrent.atomic.AtomicReference[Function0[A]]

        override def self = if (!isNull) r.get.apply() else null.asInstanceOf[A]
        override def assign(that: => A) = r.compareAndSet(null, function.byLazy(that))
        override def resign = r.set(null)
        override def isNull = null == r.get

        /**
         * Returns a shallow copy. (The <code>self</code> is not copied.)
         */
        override def clone: LazyVar[A] = {
            val that = new LazyVar[A]
            if (!isNull) {
                that := self
            }
            that
        }
    }
}
