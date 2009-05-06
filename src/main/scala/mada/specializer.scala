

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * An annotation that designates the definition
 * to which it is used to associate type with value.
 */
class specializer extends StaticAnnotation


/*
class Specializer1ByName[T1, R](f: T1 => R) {
    def apply(body: => T1): R
}

class Specializer1[T1, R](f: T1 => R) extends Function1[T1, R] {
    override def apply(v1: T1) = f(v1)
}
*/
