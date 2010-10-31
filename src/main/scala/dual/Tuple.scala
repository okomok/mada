

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


import tuple._


object Tuple {

    def apply[T1](v1: T1): lift1[T1] = lift1((v1))
    def apply[T1, T2](v1: T1, v2: T2): Tuple2[Box[T1], Box[T2]] = Tuple2(Box(v1), Box(v2))
    def apply[T1, T2, T3](v1: T1, v2: T2, v3: T3): Tuple3[Box[T1], Box[T2], Box[T3]] = Tuple3(Box(v1), Box(v2), Box(v3))

}
