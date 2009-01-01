

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object ToScannerFunction {
    def apply[A, B](f: Vector[A] => B): ((Scanner[A], Long, Long) => B) = {
        { (s: Scanner[A], i: Long, j: Long) => f(s.window(i, j)) }
    }
}
