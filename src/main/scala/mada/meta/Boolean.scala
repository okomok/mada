

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


// See: http://www.assembla.com/wiki/show/metascala


/**
 * Contains meta Boolean functionalities.
 */
trait Booleans { this: Meta.type =>

    sealed trait Boolean extends Object {
        private[mada] type and[that <: Boolean] <: Boolean
        private[mada] type or[that <: Boolean] <: Boolean
        private[mada] type not <: Boolean
        private[mada] type `if`[then <: Object, else_ <: Object] <: Object

        private[mada] type ifFunction0[then <: Function0, else_ <: Function0] <: Function0
        private[mada] type ifFunction1[then <: Function1, else_ <: Function1] <: Function1
        private[mada] type ifFunction2[then <: Function2, else_ <: Function2] <: Function2
        private[mada] type ifFunction3[then <: Function3, else_ <: Function3] <: Function3

    //    private[mada] type LazyIf[then[Void], else_[Void]] <:
    }

    final class `true` extends Boolean {
        private[mada] override type and[that <: Boolean] = that
        private[mada] override type or[that <: Boolean] = `true`
        private[mada] override type not = `false`
        private[mada] override type `if`[then <: Object, else_ <: Object] = then

        private[mada] override type ifFunction0[then <: Function0, else_ <: Function0] = then
        private[mada] override type ifFunction1[then <: Function1, else_ <: Function1] = then
        private[mada] override type ifFunction2[then <: Function2, else_ <: Function2] = then
        private[mada] override type ifFunction3[then <: Function3, else_ <: Function3] = then

    //    private[mada] override type LazyIf[then[Void], else_[Void]] = then
    }

    final class `false` extends Boolean {
        private[mada] override type and[that <: Boolean] = `false`
        private[mada] override type or[that <: Boolean] = that
        private[mada] override type not = `true`
        private[mada] override type `if`[then <: Object, else_ <: Object] = else_

        private[mada] override type ifFunction0[then <: Function0, else_ <: Function0] = else_
        private[mada] override type ifFunction1[then <: Function1, else_ <: Function1] = else_
        private[mada] override type ifFunction2[then <: Function2, else_ <: Function2] = else_
        private[mada] override type ifFunction3[then <: Function3, else_ <: Function3] = else_


     //   private[mada] override type LazyIf[then[Void], else_[Void]] = else_
    }


    // will be removed.
    type &&[a <: Boolean, b <: Boolean] = a#and[b]
    type ||[a <: Boolean, b <: Boolean] = a#or[b]
    type ![a <: Boolean] = a#not

    type `if`[cond <: Boolean, then <: Object, else_ <: Object] = cond#`if`[then, else_]
  //  type LazyIf[cond <: Boolean, then[Void], else_[Void]] = cond#LazyIf[then, else_]

}
