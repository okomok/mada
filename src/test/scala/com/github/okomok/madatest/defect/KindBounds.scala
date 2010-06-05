

package com.github.okomok.madatest; package defecttest; package kindbounds


object MyTezt {

    trait A

    trait B
    trait D extends B

    trait X[T] {
        type K[_ <: T] = D
    }

    type foo[e <: { type K[_ <: A] }] = Int
    type ok = foo[X[Int]] // wrongly? compiles.

    type bar[e <: { type K[_ <: A] <: B }] = Int
    //type no = bar[X[A]] // doesn't compile.

}


object MyCompile2 {

    trait A

    trait B
    trait D extends B

    trait X[T] {
        type K[_] = D
    }

    type foo[e <: { type K[_ <: A] }] = Int
    type ok = foo[X[Int]] // wrongly? compiles.

    type bar[e <: { type K[_ <: A] <: B }] = Int
    type ok2 = bar[X[A]]

}
