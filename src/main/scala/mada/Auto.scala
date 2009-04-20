

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package auto {

    /**
     * Contains eligible objects for <code>Auto</code>.
     */
    trait Eligibles { this: Auto.type =>
        import java.io.Closeable
        import java.util.concurrent.locks.Lock

        implicit object ofInterface extends Auto[Auto.Interface] {
            override def begin(e: Auto.Interface) = e.begin
            override def end(e: Auto.Interface) = e.end
        }

        implicit object ofCloseable extends Auto[Closeable] {
            override def end(e: Closeable) = e.close
        }

        implicit object ofLock extends Auto[Lock] {
            override def begin(e: Lock) = e.lock
            override def end(e: Lock) = e.unlock
        }
    }

} // package auto


/**
 * Contains utility methods operating on <code>Auto</code>.
 */
object Auto extends auto.Eligibles {
    /**
     * Alias of <code>using</code>
     */
    def apply[A, B](e: A)(f: A => B)(implicit a: Auto[A]): B = using(e)(f)(a)

    /**
     * @return  <code>a.begin(e); try { f(e) } finally { a.end(e) }</code>.
     */
    def using[A, B](e: A)(f: A => B)(implicit a: Auto[A]): B = {
        a.begin(e)
        try {
            f(e)
        } finally {
            a.end(e)
        }
    }

    /**
     * Can be used instead of implicit objects.
     */
    trait Interface {
        def begin: Unit = ()
        def end: Unit = ()
    }

    /**
     * Alias of <code>Auto</code>
     */
    type Type[-A] = Auto[A]
}


/**
 * Trait for automatically called methods
 */
trait Auto[-A] {
    /**
     * Called when block begins.
     */
    def begin(e: A): Unit = ()

    /**
     * Called when block ends.
     */
    def end(e: A): Unit = ()

    /**
     * Alias of <code>Auto</code>
     */
    final def companion = Auto
}
