

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


import scala.collection.jcl.HashMap


object Lazy extends Lazy; trait Lazy extends Predefs {
    class MadaRngLazy[A](_1: Expr.Of[Rng[A]]) {
        def lazy_ = LazyExpr(_1).expr
    }
    implicit def toMadaRngLazy[A](_1: Expr.Of[Rng[A]]): MadaRngLazy[A] = new MadaRngLazy[A](_1)
}


case class LazyExpr[A](override val _1: Expr.Of[Rng[A]]) extends Expr.Method[Rng[A], Rng[A]] {
    override protected def _default = _1 match {
        case y @ LazyExpr(_) => y.eval
        case y @ BufferedExpr(x1) => y.eval
        case _ => LazyImpl(_1.eval)
    }
}


object LazyImpl {
    def apply[A](r: Rng[A]): Rng[A] = {
        AssertModels(r, Traversal.Forward)
        val m = new HashMap[Pointer[A], A]
        new LazyPointer(r.begin, m) <=< new LazyPointer(r.end, m)
    }
}

class LazyPointer[A](override val _base: Pointer[A], map: HashMap[Pointer[A], A])
        extends PointerAdapter[A, A, LazyPointer[A]] with NotWritablePointer[A] {
    override protected def _read = {
        val v = map.get(base)
        if (v.isEmpty) {
            val e = *(base)
            map.put(base, e)
            e
        } else {
            v.get
        }
    }
    override protected def _copy = new LazyPointer(base.copy, map)

    override def toString = new StringBuilder().append("LazyPointer of ").append(base).toString
}
