

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


/**
 * Don't call type constructor directly.
 */
class typeInstantiationErrorWorkaround extends StaticAnnotation


/**
 * If scalac wrongly says type-mismatch to `Nothing`, change metatype to
 * a super one (e.g. `Any`), then call `asXXX`.
 * In general, this is needed when you call a method parameter's method in turn.
 */
class nothingTypeMismatchWorkaround extends StaticAnnotation
