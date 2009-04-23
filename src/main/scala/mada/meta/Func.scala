

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Contains metafunction traits. (corresponding to MetafunctionClass of Boost)
 */
trait Funcs { this: Meta.type =>

    trait Func0 extends Obj {
        type Apply0[Void] <: Obj
    }

    trait Func1 extends Obj {
        type Apply1[a1 <: Obj] <: Obj
    }

    trait Func2 extends Obj {
        type Apply2[a1 <: Obj, a2 <: Obj] <: Obj
    }

    trait Func3 extends Obj {
        type Apply3[a1 <: Obj, a2 <: Obj, a3 <: Obj] <: Obj
    }

    /**
     * Takes variable arguments.
     */
    trait VarargFunc extends Func0 with Func1 with Func2 with Func3

}
