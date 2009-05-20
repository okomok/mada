

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * An alias of a name
 */
class aliasOf(name: String) extends StaticAnnotation


/**
 * Contains implicit conversions. (All the names must be "long".)
 */
class compatibles extends StaticAnnotation


/**
 * An explicit conversion
 */
class conversion extends StaticAnnotation

/**
 * A lightweight conversion
 */
class compatibleConversion extends StaticAnnotation


/**
 * Implementation detail. Don't use the name.
 */
class detail extends StaticAnnotation


/**
 * Provides pseudo methods to work around type constraints.
 */
class methodized extends StaticAnnotation


/**
 * Not thread-safe.
 */
class notThreadSafe extends StaticAnnotation


/**
 * Overrides only for optimization.
 */
class optimize extends StaticAnnotation


/**
 * Not override but overload.
 */
class overload extends StaticAnnotation


/**
 * Returns <code>this</code>.
 */
class returnThis extends StaticAnnotation

/**
 * Returns the passed argument as is. (Useful to trigger implicit conversion "explicitly".)
 */
class returnThat extends StaticAnnotation


/**
 * An annotation that designates the definition
 * to which it is used to associate type with value.
 */
class specializer extends StaticAnnotation


/**
 * An annotation that designates the definition
 * to which it is used to work around a compiler bug.
 */
class compilerWorkaround extends StaticAnnotation

/**
 * In package object, overload resolution is broken in 2.8.
 */
class packageObjectBrokenOverload extends compilerWorkaround
