

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
    def equal[A, B](it: Iterator[A], jt: Iterator[B]): Boolean = EqualWith(it, jt)(Functions.equal)

    /**
     * @return  <code>true</code> iif elements and length are the same.
     */
    def equalWith[A, B](it: Iterator[A], jt: Iterator[B])(p: Functions.Predicate2[A, B]): Boolean = EqualWith(it, jt)(p)

    /**
     * @return  <code>!it.hasNext</code>.
     */
    def isEmpty[A](it: Iterator[A]): Boolean = !it.hasNext

    /**
     * Returns the length. Note iterator is exhausted.
     */
    def length[A](it: Iterator[A]): Int = Length(it)

    /**
     * @return  <code>it.reduceLeft{ (b, a) => which(b, a) }</code>.
     */
    def best[A](it: Iterator[A], which: (A, A) => A): A = it.reduceLeft{ (b, a) => which(b, a) }


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
     * Disables overrides.
     */
    def cut[A](it: Iterator[A]): Iterator[A] = Cut(it)

    /**
     * Lightweight filter
     */
    def filter[A](it: Iterator[A])(p: A => Boolean): Iterator[A] = Filter(it)(p)

    /**
     * Returns <code>[e<sub>0</sub>, e<sub>n</sub>, e<sub>2n</sub>,...]</code>.
     */
    def step[A](it: Iterator[A], n: Int): Iterator[A] = Step(it, n)

    /**
     * Lightweight takeWhile
     */
    def takeWhile[A](it: Iterator[A])(p: A => Boolean): Iterator[A] = TakeWhile(it)(p)

    /**
     * Iterates with side-effect <code>f</code>.
     */
    def withSideEffect[A](it: Iterator[A])(f: A => Any): Iterator[A] = WithSideEffect(it)(f)

    /**
     * An infinite repetition of <code>it</code>.
     */
    def cycle[A](it: Iterable[A]): Iterator[A] = Cycle(it)


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
     * Alias of <code>madaIteratorFromIterable</code>
     */
    def fromIterable[A](from: Iterable[A]): Iterator[A] = madaIteratorFromIterable(from)

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
     * Converts to Iterable. <code>from</code> is evaluated every <code>Iterable#elements</code> call.
     */
    def toIterable[A](from: => Iterator[A]): Iterable.Projection[A] = ToIterable(from)

    /**
     * Converts to string.
     */
    def stringize[A](it: Iterator[Char]): String = Stringize(it)

    /**
     * Converts to hash-set.
     */
    def toHashSet[A](from: Iterator[A]): scala.collection.Set[A] = ToHashSet(from)

    /**
     * Converts to hash-map.
     */
    def toHashMap[K, V](from: Iterator[(K, V)]): scala.collection.Map[K, V] = ToHashMap(from)
}
