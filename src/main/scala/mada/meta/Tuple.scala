

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package meta


sealed trait tuple1[v1] extends Product1 {
    override type _1 = v1
}

sealed trait tuple2[v1, v2] extends Product2 {
    override type _1 = v1
    override type _2 = v2
}

sealed trait tuple3[v1, v2, v3] extends Product3 {
    override type _1 = v1
    override type _2 = v2
    override type _3 = v3
}
