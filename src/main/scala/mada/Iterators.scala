

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods operating on <code>Iterator</code>.
 */
object Iterators extends iter.Conversions with iter.Compatibles {
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
     * @return  <code>from.projection</code>
     */
    def apply[A](from: A*): Iterable.Projection[A] = from.projection

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
     * Alias of <code>Iterator.range</code>
     */
    def range(i: Int, j: Int): Iterator[Int] = Iterator.range(i, j)

    /**
     * Alias of <code>Iterator.from</code>
     */
    def range(i: Int, u: Unit): Iterator[Int] = iterate(i){ k => k + 1 }

    /**
     * An infinite iterator, with <code>e</code> the value of every element.
     */
    def repeat[A](e: A): Iterator[A] = iterate(e)(Functions.identity[A])


// projections

    /**
     * Lightweight filter
     */
    def filter[A](it: Iterator[A])(p: A => Boolean): Iterator[A] = Filter(it)(p)

    /**
     * Returns the prefix sum. (a.k.a. scanl)
     */
    def folderLeft[A, B](it: Iterator[A], z: B)(op: (B, A) => B): Iterator[B] = FolderLeft(it, z, op)

    /**
     * @return  <code>folderLeft[A, B](it, it.next)(op)</code>.
     */
    def reducerLeft[A, B >: A](it: Iterator[A])(op: (B, A) => B): Iterator[B] = folderLeft[A, B](it, it.next)(op)

    /**
     * Disables overrides.
     */
    def seal[A](it: Iterator[A]): Iterator[A] = Seal(it)

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
    def withSideEffect[A](it: Iterator[A])(f: A => Unit): Iterator[A] = WithSideEffect(it)(f)

    /**
     * An infinite repetition of <code>it</code>.
     */
    def cycle[A](it: Iterable[A]): Iterable.Projection[A] = Cycle(it)


// aliases

    /**
     * @return  <code>this</code>.
     */
    val Compatibles: iter.Compatibles = this

    /**
     * Alias of <code>iter.IteratorProxy</code>
     */
    type Forwarder[A] = iter.IteratorProxy[A]

    /**
     * Alias of <code>iter.IteratorProxy</code>
     */
    type IteratorProxy[A] = iter.IteratorProxy[A]

    /**
     * Alias of <code>iter.Infix</code>
     */
    val Infix = iter.Infix
}
