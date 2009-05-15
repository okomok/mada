

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.traversabletest


class NotStartable[A] extends mada.Traversable[A] {
    override def start = throw new Error("don't start me now!")
}
