

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package iterativetest


import com.github.okomok.mada

@deprecated("recursive iterative is no longer supported.")
class NotStartable[A] extends mada.sequence.Iterative[A] {
    override def begin = throw new Error("don't begin me now!")
}
