

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Specifies a type(usually trait) exists only to provide methods and values
 * to mada top-level object. Provider keeps top-level object from being mammoth.
 * User shall not reference provider name.
 */
class provider extends StaticAnnotation
