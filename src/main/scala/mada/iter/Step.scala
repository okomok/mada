

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


private[mada] object Step {
    def apply[A](it: Iterable[A], n: Int): Iterable[A] = {
        if (n < 0) {
            throw new IllegalArgumentException("Iterables.step requires nonnegative stride")
        }

        it match {
            case it: StepIterable[_] => Iterables.step(it.it, it.n * n) // step-step fusion
            case _ => new StepIterable(it, n)
        }
    }
}


private[mada] class StepIterable[A](val it: Iterable[A], val n: Int) extends Iterable.Projection[A] {
    override def elements = {
        if (n == 0) {
            new NonStepIterator(it.elements)
        } else if (n == 1) {
            it.elements
        } else {
            new StepIterator(it.elements, n)
        }
    }
}


private[mada] class StepIterator[A](private var it: Iterator[A], n: Int) extends Iterator[A] {
    Assert(n >= 2)

    override def hasNext = it.hasNext
    override def next = {
        val tmp = it.next
        it = it.drop(n - 1)
        tmp
    }
}

private[mada] class NonStepIterator[A](it: Iterator[A]) extends Iterator[A] {
    private var e = if (it.hasNext) Some(it.next) else None
    override def hasNext = !e.isEmpty
    override def next = e.get
}
