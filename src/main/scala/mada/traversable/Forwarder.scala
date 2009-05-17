

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


trait Forwarder[+A] extends Traversable[A] with any.Forwarder {
    override protected def delegate: Traversable[A]

    protected def beforeForward[B](that: Traversable[B]): Traversable[B] = that
    protected def afterForward[B](that: Traversable[B]): Traversable[B] = that

    override def begin: Traverser[A] = beforeForward(delegate).begin

    override def equalsIf[B](that: Traversable[B])(p: (A, B) => Boolean): Boolean = beforeForward(delegate).equalsIf(that)(p)
    override def equals(that: Any): Boolean = beforeForward(delegate).equals(that)
    override def hashCode: Int = beforeForward(delegate).hashCode
    override def toString: String = beforeForward(delegate).toString

    override def isEmpty: Boolean = beforeForward(delegate).isEmpty
    override def length: Int = beforeForward(delegate).length
    override def append[B >: A](that: Traversable[B]): Traversable[B] = afterForward(beforeForward(delegate).append(that))
    override def map[B](f: A => B): Traversable[B] = afterForward(beforeForward(delegate).map(f))
    override def flatMap[B](f: A => Traversable[B]): Traversable[B] = afterForward(beforeForward(delegate).flatMap(f))
    override def filter(p: A => Boolean): Traversable[A] = afterForward(beforeForward(delegate).filter(p))
    override def partition(p: A => Boolean): (Traversable[A], Traversable[A]) = beforeForward(delegate).partition(p)
    override def groupBy[K](f: A => K): scala.collection.Map[K, Traversable[A]] = beforeForward(delegate).groupBy(f)
    override def foreach[B](f: A => B): Unit = beforeForward(delegate).foreach(f)
    override def forall(p: A => Boolean): Boolean = beforeForward(delegate).forall(p)
    override def exists(p: A => Boolean): Boolean = beforeForward(delegate).exists(p)
    override def count(p: A => Boolean): Int = beforeForward(delegate).count(p)
    override def find(p: A => Boolean): Option[A] = beforeForward(delegate).find(p)
    override def foldLeft[B](z: B)(op: (B, A) => B): B = beforeForward(delegate).foldLeft(z)(op)
    //override def /:[B](z: B)(op: (B, A) => B): B = beforeForward(delegate)./:(z)(op)
    override def reduceLeft[B >: A](op: (B, A) => B): B = beforeForward(delegate).reduceLeft(op)
    override def folderLeft[B](z: B)(op: (B, A) => B): Traversable[B] = afterForward(beforeForward(delegate).folderLeft(z)(op))
    override def reducerLeft[B >: A](op: (B, A) => B): Traversable[B] = afterForward(beforeForward(delegate).reducerLeft(op))
    override def head: A = beforeForward(delegate).head
    override def headOption: Option[A] = beforeForward(delegate).headOption
    override def tail: Traversable[A] = afterForward(beforeForward(delegate).tail)
    override def last: A = beforeForward(delegate).last
    override def lastOption: Option[A] = beforeForward(delegate).lastOption
    override def take(n: Int): Traversable[A] = afterForward(beforeForward(delegate).take(n))
    override def drop(n: Int): Traversable[A] = afterForward(beforeForward(delegate).drop(n))
    override def slice(from: Int, until: Int): Traversable[A] = afterForward(beforeForward(delegate).slice(from, until))
    override def takeWhile(p: A => Boolean): Traversable[A] = afterForward(beforeForward(delegate).takeWhile(p))
    override def dropWhile(p: A => Boolean): Traversable[A] = afterForward(beforeForward(delegate).dropWhile(p))
    override def span(p: A => Boolean): (Traversable[A], Traversable[A]) = beforeForward(delegate).span(p)
    override def splitAt(n: Int): (Traversable[A], Traversable[A]) = beforeForward(delegate).splitAt(n)
    //override def copyToBuffer[B >: A](dest: Buffer[B]) = beforeForward(delegate).copyToBuffer(dest)
    //override def copyToArray[B >: A](xs: Array[B], begin: Int, len: Int) = beforeForward(delegate).copyToArray(xs, begin, len)
    //override def copyToArray[B >: A](xs: Array[B], begin: Int) = beforeForward(delegate).copyToArray(xs, begin)
    //override def toArray[B >: A]: Array[B] = beforeForward(delegate).toArray
    override def toIterable: Iterable[A] = beforeForward(delegate).toIterable

