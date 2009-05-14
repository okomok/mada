

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import traversable._


/**
 * Yet another Iterable
 */
trait Traversable[+A] {


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

    override def toString = {
        val sb = new StringBuilder
        sb.append('[')

        val t = start
        if (t) {
            sb.append(~t)
            t.++
        }
        while (t) {
            sb.append(", ")
            sb.append(~t)
            t.++
        }

        sb.append(']')
        sb.toString
    }

    override def hashCode = {
        var code = 1
        val t = start
        while (t) {
            code = 31 * code + (~t).hashCode
            t.++
        }
        code
    }


// general

    /**
     * Takes at most <code>n</code> elements.
     */
    def take(n: Int): Traversable[A] = Take(this, n)

    /**
     * Drops at most <code>n</code> elements.
     */
    def drop(n: Int): Traversable[A] = Drop(this, n)

    /**
     * @return  <code>drop(n).take(n - m)</code>.
     */
    def slice(n: Int, m: Int): Traversable[A] = Slice(this, n, m)

    /**
     * Maps elements using <code>f</code>.
     */
    def map[B](f: A => B): Traversable[B] = Map(this, f)

    /**
     * Appends <code>that</code>.
     */
    def append[B >: A](that: Traversable[B]): Traversable[B] = Append[B](this, that)

    @aliasOf("append")
    final def ++[B >: A](that: Traversable[B]) = append(that)

    /**
     * @return  <code>map(f).flatten</code>.
     */
    def flatMap[B](f: A => Traversable[B]): Traversable[B] = _flatten(map(f))

    /**
     * Filters elements using <code>f</code>.
     */
    def filter(p: A => Boolean): Traversable[A] = Filter(this, p)

    /**
     * Takes elements while <code>p</code> meets.
     */
    def takeWhile(p: A => Boolean): Traversable[A] = TakeWhile(this, p)

    /**
     * Drops elements while <code>p</code> meets.
     */
    def dropWhile(p: A => Boolean): Traversable[A] = DropWhile(this, p)

    /**
     * Zips <code>this</code> and <code>that</code>.
     */
    def zip[B](that: Traversable[B]): Traversable[(A, B)] = Zip(this, that)

    /**
     * Applies <code>f</code> to each element.
     */
    def foreach[U](f: A => U): Unit = {
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
     * Does this contain the element?
     */
    def contains(e: Any): Boolean = exists(function.equalTo(e))

    /**
     * Does <code>p</code> meet for any element?
     */
    def forall(p: A => Boolean): Boolean = find(function.not(p)).isEmpty

    /**
     * Does an element exists which <code>p</code> meets?
     */
    def exists(p: A => Boolean): Boolean = !find(p).isEmpty

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
        bind(t).foldLeft[B](e)(op)
    }

    /**
     * Is <code>this</code> empty?
     */
    def isEmpty: Boolean = start.isEnd

    /**
     * Returns the length.
     */
    def length: Int = {
        var i = 0
        val t = start
        while (t) {
            i += 1
            t.++
        }
        i
    }


// prefix sum

    /**
     * Prefix sum folding left to right.
     */
    def folderLeft[B](z: B)(op: (B, A) => B): Traversable[B] = FolderLeft(this, z, op)

    /**
     * Prefix sum reducing left to right.
     */
    def reducerLeft[B >: A](op: (B, A) => B): Traversable[B] = ReducerLeft(this, op)


// methodization

    def _flatten[B](_this: Traversable[Traversable[B]]): Traversable[B] = Flatten(_this)

    def _stringize(_this: Traversable[Char]): String = {
        val sb = new StringBuilder
        val t = start
        while (t) {
            sb.append(~t)
            t.++
        }
        sb.toString
    }

    def _byLazy[B](_this: => Traversable[B]): Traversable[B] = throw new Error


// sorted

    /**
     * @return  <code>mergeBy(that)(c)</code>.
     */
    def merge[B >: A](that: Traversable[B])(implicit c: Compare[B]) = mergeBy(that)(c)

    /**
     * Combines the elements tr the sorted traversables, into a new traversable with its elements sorted.
     */
    def mergeBy[B >: A](that: Traversable[B])(lt: compare.Func[B]): Traversable[B] = Merge[B](this, that, lt)

}


object Traversable extends Compatibles {

    sealed class OfByName[A](tr: => Traversable[A]) {
        def byLazy: Traversable[A] = tr._byLazy(tr)
    }
    implicit def ofByname[A](tr: => Traversable[A]): OfByName[A] = new OfByName(tr)

    sealed class OfTraversable[A](tr: Traversable[Traversable[A]]) {
        def flatten: Traversable[A] = tr._flatten(tr)
    }
    implicit def ofTraversable[A](tr: Traversable[Traversable[A]]): OfTraversable[A] = new OfTraversable(tr)

    sealed class OfChar(tr: Traversable[Char]) {
        def stringize: String = tr._stringize(tr)
    }
    implicit def ofChar(tr: Traversable[Char]): OfChar = new OfChar(tr)

}
