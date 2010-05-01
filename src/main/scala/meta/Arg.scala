

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package meta


/**
 * Returns the n-th argument.
 */
sealed trait arg1 {
    type apply1[v1] = v1
    type apply2[v1, v2] = v1
    type apply3[v1, v2, v3] = v1
}

sealed trait arg2 {
    type apply2[v1, v2] = v2
    type apply3[v1, v2, v3] = v2
}

sealed trait arg3 {
    type apply3[v1, v2, v3] = v3
}
