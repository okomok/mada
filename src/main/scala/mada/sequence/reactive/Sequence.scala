

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


import java.nio.channels.SelectionKey


/**
 * The bridge between phisical and logical hierarchy
 */
trait Sequence[+A] { // physical

    @conversion
    def asReactive: Reactive[A] // logical

}


object Sequence {

// methodization
    sealed class _OfSequence[A](_this: Reactive[Reactive[A]]) {
        def flatten: Reactive[A] = _this._flatten(_this)
        def unsplit(sep: Reactive[A]): Reactive[A] = _this._unsplit(_this, sep)
    }
    implicit def _ofSequence[A](_this: Sequence[Sequence[A]]): _OfSequence[A] = new _OfSequence(_this.asReactive.map(_.asReactive))

    sealed class _OfPair[A, B](_this: Reactive[(A, B)]) {
        def unzip: (Reactive[A], Reactive[B]) = _this._unzip(_this)
    }
    implicit def _ofPair[A, B](_this: Sequence[(A, B)]): _OfPair[A, B] = new _OfPair(_this.asReactive)

    sealed class _OfSelectionKey(_this: Reactive[SelectionKey]) {
        def caseAccept(f: SelectionKey => Unit): Reactive[SelectionKey] = _this.fork{ r => r.filter(_.isAcceptable).foreach(f) }
        def caseConnect(f: SelectionKey => Unit): Reactive[SelectionKey] = _this.fork{ r => r.filter(_.isConnectable).foreach(f) }
        def caseRead(f: SelectionKey => Unit): Reactive[SelectionKey] = _this.fork{ r => r.filter(_.isReadable).foreach(f) }
        def caseValid(f: SelectionKey => Unit): Reactive[SelectionKey] = _this.fork{ r => r.filter(_.isValid).foreach(f) }
        def caseWrite(f: SelectionKey => Unit): Reactive[SelectionKey] = _this.fork{ r => r.filter(_.isWritable).foreach(f) }
    }
    implicit def _ofSelectionKey(_this: Sequence[SelectionKey]): _OfSelectionKey = new _OfSelectionKey(_this.asReactive)
/*
    implicit def _ofMouseEvent(_this: Sequence[java.awt.event.MouseEvent]): Swing._OfMouseEvent = new Swing._OfMouseEvent(_this.asReactive)
    implicit def _ofMouseWheelEvent(_this: Sequence[java.awt.event.MouseWheelEvent]): Swing._OfMouseWheelEvent = new Swing._OfMouseWheelEvent(_this.asReactive)
    implicit def _ofKeyEvent(_this: Sequence[java.awt.event.KeyEvent]): Swing._OfKeyEvent = new Swing._OfKeyEvent(_this.asReactive)
*/
}


trait SequenceForwarder[+A] extends Sequence[A] with util.Forwarder {
    override protected def delegate: Sequence[A]

    override def asReactive = delegate.asReactive
    override def equals(that: Any): Boolean = delegate.equals(that)
}