    override def at(n: Int): A = beforeForward(delegate).at(n)
    override def contains(e: Any): Boolean = beforeForward(delegate).contains(e)
    override def cycle: Traversable[A] = afterForward(beforeForward(delegate).cycle)
    override def times(n: Int): Traversable[A] = beforeForward(delegate).times(n)
    override def force: Traversable[A] = afterForward(beforeForward(delegate).force)
    override def _flatten[B](_this: Traversable[Traversable[B]]): Traversable[B] = afterForward(beforeForward(delegate)._flatten(_this))
    override def mix(x: Mixin): Traversable[A] = afterForward(beforeForward(delegate).mix(x))
    override def step(n: Int): Traversable[A] = beforeForward(delegate).step(n)
    override def unique: Traversable[A] = afterForward(beforeForward(delegate).unique)
    override def uniqueBy(p: (A, A) => Boolean): Traversable[A] = afterForward(beforeForward(delegate).uniqueBy(p))
    override def _stringize(_this: Traversable[Char]): String = beforeForward(delegate)._stringize(_this)
    override def _toHashMap[K, V](_this: Traversable[(K, V)]): scala.collection.Map[K, V] = beforeForward(delegate)._toHashMap(_this)
    override def _toHashSet[B](_this: Traversable[B]): scala.collection.Set[B] = beforeForward(delegate)._toHashSet(_this)
    override def _toJIterable[B](_this: Traversable[B]): java.lang.Iterable[B] = beforeForward(delegate)._toJIterable(_this)
    override def _toVector[B](_this: Traversable[B]): Vector[B] = beforeForward(delegate)._toVector(_this)
    override def zip[B](that: Traversable[B]): Traversable[(A, B)] = afterForward(beforeForward(delegate).zip(that))

    override def merge[B >: A](that: Traversable[B])(implicit c: Compare[B]): Traversable[B] = afterForward(beforeForward(delegate).merge(that)(c))
    override def mergeBy[B >: A](that: Traversable[B])(lt: compare.Func[B]): Traversable[B] = afterForward(beforeForward(delegate).mergeBy(that)(lt))
    override def union[B >: A](that: Traversable[B])(implicit c: Compare[B]): Traversable[B] = afterForward(beforeForward(delegate).union(that)(c))
    override def unionBy[B >: A](that: Traversable[B])(lt: compare.Func[B]): Traversable[B] = afterForward(beforeForward(delegate).unionBy(that)(lt))
    override def intersection[B >: A](that: Traversable[B])(implicit c: Compare[B]): Traversable[B] = afterForward(beforeForward(delegate).intersection(that)(c))
    override def intersectionBy[B >: A](that: Traversable[B])(lt: compare.Func[B]): Traversable[B] = afterForward(beforeForward(delegate).intersectionBy(that)(lt))
    override def difference[B >: A](that: Traversable[B])(implicit c: Compare[B]): Traversable[B] = afterForward(beforeForward(delegate).difference(that)(c))
    override def differenceBy[B >: A](that: Traversable[B])(lt: compare.Func[B]): Traversable[B] = afterForward(beforeForward(delegate).differenceBy(that)(lt))
    override def symmetricDifference[B >: A](that: Traversable[B])(implicit c: Compare[B]): Traversable[B] = afterForward(beforeForward(delegate).symmetricDifference(that)(c))
    override def symmetricDifferenceBy[B >: A](that: Traversable[B])(lt: compare.Func[B]): Traversable[B] = afterForward(beforeForward(delegate).symmetricDifferenceBy(that)(lt))
}
