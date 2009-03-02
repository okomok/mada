

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
     * Returns the length. Note that iterator is exhausted.
     */
    def length[A](it: Iterator[A]): Int = Length(it)

    /**
     * @return  <code>it.reduceLeft{ (b, a) => which(b, a) }</code>.
     */
    def best[A](it: Iterator[A], which: (A, A) => A): A = it.reduceLeft{ (b, a) => which(b, a) }


// constructors

    /**
     * Alias of <code>fromValues</code>.
     */
    def apply[A](from: A*): Iterator[A] = from.elements

    /**
     * Triggers implicit conversions explicitly.
     *
     * @return  <code>to</code>.
     */
    def from[A](to: Iterator[A]): Iterator[A] = to

    /**
     * The unfolding (op can have side-effects.)
     */
    def unfoldRight[A, B](z: A)(op: A => Option[(B, A)]): Iterator[B] = UnfoldRight(z)(op)

    /**
     * @return  <code>unfoldRight(z){ x => Some(x, op(x)) }</code>.
     */
    def iterate[A](z: A)(op: A => A): Iterator[A] = Iterate(z)(op)

    /**
     * @return  <code>iterate(e){ x => x })</code>.
     */
    def repeat[A](e: A): Iterator[A] = iterate(e){ x => x }


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
     * An infinite repetition of <code>it</code> by-name.
     */
    def cycle[A](it: => Iterator[A]): Iterator[A] = repeat(()).flatMap{ (u: Unit) => it }


// Iterable conversion

    /**
     * Converts to <code>Iterable</code>. <code>from</code> is evaluated every <code>elements</code> call.
     */
    def toIterable[A](from: => Iterator[A]): Iterable.Projection[A] = ToIterable(from)


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
