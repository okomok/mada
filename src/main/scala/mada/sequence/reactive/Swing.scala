

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


import java.awt.Component


object Swing {


// MouseEvent

    import java.awt.event.{MouseEvent, MouseWheelEvent}
    import javax.swing.event.MouseInputAdapter

  // MouseListener

    def mouseClicked(c: Component): Reactive[MouseEvent] = MouseClicked(c)
    def mouseEntered(c: Component): Reactive[MouseEvent] = MouseEntered(c)
    def mouseExited(c: Component): Reactive[MouseEvent] = MouseExited(c)
    def mousePressed(c: Component): Reactive[MouseEvent] = MousePressed(c)
    def mouseReleased(c: Component): Reactive[MouseEvent] = MouseReleased(c)

    case class MouseClicked(_1: Component) extends Reactive[MouseEvent] {
        override def subscribe(k: Reactor[MouseEvent]): Unit = {
            val j = new MouseInputAdapter {
                override def mouseClicked(e: MouseEvent) = k.react(e)
            }
            _1.addMouseListener(j)
        }
    }

    case class MouseEntered(_1: Component) extends Reactive[MouseEvent] {
        override def subscribe(k: Reactor[MouseEvent]): Unit = {
            val j = new MouseInputAdapter {
                override def mouseEntered(e: MouseEvent) = k.react(e)
            }
            _1.addMouseListener(j)
        }
    }

    case class MouseExited(_1: Component) extends Reactive[MouseEvent] {
        override def subscribe(k: Reactor[MouseEvent]): Unit = {
            val j = new MouseInputAdapter {
                override def mouseExited(e: MouseEvent) = k.react(e)
            }
            _1.addMouseListener(j)
        }
    }

    case class MousePressed(_1: Component) extends Reactive[MouseEvent] {
        override def subscribe(k: Reactor[MouseEvent]): Unit = {
            val j = new MouseInputAdapter {
                override def mousePressed(e: MouseEvent) = k.react(e)
            }
            _1.addMouseListener(j)
        }
    }
    case class MouseReleased(_1: Component) extends Reactive[MouseEvent] {
        override def subscribe(k: Reactor[MouseEvent]): Unit = {
            val j = new MouseInputAdapter {
                override def mouseReleased(e: MouseEvent) = k.react(e)
            }
            _1.addMouseListener(j)
        }
    }

  // MouseMotionListener

    def mouseDragged(c: Component): Reactive[MouseEvent] = MouseDragged(c)
    def mouseMoved(c: Component): Reactive[MouseEvent] = MouseMoved(c)

    case class MouseDragged(_1: Component) extends Reactive[MouseEvent] {
        override def subscribe(k: Reactor[MouseEvent]): Unit = {
            val j = new MouseInputAdapter {
                override def mouseDragged(e: MouseEvent) = k.react(e)
            }
            _1.addMouseMotionListener(j)
        }
    }

    case class MouseMoved(_1: Component) extends Reactive[MouseEvent] {
        override def subscribe(k: Reactor[MouseEvent]): Unit = {
            val j = new MouseInputAdapter {
                override def mouseMoved(e: MouseEvent) = k.react(e)
            }
            _1.addMouseMotionListener(j)
        }
    }

  // MouseWheelListener

    def mouseWheelMoved(c: Component): Reactive[MouseWheelEvent] = MouseWheelMoved(c)

    case class MouseWheelMoved(_1: Component) extends Reactive[MouseWheelEvent] {
        override def subscribe(k: Reactor[MouseWheelEvent]): Unit = {
            val j = new MouseInputAdapter {
                override def mouseWheelMoved(e: MouseWheelEvent) = k.react(e)
            }
            _1.addMouseWheelListener(j)
        }
    }


// KeyEvent

    import java.awt.event.{KeyEvent, KeyAdapter}

  // KeyListener

    def keyPressed(c: Component): Reactive[KeyEvent] = KeyPressed(c)
    def keyReleased(c: Component): Reactive[KeyEvent] = KeyReleased(c)
    def keyTyped(c: Component): Reactive[KeyEvent] = KeyTyped(c)

    case class KeyPressed(_1: Component) extends Reactive[KeyEvent] {
        override def subscribe(k: Reactor[KeyEvent]): Unit = {
            val j = new KeyAdapter {
                override def keyPressed(e: KeyEvent) = k.react(e)
            }
            _1.addKeyListener(j)
        }
    }

    case class KeyReleased(_1: Component) extends Reactive[KeyEvent] {
        override def subscribe(k: Reactor[KeyEvent]): Unit = {
            val j = new KeyAdapter {
                override def keyReleased(e: KeyEvent) = k.react(e)
            }
            _1.addKeyListener(j)
        }
    }

    case class KeyTyped(_1: Component) extends Reactive[KeyEvent] {
        override def subscribe(k: Reactor[KeyEvent]): Unit = {
            val j = new KeyAdapter {
                override def keyTyped(e: KeyEvent) = k.react(e)
            }
            _1.addKeyListener(j)
        }
    }

}
