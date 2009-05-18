

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector


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
     * Calls <code>memoize</code> method.
     */
    val memoize: Mixin = new Mixin {
        override def apply[B](v: Vector[B]) = v.memoize
    }

    /**
     * Calls <code>parallel</code> method.
     */
    val parallel: Mixin = new Mixin {
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
trait Mixin { self =>
    def apply[B](v: Vector[B]): Vector[B]

    /**
     * Mixin composition
     */
    final def `with`(that: Mixin): Mixin = new Mixin {
        override def apply[B](v: Vector[B]) = that(self(v))
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

private[mada] class MixinVector[A](v: Vector[A], mx: Mixin) extends Forwarder[A] {
    override lazy val delegate = mx(v)
    private def carryMixin[B](that: Vector[B]): Vector[B] = that.mixin(mx)
    private def carryMixin2[B](that2: (Vector[B], Vector[B])): (Vector[B], Vector[B]) = (that2._1.mixin(mx), that2._2.mixin(mx))
  // regions
    override def region(_start: Int, _end: Int): Vector[A] = carryMixin(delegate.region(_start, _end))
    override def regionBase: Vector[A] = carryMixin(delegate.regionBase)
    override def init: Vector[A] = carryMixin(delegate.init)
    override def clear: Vector[A] = carryMixin(delegate.clear)
    override def window(n: Int, m: Int): Vector[A] = carryMixin(delegate.window(n, m))
    override def offset(i: Int, j: Int): Vector[A] = carryMixin(delegate.offset(i, j))
    override def slice(n: Int): Vector[A] = carryMixin(delegate.slice(n))
    override def slice(n: Int, m: Int): Vector[A] = carryMixin(delegate.slice(n, m))
    override def drop(n: Int): Vector[A] = carryMixin(delegate.drop(n))
    override def take(n: Int): Vector[A] = carryMixin(delegate.take(n))
    override def dropWhile(p: A => Boolean): Vector[A] = carryMixin(delegate.dropWhile(p))
    override def takeWhile(p: A => Boolean): Vector[A] = carryMixin(delegate.takeWhile(p))
  // division
    override def divide(n: Int): Vector[Vector[A]] = carryMixin(delegate.divide(n))
    override def splitAt(i: Int): (Vector[A], Vector[A]) = carryMixin2(delegate.splitAt(i))
    override def span(p: A => Boolean): (Vector[A], Vector[A]) = carryMixin2(delegate.span(p))
    override def break(p: A => Boolean): (Vector[A], Vector[A]) = carryMixin2(delegate.break(p))
  // as list
    override def tail: Vector[A] = carryMixin(delegate.tail)
  // filter
    override def filter(p: A => Boolean): Vector[A] = carryMixin(delegate.filter(p))
    override def mutatingFilter(p: A => Boolean): Vector[A] = carryMixin(delegate.mutatingFilter(p))
    override def remove(p: A => Boolean): Vector[A] = carryMixin(delegate.remove(p))
    override def mutatingRemove(p: A => Boolean): Vector[A] = carryMixin(delegate.mutatingRemove(p))
    override def partition(p: A => Boolean): (Vector[A], Vector[A]) = carryMixin2(delegate.partition(p))
  // map
    override def map[B](f: A => B): Vector[B] = carryMixin(delegate.map(f))
    override def flatMap[B](f: A => Vector[B]): Vector[B] = carryMixin(delegate.flatMap(f))
    override def asVectorOf[B]: Vector[B] = carryMixin(delegate.asVectorOf[B])
  // folding
    override def folderLeft[B](z: B)(op: (B, A) => B): Vector[B] = carryMixin(delegate.folderLeft(z)(op))
    override def folderRight[B](z: B)(op: (A, B) => B): Vector[B] = carryMixin(delegate.folderRight(z)(op))
    override def reducerLeft[B >: A](op: (B, A) => B): Vector[B] = carryMixin(delegate.reducerLeft(op))
    override def reducerRight[B >: A](op: (A, B) => B): Vector[B] = carryMixin(delegate.reducerRight(op))
  // sort
    override def sortBy(lt: compare.Func[A]): Vector[A] = carryMixin(delegate.sortBy(lt))
    override def stableSortBy(lt: compare.Func[A]): Vector[A] = carryMixin(delegate.stableSortBy(lt))
  // concatenation
    override def append(that: Vector[A]): Vector[A] = carryMixin(delegate.append(that))
  // permutation
    override def permutation(f: Int => Int): Vector[A] = carryMixin(delegate.permutation(f))
    override def cycle(n: Int): Vector[A] = carryMixin(delegate.cycle(n))
    override def nth: Vector[A] = carryMixin(delegate.nth)
    override def reverse: Vector[A] = carryMixin(delegate.reverse)
    override def step(n: Int): Vector[A] = carryMixin(delegate.step(n))
    override def rotate(i: Int): Vector[A] = carryMixin(delegate.rotate(i))
  // zip
    override def zip[B](that: Vector[B]): Vector[(A, B)] = carryMixin(delegate.zip(that))
  // attributes
    override def force: Vector[A] = carryMixin(delegate.force)
    override def memoize : Vector[A] = carryMixin(delegate.memoize)
    override def bounds: Vector[A] = carryMixin(delegate.bounds)
    override def readOnly: Vector[A] = carryMixin(delegate.readOnly)
    override def identity: Vector[A] = carryMixin(delegate.identity)
  // mixin
    override def mixin(_mx: Mixin): Vector[A] = unmixin.mixin(mx `with` _mx) // mixin-mixin fusion
    override def unmixin: Vector[A] = v
  // copy
    override def clone: Vector[A] = carryMixin(delegate.clone)
  // parallel support
    override def parallel(_grainSize: Int): Vector[A] = carryMixin(delegate.parallel(_grainSize))
  // associative folding
    override def folder(z: A)(op: (A, A) => A): Vector[A] = carryMixin(delegate.folder(z)(op))
    override def reducer(op: (A, A) => A): Vector[A] = carryMixin(delegate.reducer(op))
}
