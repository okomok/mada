

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package iterative


private object Sorted {

    def derefBy[A](it1: Iterator[A], it2: Iterator[A], _3: Ordering[A]): A = {
        if (!it1) {
            ~it2
        } else if (!it2) {
            ~it1
        } else {
            if (_3.lt(~it2, ~it1)) ~it2 else ~it1
        }
    }

    def incrementBy[A](it1: Iterator[A], it2: Iterator[A], _3: Ordering[A]): Unit = {
        if (!it1) {
            it2.++
        } else if (!it2) {
            it1.++
        } else {
            if (_3.lt(~it2, ~it1)) it2.++ else it1.++
        }
    }

}


case class Merge[A](_1: Iterative[A], _2: Iterative[A], _3: Ordering[A]) extends Iterative[A] {
    override def begin = new Iterator[A] {
        private val it1 = _1.begin
        private val it2 = _2.begin

        override def isEnd = !it1 && !it2
        override def deref = Sorted.derefBy(it1, it2, _3)
        override def increment = Sorted.incrementBy(it1, it2, _3)
    }
}


case class Union[A](_1: Iterative[A], _2: Iterative[A], _3: Ordering[A]) extends Iterative[A] {
    override def begin = new Iterator[A] {
        private val it1 = _1.begin
        private val it2 = _2.begin

        override def isEnd = !it1 && !it2
        override def deref = {
            preDeref
            Sorted.derefBy(it1, it2, _3)
        }
        override def increment = {
            preIncrement
            if (!it1) {
                it2.++
            } else if (!it2) {
                it1.++
            } else {
                if (_3.lt(~it2, ~it1)) {
                    it2.++
                } else if (_3.lt(~it1, ~it2)) {
                    it1.++
                } else {
                    it1.++
                    it2.++
                }
            }
        }
    }
}


case class Intersection[A](_1: Iterative[A], _2: Iterative[A], _3: Ordering[A]) extends Iterative[A] {
    override def begin = new Iterator[A] {
        private val it1 = _1.begin
        private val it2 = _2.begin
        ready

        override def isEnd = !it1 || !it2
        override def deref = ~it1
        override def increment = {
            it1.++
            it2.++
            ready
        }

        private def ready: Unit = {
            while (it1 && it2)  {
                if (_3.lt(~it2, ~it1)) {
                    it2.++
                } else if (_3.lt(~it1, ~it2)) {
                    it1.++
                } else {
                    return
                }
            }
        }
    }
}


case class Difference[A](_1: Iterative[A], _2: Iterative[A], _3: Ordering[A]) extends Iterative[A] {
    override def begin = new Iterator[A] {
        private val it1 = _1.begin
        private val it2 = _2.begin
        ready

        override def isEnd = !it1
        override def deref = ~it1
        override def increment = {
            it1.++
            ready
        }

        private def ready: Unit = {
            while (it1 && it2)  {
                if (_3.lt(~it2, ~it1)) {
                    it2.++
                } else if (_3.lt(~it1, ~it2)) {
                    return
                } else {
                    it1.++
                    it2.++
                }
            }
        }
    }
}


case class SymmetricDifference[A](_1: Iterative[A], _2: Iterative[A], _3: Ordering[A]) extends Iterative[A] {
    override def begin = new Iterator[A] {
        private val it1 = _1.begin
        private val it2 = _2.begin
        ready

        override def isEnd = !it1 && !it2
        override def deref = {
            preDeref
            Sorted.derefBy(it1, it2, _3)
        }
        override def increment = {
            preIncrement
            Sorted.incrementBy(it1, it2, _3)
            ready
        }

        private def ready: Unit = {
            while (it1 && it2)  {
                if (_3.lt(~it2, ~it1)) {
                    return
                } else if (_3.lt(~it1, ~it2)) {
                    return
                } else {
                    it1.++
                    it2.++
                }
            }
        }
    }
}
