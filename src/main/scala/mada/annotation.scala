

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * An annotation to declare an aliase of name defined in "private" package as the same name.
 * You must use the alias: for forward compatibility.
 */
class alias extends StaticAnnotation


/**
 * Specifies a type(usually trait) exists only to provide methods and values
 * to mada top-level object. Provider keeps top-level object from being mammoth.
 * User shall not reference provider name.
 */
class provider extends StaticAnnotation


/**
 * Returns <code>this</code>.
 */
class returnthis extends StaticAnnotation


/**
 * Returns the companion module.
 */
class returncompanion extends StaticAnnotation


/**
 * An annotation that designates the definition
 * to which it is used to associate type with value.
 */
class specializer extends StaticAnnotation


