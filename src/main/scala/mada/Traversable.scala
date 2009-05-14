

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


// as value

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


// scala traversable

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

    @aliasOf("length")
    final def size = length

    /**
     * Concatenates <code>that</code>.
     */
    def concat[B >: A](that: Traversable[B]): Traversable[B] = Concat[B](this, that)

    @aliasOf("concat")
    final def ++[B >: A](that: Traversable[B]) = concat(that)

    /**
     * Maps elements using <code>f</code>.
     */
    def map[B](f: A => B): Traversable[B] = Map(this, f)

    /**
     * @return  <code>map(f).flatten</code>.
     */
    def flatMap[B](f: A => Traversable[B]): Traversable[B] = _flatten(map(f))

    /**
     * Filters elements using <code>f</code>.
     */
    def filter(p: A => Boolean): Traversable[A] = Filter(this, p)

    /**
     * @return  <code>(filter(p), filter(function.not(p)))</code>.
     */
    def partition(p: A => Boolean): (Traversable[A], Traversable[A]) = (filter(p), filter(function.not(p)))

    /**
     * What?
     */
    def groupBy[K](f: A => K): scala.collection.Map[K, Traversable[A]] = throw new Error

    /**
     * Applies <code>f</code> to each element.
     */
    def foreach[B](f: A => B): Unit = {
        val t = start
        while (t) {
            f(~t)
            t.++
        }
    }

    /**
     * Does <code>p</code> meet for any element?
     */
    def forall(p: A => Boolean): Boolean = find(function.not(p)).isEmpty

    /**
     * Does an element exists which <code>p</code> meets?
     */
    def exists(p: A => Boolean): Boolean = !find(p).isEmpty

    /**
     * Counts elements <code>p</code> meets.
     */
    def count(p: A => Boolean): Int = {
        var i = 0
        val t = start
        while (t) {
            if (p(~t)) {
                i += 1
            }
            t.++
        }
        i
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
     * Prefix sum folding left to right.
     */
    def folderLeft[B](z: B)(op: (B, A) => B): Traversable[B] = FolderLeft(this, z, op)

    /**
     * Prefix sum reducing left to right.
     */
    def reducerLeft[B >: A](op: (B, A) => B): Traversable[B] = ReducerLeft(this, op)

    /**
     * Returns the first element.
     */
    def head: A = {
        val t = start
        if (!t) {
            throw new NoSuchElementException("head on empty traversable")
        }
        ~t
    }

    /**
     * Optionally returns the first element.
     */
    def headOption: Option[A] = {
        val t = start
        if (!t) {
            None
        } else {
            Some(~t)
        }
    }

    /**
     * Returns all the elements without the first one.
     */
    def tail: Traversable[A] = {
        val t = start
        if (!t) {
            throw new NoSuchElementException("tail on empty traversable")
        }
        bind(t)
    }

    /**
     * Returns the last element.
     */
    def last: A = {
        val t = start
        if (!t) {
            throw new NoSuchElementException("last on empty traversable")
        }
        var e = ~t
        t.++
        while (t) {
            e = ~t
        }
        e
    }

    /**
     * Optionally returns the last element.
     */
    def lastOption: Option[A] = {
        var e = new Proxies.Var[A]
        val t = start
        while (t) {
            e.assign(~t)
        }
        // TODO: e.toOption is needed.
        if (e.isNull) {
            None
        } else {
            Some(e.self)
        }
    }

    /**
     * Takes at most <code>n</code> elements.
     */
    def take(n: Int): Traversable[A] = {
        throwIfNegative(n, "take")
        Take(this, n)
    }

    /**
     * Drops at most <code>n</code> elements.
     */
    def drop(n: Int): Traversable[A] = {
        throwIfNegative(n, "drop")
        Drop(this, n)
    }

    /**
     * @return  <code>drop(n).take(n - m)</code>.
     */
    def slice(from: Int, until: Int): Traversable[A] = Slice(this, from, until)

    /**
     * Takes elements while <code>p</code> meets.
     */
    def takeWhile(p: A => Boolean): Traversable[A] = TakeWhile(this, p)

    /**
     * Drops elements while <code>p</code> meets.
     */
    def dropWhile(p: A => Boolean): Traversable[A] = DropWhile(this, p)

    /**
     * @return  <code>(takeWhile(p), dropWhile(p))</code>.
     */
    def span(p: A => Boolean): (Traversable[A], Traversable[A]) = (takeWhile(p), dropWhile(p))

    /**
     * @return  <code>(take(n), drop(n))</code>.
     */
    def splitAt(n: Int): (Traversable[A], Traversable[A]) = {
        throwIfNegative(n, "splitAt")
        (take(n), drop(n))
    }

    @conversion
    def toIterable: Iterable[A] = ToIterable(this)


// misc

    /**
     * @return  <code>it.elements.drop(n).next</code>.
     */
    def at(n: Int): A = {
        throwIfNegative(n, "at")
        var i = n
        val t = start
        while (t) {
            if (i == 0) {
                return ~t
            }
            i -= 1
            t.++
        }
        throw new NoSuchElementException("at" + Tuple1(n))
    }

    /**
     * Does this contain the element?
     */
    def contains(e: Any): Boolean = exists(function.equalTo(e))

    /**
     * Disables overrides.
     */
    final def seal: Traversable[A] = Seal(this)

    /**
     * Disables retraversing.
     */
    final def singlePass: Traversable[A] = SinglePass(this)

    /**
     * Zips <code>this</code> and <code>that</code>.
     */
    def zip[B](that: Traversable[B]): Traversable[(A, B)] = Zip(this, that)


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

    def _toVector[B](_this: Traversable[B]): Vector[B] = ToVector(_this)


// sorted

    /**
     * Combines the elements tr the sorted traversables, into a new traversable with its elements sorted.
     */
    def mergeBy[B >: A](that: Traversable[B])(lt: compare.Func[B]): Traversable[B] = Merge[B](this, that, lt)

    /**
     * @return  <code>mergeBy(that)(c)</code>.
     */
    final def merge[B >: A](that: Traversable[B])(implicit c: Compare[B]) = mergeBy(that)(c)

}


object Traversable extends Compatibles {

    sealed class OfByName[A](tr: => Traversable[A]) {
        final def `lazy`: Traversable[A] = new Lazy(tr)
        final def asName: Traversable[A] = new AsName(tr)
    }
    implicit def ofByName[A](tr: => Traversable[A]): OfByName[A] = new OfByName(tr)

    sealed class OfInvariant[A](tr: Traversable[A]) {
        final def toVector: Vector[A] = tr._toVector(tr)
    }
    implicit def ofInvariant[A](tr: Traversable[A]): OfInvariant[A] = new OfInvariant(tr)

    sealed class OfTraversable[A](tr: Traversable[Traversable[A]]) {
        final def flatten: Traversable[A] = tr._flatten(tr)
    }
    implicit def ofTraversable[A](tr: Traversable[Traversable[A]]): OfTraversable[A] = new OfTraversable(tr)

    sealed class OfChar(tr: Traversable[Char]) {
        final def stringize: String = tr._stringize(tr)
    }
    implicit def ofChar(tr: Traversable[Char]): OfChar = new OfChar(tr)

}
