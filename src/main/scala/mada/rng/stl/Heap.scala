

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


/*
 *
 * Copyright (c) 1994
 * Hewlett-Packard Company
 *
 * Copyright (c) 1996,1997
 * Silicon Graphics Computer Systems, Inc.
 *
 * Copyright (c) 1997
 * Moscow Center for SPARC Technology
 *
 * Copyright (c) 1999
 * Boris Fomitchev
 *
 * This material is provided "as is", with absolutely no warranty expressed
 * or implied. Any use is at your own risk.
 *
 * Permission to use or copy this software for any purpose is hereby granted
 * without fee, provided the above notices are retained on all copies.
 * Permission to modify the code and to distribute modified code is granted,
 * provided the above notices are retained, and a notice that the code was
 * modified is included with the above copyright notice.
 *
 */


package mada.rng.stl


// pushHeap

object PushHeap extends PushHeap; trait PushHeap extends Predefs {
    class MadaRngStlPushHeap[A](_1: Expr.Of[Rng[A]]) {
        def stl_pushHeap(implicit _2: A => Ordered[A]) = PushHeapExpr[A](_1, _2(_) < _).expr
        def stl_pushHeapWith(_2: (A, A) => Boolean) = PushHeapExpr(_1, _2).expr
    }
    implicit def toMadaRngStlPushHeap[A](_1: Expr.Of[Rng[A]]): MadaRngStlPushHeap[A] = new MadaRngStlPushHeap[A](_1)
}

case class PushHeapExpr[A](override val _1: Expr.Of[Rng[A]], _2: (A, A) => Boolean) extends Expr.Method[Rng[A], Unit] {
    override protected def _default = PushHeapImpl(_1.eval, _2)
}

object PushHeapImpl {
    def apply[A](r: Rng[A], __comp: (A, A) => Boolean): Unit = {
        r.assertModels(Traversal.RandomAccess)
        val (__*, __first, __last) = r.toTriple
        apply(__*, __first, __last, __comp)
    }

    def apply[A](* : Pointer[A], __first: Long, __last: Long, __comp: (A, A) => Boolean): Unit = {
        apply(*, __first, (__last - __first) - 1, 0, *(__last - 1), __comp)
    }

    def apply[A](* : Pointer[A], __first: Long, holeIndex: Long, __topIndex: Long, __value: A, __comp: (A, A) => Boolean): Unit = {
        var __holeIndex = holeIndex

        var __parent = (__holeIndex - 1) / 2
        while (__holeIndex > __topIndex && __comp(*(__first + __parent), __value)) {
            *(__first + __holeIndex) = *(__first + __parent)
            __holeIndex = __parent
            __parent = (__holeIndex - 1) / 2
        }
        *(__first + __holeIndex) = __value
    }
}

// popHeap

object PopHeap extends PopHeap; trait PopHeap extends Predefs {
    class MadaRngStlPopHeap[A](_1: Expr.Of[Rng[A]]) {
        def stl_popHeap(implicit _2: A => Ordered[A]) = PopHeapExpr[A](_1, _2(_) < _).expr
        def stl_popHeapWith(_2: (A, A) => Boolean) = PopHeapExpr(_1, _2).expr
    }
    implicit def toMadaRngStlPopHeap[A](_1: Expr.Of[Rng[A]]): MadaRngStlPopHeap[A] = new MadaRngStlPopHeap[A](_1)
}

case class PopHeapExpr[A](override val _1: Expr.Of[Rng[A]], _2: (A, A) => Boolean) extends Expr.Method[Rng[A], Unit] {
    override protected def _default = PopHeapImpl(_1.eval, _2)
}

object PopHeapImpl {
    def apply[A](r: Rng[A], __comp: (A, A) => Boolean): Unit = {
        r.assertModels(Traversal.RandomAccess)
        val (__*, __first, __last) = r.toTriple
        apply(__*, __first, __last, __comp)
    }

    def apply[A](* : Pointer[A], __first: Long, __last: Long, __comp: (A, A) => Boolean): Unit = {
        apply(*, __first, __last - 1, __last - 1, *(__last - 1), __comp)
    }

