

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.blend


/**
 * Intermediate trait for implicit conversions.
 */
sealed trait If[A, b <: Meta.Boolean] {
    private[mada] def block: A
}

/**
 * Contains implicit conversions for <code>`if`</code>.
 */
trait IfImplicits { this: Blend.type =>

    final class MadaBlendTrueThen[A](block: => A) {
        def `else`(unused: => A) = block
        def elseIf[b <: Meta.Boolean](unused: => A): MadaBlendTrueThen[A] = this
    }

    final class MadaBlendFalseThen[A] {
        def `else`(block: => A) = block
        def elseIf[b <: Meta.Boolean](block: => A): If[A, b] = If.apply[A, b](block)
    }

    implicit def madaBlendIfTrue[A](t: If[A, Meta.`true`]): MadaBlendTrueThen[A] = new MadaBlendTrueThen[A](t.block)
    implicit def madaBlendIfFalse[A](f: If[A, Meta.`false`]): MadaBlendFalseThen[A] = new MadaBlendFalseThen[A]

}

private[mada] object If {
    def apply[A, b <: Meta.Boolean](_block: => A): If[A, b] = new If[A, b] {
        private[mada] override def block = _block
    }
}
