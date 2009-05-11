

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains environment constants of mada.
 */
object Env {

    /**
     * Is mada in debug mode?
     */
    val isDebug = meta.unmeta[isDebug, Boolean]

    /**
     * Is mada in debug mode?
     */
    type isDebug = meta.`true`

}
