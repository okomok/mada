

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Binds metafunction arguments.
 */
trait Binds { this: Meta.type =>
/*
    private type Substitute0[f <: Func0] = f#IsBind#IfFunc0[f, Always[f]]
    private type Substitute1[f <: Func1] = f#IsBind#IfFunc1[f, Always[f]]
    private type Substitute2[f <: Func2] = f#IsBind#IfFunc2[f, Always[f]]
    private type Substitute3[f <: Func3] = f#IsBind#IfFunc3[f, Always[f]]

    final class Bind0[f <: Func0] extends VarargFunc {
        override type IsBind = True

        override type Apply0[Void] = Substitute0[f]#Apply0[ Void ]
        override type Apply1[a1 <: Obj] = Substitute0[f]#Apply0[ Void ]
        override type Apply2[a1 <: Obj, a2 <: Obj] = Substitute0[f]#Apply0[ Void ]
        override type Apply3[a1 <: Obj, a2 <: Obj, a3 <: Obj] = Substitute0[f]#Apply0[ Void ]
    }

    final class Bind1[f <: Func1, b1 <: VarargFunc] extends VarargFunc {
        override type IsBind = True

        override type Apply0[Void] = Substitute1[f]#Apply1[Void]#Apply1[ Substitute0[b1]#Apply0[Void] ]
        override type Apply1[a1 <: Obj] = Substitute1[f]#Apply1[a1]#Apply1[ Substitute1[b1]#Apply1[a1] ]
        override type Apply2[a1 <: Obj, a2 <: Obj] = Substitute1[f]#Apply2[a1, a2]#Apply1[ Substitute2[b1]#Apply2[a1, a2] ]
        override type Apply3[a1 <: Obj, a2 <: Obj, a3 <: Obj] = Substitute1[f]#Apply3[a1, a2, a3]#Apply1[ Substitute3[b1]#Apply3[a1, a2, a3] ]
    }

    final class Bind2[f <: Func2, b1 <: VarargFunc, b2 <: VarargFunc] extends VarargFunc {
        override type IsBind = True

        override type Apply0[Void] = Substitute2[f]#Apply0[Void]#Apply2[ Substitute0[b1]#Apply0[Void], Substitute0[b2]#Apply0[Void] ]
        override type Apply1[a1 <: Obj] = Substitute2[f]#Apply1[a1]#Apply2[ Substitute1[b1]#Apply1[a1], Substitute1[b2]#Apply1[a1] ]
        override type Apply2[a1 <: Obj, a2 <: Obj] = Substitute2[f]#Apply2[a1, a2]#Apply2[ Substitute2[b1]#Apply2[a1, a2], Substitute2[b2]#Apply2[a1, a2] ]
        override type Apply3[a1 <: Obj, a2 <: Obj, a3 <: Obj] = Substitute2[f]#Apply3[a1, a2, a3]#Apply2[ Substitute3[b1]#Apply3[a1, a2, a3], Substitute3[b2]#Apply3[a1, a2, a3] ]
     }

    final class Bind3[f <: Func3, b1 <: VarargFunc, b2 <: VarargFunc, b3 <: VarargFunc] extends VarargFunc {
        override type IsBind = True

        override type Apply0[Void] = Substitute3[f]#Apply0[Void]#Apply3[ Substitute0[b1]#Apply0[Void], Substitute0[b2]#Apply0[Void], Substitute0[b3]#Apply0[Void] ]
        override type Apply1[a1 <: Obj] = Substitute3[f]#Apply1[a1]#Apply3[ Substitute1[b1]#Apply1[a1], Substitute1[b2]#Apply1[a1], Substitute1[b3]#Apply1[a1] ]
        override type Apply2[a1 <: Obj, a2 <: Obj] = Substitute3[f]#Apply2[a1, a2]#Apply3[ Substitute2[b1]#Apply2[a1, a2], Substitute2[b2]#Apply2[a1, a2], Substitute2[b3]#Apply2[a1, a2] ]
        override type Apply3[a1 <: Obj, a2 <: Obj, a3 <: Obj] = Substitute3[f]#Apply3[a1, a2, a3]#Apply3[ Substitute3[b1]#Apply3[a1, a2, a3], Substitute3[b2]#Apply3[a1, a2, a3], Substitute3[b3]#Apply3[a1, a2, a3] ]
    }
*/
}