    def apply[A](* : Pointer[A], __first: Long, __last: Long, __result: Long, __value: A, __comp: (A, A) => Boolean): Unit = {
        *(__result) = *(__first)
        HeapImpl.adjust(*, __first, 0, __last - __first, __value, __comp);
    }
}


// makeHeap

object MakeHeap extends MakeHeap; trait MakeHeap extends Predefs {
    class MadaRngStlMakeHeap[A](_1: Expr.Of[Rng[A]]) {
        def stl_makeHeap(implicit _2: A => Ordered[A]) = MakeHeapExpr[A](_1, _2(_) < _).expr
        def stl_makeHeapWith(_2: (A, A) => Boolean) = MakeHeapExpr(_1, _2).expr
    }
    implicit def toMadaRngStlMakeHeap[A](_1: Expr.Of[Rng[A]]): MadaRngStlMakeHeap[A] = new MadaRngStlMakeHeap[A](_1)
}


case class MakeHeapExpr[A](override val _1: Expr.Of[Rng[A]], _2: (A, A) => Boolean) extends Expr.Method[Rng[A], Unit] {
    override protected def _default = MakeHeapImpl(_1.eval, _2)
}


object MakeHeapImpl {
    def apply[A](r: Rng[A], __comp: (A, A) => Boolean): Unit = {
        r.assertModels(Traversal.RandomAccess)
        val (__*, __first, __last) = r.toTriple
        apply(__*, __first, __last, __comp)
    }

    def apply[A](* : Pointer[A], __first: Long, __last: Long, __comp: (A, A) => Boolean): Unit = {
        if (__last - __first < 2) {
            return
        }
        val __len = __last - __first
        var __parent = (__len - 2)/2

        while (true) {
            HeapImpl.adjust(*, __first, __parent, __len, *(__first + __parent), __comp)
            if (__parent == 0) {
                return
            }
            __parent -= 1
        }
    }
}


// sortHeap

object SortHeap extends SortHeap; trait SortHeap extends Predefs {
    class MadaRngStlSortHeap[A](_1: Expr.Of[Rng[A]]) {
        def stl_sortHeap(implicit _2: A => Ordered[A]) = SortHeapExpr[A](_1, _2(_) < _).expr
        def stl_sortHeapWith(_2: (A, A) => Boolean) = SortHeapExpr(_1, _2).expr
    }
    implicit def toMadaRngStlSortHeap[A](_1: Expr.Of[Rng[A]]): MadaRngStlSortHeap[A] = new MadaRngStlSortHeap[A](_1)
}

case class SortHeapExpr[A](override val _1: Expr.Of[Rng[A]], _2: (A, A) => Boolean) extends Expr.Method[Rng[A], Unit] {
    override protected def _default = SortHeapImpl(_1.eval, _2)
}

object SortHeapImpl {
    def apply[A](r: Rng[A], __comp: (A, A) => Boolean): Unit = {
        r.assertModels(Traversal.RandomAccess)
        val (__*, __first, __last) = r.toTriple
        apply(__*, __first, __last, __comp)
    }

    def apply[A](* : Pointer[A], __first: Long, last: Long, __comp: (A, A) => Boolean): Unit = {
        var __last = last

        while (__last - __first > 1) {
            PopHeapImpl(*, __first, __last, __comp)
            __last -= 1
        }
    }
}


object HeapImpl {
    def adjust[A](* : Pointer[A], __first: Long, holeIndex: Long, __len: Long, __value: A, __comp: (A, A) => Boolean): Unit = {
        var __holeIndex = holeIndex

        val __topIndex = __holeIndex;
        var __secondChild = 2 * __holeIndex + 2
        while (__secondChild < __len) {
            if (__comp(*(__first + __secondChild), *(__first + __secondChild - 1))) {
                __secondChild -= 1
            }
            *(__first + __holeIndex) = *(__first + __secondChild)
            __holeIndex = __secondChild
            __secondChild = 2 * (__secondChild + 1)
        }
        if (__secondChild == __len) {
            *(__first + __holeIndex) = *(__first + (__secondChild - 1))
            __holeIndex = __secondChild - 1
        }
        PushHeapImpl(*, __first, __holeIndex, __topIndex, __value, __comp)
    }
}
