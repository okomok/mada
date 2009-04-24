

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Binds metafunction arguments.
 */
trait Binds { this: Meta.type =>
/*
    private type Substitute0[f <: Function0] = f#isBind#IfFunction0[f, Always[f]]
    private type Substitute1[f <: Function1] = f#isBind#IfFunction1[f, Always[f]]
    private type Substitute2[f <: Function2] = f#isBind#IfFunction2[f, Always[f]]
    private type Substitute3[f <: Function3] = f#isBind#IfFunction3[f, Always[f]]

    final class Bind0[f <: Function0] extends FunctionV {
        override type isBind = `true`

        override type apply0[Void] = Substitute0[f]#apply0[ Void ]
        override type apply1[a1 <: Object] = Substitute0[f]#apply0[ Void ]
        override type apply2[a1 <: Object, a2 <: Object] = Substitute0[f]#apply0[ Void ]
        override type apply3[a1 <: Object, a2 <: Object, a3 <: Object] = Substitute0[f]#apply0[ Void ]
    }

    final class Bind1[f <: Function1, b1 <: FunctionV] extends FunctionV {
        override type isBind = `true`

        override type apply0[Void] = Substitute1[f]#apply1[Void]#apply1[ Substitute0[b1]#apply0[Void] ]
        override type apply1[a1 <: Object] = Substitute1[f]#apply1[a1]#apply1[ Substitute1[b1]#apply1[a1] ]
        override type apply2[a1 <: Object, a2 <: Object] = Substitute1[f]#apply2[a1, a2]#apply1[ Substitute2[b1]#apply2[a1, a2] ]
        override type apply3[a1 <: Object, a2 <: Object, a3 <: Object] = Substitute1[f]#apply3[a1, a2, a3]#apply1[ Substitute3[b1]#apply3[a1, a2, a3] ]
    }

    final class Bind2[f <: Function2, b1 <: FunctionV, b2 <: FunctionV] extends FunctionV {
        override type isBind = `true`

        override type apply0[Void] = Substitute2[f]#apply0[Void]#apply2[ Substitute0[b1]#apply0[Void], Substitute0[b2]#apply0[Void] ]
        override type apply1[a1 <: Object] = Substitute2[f]#apply1[a1]#apply2[ Substitute1[b1]#apply1[a1], Substitute1[b2]#apply1[a1] ]
        override type apply2[a1 <: Object, a2 <: Object] = Substitute2[f]#apply2[a1, a2]#apply2[ Substitute2[b1]#apply2[a1, a2], Substitute2[b2]#apply2[a1, a2] ]
        override type apply3[a1 <: Object, a2 <: Object, a3 <: Object] = Substitute2[f]#apply3[a1, a2, a3]#apply2[ Substitute3[b1]#apply3[a1, a2, a3], Substitute3[b2]#apply3[a1, a2, a3] ]
     }

    final class Bind3[f <: Function3, b1 <: FunctionV, b2 <: FunctionV, b3 <: FunctionV] extends FunctionV {
        override type isBind = `true`

        override type apply0[Void] = Substitute3[f]#apply0[Void]#apply3[ Substitute0[b1]#apply0[Void], Substitute0[b2]#apply0[Void], Substitute0[b3]#apply0[Void] ]
        override type apply1[a1 <: Object] = Substitute3[f]#apply1[a1]#apply3[ Substitute1[b1]#apply1[a1], Substitute1[b2]#apply1[a1], Substitute1[b3]#apply1[a1] ]
        override type apply2[a1 <: Object, a2 <: Object] = Substitute3[f]#apply2[a1, a2]#apply3[ Substitute2[b1]#apply2[a1, a2], Substitute2[b2]#apply2[a1, a2], Substitute2[b3]#apply2[a1, a2] ]
        override type apply3[a1 <: Object, a2 <: Object, a3 <: Object] = Substitute3[f]#apply3[a1, a2, a3]#apply3[ Substitute3[b1]#apply3[a1, a2, a3], Substitute3[b2]#apply3[a1, a2, a3], Substitute3[b3]#apply3[a1, a2, a3] ]
    }
*/
}
