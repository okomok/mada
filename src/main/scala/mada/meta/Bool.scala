

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


// See: http://www.assembla.com/wiki/show/metascala


/**
 * Contains meta Boolean functionalities.
 */
trait Bools { this: Meta.type =>

    sealed trait Bool extends Obj {
        private[mada] type And[that <: Bool] <: Bool
        private[mada] type Or[that <: Bool] <: Bool
        private[mada] type Not <: Bool
        private[mada] type If[then <: Obj, else_ <: Obj] <: Obj

        private[mada] type IfFunc0[then <: Func0, else_ <: Func0] <: Func0
        private[mada] type IfFunc1[then <: Func1, else_ <: Func1] <: Func1
        private[mada] type IfFunc2[then <: Func2, else_ <: Func2] <: Func2
        private[mada] type IfFunc3[then <: Func3, else_ <: Func3] <: Func3

    //    private[mada] type LazyIf[then[Void], else_[Void]] <:
    }

    final class True extends Bool {
        private[mada] override type And[that <: Bool] = that
        private[mada] override type Or[that <: Bool] = True
        private[mada] override type Not = False
        private[mada] override type If[then <: Obj, else_ <: Obj] = then

        private[mada] override type IfFunc0[then <: Func0, else_ <: Func0] = then
        private[mada] override type IfFunc1[then <: Func1, else_ <: Func1] = then
        private[mada] override type IfFunc2[then <: Func2, else_ <: Func2] = then
        private[mada] override type IfFunc3[then <: Func3, else_ <: Func3] = then

    //    private[mada] override type LazyIf[then[Void], else_[Void]] = then
    }

    final class False extends Bool {
        private[mada] override type And[that <: Bool] = False
        private[mada] override type Or[that <: Bool] = that
        private[mada] override type Not = True
        private[mada] override type If[then <: Obj, else_ <: Obj] = else_

        private[mada] override type IfFunc0[then <: Func0, else_ <: Func0] = else_
        private[mada] override type IfFunc1[then <: Func1, else_ <: Func1] = else_
        private[mada] override type IfFunc2[then <: Func2, else_ <: Func2] = else_
        private[mada] override type IfFunc3[then <: Func3, else_ <: Func3] = else_


     //   private[mada] override type LazyIf[then[Void], else_[Void]] = else_
    }


    type &&[a <: Bool, b <: Bool] = a#And[b]
    type ||[a <: Bool, b <: Bool] = a#Or[b]
    type ![a <: Bool] = a#Not
    type If[cond <: Bool, then <: Obj, else_ <: Obj] = cond#If[then, else_]
  //  type LazyIf[cond <: Bool, then[Void], else_[Void]] = cond#LazyIf[then, else_]

}
