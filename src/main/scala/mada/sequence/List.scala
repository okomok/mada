

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence



// broken.



import list._


/**
 * Yet another Stream:
 * <ul>
 * <li/>Backtrackable to any subsequence, while <code>Iterative</code> is backtrackable only to "begin".
 * <li/>No intermediate objects, while number of iterator objects may be exponential growth in recursive <code>Iterative</code>.
 * <li/>A projection method usually need an entire copy. It can be lazy, though.
 * </ul>
 */
sealed trait List[+A] extends Sequence[A] {


    override def asList: List[A] = this


// kernel

    /**
     * Is <code>this</code> nil?
     */
    def isNil: Boolean

    /**
     * The first element
     */
    def head: A

    /**
     * The remaining elements after the first one.
     */
    def tail: List[A]


// iterative

    /**
     * Returns true if and only if both sequences have the same size,
     * and all corresponding pairs of elements in the two sequences
     * satisfy the predicate <code>p</code>.
     */
    def equalsIf[B](that: List[B])(p: (A, B) => Boolean): Boolean = {
        var it = this
        var jt = that
        while (!it.isNil && !jt.isNil) {
            if (!p(it.head, jt.head)) {
                return false
            }
            it = it.tail; jt = jt.tail
        }
        it.isNil && jt.isNil
    }

    @aliasOf("isNil")
    final def isEmpty: Boolean = isNil

}


/**
 * The nil list
 */
case object Nil extends List[Nothing] {
    override def isNil = true
    override def head = throw new NoSuchElementException("head on nil")
    override def tail =  throw new UnsupportedOperationException("tail on nil")
}

/**
 * The cons list
 */
final case class Cons[+A](_1: util.ByLazy[A], _2: util.ByLazy[List[A]]) extends List[A] {
    override def isNil: Boolean = false
    override def head: A = _1()
    override def tail: List[A] = _2()
}
