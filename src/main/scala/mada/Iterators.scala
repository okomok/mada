

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods operating on <code>Iterator</code>.
 */
object Iterators extends iter.Compatibles {


// algorithms

    /**
     * @return  <code>equalWith(it, jt)(Functions.equal)
     */
     def equal[A, B](it: Iterator[A], jt: Iterator[B]): Boolean = {
         equalWith(it, jt)(Functions.equal)
     }

    /**
     * Returns <code>true</code> iif elements and length are the same.
     */
    def equalWith[A, B](it: Iterator[A], jt: Iterator[B])(p: Functions.Predicate2[A, B]): Boolean = {
        while (it.hasNext && jt.hasNext) {
            if (!p(it.next, jt.next)) {
                return false
            }
        }
        !it.hasNext && !jt.hasNext
    }

    /**
     * Returns the length.
     */
    def length[A, B](it: Iterator[A]): Int = {
        var l = 0
        while (it.hasNext) {
            l += 1
            it.next
        }
        l
    }


// constructors

    /**
     * Triggers implicit conversions explicitly.
     *
     * @return  <code>to</code>.
     */
    def from[A](to: Iterator[A]): Iterator[A] = to

    /**
     * The unfolding
     */
    def unfoldRight[A, B](z: A)(op: A => Option[(B, A)]): Iterator[B] = new Iterator[B] {
        private var acc = op(z)
        override def hasNext = !acc.isEmpty
        override def next = {
            val tmp = acc.get
            acc = op(tmp._2)
            tmp._1
        }
    }

    /**
     * An infinite iterator of repeated applications of <code>op</code> to <code>z</code>.
     */
    def iterate[A](z: A)(op: A => A): Iterator[A] = new Iterator[A] {
        private var acc = z
        override def hasNext = true
        override def next = {
            val tmp = acc
            acc = op(acc)
            tmp
        }
    } // unfoldRight(z)({ x => Some(x, op(x)) }) always needs heap-allocation of Option.

    /**
     * An infinite iterator, with <code>e</code> the value of every element.
     */
    def repeat[A](e: A): Iterator[A] = iterate(e)(Functions.identity[A])


// projections

    /**
     * An infinite repetition of <code>it</code>.
     */
    def cycle[A](it: Iterator[A]): Iterator[A] = {
        val buf = new java.util.ArrayList[A]
        var firstTime = true
        val f = { (u: Unit) =>
            if (firstTime) {
                firstTime = false
                withSideEffect(it)({ e => buf.add(e) })
            } else {
                fromJclIterator(buf.iterator)
            }
        }
        repeat(()).flatMap(f)
    }

    /**
     * Iterates with side-effect <code>f</code>.
     */
    def withSideEffect[A](it: Iterator[A])(f: A => Any): Iterator[A] = new Iterator[A] {
        override def hasNext = it.hasNext
        override def next = {
            val e = it.next
            f(e)
            e
        }
    }


// aliases

    /**
     * Alias of <code>iter.IteratorProxy</code>
     */
    type IteratorProxy[A] = iter.IteratorProxy[A]

    /**
     * Alias of <code>iter.Infix</code>
     */
    val Infix = iter.Infix


// alias compatibles

    /**
     * @return  <code>this</code>.
     */
    val Compatibles: iter.Compatibles = this

  // from

    /**
     * Alias of <code>madaIteratorFromJclEnumeration</code>
     */
    def fromJclEnumeration[A](from: java.util.Enumeration[A]): Iterator[A] = madaIteratorFromJclEnumeration(from)

    /**
     * Alias of <code>madaIteratorFromJclIterator</code>
     */
    def fromJclIterator[A](from: java.util.Iterator[A]): Iterator[A] = madaIteratorFromJclIterator(from)

    /**
     * Alias of <code>madaIteratorFromObjectInput</code>
     */
    def fromObjectInput(from: java.io.ObjectInput): Iterator[AnyRef] = madaIteratorFromObjectInput(from)

  // to

    /**
     * Alias of <code>madaIteratorToJclEnumeration</code>
     */
    def toJclEnumeration[A](from: Iterator[A]): java.util.Enumeration[A] = madaIteratorToJclEnumeration(from)

    /**
     * Alias of <code>madaIteratorToJclIterator</code>
     */
    def toJclIterator[A](from: Iterator[A]): java.util.Iterator[A] = madaIteratorToJclIterator(from)


// incompatibles

  // to

    /**
     * Converts to a hash-set.
     */
    def toHashSet[A](from: Iterator[A]): scala.collection.Set[A] = {
        val to = new scala.collection.jcl.HashSet[A]
        for (e <- from) {
            to.add(e)
        }
        to
    }

    /**
     * Converts to a hash-map.
     */
    def toHashMap[K, V](from: Iterator[(K, V)]): scala.collection.Map[K, V] = {
        val to = new scala.collection.jcl.HashMap[K, V]
        for (e <- from) {
            to.put(e._1, e._2)
        }
        to
    }
}
