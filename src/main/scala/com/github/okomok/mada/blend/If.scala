

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package blend


@specializer
sealed abstract class If[A, b <: meta.Boolean] {
    def apply(block: => A): Then[A]
}

/**
 * Intermediate trait for if expressions.
 */
sealed abstract class Then[A] {
    def `else`(block: => A): A
    def elseIf[b <: meta.Boolean](block: => A)(implicit _if: If[A, b]): Then[A]
}


object If {

    implicit def _ofTrue[A] = new If[A, meta.`true`] {
        override def apply(block: => A) = new Then[A] {
            override def `else`(unused: => A) = block
            override def elseIf[b <: meta.Boolean](unused: => A)(implicit _if: If[A, b]) = this

        }
    }

    implicit def _ofFalse[A] = new If[A, meta.`false`] {
        override def apply(unused: => A) = new Then[A] {
            override def `else`(block: => A) = block
            override def elseIf[b <: meta.Boolean](block: => A)(implicit _if: If[A, b]) = _if(block)
        }
    }

}
