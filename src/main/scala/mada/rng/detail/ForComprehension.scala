

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng.detail


import AsRngBy._
import Filter._
import Flatten._
import Foreach._
import Map._
import Traversal._


trait ForComprehension[A] { self: Rng[A] =>
    final def map[B](f: A => B) = self./.asRngBy(SinglePass).map(f)./
    final def flatMap[B](f: A => Rng[B]) = self./.asRngBy(SinglePass).map(f).flatten./
    final def filter(p: A => Boolean) = self./.asRngBy(SinglePass).filter(p)./
    final def foreach(f: A => Unit) = self./.asRngBy(SinglePass).foreach(f)./
}
