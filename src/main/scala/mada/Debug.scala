

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains debugging properties.
 */
object Debug {

    val isDebug = new isDebug{}.unmeta

    type isDebug = Meta.`true`

}
