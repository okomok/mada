

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest


class NotStartable[A] extends mada.Sequence[A] {
    override def begin = throw new Error("don't begin me now!")
}
