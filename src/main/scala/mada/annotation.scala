

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * An annotation to declare an aliase of name defined in "private" package as the same name.
 * You must use the alias: for forward compatibility.
 */
class alias extends StaticAnnotation

/**
 * An alias of a name
 */
class aliasOf(name: String) extends StaticAnnotation


/**
 * A companion module
 */
class companionModule extends StaticAnnotation

/**
 * Returns the companion module.
 */
class returncompanion extends StaticAnnotation


/**
 * Contains implicit conversions. (All the names must be "long".)
 */
class compatibles extends StaticAnnotation

/**
 * An explicit conversion
 */
class conversion extends StaticAnnotation

/**
 * Contains eligibles.
 */
class eligibles extends StaticAnnotation


/**
 * Implementation detail. Don't use the name.
 */
class detail extends StaticAnnotation


/**
 * Forwards all its method calls to another object.
 */
class forwarder extends StaticAnnotation


/**
 * Provides pseudo methods to work around type constraints.
 */
class methodization extends StaticAnnotation


/**
 * Not thread-safe.
 */
class notThreadSafe extends StaticAnnotation


/**
 * Specifies a type(usually trait) exists only to provide methods and values
 * to mada top-level object. Provider keeps top-level object from being mammoth.
 * User shall not reference provider name.
 */
class provider extends StaticAnnotation


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
