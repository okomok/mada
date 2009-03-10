

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


private[mada] object Step {
    def apply[A](it: Iterable[A], n: Int): Iterable[A] = Iterables.makeByName(impl(it.elements, n))

    def impl[A](it: Iterator[A], n: Int): Iterator[A] = {
        if (n < 0) {
            throw new IllegalArgumentException("Iterables.step requires nonnegative stride")
        }

        it match {
    //        case it: StepIterator[_] => Iterables.step(it.it, it.n * n) // step-step fusion
            case _ => {
                if (n == 0) {
                    new NonStepIterator(it)
                } else if (n == 1) {
                    it
                } else {
                    new StepIterator(it, n)
                }
            }
        }
    }
}

private[mada] class StepIterator[A](var it: Iterator[A], val n: Int) extends Iterator[A] {
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
