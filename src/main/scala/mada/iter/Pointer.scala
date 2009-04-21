

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


/**
 * Contains utility methods operating on <code>Pointer</code>.
 */
object Pointer extends PointerConversions with PointerCompatibles {
    /**
     * The end pointer
     */
    val end: Pointer[Nothing] = new Pointer[Nothing] {
        override def isEnd = true
        override def deref = throw new NoSuchElementException("deref on the end pointer")
        override def increment = throw new UnsupportedOperationException("increment on the end pointer")
    }

    /**
     * @return  <code>end</code>.
     */
    def endOf[A]: Pointer[A] = end

    /**
     * Triggers implicit conversions explicitly.
     *
     * @return  <code>to</code>.
     */
    def from[A](to: Pointer[A]): Pointer[A] = to

    /**
     * @return  <code>this</code>.
     */
    val Compatibles: PointerCompatibles = this

    /**
     * Alias of <code>Pointer</code>
     */
    type Type[+A] = Pointer[A]
}


/**
 * Equivalent to <code>Iterator</code>, but useful if <code>Iterator</code>
 * is cumbersome for implementing complicated algorithms.
 *
 * @see     scalax.BufferedIterator
 */
trait Pointer[+A] {
    /**
     * Alias of <code>isEnd</code>
     */
    final def unary_! : Boolean = isEnd

    /**
     * Alias of <code>deref</code>
     */
    final def unary_~ : A = deref

    /**
     * Alias of <code>increment</code>
     */
    final def ++ : Unit = increment

    /**
     * Is pointer pass-the-end?
     */
    def isEnd: Boolean

    /**
     * Dereferences pointer.
     */
    def deref: A

    /**
     * Increments pointer.
     */
    def increment: Unit

    /**
     * Alias of <code>Pointer</code>
     */
    final def companion = Pointer
}


/**
 * Contains explicit conversions around <code>Pointer</code>.
 */
trait PointerConversions { this: Pointer.type =>
    def fromIterator[A](from: Iterator[A]): Pointer[A] = new Pointer[A] {
        private val e = new Proxies.Var[A]
        if (from.hasNext) {
            e.assign(from.next)
        }

        override def isEnd = e.isNull
        override def deref = e.self
        override def increment = {
            if (from.hasNext) {
                e.assign(from.next)
            } else {
                e.resign
            }

        }
    }

    def toIterator[A](from: Pointer[A]): Iterator[A] = new Iterator[A] {
        override def hasNext = !from.isEnd
        override def next = {
            val tmp = ~from
            from.++
            tmp
        }
    }
}


/**
 * Contains implicit conversions around <code>Pointer</code>.
 */
trait PointerCompatibles { this: Pointer.type =>
    implicit def madaPointerFromIterator[A](from: Iterator[A]): Pointer[A] = fromIterator(from)
    implicit def madaPointerToIterator[A](from: Pointer[A]): Iterator[A] = toIterator(from)
}
