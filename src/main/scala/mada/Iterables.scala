

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods operating on <code>Iterable</code>.
 */
object Iterables extends iter.Conversions with iter.Compatibles {
    import iter._


// algorithms

    /**
     * @return  <code>equalBy(it, jt)(Functions.equal)</code>
     */
    def equal[A, B](it: Iterable[A], jt: Iterable[B]): Boolean = EqualBy(it, jt, Functions.equal)

    /**
     * @return  <code>true</code> iif elements and length are the same.
     */
    def equalBy[A, B](it: Iterable[A], jt: Iterable[B])(p: Functions.Predicate2[A, B]): Boolean = EqualBy(it, jt, p)

    /**
     * Returns the length. Note that iterator is exhausted.
     */
    def length[A](it: Iterable[A]): Int = Length(it)

    /**
     * @return  <code>it.reduceLeft{ (b, a) => which(b, a) }</code>.
     */
    def best[A](it: Iterable[A])(which: (A, A) => A): A = it.reduceLeft{ (b, a) => which(b, a) }


// constructors

    /**
     * Alias of <code>fromValues</code>
     */
    def apply[A](from: A*): Iterable[A] = from

    /**
     * @return  <code>Iterable.empty</code>.
     */
    def emptyOf[A]: Iterable[A] = Iterable.empty

    /**
     * @return  <code>from</code>.
     */
    def fromValues[A](from: A*): Iterable[A] = from

    /**
     * Constructs <code>Iterable</code> from <code>Iterator</code>.
     */
    def make[A](it: Iterator[A]): Iterable[A] = Make(it)

    /**
     * Constructs <code>Iterable</code> from <code>Iterator</code> by name.
     */
    def makeByName[A](it: => Iterator[A]): Iterable[A] = MakeByName(it)

    /**
     * Triggers implicit conversions explicitly.
     *
     * @return  <code>to</code>.
     */
    def from[A](to: Iterable[A]): Iterable[A] = to

    /**
     * Returns lazy one.
     */
    def `lazy`[A](it: => Iterable[A]): Iterable[A] = new IterableProxy[A] {
        override lazy val self = it
    }

    /**
     * The unfolding
     */
    def unfoldRight[A, B](z: A)(op: A => Option[(B, A)]): Iterable[B] = UnfoldRight(z, op)

    /**
     * @return  <code>unfoldRight(z){ x => Some(x, op(x)) }</code>.
     */
    def iterate[A](z: A)(op: A => A): Iterable[A] = Iterate(z, op)

    /**
     * @return  <code>iterate(e){ x => x }</code>.
     */
    def repeat[A](e: A): Iterable[A] = iterate(e){ x => x }


// Enumeration
/*
    /**
     * Converts <code>Iterator</code> to <code>Enumeration</code>.
     */
    def iteratorToEnumeration[A](it: Iterator[A]): java.util.Enumeration[A] = jcl.EnumerationFromIterator(it)

    /**
     * Converts <code>Enumeration</code> to <code>Iterator</code>.
     */
    def iteratorFromEnumeration[A](en: java.util.Enumeration[A]): Iterator[A] = jcl.EnumerationToIterator(en)
*/


// projections

    /**
     * Lightweight filter
     */
    def filter[A](it: Iterable[A])(p: A => Boolean): Iterable[A] = Filter(it, p)

    /**
     * Flattens an iterable of iterables.
     */
    def flatten[A](its: Iterable[Iterable[A]]): Iterable[A] = Flatten(its)

    /**
     * Returns the prefix sum. (a.k.a. scanl)
     */
    def folderLeft[A, B](it: Iterable[A], z: B)(op: (B, A) => B): Iterable[B] = FolderLeft(it, z, op)

    /**
     * @return  <code>folderLeft[A, B](it, it.next)(op)</code>.
     */
    def reducerLeft[A, B >: A](it: Iterable[A])(op: (B, A) => B): Iterable[B] = ReducerLeft(it, op)

    /**
     * Disables overrides.
     */
    def seal[A](it: Iterable[A]): Iterable[A] = Seal(it)

    /**
     * Returns <code>[e<sub>0</sub>, e<sub>n</sub>, e<sub>2n</sub>,...]</code>.
     */
    def step[A](it: Iterable[A], n: Int): Iterable[A] = Step(it, n)

    /**
     * Works around 2.7.3 deprecated warning.
     */
    def take[A](it: Iterable[A], n: Int): Iterable[A] = makeByName(it.elements.take(n))

    /**
     * Lightweight takeWhile
     */
    def takeWhile[A](it: Iterable[A])(p: A => Boolean): Iterable[A] = TakeWhile(it, p)

    /**
     * Iterates with side-effect <code>f</code>.
     */
    def withSideEffect[A](it: Iterable[A])(f: A => Unit): Iterable[A] = WithSideEffect(it, f)

    /**
     * An infinite repetition of <code>it</code>.
     */
    def cycle[A](it: Iterable[A]): Iterable[A] = repeat(()).flatMap{ (u: Unit) => it }

    /**
     * @return  <code>make(it.elements)</code>.
     */
    def singlePass[A](it: Iterable[A]): Iterable[A] = make(it.elements)


// aliases

    /**
     * @return  <code>this</code>.
     */
    val Compatibles: iter.Compatibles = this

    /**
     * Alias of <code>IterableProxy</code>
     */
    type Forwarder[A] = IterableProxy[A]

    /**
     * Alias of <code>iter.IteratorProxy</code>
     */
    type IteratorForwarder[A] = iter.IteratorProxy[A]

    /**
     * Alias of <code>iter.IteratorProxy</code>
     */
    type IteratorProxy[A] = iter.IteratorProxy[A]

    /**
     * Alias of <code>iter.Infix</code>
     */
    val Infix = iter.Infix
}
