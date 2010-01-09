

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package meta


/**
 * Returns metamethod to always return <code>a</code>.
 */
sealed trait always[a] {
    type apply0 = a
    type apply1[v1] = a
    type apply2[v1, v2] = a
    type apply3[v1, v2, v3] = a
}
