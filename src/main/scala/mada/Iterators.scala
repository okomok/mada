

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods operating on <code>Iterator</code>.
 */
object Iterators extends iter.Compatibles {
    import iter._


// algorithms

    /**
     * @return  <code>equalWith(it, jt)(Functions.equal)</code>
     */
     def equal[A, B](it: Iterator[A], jt: Iterator[B]): Boolean = equalWith(it, jt)(Functions.equal)

    /**
     * Returns <code>true</code> iif elements and length are the same.
     */
    def equalWith[A, B](it: Iterator[A], jt: Iterator[B])(p: Functions.Predicate2[A, B]): Boolean = EqualWith(it, jt)(p)

    /**
     * Returns the length. Note <code>it</code> is exhausted.
     */
    def length[A](it: Iterator[A]): Int = Length(it)

    /**
     * @return  <code>!it.hasNext</code>.
     */
    def isEmpty[A](it: Iterator[A]): Boolean = !it.hasNext


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
    def unfoldRight[A, B](z: A)(op: A => Option[(B, A)]): Iterator[B] = UnfoldRight(z)(op)

    /**
     * An infinite iterator of repeated applications of <code>op</code> to <code>z</code>.
     */
    def iterate[A](z: A)(op: A => A): Iterator[A] = Iterate(z)(op)

    /**
     * An infinite iterator, with <code>e</code> the value of every element.
     */
    def repeat[A](e: A): Iterator[A] = iterate(e)(Functions.identity[A])


// projections

    /**
     * An infinite repetition of <code>it</code>.
     */
    def cycle[A](it: Iterator[A]): Iterator[A] = Cycle(it)

    /**
     * Returns <code>[e<sub>0</sub>, e<sub>n</sub>, e<sub>2n</sub>,...]</code>.
     */
    def step[A](it: Iterator[A], n: Int): Iterator[A] = Step(it, n)

    /**
     * Iterates with side-effect <code>f</code>.
     */
    def withSideEffect[A](it: Iterator[A])(f: A => Any): Iterator[A] = WithSideEffect(it)(f)


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
    def toHashSet[A](from: Iterator[A]): scala.collection.Set[A] = ToHashSet(from)

    /**
     * Converts to a hash-map.
     */
    def toHashMap[K, V](from: Iterator[(K, V)]): scala.collection.Map[K, V] = ToHashMap(from)
}
