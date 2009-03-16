

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter.ptr


/**
 * Contains explicit conversions around <code>Stack</code>.
 */
trait Conversions {
    def fromIterator[A](from: Iterator[A]): Pointer[A] = new Pointer[A] {
        private val e = new Proxies.Var[A]
        if (from.hasNext) {
            e.assign(from.next)
        }

        override def isEnd: Boolean = e.isNull
        override def deref: A = e.self
        override def increment: Unit = {
            if (from.hasNext) {
                e.assign(from.next)
            } else {
                e.resign
            }

        }
    }

    def toIterator[A](from: Pointer[A]): Iterator[A] = new Iterator[A] {
        override def hasNext = !from
        override def next = {
            val tmp = ~from
            from.++
            tmp
        }
    }
}
