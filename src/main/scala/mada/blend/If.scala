

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.blend


/**
 * Contains eligibles for <code>`if`</code>.
 */
object If {

    implicit def if_true[A] = new If[A, Meta.`true`] {
        override def apply(block: => A) = new Then[A] {
            override def `else`(unused: => A) = block
            override def elseIf[b <: Meta.Boolean](unused: => A)(implicit _if: If[A, b]) = this

        }
    }

    implicit def if_false[A] = new If[A, Meta.`false`] {
        override def apply(unused: => A) = new Then[A] {
            override def `else`(block: => A) = block
            override def elseIf[b <: Meta.Boolean](block: => A)(implicit _if: If[A, b]) = _if(block)
        }
    }

}

/**
 * Intermediate trait for implicit conversions.
 */
sealed trait If[A, b <: Meta.Boolean] {
    def apply(block: => A): Then[A]
}

/**
 * Intermediate trait for if expressions.
 */
sealed trait Then[A] {
    def `else`(block: => A): A
    def elseIf[b <: Meta.Boolean](block: => A)(implicit _if: If[A, b]): Then[A]
}
