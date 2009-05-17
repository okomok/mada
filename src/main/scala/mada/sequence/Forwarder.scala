

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


trait Forwarder[+A] extends Sequence[A] with any.Forwarder {
    override protected def delegate: Sequence[A]

    protected def beforeForward[B](that: Sequence[B]): Sequence[B] = that
    protected def afterForward[B](that: Sequence[B]): Sequence[B] = that
    private def afterForward2[B](that: (Sequence[B], Sequence[B])): (Sequence[B], Sequence[B]) = (afterForward(that._1), afterForward(that._2))

    override def begin: Iterator[A] = beforeForward(delegate).begin

    override def equalsIf[B](that: Sequence[B])(p: (A, B) => Boolean): Boolean = beforeForward(delegate).equalsIf(that)(p)
    override def equals(that: Any): Boolean = beforeForward(delegate).equals(that)
    override def hashCode: Int = beforeForward(delegate).hashCode
    override def toString: String = beforeForward(delegate).toString

    override def isEmpty: Boolean = beforeForward(delegate).isEmpty
    override def length: Int = beforeForward(delegate).length
    override def append[B >: A](that: Sequence[B]): Sequence[B] = afterForward(beforeForward(delegate).append(that))
    override def map[B](f: A => B): Sequence[B] = afterForward(beforeForward(delegate).map(f))
    override def flatMap[B](f: A => Sequence[B]): Sequence[B] = afterForward(beforeForward(delegate).flatMap(f))
    override def filter(p: A => Boolean): Sequence[A] = afterForward(beforeForward(delegate).filter(p))
    override def partition(p: A => Boolean): (Sequence[A], Sequence[A]) = afterForward2(beforeForward(delegate).partition(p))
    override def groupBy[K](f: A => K): scala.collection.Map[K, Sequence[A]] = beforeForward(delegate).groupBy(f)
    override def foreach[B](f: A => B): Unit = beforeForward(delegate).foreach(f)
    override def forall(p: A => Boolean): Boolean = beforeForward(delegate).forall(p)
    override def exists(p: A => Boolean): Boolean = beforeForward(delegate).exists(p)
    override def count(p: A => Boolean): Int = beforeForward(delegate).count(p)
    override def find(p: A => Boolean): Option[A] = beforeForward(delegate).find(p)
    override def foldLeft[B](z: B)(op: (B, A) => B): B = beforeForward(delegate).foldLeft(z)(op)
    //override def /:[B](z: B)(op: (B, A) => B): B = beforeForward(delegate)./:(z)(op)
    override def reduceLeft[B >: A](op: (B, A) => B): B = beforeForward(delegate).reduceLeft(op)
    override def folderLeft[B](z: B)(op: (B, A) => B): Sequence[B] = afterForward(beforeForward(delegate).folderLeft(z)(op))
    override def reducerLeft[B >: A](op: (B, A) => B): Sequence[B] = afterForward(beforeForward(delegate).reducerLeft(op))
    override def head: A = beforeForward(delegate).head
    override def headOption: Option[A] = beforeForward(delegate).headOption
    override def tail: Sequence[A] = afterForward(beforeForward(delegate).tail)
    override def last: A = beforeForward(delegate).last
    override def lastOption: Option[A] = beforeForward(delegate).lastOption
    override def take(n: Int): Sequence[A] = afterForward(beforeForward(delegate).take(n))
    override def drop(n: Int): Sequence[A] = afterForward(beforeForward(delegate).drop(n))
    override def slice(from: Int, until: Int): Sequence[A] = afterForward(beforeForward(delegate).slice(from, until))
    override def takeWhile(p: A => Boolean): Sequence[A] = afterForward(beforeForward(delegate).takeWhile(p))
    override def dropWhile(p: A => Boolean): Sequence[A] = afterForward(beforeForward(delegate).dropWhile(p))
    override def span(p: A => Boolean): (Sequence[A], Sequence[A]) = afterForward2(beforeForward(delegate).span(p))
    override def splitAt(n: Int): (Sequence[A], Sequence[A]) = afterForward2(beforeForward(delegate).splitAt(n))
    //override def copyToBuffer[B >: A](dest: Buffer[B]) = beforeForward(delegate).copyToBuffer(dest)
    //override def copyToArray[B >: A](xs: Array[B], begin: Int, len: Int) = beforeForward(delegate).copyToArray(xs, begin, len)
    //override def copyToArray[B >: A](xs: Array[B], begin: Int) = beforeForward(delegate).copyToArray(xs, begin)
    //override def toArray[B >: A]: Array[B] = beforeForward(delegate).toArray
    override def toSIterable: Iterable[A] = beforeForward(delegate).toSIterable

