

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


// v.projective(Project.force).identity --> v.force


/**
 * Contains utility methods operating on <code>Project</code>.
 */
object Project {
    /**
     * Calls <code>force</code> method.
     */
    val force: Project = new Project {
        override def apply[B](v: Vector[B]) = v.force
    }

    /**
     * Calls <code>lazyValues</code> method.
     */
    val lazyValues: Project = new Project {
        override def apply[B](v: Vector[B]) = v.lazyValues
    }

    /**
     * Calls <code>parallel</code> method.
     */
    def parallel: Project = new Project {
        override def apply[B](v: Vector[B]) = v.parallel
    }

    /**
     * Calls <code>parallel</code> method.
     */
    def parallel(g: Int): Project = new Project {
        override def apply[B](v: Vector[B]) = v.parallel(g)
    }

    /**
     * Calls <code>readOnly</code> method.
     */
    val readOnly: Project = new Project {
        override def apply[B](v: Vector[B]) = v.readOnly
    }
}

/**
 * Applies <code>apply</code> to result of vector-to-vector methods.
 */
trait Project {
    def apply[B](v: Vector[B]): Vector[B]
}


private[mada] object Projective {
    def apply[A](v: Vector[A], p: Project): Vector[A] = new ProjectiveVector(v, p)
}

private[mada] class ProjectiveVector[A](override val self: Vector[A], p: Project) extends VectorProxy[A] {
  // regions
    override def region(_start: Int, _end: Int): Vector[A] = _projection(underlying.region(_start, _end))
    override def regionBase: Vector[A] = _projection(underlying.regionBase)
    override def init: Vector[A] = _projection(underlying.init)
    override def clear: Vector[A] = _projection(underlying.clear)
    override def window(n: Int, m: Int): Vector[A] = _projection(underlying.window(n, m))
    override def offset(i: Int, j: Int): Vector[A] = _projection(underlying.offset(i, j))
    override def slice(n: Int): Vector[A] = _projection(underlying.slice(n))
    override def slice(n: Int, m: Int): Vector[A] = _projection(underlying.slice(n, m))
    override def drop(n: Int): Vector[A] = _projection(underlying.drop(n))
    override def take(n: Int): Vector[A] = _projection(underlying.take(n))
    override def dropWhile(p: A => Boolean): Vector[A] = _projection(underlying.dropWhile(p))
    override def takeWhile(p: A => Boolean): Vector[A] = _projection(underlying.takeWhile(p))
  // division
    override def divide(n: Int): Vector[Vector[A]] = _projection(underlying.divide(n))
    override def splitAt(i: Int): (Vector[A], Vector[A]) = _projection2(underlying.splitAt(i))
    override def span(p: A => Boolean): (Vector[A], Vector[A]) = _projection2(underlying.span(p))
    override def break(p: A => Boolean): (Vector[A], Vector[A]) = _projection2(underlying.break(p))
  // as list
    override def tail: Vector[A] = _projection(underlying.tail)
  // filter
    override def filter(p: A => Boolean): Vector[A] = _projection(underlying.filter(p))
    override def mutatingFilter(p: A => Boolean): Vector[A] = _projection(underlying.mutatingFilter(p))
    override def remove(p: A => Boolean): Vector[A] = _projection(underlying.remove(p))
    override def mutatingRemove(p: A => Boolean): Vector[A] = _projection(underlying.mutatingRemove(p))
    override def partition(p: A => Boolean): (Vector[A], Vector[A]) = _projection2(underlying.partition(p))
  // map
    override def map[B](f: A => B): Vector[B] = _projection(underlying.map(f))
    override def flatMap[B](f: A => Vector[B]): Vector[B] = _projection(underlying.flatMap(f))
    override def asVectorOf[B]: Vector[B] = _projection(underlying.asVectorOf[B])
  // folding
    override def folderLeft[B](z: B)(op: (B, A) => B): Vector[B] = _projection(underlying.folderLeft(z)(op))
    override def folderRight[B](z: B)(op: (A, B) => B): Vector[B] = _projection(underlying.folderRight(z)(op))
    override def reducerLeft[B >: A](op: (B, A) => B): Vector[B] = _projection(underlying.reducerLeft(op))
    override def reducerRight[B >: A](op: (A, B) => B): Vector[B] = _projection(underlying.reducerRight(op))
  // sort
    override def sortWith(lt: Compare.Type[A]): Vector[A] = _projection(underlying.sortWith(lt))
    override def sort(implicit c: Compare.GetOrdered[A]): Vector[A] = _projection(underlying.sort(c))
  // concatenation
    override def append(that: Vector[A]): Vector[A] = _projection(underlying.append(that))
  // permutation
    override def permutation(f: Int => Int): Vector[A] = _projection(underlying.permutation(f))
    override def cycle(n: Int): Vector[A] = _projection(underlying.cycle(n))
    override def nth: Vector[A] = _projection(underlying.nth)
    override def reverse: Vector[A] = _projection(underlying.reverse)
    override def step(n: Int): Vector[A] = _projection(underlying.step(n))
    override def rotate(i: Int): Vector[A] = _projection(underlying.rotate(i))
  // zip
    override def zip[B](that: Vector[B]): Vector[(A, B)] = _projection(underlying.zip(that))
  // attributes
    override def force: Vector[A] = _projection(underlying.force)
    override def lazyValues : Vector[A] = _projection(underlying.lazyValues)
    override def bounds: Vector[A] = _projection(underlying.bounds)
    override def readOnly: Vector[A] = _projection(underlying.readOnly)
    override def identity: Vector[A] = _projection(underlying.identity)
  // copy
    override def clone: Vector[A] = _projection(underlying.clone)
  // parallel support
    override def parallel(_grainSize: Int): Vector[A] = _projection(underlying.parallel(_grainSize))
    override def unparallel: Vector[A] = _projection(underlying.unparallel)
  // associative folding
    override def folder(z: A)(op: (A, A) => A): Vector[A] = _projection(underlying.folder(z)(op))
    override def reducer(op: (A, A) => A): Vector[A] = _projection(underlying.reducer(op))

    private def _projection[B](v: Vector[B]): Vector[B] = p(v).projective(p)
    private def _projection2[B](vv: (Vector[B], Vector[B])): (Vector[B], Vector[B]) = (p(vv._1).projective(p), p(vv._2).projective(p))
}
