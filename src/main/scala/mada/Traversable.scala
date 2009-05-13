

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import traversable._


/**
 * Yet another Iterable
 */
trait Traversable[+A] { ^ =>


// start

    /**
     * Returns a starting traverser.
     */
    def start: Traverser[A]


// value semantics

    override def equals(that: Any) = that match {
        case that: Traversable[_] => equalsIf(that)(function.equal)
        case _ => false
    }

    /**
     * Equals <code>that</code> iif they has the same length and <code>p</code> meets.
     */
    def equalsIf[B](that: Traversable[B])(p: (A, B) => Boolean): Boolean = {
        val t = start
        val u = that.start
        while (t && !u.isEnd) {
            if (!p(~t, ~u)) {
                return false
            }
            t.++; u.++
        }
        !t && !u
    }

    //TODO
    //override def hashCode = throw new Error


// general

    /**
     * Takes at most <code>n</code> elements.
     */
    def take(n: Int): Traversable[A] = new Take(this, n)

    /**
     * Drops at most <code>n</code> elements.
     */
    def drop(n: Int): Traversable[A] = new Drop(this, n)

    /**
     * @return  <code>drop(n).take(n - m)</code>.
     */
    def slice(n: Int, m: Int): Traversable[A] = new Slice(this, n, m)

    /**
     * Maps elements using <code>f</code>.
     */
    def map[B](f: A => B): Traversable[B] = new Map(this, f)

    /**
     * Appends <code>that</code>.
     */
    def append[B >: A](that: Traversable[B]): Traversable[B] = new Append[B](this, that)

    @aliasOf("append")
    final def ++[B >: A](that: Traversable[B]) = append(that)

    /**
     * @return  <code>map(f).flatten</code>.
     */
    final def flatMap[B](f: A => Traversable[B]): Traversable[B] = _flatten(map(f))

    /**
     * Filters elements using <code>f</code>.
     */
    def filter(p: A => Boolean): Traversable[A] = new Filter(this, p)

    /**
     * Takes elements while <code>p</code> meets.
     */
    def takeWhile(p: A => Boolean): Traversable[A] = new TakeWhile(this, p)

    /**
     * Drops elements while <code>p</code> meets.
     */
    def dropWhile(p: A => Boolean): Traversable[A] = new DropWhile(this, p)

    /**
     * Zips <code>this</code> and <code>that</code>.
     */
    def zip[B](that: Traversable[B]): Traversable[(A, B)] = new Zip(this, that)

    /**
     * Applies <code>f</code> to each element.
     */
    def foreach(f: A => Unit): Unit = {
        val t = start
        while (t) {
            f(~t)
            t.++
        }
    }

    /**
     * Finds an element <code>p</code> meets.
     */
    def find(p: A => Boolean): Option[A] = {
        val t = start
        while (t) {
            val e = ~t
            if (p(e)) {
                return Some(e)
            }
            t.++
        }
        None
    }

    /**
     * Does this contains an element?
     */
    final def contains(e: Any): Boolean = exists(function.equalTo(e))

    /**
     * Does <code>p</code> meet for any element?
     */
    final def forall(p: A => Boolean): Boolean = find(function.not(p)).isEmpty

    /**
     * Does an element exists which <code>p</code> meets?
     */
    final def exists(p: A => Boolean): Boolean = !find(p).isEmpty

    /**
     * Folds left to right.
     */
    def foldLeft[B](z: B)(op: (B, A) => B): B = {
        var acc = z
        val t = start
        while (t) {
            acc = op(acc, ~t)
            t.++
        }
        acc
    }

    @aliasOf("foldLeft")
    final def /:[B](z: B)(op: (B, A) => B): B = foldLeft(z)(op)

    /**
     * Reduces left to right.
     */
    def reduceLeft[B >: A](op: (B, A) => B): B = {
        val t = start
        if (!t) {
            throw new UnsupportedOperationException("reduceLeft on empty traversable")
        }
        val e = ~t
        t.++
        bindOnce(t).foldLeft[B](e)(op)
    }

    /**
     * Is <code>this</code> empty?
     */
    def isEmpty: Boolean = start.isEnd

    /**
     * Returns the length.
     */
    def length: Int = {
        val t = start
        var i = 0
        while (t) {
            t.++
            i += 1
        }
        i
    }


// methodized

    def _flatten[B](_this: Traversable[Traversable[B]]): Traversable[B] = new Flatten(_this)

    def _stringize(_this: Traversable[Char]): String = throw new Error

    def _lazy[B](_this: => Traversable[B]): Traversable[B] = throw new Error


// sorted

    /**
     * @return  <code>mergeBy(that)(c)</code>.
     */
    final def merge[B >: A](that: Traversable[B])(implicit c: Compare[B]) = mergeBy(that)(c)

    /**
     * Combines the elements tr the sorted traversables, into a new traversable with its elements sorted.
     */
    def mergeBy[B >: A](that: Traversable[B])(lt: compare.Func[B]): Traversable[B] = bind(new MergeTraverser[B](start, that.start, lt))

}


object Traversable {


// methodized

    sealed trait OfByName[A] {
        def `lazy`: Traversable[A]
    }
    implicit def ofByname[A](tr: => Traversable[A]): OfByName[A] = new OfByName[A] {
        override def `lazy` = tr._lazy(tr)
    }

    sealed trait OfTraversable[A] {
        def flatten: Traversable[A]
    }
    implicit def ofTraversable[A](tr: Traversable[Traversable[A]]): OfTraversable[A] = new OfTraversable[A] {
        override def flatten = tr._flatten(tr)
    }

    sealed trait OfChar {
        def stringize: String
    }
    implicit def ofChar(tr: Traversable[Char]): OfChar = new OfChar {
        override def stringize = tr._stringize(tr)
    }

}
