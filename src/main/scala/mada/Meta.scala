

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import meta._


object Meta {


// metafunctions

    /**
     * Alias of <code>Func0</code>
     */
    type Func = Func0

    /**
     * Nullary metafunction
     */
    trait Func0 {
        type Type
    }

    /**
     * Unary metafunction
     */
    trait Func1[t1] {
        type Type
    }

    /**
     * Binary metafunction
     */
    trait Func2[t1, t2] {
        type Type
    }

    /**
     * Metafunction Class
     */
    trait FuncClass {
        type Apply <: Func
    }

    /**
     * Identity metafunction
     */
    trait Identity[a] extends Func0 {
        type Type = a
    }


// predicates

    /**
     * Nullary meta-predicate
     */
    trait Predicate extends Func {
        type Type <: Bool
    }

    /**
     * Tests type equality. (probably infeasible.)
     */
    type Equals[a, b] = Nothing // How?


// bool

    /**
     * Compile-time logical-and
     */
    type &&[a <: Bool, b <: Bool] = a#And[b]

    /**
     * Compile-time logical-or
     */
    type ||[a <: Bool, b <: Bool] = a#Or[b]

    /**
     * Compile-time logical-not
     */
    type ![a <: Bool] = a#Not

    /**
     * Compile-time if
     */
    type If[cond <: Bool, then, else_] = cond#If[then, else_]

    /**
     * Compile-time if by lazy
     */
    type LazyIf[cond <: Bool, then <: Func, else_ <: Func] = cond#LazyIf[then, else_]


// assertions

    /**
     * Compile-time assertion
     */
    case class Assert[a >: True <: True]()

    /**
     * Compile-time assertion of type equality
     */
    case class AssertEquals[a >: b <: b, b]()


// misc

    /**
     * @return  <code>null.asInstanceOf[a]</code>.
     */
    def nullOf[a]: a = null.asInstanceOf[a]


// aliases

    /**
     * Alias of <code>meta.Bool</code>
     */
    type Bool = meta.Bool

    /**
     * Alias of <code>meta.True</code>
     */
    type True = meta.True

    /**
     * Alias of <code>meta.False</code>
     */
    type False = meta.False

    /**
     * @return  <code>new True</code>.
     */
    val True = new True

    /**
     * @return  <code>new False</code>.
     */
    val False = new False



//    implicit val falseToBoolean = TypeToValue[False, Boolean](false)
//    implicit val trueToBoolean = TypeToValue[True, Boolean](true)

}






















