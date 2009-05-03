

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Customization point for type-to-value specialization
 */
case class Unmeta[From <: Object, To](value: To)
