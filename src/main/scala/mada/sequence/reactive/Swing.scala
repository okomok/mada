

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
        override def subscribe(k: Reactor[ActionEvent]) = {
            l := new ActionListener {
                override def actionPerformed(e: ActionEvent) = k.react(e)
            }
            _1.addActionListener(l)
        }
        override def close = _1.removeActionListener(l)
    }


// MouseEvent

    import java.awt.event.{MouseEvent, MouseWheelEvent}
    import javax.swing.event.MouseInputAdapter


  // MouseListener

    def mouseClicked(c: Component): Closeable[MouseEvent] = MouseClicked(c)
    def mouseEntered(c: Component): Closeable[MouseEvent] = MouseEntered(c)
    def mouseExited(c: Component): Closeable[MouseEvent] = MouseExited(c)
    def mousePressed(c: Component): Closeable[MouseEvent] = MousePressed(c)
    def mouseReleased(c: Component): Closeable[MouseEvent] = MouseReleased(c)

    case class MouseClicked(_1: Component) extends Closeable[MouseEvent] {
        private val l = new OneTimeVar[MouseInputAdapter]
        override def subscribe(k: Reactor[MouseEvent]) = {
            l := new MouseInputAdapter {
                override def mouseClicked(e: MouseEvent) = k.react(e)
            }
            _1.addMouseListener(l)
        }
        override def close = _1.removeMouseListener(l)
    }

    case class MouseEntered(_1: Component) extends Closeable[MouseEvent] {
        private val l = new OneTimeVar[MouseInputAdapter]
        override def subscribe(k: Reactor[MouseEvent]) = {
            l := new MouseInputAdapter {
                override def mouseEntered(e: MouseEvent) = k.react(e)
            }
            _1.addMouseListener(l)
        }
        override def close = _1.removeMouseListener(l)
    }

    case class MouseExited(_1: Component) extends Closeable[MouseEvent] {
        private val l = new OneTimeVar[MouseInputAdapter]
        override def subscribe(k: Reactor[MouseEvent]) = {
            l := new MouseInputAdapter {
                override def mouseExited(e: MouseEvent) = k.react(e)
            }
            _1.addMouseListener(l)
        }
        override def close = _1.removeMouseListener(l)
    }

    case class MousePressed(_1: Component) extends Closeable[MouseEvent] {
        private val l = new OneTimeVar[MouseInputAdapter]
        override def subscribe(k: Reactor[MouseEvent]) = {
            l := new MouseInputAdapter {
                override def mousePressed(e: MouseEvent) = k.react(e)
            }
            _1.addMouseListener(l)
        }
        override def close = _1.removeMouseListener(l)
    }

    case class MouseReleased(_1: Component) extends Closeable[MouseEvent] {
        private val l = new OneTimeVar[MouseInputAdapter]
        override def subscribe(k: Reactor[MouseEvent]) = {
            l := new MouseInputAdapter {
                override def mouseReleased(e: MouseEvent) = k.react(e)
            }
            _1.addMouseListener(l)
        }
        override def close = _1.removeMouseListener(l)
    }

  // MouseMotionListener

    def mouseDragged(c: Component): Closeable[MouseEvent] = MouseDragged(c)
    def mouseMoved(c: Component): Closeable[MouseEvent] = MouseMoved(c)

    case class MouseDragged(_1: Component) extends Closeable[MouseEvent] {
        private val l = new OneTimeVar[MouseInputAdapter]
        override def subscribe(k: Reactor[MouseEvent]) = {
            l := new MouseInputAdapter {
                override def mouseDragged(e: MouseEvent) = k.react(e)
            }
            _1.addMouseMotionListener(l)
        }
        override def close = _1.removeMouseMotionListener(l)
    }

    case class MouseMoved(_1: Component) extends Closeable[MouseEvent] {
        private val l = new OneTimeVar[MouseInputAdapter]
        override def subscribe(k: Reactor[MouseEvent]) = {
            l := new MouseInputAdapter {
                override def mouseMoved(e: MouseEvent) = k.react(e)
            }
            _1.addMouseMotionListener(l)
        }
        override def close = _1.removeMouseMotionListener(l)
    }

  // MouseWheelListener

    def mouseWheelMoved(c: Component): Closeable[MouseWheelEvent] = MouseWheelMoved(c)

    case class MouseWheelMoved(_1: Component) extends Closeable[MouseWheelEvent] {
        private val l = new OneTimeVar[MouseInputAdapter]
        override def subscribe(k: Reactor[MouseWheelEvent]) = {
            l := new MouseInputAdapter {
                override def mouseWheelMoved(e: MouseWheelEvent) = k.react(e)
            }
            _1.addMouseWheelListener(l)
        }
        override def close = _1.removeMouseWheelListener(l)
    }


// KeyEvent

    import java.awt.event.{KeyEvent, KeyAdapter}

  // KeyListener

    def keyPressed(c: Component): Closeable[KeyEvent] = KeyPressed(c)
    def keyReleased(c: Component): Closeable[KeyEvent] = KeyReleased(c)
    def keyTyped(c: Component): Closeable[KeyEvent] = KeyTyped(c)

    case class KeyPressed(_1: Component) extends Closeable[KeyEvent] {
        private val l = new OneTimeVar[KeyAdapter]
        override def subscribe(k: Reactor[KeyEvent]) = {
            l := new KeyAdapter {
                override def keyPressed(e: KeyEvent) = k.react(e)
            }
            _1.addKeyListener(l)
        }
        override def close = _1.removeKeyListener(l)
    }

    case class KeyReleased(_1: Component) extends Closeable[KeyEvent] {
        private val l = new OneTimeVar[KeyAdapter]
        override def subscribe(k: Reactor[KeyEvent]) = {
            l := new KeyAdapter {
                override def keyReleased(e: KeyEvent) = k.react(e)
            }
            _1.addKeyListener(l)
        }
        override def close = _1.removeKeyListener(l)
    }

    case class KeyTyped(_1: Component) extends Closeable[KeyEvent] {
        private val l = new OneTimeVar[KeyAdapter]
        override def subscribe(k: Reactor[KeyEvent]) = {
            l := new KeyAdapter {
                override def keyTyped(e: KeyEvent) = k.react(e)
            }
            _1.addKeyListener(l)
        }
        override def close = _1.removeKeyListener(l)
    }

}
