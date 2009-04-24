

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * A nullary metamethod is defined by <code>type F[void] = Int</code>
 * instance of <code>type F[] = Int</code>, which is illegal.
 * Note that this is different from the meta Unit.
 */
sealed trait void extends Object
