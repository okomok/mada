

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package reactivetest


class Call[A](f: => A) {
    private var i = 0
    def apply(): Unit = {
        i += 1
        f
    }

    def count: Int = i
    def isCalled: Boolean = i >= 0
}
