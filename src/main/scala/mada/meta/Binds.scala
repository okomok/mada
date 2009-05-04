

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

    trait Bind0[f <: Function0] extends FunctionV {
        override type isBind = `true`

        override type apply0 = Substitute0[f]#apply0[ void ]
        override type apply1[a1] = Substitute0[f]#apply0[ void ]
        override type apply2[a1, a2] = Substitute0[f]#apply0[ void ]
        override type apply3[a1, a2, a3] = Substitute0[f]#apply0[ void ]
    }

    trait Bind1[T1f <: Function1, b1 <: FunctionV] extends FunctionV {
        override type isBind = `true`

        override type apply0 = Substitute1[f]#apply1#apply1[ Substitute0[b1]#apply0 ]
        override type apply1[a1] = Substitute1[f]#apply1[a1]#apply1[ Substitute1[b1]#apply1[a1] ]
        override type apply2[a1, a2] = Substitute1[f]#apply2[a1, a2]#apply1[ Substitute2[b1]#apply2[a1, a2] ]
        override type apply3[a1, a2, a3] = Substitute1[f]#apply3[a1, a2, a3]#apply1[ Substitute3[b1]#apply3[a1, a2, a3] ]
    }

    trait Bind2[f <: Function2, b1 <: FunctionV, b2 <: FunctionV] extends FunctionV {
        override type isBind = `true`

        override type apply0 = Substitute2[f]#apply0#apply2[ Substitute0[b1]#apply0, Substitute0[b2]#apply0 ]
        override type apply1[a1] = Substitute2[f]#apply1[a1]#apply2[ Substitute1[b1]#apply1[a1], Substitute1[b2]#apply1[a1] ]
        override type apply2[a1, a2] = Substitute2[f]#apply2[a1, a2]#apply2[ Substitute2[b1]#apply2[a1, a2], Substitute2[b2]#apply2[a1, a2] ]
        override type apply3[a1, a2, a3] = Substitute2[f]#apply3[a1, a2, a3]#apply2[ Substitute3[b1]#apply3[a1, a2, a3], Substitute3[b2]#apply3[a1, a2, a3] ]
     }

    trait Bind3[T1, T2, T3, R, f <: Function3[_ <: T1, _ <: T2, _ <: T3, _ <: R], b1 <: T1, b2 <: T3, b3 <: T3] extends FunctionV {
        override type isBind = `true`

        override type apply0 = Substitute3[f]#apply0#apply3[ Substitute0[b1]#apply0, Substitute0[b2]#apply0, Substitute0[b3]#apply0 ]
        override type apply1[a1] = Substitute3[f]#apply1[a1]#apply3[ Substitute1[b1]#apply1[a1], Substitute1[b2]#apply1[a1], Substitute1[b3]#apply1[a1] ]
        override type apply2[a1, a2] = Substitute3[f]#apply2[a1, a2]#apply3[ Substitute2[b1]#apply2[a1, a2], Substitute2[b2]#apply2[a1, a2], Substitute2[b3]#apply2[a1, a2] ]
        override type apply3[a1, a2, a3] = Substitute3[f]#apply3[a1, a2, a3]#apply3[ Substitute3[b1]#apply3[a1, a2, a3], Substitute3[b2]#apply3[a1, a2, a3], Substitute3[b3]#apply3[a1, a2, a3] ]
    }
*/
}
