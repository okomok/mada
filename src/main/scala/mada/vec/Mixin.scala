

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


// any f != mixin; v.mixin(wx).f --> v.wx.f.mixin(wx)
//
// v.mixin(force).mixin(bounds).map(g)
   // v.mixin(force `with` bounds).map(g) // fusion
   // v.force.bounds.map(g).mixin(force `with` bounds)
// v.mixin(force).bounds.  map(g).mixin(bounds)
// v.force.bounds.mixin(force).map(g).mixin(bounds)
// v.force.bounds.force.map(g).mixin(force).mixin(bounds). map(h)
// v.force.bounds.force.map(g).force.bounds.force.map(h).mixin(force).mixin(bounds)

// v.mixin(force).map(g).mixin(bounds).map(h)
// v.force.map(g).mixin(force).mixin(bounds).map(h)
   // v.force.map(g).mixin(force `with` bounds).map(h) // fusion
   // v.force.map(g).force.bounds.map(h).mixin(force `with` bounds)
// v.force.map(g).mixin(force).bounds.map(h).mixin(bounds)
// v.force.map(g).force.bounds.mixin(force).map(h).mixin(bounds)
// v.force.map(g).force.bounds.force.map(h).mixin(force).mixin(bounds)


private[mada] object NewMixin {
    def apply[A](v: Vector[A], mx: Mixin): Vector[A] = new MixinVector(v, mx)
}

private[mada] class MixinVector[A](v: Vector[A], mx: Mixin) extends VectorProxy[A] {
    override lazy val self = mx(v)
    private def carryMixin[B](that: Vector[B]): Vector[B] = that.mixin(mx)
    private def carryMixin2[B](that2: (Vector[B], Vector[B])): (Vector[B], Vector[B]) = (that2._1.mixin(mx), that2._2.mixin(mx))
  // regions
    override def region(_start: Int, _end: Int): Vector[A] = carryMixin(underlying.region(_start, _end))
    override def regionBase: Vector[A] = carryMixin(underlying.regionBase)
    override def init: Vector[A] = carryMixin(underlying.init)
    override def clear: Vector[A] = carryMixin(underlying.clear)
    override def window(n: Int, m: Int): Vector[A] = carryMixin(underlying.window(n, m))
    override def offset(i: Int, j: Int): Vector[A] = carryMixin(underlying.offset(i, j))
    override def slice(n: Int): Vector[A] = carryMixin(underlying.slice(n))
    override def slice(n: Int, m: Int): Vector[A] = carryMixin(underlying.slice(n, m))
    override def drop(n: Int): Vector[A] = carryMixin(underlying.drop(n))
    override def take(n: Int): Vector[A] = carryMixin(underlying.take(n))
    override def dropWhile(p: A => Boolean): Vector[A] = carryMixin(underlying.dropWhile(p))
    override def takeWhile(p: A => Boolean): Vector[A] = carryMixin(underlying.takeWhile(p))
  // division
    override def divide(n: Int): Vector[Vector[A]] = carryMixin(underlying.divide(n))
    override def splitAt(i: Int): (Vector[A], Vector[A]) = carryMixin2(underlying.splitAt(i))
    override def span(p: A => Boolean): (Vector[A], Vector[A]) = carryMixin2(underlying.span(p))
    override def break(p: A => Boolean): (Vector[A], Vector[A]) = carryMixin2(underlying.break(p))
  // as list
    override def tail: Vector[A] = carryMixin(underlying.tail)
  // filter
    override def filter(p: A => Boolean): Vector[A] = carryMixin(underlying.filter(p))
    override def mutatingFilter(p: A => Boolean): Vector[A] = carryMixin(underlying.mutatingFilter(p))
    override def remove(p: A => Boolean): Vector[A] = carryMixin(underlying.remove(p))
    override def mutatingRemove(p: A => Boolean): Vector[A] = carryMixin(underlying.mutatingRemove(p))
    override def partition(p: A => Boolean): (Vector[A], Vector[A]) = carryMixin2(underlying.partition(p))
  // map
    override def map[B](f: A => B): Vector[B] = carryMixin(underlying.map(f))
    override def flatMap[B](f: A => Vector[B]): Vector[B] = carryMixin(underlying.flatMap(f))
    override def asVectorOf[B]: Vector[B] = carryMixin(underlying.asVectorOf[B])
  // folding
    override def folderLeft[B](z: B)(op: (B, A) => B): Vector[B] = carryMixin(underlying.folderLeft(z)(op))
    override def folderRight[B](z: B)(op: (A, B) => B): Vector[B] = carryMixin(underlying.folderRight(z)(op))
    override def reducerLeft[B >: A](op: (B, A) => B): Vector[B] = carryMixin(underlying.reducerLeft(op))
    override def reducerRight[B >: A](op: (A, B) => B): Vector[B] = carryMixin(underlying.reducerRight(op))
  // sort
    override def sort(implicit c: Compare[A]): Vector[A] = carryMixin(underlying.sort(c))
    override def sortBy(lt: Compare.Predicate[A]): Vector[A] = carryMixin(underlying.sortBy(lt))
  // concatenation
    override def append(that: Vector[A]): Vector[A] = carryMixin(underlying.append(that))
  // permutation
    override def permutation(f: Int => Int): Vector[A] = carryMixin(underlying.permutation(f))
    override def cycle(n: Int): Vector[A] = carryMixin(underlying.cycle(n))
    override def nth: Vector[A] = carryMixin(underlying.nth)
    override def reverse: Vector[A] = carryMixin(underlying.reverse)
    override def step(n: Int): Vector[A] = carryMixin(underlying.step(n))
    override def rotate(i: Int): Vector[A] = carryMixin(underlying.rotate(i))
  // zip
    override def zip[B](that: Vector[B]): Vector[(A, B)] = carryMixin(underlying.zip(that))
  // attributes
    override def force: Vector[A] = carryMixin(underlying.force)
    override def lazyValues : Vector[A] = carryMixin(underlying.lazyValues)
    override def bounds: Vector[A] = carryMixin(underlying.bounds)
    override def readOnly: Vector[A] = carryMixin(underlying.readOnly)
    override def identity: Vector[A] = carryMixin(underlying.identity)
  // mixin
    override def mixin(_mx: Mixin): Vector[A] = unmixin.mixin(mx `with` _mx) // mixin-mixin fusion
    override def unmixin: Vector[A] = v
  // copy
    override def clone: Vector[A] = carryMixin(underlying.clone)
  // parallel support
    override def parallel(_grainSize: Int): Vector[A] = carryMixin(underlying.parallel(_grainSize))
    override def unparallel: Vector[A] = carryMixin(underlying.unparallel)
  // associative folding
    override def folder(z: A)(op: (A, A) => A): Vector[A] = carryMixin(underlying.folder(z)(op))
    override def reducer(op: (A, A) => A): Vector[A] = carryMixin(underlying.reducer(op))
}
