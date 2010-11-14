

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterative


private
object Sorted {

    def derefBy[A](it1: Iterator[A], it2: Iterator[A], _3: Ordering[A]): A = {
        if (!it1) {
            ~it2
        } else if (!it2) {
            ~it1
        } else {
            if (_3.lt(~it2, ~it1)) ~it2 else ~it1
        }
    }

    def incrementBy[A](it1: Iterator[A], it2: Iterator[A], _3: Ordering[A]) {
        if (!it1) {
            it2.++
        } else if (!it2) {
            it1.++
        } else {
            if (_3.lt(~it2, ~it1)) it2.++ else it1.++
        }
    }

}


private
case class Merge[A](_1: Iterative[A], _2: Iterative[A], _3: Ordering[A]) extends Iterative[A] {
    override def begin = new Iterator[A] {
        private[this] val it1 = _1.begin
        private[this] val it2 = _2.begin

        override def isEnd = !it1 && !it2
        override def deref = Sorted.derefBy(it1, it2, _3)
        override def increment() = Sorted.incrementBy(it1, it2, _3)
    }
}


private
case class Union[A](_1: Iterative[A], _2: Iterative[A], _3: Ordering[A]) extends Iterative[A] {
    override def begin = new _Iterator[A] {
        private[this] val it1 = _1.begin
        private[this] val it2 = _2.begin

        override protected def _isEnd = !it1 && !it2
        override protected def _deref = Sorted.derefBy(it1, it2, _3)
        override protected def _increment() {
            if (!it1) {
                it2.++
            } else if (!it2) {
                it1.++
            } else {
                val way = _3.compare(~it2, ~it1)
                if (way < 0) {
                    it2.++
                } else if (way > 0) {
                    it1.++
                } else {
                    it1.++
                    it2.++
                }
            }
        }
    }
}


private
case class Intersection[A](_1: Iterative[A], _2: Iterative[A], _3: Ordering[A]) extends Iterative[A] {
    override def begin = new Iterator[A] {
        private[this] val it1 = _1.begin
        private[this] val it2 = _2.begin
        ready()

        override def isEnd = !it1 || !it2
        override def deref = ~it1
        override def increment() {
            it1.++
            it2.++
            ready()
        }

        private def ready() {
            while (it1 && it2)  {
                val way = _3.compare(~it2, ~it1)
                if (way < 0) {
                    it2.++
                } else if (way > 0) {
                    it1.++
                } else {
                    return
                }
            }
        }
    }
}


private
case class Difference[A](_1: Iterative[A], _2: Iterative[A], _3: Ordering[A]) extends Iterative[A] {
    override def begin = new Iterator[A] {
        private[this] val it1 = _1.begin
        private[this] val it2 = _2.begin
        ready()

        override def isEnd = !it1
        override def deref = ~it1
        override def increment() {
            it1.++
            ready()
        }

        private def ready() {
            while (it1 && it2)  {
                val way = _3.compare(~it2, ~it1)
                if (way < 0) {
                    it2.++
                } else if (way > 0) {
                    return
                } else {
                    it1.++
                    it2.++
                }
            }
        }
    }
}


private
case class SymmetricDifference[A](_1: Iterative[A], _2: Iterative[A], _3: Ordering[A]) extends Iterative[A] {
    override def begin = new _Iterator[A] {
        private[this] val it1 = _1.begin
        private[this] val it2 = _2.begin
        ready()

        override protected def _isEnd = !it1 && !it2
        override protected def _deref = Sorted.derefBy(it1, it2, _3)
        override protected def _increment() {
            Sorted.incrementBy(it1, it2, _3)
            ready()
        }

        private def ready() {
            while (it1 && it2)  {
                val way = _3.compare(~it2, ~it1)
                if (way < 0) {
                    return
                } else if (way > 0) {
                    return
                } else {
                    it1.++
                    it2.++
                }
            }
        }
    }
}
