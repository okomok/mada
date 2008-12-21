

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


class NotReadableError[A](val vector: Vector[A]) extends Error
class NotWritableError[A](val vector: Vector[A]) extends Error
