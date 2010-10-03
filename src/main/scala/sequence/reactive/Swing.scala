

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

    class ActionPerformed(val source: ActionEventSource) extends Resource[ActionEvent] {
        private var l: ActionListener = null
        override protected def closeResource = source.removeActionListener(l)
        override protected def openResource(f: ActionEvent => Unit) = {
            l = new ActionListener {
                override def actionPerformed(e: ActionEvent) = f(e)
            }
            source.addActionListener(l)
        }
    }


    import java.awt.event.{MouseEvent, MouseWheelEvent}
    import javax.swing.event.MouseInputAdapter


    case class Mouse(source: Component) {

        // MouseEvent

        class Clicked extends Resource[MouseEvent] {
            private var l: MouseInputAdapter = null
            override protected def closeResource = source.removeMouseListener(l)
            override protected def openResource(f: MouseEvent => Unit) = {
                l = new MouseInputAdapter {
                    override def mouseClicked(e: MouseEvent) = f(e)
                }
                source.addMouseListener(l)
            }
        }
        def Clicked: Resource[MouseEvent] = new Clicked

        class Entered extends Resource[MouseEvent] {
            private var l: MouseInputAdapter = null
            override protected def closeResource = source.removeMouseListener(l)
            override protected def openResource(f: MouseEvent => Unit) = {
                l = new MouseInputAdapter {
                    override def mouseEntered(e: MouseEvent) = f(e)
                }
                source.addMouseListener(l)
            }
        }
        def Entered: Resource[MouseEvent] = new Entered

        class Exited extends Resource[MouseEvent] {
            private var l: MouseInputAdapter = null
            override protected def closeResource = source.removeMouseListener(l)
            override protected def openResource(f: MouseEvent => Unit) = {
                l = new MouseInputAdapter {
                    override def mouseExited(e: MouseEvent) = f(e)
                }
                source.addMouseListener(l)
            }
        }
        def Exited: Resource[MouseEvent] = new Exited

        class Pressed extends Resource[MouseEvent] {
            private var l: MouseInputAdapter = null
            override protected def closeResource = source.removeMouseListener(l)
            override protected def openResource(f: MouseEvent => Unit) = {
                l = new MouseInputAdapter {
                    override def mousePressed(e: MouseEvent) = f(e)
                }
                source.addMouseListener(l)
            }
        }
        def Pressed: Resource[MouseEvent] = new Pressed

        class Released extends Resource[MouseEvent] {
            private var l: MouseInputAdapter = null
            override protected def closeResource = source.removeMouseListener(l)
            override protected def openResource(f: MouseEvent => Unit) = {
                l = new MouseInputAdapter {
                    override def mouseReleased(e: MouseEvent) = f(e)
                }
                source.addMouseListener(l)
            }
        }
        def Released: Resource[MouseEvent] = new Released

        // MouseMotionListener

        class Dragged extends Resource[MouseEvent] {
            private var l: MouseInputAdapter = null
            override protected def closeResource = source.removeMouseMotionListener(l)
            override protected def openResource(f: MouseEvent => Unit) = {
                l = new MouseInputAdapter {
                    override def mouseDragged(e: MouseEvent) = f(e)
                }
                source.addMouseMotionListener(l)
            }
        }
        def Dragged: Resource[MouseEvent] = new Dragged

        class Moved extends Resource[MouseEvent] {
            private var l: MouseInputAdapter = null
            override protected def closeResource = source.removeMouseMotionListener(l)
            override protected def openResource(f: MouseEvent => Unit) = {
                l = new MouseInputAdapter {
                    override def mouseMoved(e: MouseEvent) = f(e)
                }
                source.addMouseMotionListener(l)
            }
        }
        def Moved: Resource[MouseEvent] = new Moved

        // MouseWheelListener

        class WheelMoved extends Resource[MouseWheelEvent] {
            private var l: MouseInputAdapter = null
            override protected def closeResource = source.removeMouseWheelListener(l)
            override protected def openResource(f: MouseWheelEvent => Unit) = {
                l = new MouseInputAdapter {
                    override def mouseWheelMoved(e: MouseWheelEvent) = f(e)
                }
                source.addMouseWheelListener(l)
            }
        }
        def WheelMoved: Resource[MouseEvent] = new WheelMoved

    }


// KeyEvent

    import java.awt.event.{KeyEvent, KeyAdapter}

    case class Key(source: Component) {

        // KeyListener

        class Pressed extends Resource[KeyEvent] {
            private var l: KeyAdapter = null
            override protected def closeResource = source.removeKeyListener(l)
            override protected def openResource(f: KeyEvent => Unit) = {
                l = new KeyAdapter {
                    override def keyPressed(e: KeyEvent) = f(e)
                }
                source.addKeyListener(l)
            }
        }
        def Pressed: Resource[KeyEvent] = new Pressed

        class Released extends Resource[KeyEvent] {
            private var l: KeyAdapter = null
            override protected def closeResource = source.removeKeyListener(l)
            override protected def openResource(f: KeyEvent => Unit) = {
                l = new KeyAdapter {
                    override def keyReleased(e: KeyEvent) = f(e)
                }
                source.addKeyListener(l)
            }
        }
        def Released: Resource[KeyEvent] = new Released

        class Typed extends Resource[KeyEvent] {
            private var l: KeyAdapter = null
            override protected def closeResource = source.removeKeyListener(l)
            override protected def openResource(f: KeyEvent => Unit) = {
                l = new KeyAdapter {
                    override def keyTyped(e: KeyEvent) = f(e)
                }
                source.addKeyListener(l)
            }
        }
        def Typed: Resource[KeyEvent] = new Typed

    }

}
