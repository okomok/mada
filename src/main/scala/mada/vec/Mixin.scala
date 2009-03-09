

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


/**
 * Contains utility methods operating on <code>Mixin</code>.
 */
object Mixin {
    /**
     * Calls <code>bounds</code> method.
     */
    val bounds: Mixin = new Mixin {
        override def apply[B](v: Vector[B]) = v.bounds
    }

    /**
     * Calls <code>force</code> method.
     */
    val force: Mixin = new Mixin {
        override def apply[B](v: Vector[B]) = v.force
    }

    /**
     * Calls <code>lazyValues</code> method.
     */
    val lazyValues: Mixin = new Mixin {
        override def apply[B](v: Vector[B]) = v.lazyValues
    }

    /**
     * Calls <code>parallel</code> method.
     */
    def parallel: Mixin = new Mixin {
        override def apply[B](v: Vector[B]) = v.parallel
    }

    /**
     * Calls <code>parallel</code> method.
     */
    def parallel(grainSize: Int): Mixin = new Mixin {
        override def apply[B](v: Vector[B]) = v.parallel(grainSize)
    }

    /**
     * Calls <code>readOnly</code> method.
     */
    val readOnly: Mixin = new Mixin {
        override def apply[B](v: Vector[B]) = v.readOnly
    }
}

/**
 * Applies <code>apply</code> to result of vector-to-vector methods.
 */
trait Mixin {
    def apply[B](v: Vector[B]): Vector[B]

    /**
     * Mixin composition
     */
    final def `with`(that: Mixin): Mixin = new Mixin {
        override def apply[B](v: Vector[B]) = that(Mixin.this.apply(v))
    }
}


private[mada] object NewMixin {
    def apply[A](v: Vector[A], mx: Mixin): Vector[A] = new MixinVector(v, mx)
}

private[mada] class MixinVector[A](override val self: Vector[A], mx: Mixin) extends VectorProxy[A] {
    private lazy val mixed = mx(underlying)
    private def affectMixin[B](that: Vector[B]): Vector[B] = that.mixin(mx)
    private def affectMixin2[B](that2: (Vector[B], Vector[B])): (Vector[B], Vector[B]) = (that2._1.mixin(mx), that2._2.mixin(mx))
  // regions
    override def region(_start: Int, _end: Int): Vector[A] = affectMixin(mixed.region(_start, _end))
    override def regionBase: Vector[A] = affectMixin(mixed.regionBase)
    override def init: Vector[A] = affectMixin(mixed.init)
    override def clear: Vector[A] = affectMixin(mixed.clear)
    override def window(n: Int, m: Int): Vector[A] = affectMixin(mixed.window(n, m))
    override def offset(i: Int, j: Int): Vector[A] = affectMixin(mixed.offset(i, j))
    override def slice(n: Int): Vector[A] = affectMixin(mixed.slice(n))
    override def slice(n: Int, m: Int): Vector[A] = affectMixin(mixed.slice(n, m))
    override def drop(n: Int): Vector[A] = affectMixin(mixed.drop(n))
    override def take(n: Int): Vector[A] = affectMixin(mixed.take(n))
    override def dropWhile(mx: A => Boolean): Vector[A] = affectMixin(mixed.dropWhile(mx))
    override def takeWhile(mx: A => Boolean): Vector[A] = affectMixin(mixed.takeWhile(mx))
  // division
    override def divide(n: Int): Vector[Vector[A]] = affectMixin(mixed.divide(n))
    override def splitAt(i: Int): (Vector[A], Vector[A]) = affectMixin2(mixed.splitAt(i))
    override def span(mx: A => Boolean): (Vector[A], Vector[A]) = affectMixin2(mixed.span(mx))
    override def break(mx: A => Boolean): (Vector[A], Vector[A]) = affectMixin2(mixed.break(mx))
  // as list
    override def tail: Vector[A] = affectMixin(mixed.tail)
  // filter
    override def filter(mx: A => Boolean): Vector[A] = affectMixin(mixed.filter(mx))
    override def mutatingFilter(mx: A => Boolean): Vector[A] = affectMixin(mixed.mutatingFilter(mx))
    override def remove(mx: A => Boolean): Vector[A] = affectMixin(mixed.remove(mx))
    override def mutatingRemove(mx: A => Boolean): Vector[A] = affectMixin(mixed.mutatingRemove(mx))
    override def partition(mx: A => Boolean): (Vector[A], Vector[A]) = affectMixin2(mixed.partition(mx))
  // map
    override def map[B](f: A => B): Vector[B] = affectMixin(mixed.map(f))
    override def flatMap[B](f: A => Vector[B]): Vector[B] = affectMixin(mixed.flatMap(f))
    override def asVectorOf[B]: Vector[B] = affectMixin(mixed.asVectorOf[B])
  // folding
    override def folderLeft[B](z: B)(op: (B, A) => B): Vector[B] = affectMixin(mixed.folderLeft(z)(op))
    override def folderRight[B](z: B)(op: (A, B) => B): Vector[B] = affectMixin(mixed.folderRight(z)(op))
    override def reducerLeft[B >: A](op: (B, A) => B): Vector[B] = affectMixin(mixed.reducerLeft(op))
    override def reducerRight[B >: A](op: (A, B) => B): Vector[B] = affectMixin(mixed.reducerRight(op))
  // sort
    override def sortWith(lt: Compare.Type[A]): Vector[A] = affectMixin(mixed.sortWith(lt))
    override def sort(implicit c: Compare.GetOrdered[A]): Vector[A] = affectMixin(mixed.sort(c))
  // concatenation
    override def append(that: Vector[A]): Vector[A] = affectMixin(mixed.append(that))
  // permutation
    override def permutation(f: Int => Int): Vector[A] = affectMixin(mixed.permutation(f))
    override def cycle(n: Int): Vector[A] = affectMixin(mixed.cycle(n))
    override def nth: Vector[A] = affectMixin(mixed.nth)
    override def reverse: Vector[A] = affectMixin(mixed.reverse)
    override def step(n: Int): Vector[A] = affectMixin(mixed.step(n))
    override def rotate(i: Int): Vector[A] = affectMixin(mixed.rotate(i))
  // zip
    override def zip[B](that: Vector[B]): Vector[(A, B)] = affectMixin(mixed.zip(that))
  // attributes
    override def force: Vector[A] = affectMixin(mixed.force)
    override def lazyValues : Vector[A] = affectMixin(mixed.lazyValues)
    override def bounds: Vector[A] = affectMixin(mixed.bounds)
    override def readOnly: Vector[A] = affectMixin(mixed.readOnly)
    override def identity: Vector[A] = affectMixin(mixed.identity)
  // mixin
    override def unmixin: Vector[A] = underlying
  // copy
    override def clone: Vector[A] = affectMixin(mixed.clone)
  // parallel support
    override def parallel(_grainSize: Int): Vector[A] = affectMixin(mixed.parallel(_grainSize))
    override def unparallel: Vector[A] = affectMixin(mixed.unparallel)
  // associative folding
    override def folder(z: A)(op: (A, A) => A): Vector[A] = affectMixin(mixed.folder(z)(op))
    override def reducer(op: (A, A) => A): Vector[A] = affectMixin(mixed.reducer(op))
}