    override def at(n: Int): A = beforeForward(delegate).at(n)
    override def contains(e: Any): Boolean = beforeForward(delegate).contains(e)
    override def cycle: Sequence[A] = afterForward(beforeForward(delegate).cycle)
    override def times(n: Int): Sequence[A] = beforeForward(delegate).times(n)
    override def force: Sequence[A] = afterForward(beforeForward(delegate).force)
    override def _flatten[B](_this: Sequence[Sequence[B]]): Sequence[B] = afterForward(beforeForward(delegate)._flatten(_this))
    override def mix(x: Mixin): Sequence[A] = afterForward(beforeForward(delegate).mix(x))
    override def step(n: Int): Sequence[A] = beforeForward(delegate).step(n)
    override def unique: Sequence[A] = afterForward(beforeForward(delegate).unique)
    override def uniqueBy(p: (A, A) => Boolean): Sequence[A] = afterForward(beforeForward(delegate).uniqueBy(p))
    override def _stringize(_this: Sequence[Char]): String = beforeForward(delegate)._stringize(_this)
    override def _toSHashMap[K, V](_this: Sequence[(K, V)]): scala.collection.Map[K, V] = beforeForward(delegate)._toSHashMap(_this)
    override def _toSHashSet[B](_this: Sequence[B]): scala.collection.Set[B] = beforeForward(delegate)._toSHashSet(_this)
    override def _toJIterable[B](_this: Sequence[B]): java.lang.Iterable[B] = beforeForward(delegate)._toJIterable(_this)
    override def _toVector[B](_this: Sequence[B]): Vector[B] = beforeForward(delegate)._toVector(_this)
    override def zip[B](that: Sequence[B]): Sequence[(A, B)] = afterForward(beforeForward(delegate).zip(that))

    override def merge[B >: A](that: Sequence[B])(implicit c: Compare[B]): Sequence[B] = afterForward(beforeForward(delegate).merge(that)(c))
    override def mergeBy[B >: A](that: Sequence[B])(lt: compare.Func[B]): Sequence[B] = afterForward(beforeForward(delegate).mergeBy(that)(lt))
    override def union[B >: A](that: Sequence[B])(implicit c: Compare[B]): Sequence[B] = afterForward(beforeForward(delegate).union(that)(c))
    override def unionBy[B >: A](that: Sequence[B])(lt: compare.Func[B]): Sequence[B] = afterForward(beforeForward(delegate).unionBy(that)(lt))
    override def intersection[B >: A](that: Sequence[B])(implicit c: Compare[B]): Sequence[B] = afterForward(beforeForward(delegate).intersection(that)(c))
    override def intersectionBy[B >: A](that: Sequence[B])(lt: compare.Func[B]): Sequence[B] = afterForward(beforeForward(delegate).intersectionBy(that)(lt))
    override def difference[B >: A](that: Sequence[B])(implicit c: Compare[B]): Sequence[B] = afterForward(beforeForward(delegate).difference(that)(c))
    override def differenceBy[B >: A](that: Sequence[B])(lt: compare.Func[B]): Sequence[B] = afterForward(beforeForward(delegate).differenceBy(that)(lt))
    override def symmetricDifference[B >: A](that: Sequence[B])(implicit c: Compare[B]): Sequence[B] = afterForward(beforeForward(delegate).symmetricDifference(that)(c))
    override def symmetricDifferenceBy[B >: A](that: Sequence[B])(lt: compare.Func[B]): Sequence[B] = afterForward(beforeForward(delegate).symmetricDifferenceBy(that)(lt))
}
