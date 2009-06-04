

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterative


trait Forwarder[+A] extends Iterative[A] with SequenceForwarder[A] {
    override protected def delegate: Iterative[A]

    protected def afterForward[B](that: Iterative[B]): Iterative[B] = that
    private def afterForward2[B, C](that: (Iterative[B], Iterative[C])): (Iterative[B], Iterative[C]) = (afterForward(that._1), afterForward(that._2))

    @quasiFinal override def begin: Iterator[A] = delegate.begin

    override def equalsIf[B](that: Iterative[B])(p: (A, B) => Boolean): Boolean = delegate.equalsIf(that)(p)
    override def isEmpty: Boolean = delegate.isEmpty
    override def size: Int = delegate.size
    override def ++[B >: A](that: Iterative[B]): Iterative[B] = afterForward(delegate.++(that))
    override def map[B](f: A => B): Iterative[B] = afterForward(delegate.map(f))
    override def flatMap[B](f: A => Iterative[B]): Iterative[B] = afterForward(delegate.flatMap(f))
    override def filter(p: A => Boolean): Iterative[A] = afterForward(delegate.filter(p))
    override def remove(p: A => Boolean): Iterative[A] = afterForward(delegate.remove(p))
    override def partition(p: A => Boolean): (Iterative[A], Iterative[A]) = afterForward2(delegate.partition(p))
    override def groupBy[K](f: A => K): scala.collection.Map[K, Iterative[A]] = delegate.groupBy(f)
    override def foreach(f: A => Unit): Unit = delegate.foreach(f)
    override def forall(p: A => Boolean): Boolean = delegate.forall(p)
    override def exists(p: A => Boolean): Boolean = delegate.exists(p)
    override def count(p: A => Boolean): Int = delegate.count(p)
    override def find(p: A => Boolean): Option[A] = delegate.find(p)
    override def foldLeft[B](z: B)(op: (B, A) => B): B = delegate.foldLeft(z)(op)
    //override def /:[B](z: B)(op: (B, A) => B): B = delegate./:(z)(op)
    override def reduceLeft[B >: A](op: (B, A) => B): B = delegate.reduceLeft(op)
    override def folderLeft[B](z: B)(op: (B, A) => B): Iterative[B] = afterForward(delegate.folderLeft(z)(op))
    override def reducerLeft[B >: A](op: (B, A) => B): Iterative[B] = afterForward(delegate.reducerLeft(op))
    override def head: A = delegate.head
    override def headOption: Option[A] = delegate.headOption
    override def tail: Iterative[A] = afterForward(delegate.tail)
    override def last: A = delegate.last
    override def lastOption: Option[A] = delegate.lastOption
    override def take(n: Int): Iterative[A] = afterForward(delegate.take(n))
    override def drop(n: Int): Iterative[A] = afterForward(delegate.drop(n))
    override def slice(from: Int, until: Int): Iterative[A] = afterForward(delegate.slice(from, until))
    override def takeWhile(p: A => Boolean): Iterative[A] = afterForward(delegate.takeWhile(p))
    override def dropWhile(p: A => Boolean): Iterative[A] = afterForward(delegate.dropWhile(p))
    override def span(p: A => Boolean): (Iterative[A], Iterative[A]) = afterForward2(delegate.span(p))
    override def splitAt(n: Int): (Iterative[A], Iterative[A]) = afterForward2(delegate.splitAt(n))
    //override def copyToBuffer[B >: A](dest: Buffer[B]) = delegate.copyToBuffer(dest)
    //override def copyToArray[B >: A](xs: Array[B], begin: Int, len: Int) = delegate.copyToArray(xs, begin, len)
    //override def copyToArray[B >: A](xs: Array[B], begin: Int) = delegate.copyToArray(xs, begin)
    //override def toArray[B >: A]: Array[B] = delegate.toArray
    override def toSSequence: scala.collection.Sequence[A] = delegate.toSSequence

