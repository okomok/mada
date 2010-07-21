

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package reactive


import java.awt.Component


object Swing {


// ActionEvent

    import java.awt.event.{ActionEvent, ActionListener}

    type ActionEventSource = {
        def addActionListener(l: ActionListener)
        def removeActionListener(l: ActionListener)
    }

    class ActionPerformed(val source: ActionEventSource) extends Closeable[ActionEvent] {
        private val l = new OneTimeVar[ActionListener]
        override def activate(k: Reactor[ActionEvent]) = {
            l := new ActionListener {
                override def actionPerformed(e: ActionEvent) = k.react(e)
            }
            source.addActionListener(l)
        }
        override def close = source.removeActionListener(l)
    }


// MouseEvent

    import java.awt.event.{MouseEvent, MouseWheelEvent}
    import javax.swing.event.MouseInputAdapter

    class MouseClicked(val source: Component) extends Closeable[MouseEvent] {
        private val l = new OneTimeVar[MouseInputAdapter]
        override def activate(k: Reactor[MouseEvent]) = {
            l := new MouseInputAdapter {
                override def mouseClicked(e: MouseEvent) = k.react(e)
            }
            source.addMouseListener(l)
        }
        override def close = source.removeMouseListener(l)
    }

    class MouseEntered(val source: Component) extends Closeable[MouseEvent] {
        private val l = new OneTimeVar[MouseInputAdapter]
        override def activate(k: Reactor[MouseEvent]) = {
            l := new MouseInputAdapter {
                override def mouseEntered(e: MouseEvent) = k.react(e)
            }
            source.addMouseListener(l)
        }
        override def close = source.removeMouseListener(l)
    }

    class MouseExited(val source: Component) extends Closeable[MouseEvent] {
        private val l = new OneTimeVar[MouseInputAdapter]
        override def activate(k: Reactor[MouseEvent]) = {
            l := new MouseInputAdapter {
                override def mouseExited(e: MouseEvent) = k.react(e)
            }
            source.addMouseListener(l)
        }
        override def close = source.removeMouseListener(l)
    }

    class MousePressed(val source: Component) extends Closeable[MouseEvent] {
        private val l = new OneTimeVar[MouseInputAdapter]
        override def activate(k: Reactor[MouseEvent]) = {
            l := new MouseInputAdapter {
                override def mousePressed(e: MouseEvent) = k.react(e)
            }
            source.addMouseListener(l)
        }
        override def close = source.removeMouseListener(l)
    }

    class MouseReleased(val source: Component) extends Closeable[MouseEvent] {
        private val l = new OneTimeVar[MouseInputAdapter]
        override def activate(k: Reactor[MouseEvent]) = {
            l := new MouseInputAdapter {
                override def mouseReleased(e: MouseEvent) = k.react(e)
            }
            source.addMouseListener(l)
        }
        override def close = source.removeMouseListener(l)
    }

  // MouseMotionListener

    class MouseDragged(val source: Component) extends Closeable[MouseEvent] {
        private val l = new OneTimeVar[MouseInputAdapter]
        override def activate(k: Reactor[MouseEvent]) = {
            l := new MouseInputAdapter {
                override def mouseDragged(e: MouseEvent) = k.react(e)
            }
            source.addMouseMotionListener(l)
        }
        override def close = source.removeMouseMotionListener(l)
    }

    class MouseMoved(val source: Component) extends Closeable[MouseEvent] {
        private val l = new OneTimeVar[MouseInputAdapter]
        override def activate(k: Reactor[MouseEvent]) = {
            l := new MouseInputAdapter {
                override def mouseMoved(e: MouseEvent) = k.react(e)
            }
            source.addMouseMotionListener(l)
        }
        override def close = source.removeMouseMotionListener(l)
    }

  // MouseWheelListener

    class MouseWheelMoved(val source: Component) extends Closeable[MouseWheelEvent] {
        private val l = new OneTimeVar[MouseInputAdapter]
        override def activate(k: Reactor[MouseWheelEvent]) = {
            l := new MouseInputAdapter {
                override def mouseWheelMoved(e: MouseWheelEvent) = k.react(e)
            }
            source.addMouseWheelListener(l)
        }
        override def close = source.removeMouseWheelListener(l)
    }


// KeyEvent

    import java.awt.event.{KeyEvent, KeyAdapter}

  // KeyListener

    class KeyPressed(val source: Component) extends Closeable[KeyEvent] {
        private val l = new OneTimeVar[KeyAdapter]
        override def activate(k: Reactor[KeyEvent]) = {
            l := new KeyAdapter {
                override def keyPressed(e: KeyEvent) = k.react(e)
            }
            source.addKeyListener(l)
        }
        override def close = source.removeKeyListener(l)
    }

    class KeyReleased(val source: Component) extends Closeable[KeyEvent] {
        private val l = new OneTimeVar[KeyAdapter]
        override def activate(k: Reactor[KeyEvent]) = {
            l := new KeyAdapter {
                override def keyReleased(e: KeyEvent) = k.react(e)
            }
            source.addKeyListener(l)
        }
        override def close = source.removeKeyListener(l)
    }

    class KeyTyped(val source: Component) extends Closeable[KeyEvent] {
        private val l = new OneTimeVar[KeyAdapter]
        override def activate(k: Reactor[KeyEvent]) = {
            l := new KeyAdapter {
                override def keyTyped(e: KeyEvent) = k.react(e)
            }
            source.addKeyListener(l)
        }
        override def close = source.removeKeyListener(l)
    }

}
