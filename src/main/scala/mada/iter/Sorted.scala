

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


/**
 * Contains utility methods operating on already-sorted <code>Iterable</code>.
 */
object Sorted {
    import Compare.Predicate


    private def derefBy[A](pt1: Pointer[A], pt2: Pointer[A], lt: Predicate[A]): A = {
        if (!pt1) {
            ~pt2
        } else if (!pt2) {
            ~pt1
        } else {
            if (lt(~pt2, ~pt1)) ~pt2 else ~pt1
        }
    }

    private def incrementBy[A](pt1: Pointer[A], pt2: Pointer[A], lt: Predicate[A]): Unit = {
        if (!pt1) {
            pt2.++
        } else if (!pt2) {
            pt1.++
        } else {
            if (lt(~pt2, ~pt1)) pt2.++ else pt1.++
        }
    }


// merge

    def merge[A](it1: Iterable[A], it2: Iterable[A])(implicit c: Compare[A]): Iterable[A] = Merge(it1, it2, c)
    def mergeBy[A](it1: Iterable[A], it2: Iterable[A])(lt: Predicate[A]): Iterable[A] = Merge(it1, it2, lt)

    private object Merge {
        def apply[A](it1: Iterable[A], it2: Iterable[A], lt: Predicate[A]): Iterable[A] = Iterables.byName(iimpl(it1.elements, it2.elements, lt))
        def iimpl[A](it1: Iterator[A], it2: Iterator[A], lt: Predicate[A]): Iterator[A] = new MergePointer(it1, it2, lt)
    }

    private class MergePointer[A](pt1: Pointer[A], pt2: Pointer[A], lt: Predicate[A]) extends Pointer[A] {
        override def isEnd = !pt1 && !pt2
        override def deref = derefBy(pt1, pt2, lt)
        override def increment = incrementBy(pt1, pt2, lt)
    }


// union

    def union[A](it1: Iterable[A], it2: Iterable[A])(implicit c: Compare[A]): Iterable[A] = Union(it1, it2, c)
    def unionBy[A](it1: Iterable[A], it2: Iterable[A])(lt: Predicate[A]): Iterable[A] = Union(it1, it2, lt)

    private object Union {
        def apply[A](it1: Iterable[A], it2: Iterable[A], lt: Predicate[A]): Iterable[A] = Iterables.byName(iimpl(it1.elements, it2.elements, lt))
        def iimpl[A](it1: Iterator[A], it2: Iterator[A], lt: Predicate[A]): Iterator[A] = new UnionPointer(it1, it2, lt)
    }

    private class UnionPointer[A](pt1: Pointer[A], pt2: Pointer[A], lt: Predicate[A]) extends Pointer[A] {
        override def isEnd = !pt1 && !pt2
        override def deref = derefBy(pt1, pt2, lt)
        override def increment = {
            if (!pt1) {
                pt2.++
            } else if (!pt2) {
                pt1.++
            } else {
                if (lt(~pt2, ~pt1)) {
                    pt2.++
                } else if (lt(~pt1, ~pt2)) {
                    pt1.++
                } else {
                    pt1.++
                    pt2.++
                }
            }
        }
    }


// intersection

    def intersection[A](it1: Iterable[A], it2: Iterable[A])(implicit c: Compare[A]): Iterable[A] = Intersection(it1, it2, c)
    def intersectionBy[A](it1: Iterable[A], it2: Iterable[A])(lt: Predicate[A]): Iterable[A] = Intersection(it1, it2, lt)

    private object Intersection {
        def apply[A](it1: Iterable[A], it2: Iterable[A], lt: Predicate[A]): Iterable[A] = Iterables.byName(iimpl(it1.elements, it2.elements, lt))
        def iimpl[A](it1: Iterator[A], it2: Iterator[A], lt: Predicate[A]): Iterator[A] = new IntersectionPointer(it1, it2, lt)
    }

    private class IntersectionPointer[A](pt1: Pointer[A], pt2: Pointer[A], lt: Predicate[A]) extends Pointer[A] {
        ready

        override def isEnd = !pt1 || !pt2
        override def deref = ~pt1
        override def increment = { pt1.++; pt2.++; ready }

        private def ready: Unit = {
            while (!pt1.isEnd && !pt2.isEnd)  {
                if (lt(~pt2, ~pt1)) {
                    pt2.++
                } else if (lt(~pt1, ~pt2)) {
                    pt1.++
                } else {
                    return
                }
            }
        }
    }


// difference

    def difference[A](it1: Iterable[A], it2: Iterable[A])(implicit c: Compare[A]): Iterable[A] = Difference(it1, it2, c)
    def differenceBy[A](it1: Iterable[A], it2: Iterable[A])(lt: Predicate[A]): Iterable[A] = Difference(it1, it2, lt)

    private object Difference {
        def apply[A](it1: Iterable[A], it2: Iterable[A], lt: Predicate[A]): Iterable[A] = Iterables.byName(iimpl(it1.elements, it2.elements, lt))
        def iimpl[A](it1: Iterator[A], it2: Iterator[A], lt: Predicate[A]): Iterator[A] = new DifferencePointer(it1, it2, lt)
    }

    private class DifferencePointer[A](pt1: Pointer[A], pt2: Pointer[A], lt: Predicate[A]) extends Pointer[A] {
        ready

        override def isEnd = !pt1
        override def deref = ~pt1
        override def increment = { pt1.++; ready }

        private def ready: Unit = {
            while (!pt1.isEnd && !pt2.isEnd)  {
                if (lt(~pt2, ~pt1)) {
                    pt2.++
                } else if (lt(~pt1, ~pt2)) {
                    return
                } else {
                    pt1.++
                    pt2.++
                }
            }
        }
    }


// symmetricDifference

    def symmetricDifference[A](it1: Iterable[A], it2: Iterable[A])(implicit c: Compare[A]): Iterable[A] = SymmetricDifference(it1, it2, c)
    def symmetricDifferenceBy[A](it1: Iterable[A], it2: Iterable[A])(lt: Predicate[A]): Iterable[A] = SymmetricDifference(it1, it2, lt)

    private object SymmetricDifference {
        def apply[A](it1: Iterable[A], it2: Iterable[A], lt: Predicate[A]): Iterable[A] = Iterables.byName(iimpl(it1.elements, it2.elements, lt))
        def iimpl[A](it1: Iterator[A], it2: Iterator[A], lt: Predicate[A]): Iterator[A] = new SymmetricDifferencePointer(it1, it2, lt)
    }

    private class SymmetricDifferencePointer[A](pt1: Pointer[A], pt2: Pointer[A], lt: Predicate[A]) extends Pointer[A] {
        ready

        override def isEnd = !pt1 && !pt2
        override def deref = derefBy(pt1, pt2, lt)
        override def increment = { incrementBy(pt1, pt2, lt); ready }

        private def ready: Unit = {
            while (!pt1.isEnd && !pt2.isEnd)  {
                if (lt(~pt2, ~pt1)) {
                    return
                } else if (lt(~pt1, ~pt2)) {
                    return
                } else {
                    pt1.++
                    pt2.++
                }
            }
        }
    }
}