    override def at(n: Int): A = delegate.at(n)
    override def contains(e: Any): Boolean = delegate.contains(e)
    override def cycle: Iterative[A] = afterForward(delegate.cycle)
    override def times(n: Int): Iterative[A] = delegate.times(n)
    override def force: Iterative[A] = afterForward(delegate.force)
    override def _flatten[B](_this: Iterative[Sequence[B]]): Iterative[B] = afterForward(delegate.asInstanceOf[Iterative[Sequence[B]]].flatten)
    override def memoize: Iterative[A] = afterForward(delegate.memoize)
    override def mix(x: Mixin): Iterative[A] = afterForward(delegate.mix(x))
    override def seal: Iterative[A] = afterForward(delegate.seal) // wow?
    override def singlePass: Iterative[A] = afterForward(delegate.singlePass)
    override def step(n: Int): Iterative[A] = delegate.step(n)
    override def unique: Iterative[A] = afterForward(delegate.unique)
    override def uniqueBy(p: (A, A) => Boolean): Iterative[A] = afterForward(delegate.uniqueBy(p))
    override def toSome: ToSome[A] = delegate.toSome
    override def _stringize(_this: Iterative[Char]): String = delegate.asInstanceOf[Iterative[Char]].stringize
    override def _toSHashMap[K, V](_this: Iterative[(K, V)]): scala.collection.Map[K, V] = delegate.asInstanceOf[Iterative[(K, V)]].toSHashMap
    override def _toSHashSet[B](_this: Iterative[B]): scala.collection.Set[B] = delegate.asInstanceOf[Iterative[B]].toSHashSet
    override def _toJIterable[B](_this: Iterative[B]): java.lang.Iterable[B] = delegate.asInstanceOf[Iterative[B]].toJIterable
    override def _toVector[B](_this: Iterative[B]): Vector[B] = delegate.asInstanceOf[Iterative[B]].toVector
    override def zip[B](that: Iterative[B]): Iterative[(A, B)] = afterForward(delegate.zip(that))
    override def _unzip[B, C](_this: Iterative[(B, C)]): (Iterative[B], Iterative[C]) = afterForward2(delegate.asInstanceOf[Iterative[(B, C)]].unzip)
    override def zipBy[B, C](that: Iterative[B])(f: (A, B) => C): Iterative[C] = afterForward(delegate.zipBy(that)(f))

    override def merge[B >: A](that: Iterative[B])(implicit c: Compare[B]): Iterative[B] = afterForward(delegate.merge(that)(c))
    override def mergeBy[B >: A](that: Iterative[B])(lt: compare.Func[B]): Iterative[B] = afterForward(delegate.mergeBy(that)(lt))
    override def union[B >: A](that: Iterative[B])(implicit c: Compare[B]): Iterative[B] = afterForward(delegate.union(that)(c))
    override def unionBy[B >: A](that: Iterative[B])(lt: compare.Func[B]): Iterative[B] = afterForward(delegate.unionBy(that)(lt))
    override def intersection[B >: A](that: Iterative[B])(implicit c: Compare[B]): Iterative[B] = afterForward(delegate.intersection(that)(c))
    override def intersectionBy[B >: A](that: Iterative[B])(lt: compare.Func[B]): Iterative[B] = afterForward(delegate.intersectionBy(that)(lt))
    override def difference[B >: A](that: Iterative[B])(implicit c: Compare[B]): Iterative[B] = afterForward(delegate.difference(that)(c))
    override def differenceBy[B >: A](that: Iterative[B])(lt: compare.Func[B]): Iterative[B] = afterForward(delegate.differenceBy(that)(lt))
    override def symmetricDifference[B >: A](that: Iterative[B])(implicit c: Compare[B]): Iterative[B] = afterForward(delegate.symmetricDifference(that)(c))
    override def symmetricDifferenceBy[B >: A](that: Iterative[B])(lt: compare.Func[B]): Iterative[B] = afterForward(delegate.symmetricDifferenceBy(that)(lt))
}
