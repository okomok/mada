

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


import java.awt.Component


object Swing { // nightmare...


// ActionEvent

    import java.awt.event.{ActionEvent, ActionListener}

    type ActionEventSource = {
        def addActionListener(l: ActionListener)
        def removeActionListener(l: ActionListener)
    }

    case class ActionPerformed(_1: ActionEventSource) extends Closeable[ActionEvent] {
        private val l = new OneTimeVar[ActionListener]
        override def start(k: Reactor[ActionEvent]) = {
            l := new ActionListener {
                override def actionPerformed(e: ActionEvent) = k.react(e)
            }
            _1.addActionListener(l)
        }
        override def close = _1.removeActionListener(l)
    }


// MouseEvent

    import java.awt.event.{MouseEvent, MouseWheelEvent, MouseListener, MouseMotionListener, MouseWheelListener}

    class MouseEventFrom(val source: Component) extends Closeable[MouseEvent] {
        private var l = new OneTimeVar[MouseListener]
        override def start(k: Reactor[MouseEvent]) = {
            l := new MouseListener {
                override def mouseClicked(e: MouseEvent) = k.react(e)
                override def mouseEntered(e: MouseEvent) = k.react(e)
                override def mouseExited(e: MouseEvent) = k.react(e)
                override def mousePressed(e: MouseEvent) = k.react(e)
                override def mouseReleased(e: MouseEvent) = k.react(e)
            }
            source.addMouseListener(l)
        }
        override def close = source.removeMouseListener(l)
    }

    class MouseMotionEventFrom(val source: Component) extends Closeable[MouseEvent] {
        private var l = new OneTimeVar[MouseMotionListener]
        override def start(k: Reactor[MouseEvent]) = {
            l := new MouseMotionListener {
                override def mouseDragged(e: MouseEvent) = k.react(e)
                override def mouseMoved(e: MouseEvent) = k.react(e)
            }
            source.addMouseMotionListener(l)
        }
        override def close = source.removeMouseMotionListener(l)
    }

    class MouseWheelEventFrom(val source: Component) extends Closeable[MouseWheelEvent] {
        private var l = new OneTimeVar[MouseWheelListener]
        override def start(k: Reactor[MouseWheelEvent]) = {
            l := new MouseWheelListener {
                override def mouseWheelMoved(e: MouseWheelEvent) = k.react(e)
            }
            source.addMouseWheelListener(l)
        }
        override def close = source.removeMouseWheelListener(l)
    }

    class _OfMouseEvent(_this: Reactive[MouseEvent]) {
        def mouseClicked(f: Reactive[MouseEvent] => Unit): Reactive[MouseEvent] = _this.fork{ r => f(r.filter(_.getID == MouseEvent.MOUSE_CLICKED)) }
        def mouseEntered(f: Reactive[MouseEvent] => Unit): Reactive[MouseEvent] = _this.fork{ r => f(r.filter(_.getID == MouseEvent.MOUSE_ENTERED)) }
        def mouseExited(f: Reactive[MouseEvent] => Unit): Reactive[MouseEvent] = _this.fork{ r => f(r.filter(_.getID == MouseEvent.MOUSE_EXITED)) }
        def mousePressed(f: Reactive[MouseEvent] => Unit): Reactive[MouseEvent] = _this.fork{ r => f(r.filter(_.getID == MouseEvent.MOUSE_PRESSED)) }
        def mouseReleased(f: Reactive[MouseEvent] => Unit): Reactive[MouseEvent] = _this.fork{ r => f(r.filter(_.getID == MouseEvent.MOUSE_RELEASED)) }
        def mouseDragged(f: Reactive[MouseEvent] => Unit): Reactive[MouseEvent] = _this.fork{ r => f(r.filter(_.getID == MouseEvent.MOUSE_DRAGGED)) }
        def mouseMoved(f: Reactive[MouseEvent] => Unit): Reactive[MouseEvent] = _this.fork{ r => f(r.filter(_.getID == MouseEvent.MOUSE_MOVED)) }
    }

    class _OfMouseWheelEvent(_this: Reactive[MouseWheelEvent]) {
        def mouseWheelMoved(f: Reactive[MouseWheelEvent] => Unit): Reactive[MouseWheelEvent] = _this.fork{ r => f(r.filter(_.getID == MouseEvent.MOUSE_WHEEL)) }
    }


// KeyEvent

    import java.awt.event.{KeyEvent, KeyListener}

    class KeyEventFrom(val source: Component) extends Closeable[KeyEvent] {
        private val l = new OneTimeVar[KeyListener]
        override def start(k: Reactor[KeyEvent]) = {
            l := new KeyListener {
                override def keyPressed(e: KeyEvent) = k.react(e)
                override def keyReleased(e: KeyEvent) = k.react(e)
                override def keyTyped(e: KeyEvent) = k.react(e)
            }
            source.addKeyListener(l)
        }
        override def close = source.removeKeyListener(l)
    }

    class _OfKeyEvent(_this: Reactive[KeyEvent]) {
        def keyPressed(f: Reactive[KeyEvent] => Unit): Reactive[KeyEvent] = _this.fork{ r => f(r.filter(_.getID == KeyEvent.KEY_PRESSED)) }
        def keyReleased(f: Reactive[KeyEvent] => Unit): Reactive[KeyEvent] = _this.fork{ r => f(r.filter(_.getID == KeyEvent.KEY_RELEASED)) }
        def keyTyped(f: Reactive[KeyEvent] => Unit): Reactive[KeyEvent] = _this.fork{ r => f(r.filter(_.getID == KeyEvent.KEY_TYPED)) }
    }

}
