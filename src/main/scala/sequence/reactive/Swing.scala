

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


import java.awt.Component


object Swing {


// Invoke

    object InvokeLater extends Reactive[Unit] {
        override def foreach(f: Unit => Unit) = {
            javax.swing.SwingUtilities.invokeLater(new Runnable {
                override def run = f()
            })
        }
    }

    object InvokeAndWait extends Reactive[Unit] {
        override def foreach(f: Unit => Unit) = {
            javax.swing.SwingUtilities.invokeAndWait(new Runnable {
                override def run = f()
            })
        }
    }


// ActionEvent

    import java.awt.event.{ActionEvent, ActionListener}

    type ActionEventSource = {
        def addActionListener(l: ActionListener)
        def removeActionListener(l: ActionListener)
    }

    class ActionPerformed(val source: ActionEventSource) extends Closeable[ActionEvent] {
        private var l: ActionListener = null
        override protected def foreachOnce(f: ActionEvent => Unit) = {
            l = new ActionListener {
                override def actionPerformed(e: ActionEvent) = f(e)
            }
            source.addActionListener(l)
        }
        override def close = if (l ne null) source.removeActionListener(l)
    }


// MouseEvent

    import java.awt.event.{MouseEvent, MouseWheelEvent}
    import javax.swing.event.MouseInputAdapter

    class MouseClicked(val source: Component) extends Closeable[MouseEvent] {
        private var l: MouseInputAdapter = null
        override protected def foreachOnce(f: MouseEvent => Unit) = {
            l = new MouseInputAdapter {
                override def mouseClicked(e: MouseEvent) = f(e)
            }
            source.addMouseListener(l)
        }
        override def close = if (l ne null) source.removeMouseListener(l)
    }

    class MouseEntered(val source: Component) extends Closeable[MouseEvent] {
        private var l: MouseInputAdapter = null
        override protected def foreachOnce(f: MouseEvent => Unit) = {
            l = new MouseInputAdapter {
                override def mouseEntered(e: MouseEvent) = f(e)
            }
            source.addMouseListener(l)
        }
        override def close = if (l ne null) source.removeMouseListener(l)
    }

    class MouseExited(val source: Component) extends Closeable[MouseEvent] {
        private var l: MouseInputAdapter = null
        override protected def foreachOnce(f: MouseEvent => Unit) = {
            l = new MouseInputAdapter {
                override def mouseExited(e: MouseEvent) = f(e)
            }
            source.addMouseListener(l)
        }
        override def close = if (l ne null) source.removeMouseListener(l)
    }

    class MousePressed(val source: Component) extends Closeable[MouseEvent] {
        private var l: MouseInputAdapter = null
        override protected def foreachOnce(f: MouseEvent => Unit) = {
            l = new MouseInputAdapter {
                override def mousePressed(e: MouseEvent) = f(e)
            }
            source.addMouseListener(l)
        }
        override def close = if (l ne null) source.removeMouseListener(l)
    }

    class MouseReleased(val source: Component) extends Closeable[MouseEvent] {
        private var l: MouseInputAdapter = null
        override protected def foreachOnce(f: MouseEvent => Unit) = {
            l = new MouseInputAdapter {
                override def mouseReleased(e: MouseEvent) = f(e)
            }
            source.addMouseListener(l)
        }
        override def close = if (l ne null) source.removeMouseListener(l)
    }

  // MouseMotionListener

    class MouseDragged(val source: Component) extends Closeable[MouseEvent] {
        private var l: MouseInputAdapter = null
        override protected def foreachOnce(f: MouseEvent => Unit) = {
            l = new MouseInputAdapter {
                override def mouseDragged(e: MouseEvent) = f(e)
            }
            source.addMouseMotionListener(l)
        }
        override def close = if (l ne null) source.removeMouseMotionListener(l)
    }

    class MouseMoved(val source: Component) extends Closeable[MouseEvent] {
        private var l: MouseInputAdapter = null
        override protected def foreachOnce(f: MouseEvent => Unit) = {
            l = new MouseInputAdapter {
                override def mouseMoved(e: MouseEvent) = f(e)
            }
            source.addMouseMotionListener(l)
        }
        override def close = if (l ne null) source.removeMouseMotionListener(l)
    }

  // MouseWheelListener

    class MouseWheelMoved(val source: Component) extends Closeable[MouseWheelEvent] {
        private var l: MouseInputAdapter = null
        override protected def foreachOnce(f: MouseWheelEvent => Unit) = {
            l = new MouseInputAdapter {
                override def mouseWheelMoved(e: MouseWheelEvent) = f(e)
            }
            source.addMouseWheelListener(l)
        }
        override def close = if (l ne null) source.removeMouseWheelListener(l)
    }


// KeyEvent

    import java.awt.event.{KeyEvent, KeyAdapter}

  // KeyListener

    class KeyPressed(val source: Component) extends Closeable[KeyEvent] {
        private var l: KeyAdapter = null
        override protected def foreachOnce(f: KeyEvent => Unit) = {
            l = new KeyAdapter {
                override def keyPressed(e: KeyEvent) = f(e)
            }
            source.addKeyListener(l)
        }
        override def close = if (l ne null) source.removeKeyListener(l)
    }

    class KeyReleased(val source: Component) extends Closeable[KeyEvent] {
        private var l: KeyAdapter = null
        override protected def foreachOnce(f: KeyEvent => Unit) = {
            l = new KeyAdapter {
                override def keyReleased(e: KeyEvent) = f(e)
            }
            source.addKeyListener(l)
        }
        override def close = if (l ne null) source.removeKeyListener(l)
    }

    class KeyTyped(val source: Component) extends Closeable[KeyEvent] {
        private var l: KeyAdapter = null
        override protected def foreachOnce(f: KeyEvent => Unit) = {
            l = new KeyAdapter {
                override def keyTyped(e: KeyEvent) = f(e)
            }
            source.addKeyListener(l)
        }
        override def close = if (l ne null) source.removeKeyListener(l)
    }

}
