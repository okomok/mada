

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


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
        override def foreach(f: ActionEvent => Unit) = {
            l := new ActionListener {
                override def actionPerformed(e: ActionEvent) = f(e)
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
        override def foreach(f: MouseEvent => Unit) = {
            l := new MouseInputAdapter {
                override def mouseClicked(e: MouseEvent) = f(e)
            }
            source.addMouseListener(l)
        }
        override def close = source.removeMouseListener(l)
    }

    class MouseEntered(val source: Component) extends Closeable[MouseEvent] {
        private val l = new OneTimeVar[MouseInputAdapter]
        override def foreach(f: MouseEvent => Unit) = {
            l := new MouseInputAdapter {
                override def mouseEntered(e: MouseEvent) = f(e)
            }
            source.addMouseListener(l)
        }
        override def close = source.removeMouseListener(l)
    }

    class MouseExited(val source: Component) extends Closeable[MouseEvent] {
        private val l = new OneTimeVar[MouseInputAdapter]
        override def foreach(f: MouseEvent => Unit) = {
            l := new MouseInputAdapter {
                override def mouseExited(e: MouseEvent) = f(e)
            }
            source.addMouseListener(l)
        }
        override def close = source.removeMouseListener(l)
    }

    class MousePressed(val source: Component) extends Closeable[MouseEvent] {
        private val l = new OneTimeVar[MouseInputAdapter]
        override def foreach(f: MouseEvent => Unit) = {
            l := new MouseInputAdapter {
                override def mousePressed(e: MouseEvent) = f(e)
            }
            source.addMouseListener(l)
        }
        override def close = source.removeMouseListener(l)
    }

    class MouseReleased(val source: Component) extends Closeable[MouseEvent] {
        private val l = new OneTimeVar[MouseInputAdapter]
        override def foreach(f: MouseEvent => Unit) = {
            l := new MouseInputAdapter {
                override def mouseReleased(e: MouseEvent) = f(e)
            }
            source.addMouseListener(l)
        }
        override def close = source.removeMouseListener(l)
    }

  // MouseMotionListener

    class MouseDragged(val source: Component) extends Closeable[MouseEvent] {
        private val l = new OneTimeVar[MouseInputAdapter]
        override def foreach(f: MouseEvent => Unit) = {
            l := new MouseInputAdapter {
                override def mouseDragged(e: MouseEvent) = f(e)
            }
            source.addMouseMotionListener(l)
        }
        override def close = source.removeMouseMotionListener(l)
    }

    class MouseMoved(val source: Component) extends Closeable[MouseEvent] {
        private val l = new OneTimeVar[MouseInputAdapter]
        override def foreach(f: MouseEvent => Unit) = {
            l := new MouseInputAdapter {
                override def mouseMoved(e: MouseEvent) = f(e)
            }
            source.addMouseMotionListener(l)
        }
        override def close = source.removeMouseMotionListener(l)
    }

  // MouseWheelListener

    class MouseWheelMoved(val source: Component) extends Closeable[MouseWheelEvent] {
        private val l = new OneTimeVar[MouseInputAdapter]
        override def foreach(f: MouseWheelEvent => Unit) = {
            l := new MouseInputAdapter {
                override def mouseWheelMoved(e: MouseWheelEvent) = f(e)
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
        override def foreach(f: KeyEvent => Unit) = {
            l := new KeyAdapter {
                override def keyPressed(e: KeyEvent) = f(e)
            }
            source.addKeyListener(l)
        }
        override def close = source.removeKeyListener(l)
    }

    class KeyReleased(val source: Component) extends Closeable[KeyEvent] {
        private val l = new OneTimeVar[KeyAdapter]
        override def foreach(f: KeyEvent => Unit) = {
            l := new KeyAdapter {
                override def keyReleased(e: KeyEvent) = f(e)
            }
            source.addKeyListener(l)
        }
        override def close = source.removeKeyListener(l)
    }

    class KeyTyped(val source: Component) extends Closeable[KeyEvent] {
        private val l = new OneTimeVar[KeyAdapter]
        override def foreach(f: KeyEvent => Unit) = {
            l := new KeyAdapter {
                override def keyTyped(e: KeyEvent) = f(e)
            }
            source.addKeyListener(l)
        }
        override def close = source.removeKeyListener(l)
    }

}
