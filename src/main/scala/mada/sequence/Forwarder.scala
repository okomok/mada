

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


trait Forwarder[+A] extends Sequence[A] with util.Forwarder {
    override protected def delegate: Sequence[A]

    protected def afterForward[B](that: Sequence[B]): Sequence[B] = that
    private def afterForward2[B](that: (Sequence[B], Sequence[B])): (Sequence[B], Sequence[B]) = (afterForward(that._1), afterForward(that._2))

    override def begin: Iterator[A] = delegate.begin

    override def equalsIf[B](that: Sequence[B])(p: (A, B) => Boolean): Boolean = delegate.equalsIf(that)(p)
    override def equals(that: Any): Boolean = delegate.equals(that)
    override def hashCode: Int = delegate.hashCode
    override def toString: String = delegate.toString

    override def isEmpty: Boolean = delegate.isEmpty
    override def size: Int = delegate.size
    override def append[B >: A](that: Sequence[B]): Sequence[B] = afterForward(delegate.append(that))
    override def map[B](f: A => B): Sequence[B] = afterForward(delegate.map(f))
    override def flatMap[B](f: A => Sequence[B]): Sequence[B] = afterForward(delegate.flatMap(f))
    override def filter(p: A => Boolean): Sequence[A] = afterForward(delegate.filter(p))
    override def filterNot(p: A => Boolean): Sequence[A] = afterForward(delegate.filterNot(p))
    override def partition(p: A => Boolean): (Sequence[A], Sequence[A]) = afterForward2(delegate.partition(p))
    override def groupBy[K](f: A => K): scala.collection.Map[K, Sequence[A]] = delegate.groupBy(f)
    override def foreach(f: A => Unit): Unit = delegate.foreach(f)
    override def forall(p: A => Boolean): Boolean = delegate.forall(p)
    override def exists(p: A => Boolean): Boolean = delegate.exists(p)
    override def count(p: A => Boolean): Int = delegate.count(p)
    override def find(p: A => Boolean): Option[A] = delegate.find(p)
    override def foldLeft[B](z: B)(op: (B, A) => B): B = delegate.foldLeft(z)(op)
    //override def /:[B](z: B)(op: (B, A) => B): B = delegate./:(z)(op)
    override def reduceLeft[B >: A](op: (B, A) => B): B = delegate.reduceLeft(op)
    override def folderLeft[B](z: B)(op: (B, A) => B): Sequence[B] = afterForward(delegate.folderLeft(z)(op))
    override def reducerLeft[B >: A](op: (B, A) => B): Sequence[B] = afterForward(delegate.reducerLeft(op))
    override def head: A = delegate.head
    override def headOption: Option[A] = delegate.headOption
    override def tail: Sequence[A] = afterForward(delegate.tail)
    override def last: A = delegate.last
    override def lastOption: Option[A] = delegate.lastOption
    override def take(n: Int): Sequence[A] = afterForward(delegate.take(n))
    override def drop(n: Int): Sequence[A] = afterForward(delegate.drop(n))
    override def slice(from: Int, until: Int): Sequence[A] = afterForward(delegate.slice(from, until))
    override def takeWhile(p: A => Boolean): Sequence[A] = afterForward(delegate.takeWhile(p))
    override def dropWhile(p: A => Boolean): Sequence[A] = afterForward(delegate.dropWhile(p))
    override def span(p: A => Boolean): (Sequence[A], Sequence[A]) = afterForward2(delegate.span(p))
    override def splitAt(n: Int): (Sequence[A], Sequence[A]) = afterForward2(delegate.splitAt(n))
    //override def copyToBuffer[B >: A](dest: Buffer[B]) = delegate.copyToBuffer(dest)
    //override def copyToArray[B >: A](xs: Array[B], begin: Int, len: Int) = delegate.copyToArray(xs, begin, len)
    //override def copyToArray[B >: A](xs: Array[B], begin: Int) = delegate.copyToArray(xs, begin)
    //override def toArray[B >: A]: Array[B] = delegate.toArray
    override def toSSequence: scala.collection.Sequence[A] = delegate.toSSequence

    override def at(n: Int): A = delegate.at(n)
    override def contains(e: Any): Boolean = delegate.contains(e)
    override def cycle: Sequence[A] = afterForward(delegate.cycle)
    override def times(n: Int): Sequence[A] = delegate.times(n)
    override def force: Sequence[A] = afterForward(delegate.force)
    override def _flatten[B](_this: Sequence[Sequence[B]]): Sequence[B] = afterForward(delegate.asInstanceOf[Sequence[Sequence[B]]].flatten)
    override def mix(x: Mixin): Sequence[A] = afterForward(delegate.mix(x))
    override def seal: Sequence[A] = afterForward(delegate.seal) // wow?
    override def singlePass: Sequence[A] = afterForward(delegate.singlePass)
    override def step(n: Int): Sequence[A] = delegate.step(n)
    override def unique: Sequence[A] = afterForward(delegate.unique)
    override def uniqueBy(p: (A, A) => Boolean): Sequence[A] = afterForward(delegate.uniqueBy(p))
    override def toSome: ToSome[A] = delegate.toSome
    override def _stringize(_this: Sequence[Char]): String = delegate.asInstanceOf[Sequence[Char]].stringize
    override def _toSHashMap[K, V](_this: Sequence[(K, V)]): scala.collection.Map[K, V] = delegate.asInstanceOf[Sequence[(K, V)]].toSHashMap
    override def _toSHashSet[B](_this: Sequence[B]): scala.collection.Set[B] = delegate.asInstanceOf[Sequence[B]].toSHashSet
    override def _toJIterable[B](_this: Sequence[B]): java.lang.Iterable[B] = delegate.asInstanceOf[Sequence[B]].toJIterable
    override def _toVector[B](_this: Sequence[B]): Vector[B] = delegate.asInstanceOf[Sequence[B]].toVector
    override def zip[B](that: Sequence[B]): Sequence[(A, B)] = afterForward(delegate.zip(that))

    override def merge[B >: A](that: Sequence[B])(implicit c: Compare[B]): Sequence[B] = afterForward(delegate.merge(that)(c))
    override def mergeBy[B >: A](that: Sequence[B])(lt: compare.Func[B]): Sequence[B] = afterForward(delegate.mergeBy(that)(lt))
    override def union[B >: A](that: Sequence[B])(implicit c: Compare[B]): Sequence[B] = afterForward(delegate.union(that)(c))
    override def unionBy[B >: A](that: Sequence[B])(lt: compare.Func[B]): Sequence[B] = afterForward(delegate.unionBy(that)(lt))
    override def intersection[B >: A](that: Sequence[B])(implicit c: Compare[B]): Sequence[B] = afterForward(delegate.intersection(that)(c))
    override def intersectionBy[B >: A](that: Sequence[B])(lt: compare.Func[B]): Sequence[B] = afterForward(delegate.intersectionBy(that)(lt))
    override def difference[B >: A](that: Sequence[B])(implicit c: Compare[B]): Sequence[B] = afterForward(delegate.difference(that)(c))
    override def differenceBy[B >: A](that: Sequence[B])(lt: compare.Func[B]): Sequence[B] = afterForward(delegate.differenceBy(that)(lt))
    override def symmetricDifference[B >: A](that: Sequence[B])(implicit c: Compare[B]): Sequence[B] = afterForward(delegate.symmetricDifference(that)(c))
    override def symmetricDifferenceBy[B >: A](that: Sequence[B])(lt: compare.Func[B]): Sequence[B] = afterForward(delegate.symmetricDifferenceBy(that)(lt))
}
