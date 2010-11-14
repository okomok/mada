

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


import java.awt.{Component => AComponent, Container => AContainer}
import javax.swing.JComponent


object Swing {


// ActionEvent

    import java.awt.event.{ActionEvent, ActionListener}

    type ActionEventSource = {
        def addActionListener(l: ActionListener)
        def removeActionListener(l: ActionListener)
    }

    case class ActionPerformed(source: ActionEventSource) extends Resource[ActionEvent] {
        private[this] var l: ActionListener = null
        override protected def closeResource = source.removeActionListener(l)
        override protected def openResource(f: ActionEvent => Unit) {
            l = new ActionListener {
                override def actionPerformed(e: ActionEvent) = f(e)
            }
            source.addActionListener(l)
        }
    }


// AncestorEvent

    import javax.swing.event.{AncestorEvent, AncestorListener}

    private
    class AncestorAdapter extends AncestorListener {
        override def ancestorAdded(e: AncestorEvent) = ()
        override def ancestorMoved(e: AncestorEvent) = ()
        override def ancestorRemoved(e: AncestorEvent) = ()
    }

    case class Ancestor(source: JComponent) {

        class Event extends Resource[AncestorEvent] {
            private[this] var l: AncestorAdapter = null
            override protected def closeResource = source.removeAncestorListener(l)
            override protected def openResource(f: AncestorEvent => Unit) {
                l = new AncestorAdapter {
                    override def ancestorAdded(e: AncestorEvent) = f(e)
                    override def ancestorMoved(e: AncestorEvent) = f(e)
                    override def ancestorRemoved(e: AncestorEvent) = f(e)
                }
                source.addAncestorListener(l)
            }
        }
        def Event: Resource[AncestorEvent] = new Event

        class Added extends Resource[AncestorEvent] {
            private[this] var l: AncestorAdapter = null
            override protected def closeResource = source.removeAncestorListener(l)
            override protected def openResource(f: AncestorEvent => Unit) {
                l = new AncestorAdapter {
                    override def ancestorAdded(e: AncestorEvent) = f(e)
                }
                source.addAncestorListener(l)
            }
        }
        def Added: Resource[AncestorEvent] = new Added

        class Moved extends Resource[AncestorEvent] {
            private[this] var l: AncestorAdapter = null
            override protected def closeResource = source.removeAncestorListener(l)
            override protected def openResource(f: AncestorEvent => Unit) {
                l = new AncestorAdapter {
                    override def ancestorMoved(e: AncestorEvent) = f(e)
                }
                source.addAncestorListener(l)
            }
        }
        def Moved: Resource[AncestorEvent] = new Moved

        class Removed extends Resource[AncestorEvent] {
            private[this] var l: AncestorAdapter = null
            override protected def closeResource = source.removeAncestorListener(l)
            override protected def openResource(f: AncestorEvent => Unit) {
                l = new AncestorAdapter {
                    override def ancestorRemoved(e: AncestorEvent) = f(e)
                }
                source.addAncestorListener(l)
            }
        }
        def Removed: Resource[AncestorEvent] = new Removed

    }


// ChangeEvent

    import javax.swing.event.{ChangeEvent, ChangeListener}

    type ChangeEventSource = {
        def addChangeListener(l: ChangeListener)
        def removeChangeListener(l: ChangeListener)
    }

    case class StateChanged(source: ChangeEventSource) extends Resource[ChangeEvent] {
        private[this] var l: ChangeListener = null
        override protected def closeResource = source.removeChangeListener(l)
        override protected def openResource(f: ChangeEvent => Unit) {
            l = new ChangeListener {
                override def stateChanged(e: ChangeEvent) = f(e)
            }
            source.addChangeListener(l)
        }
    }


// ComponentEvent

    import java.awt.event.{ComponentEvent, ComponentAdapter}

    case class Component(source: AComponent) {

        class Event extends Resource[ComponentEvent] {
            private[this] var l: ComponentAdapter = null
            override protected def closeResource = source.removeComponentListener(l)
            override protected def openResource(f: ComponentEvent => Unit) {
                l = new ComponentAdapter {
                    override def componentHidden(e: ComponentEvent) = f(e)
                    override def componentMoved(e: ComponentEvent) = f(e)
                    override def componentResized(e: ComponentEvent) = f(e)
                    override def componentShown(e: ComponentEvent) = f(e)
                }
                source.addComponentListener(l)
            }
        }
        def Event: Resource[ComponentEvent] = new Event

        class Hidden extends Resource[ComponentEvent] {
            private[this] var l: ComponentAdapter = null
            override protected def closeResource = source.removeComponentListener(l)
            override protected def openResource(f: ComponentEvent => Unit) {
                l = new ComponentAdapter {
                    override def componentHidden(e: ComponentEvent) = f(e)
                }
                source.addComponentListener(l)
            }
        }
        def Hidden: Resource[ComponentEvent] = new Hidden

        class Moved extends Resource[ComponentEvent] {
            private[this] var l: ComponentAdapter = null
            override protected def closeResource = source.removeComponentListener(l)
            override protected def openResource(f: ComponentEvent => Unit) {
                l = new ComponentAdapter {
                    override def componentMoved(e: ComponentEvent) = f(e)
                }
                source.addComponentListener(l)
            }
        }
        def Moved: Resource[ComponentEvent] = new Moved

        class Resized extends Resource[ComponentEvent] {
            private[this] var l: ComponentAdapter = null
            override protected def closeResource = source.removeComponentListener(l)
            override protected def openResource(f: ComponentEvent => Unit) {
                l = new ComponentAdapter {
                    override def componentResized(e: ComponentEvent) = f(e)
                }
                source.addComponentListener(l)
            }
        }
        def Resized: Resource[ComponentEvent] = new Resized

        class Shown extends Resource[ComponentEvent] {
            private[this] var l: ComponentAdapter = null
            override protected def closeResource = source.removeComponentListener(l)
            override protected def openResource(f: ComponentEvent => Unit) {
                l = new ComponentAdapter {
                    override def componentShown(e: ComponentEvent) = f(e)
                }
                source.addComponentListener(l)
            }
        }
        def Shown: Resource[ComponentEvent] = new Shown
    }


// ContainerEvent

    import java.awt.event.{ContainerEvent, ContainerAdapter}

    case class Container(source: AContainer) {

        class Event extends Resource[ContainerEvent] {
            private[this] var l: ContainerAdapter = null
            override protected def closeResource = source.removeContainerListener(l)
            override protected def openResource(f: ContainerEvent => Unit) {
                l = new ContainerAdapter {
                    override def componentAdded(e: ContainerEvent) = f(e)
                    override def componentRemoved(e: ContainerEvent) = f(e)
                }
                source.addContainerListener(l)
            }
        }
        def Event: Resource[ContainerEvent] = new Event

        class Added extends Resource[ContainerEvent] {
            private[this] var l: ContainerAdapter = null
            override protected def closeResource = source.removeContainerListener(l)
            override protected def openResource(f: ContainerEvent => Unit) {
                l = new ContainerAdapter {
                    override def componentAdded(e: ContainerEvent) = f(e)
                }
                source.addContainerListener(l)
            }
        }
        def Added: Resource[ContainerEvent] = new Added

        class Removed extends Resource[ContainerEvent] {
            private[this] var l: ContainerAdapter = null
            override protected def closeResource = source.removeContainerListener(l)
            override protected def openResource(f: ContainerEvent => Unit) {
                l = new ContainerAdapter {
                    override def componentRemoved(e: ContainerEvent) = f(e)
                }
                source.addContainerListener(l)
            }
        }
        def Removed: Resource[ContainerEvent] = new Removed

    }


// FocusEvent

    import java.awt.event.{FocusEvent, FocusAdapter}

    case class Focus(source: AComponent) {

        class Event extends Resource[FocusEvent] {
            private[this] var l: FocusAdapter = null
            override protected def closeResource = source.removeFocusListener(l)
            override protected def openResource(f: FocusEvent => Unit) {
                l = new FocusAdapter {
                    override def focusGained(e: FocusEvent) = f(e)
                    override def focusLost(e: FocusEvent) = f(e)
                }
                source.addFocusListener(l)
            }
        }
        def Event: Resource[FocusEvent] = new Event

        class Gained extends Resource[FocusEvent] {
            private[this] var l: FocusAdapter = null
            override protected def closeResource = source.removeFocusListener(l)
            override protected def openResource(f: FocusEvent => Unit) {
                l = new FocusAdapter {
                    override def focusGained(e: FocusEvent) = f(e)
                }
                source.addFocusListener(l)
            }
        }
        def Gained: Resource[FocusEvent] = new Gained

        class Lost extends Resource[FocusEvent] {
            private[this] var l: FocusAdapter = null
            override protected def closeResource = source.removeFocusListener(l)
            override protected def openResource(f: FocusEvent => Unit) {
                l = new FocusAdapter {
                    override def focusLost(e: FocusEvent) = f(e)
                }
                source.addFocusListener(l)
            }
        }
        def Lost: Resource[FocusEvent] = new Lost

    }


// HierarchyEvent

    import java.awt.event.{HierarchyEvent, HierarchyListener, HierarchyBoundsAdapter}

    case class Hierarchy(source: AComponent) {

        class Changed extends Resource[HierarchyEvent] {
            private[this] var l: HierarchyListener = null
            override protected def closeResource = source.removeHierarchyListener(l)
            override protected def openResource(f: HierarchyEvent => Unit) {
                l = new HierarchyListener {
                    override def hierarchyChanged(e: HierarchyEvent) = f(e)
                }
                source.addHierarchyListener(l)
            }
        }
        def Changed: Resource[HierarchyEvent] = new Changed

        class Bounds extends Resource[HierarchyEvent] {
            private[this] var l: HierarchyBoundsAdapter = null
            override protected def closeResource = source.removeHierarchyBoundsListener(l)
            override protected def openResource(f: HierarchyEvent => Unit) {
                l = new HierarchyBoundsAdapter {
                    override def ancestorMoved(e: HierarchyEvent) = f(e)
                    override def ancestorResized(e: HierarchyEvent) = f(e)
                }
                source.addHierarchyBoundsListener(l)
            }
        }
        def Bounds: Resource[HierarchyEvent] = new Bounds

        class Moved extends Resource[HierarchyEvent] {
            private[this] var l: HierarchyBoundsAdapter = null
            override protected def closeResource = source.removeHierarchyBoundsListener(l)
            override protected def openResource(f: HierarchyEvent => Unit) {
                l = new HierarchyBoundsAdapter {
                    override def ancestorMoved(e: HierarchyEvent) = f(e)
                }
                source.addHierarchyBoundsListener(l)
            }
        }
        def Moved: Resource[HierarchyEvent] = new Moved

        class Resized extends Resource[HierarchyEvent] {
            private[this] var l: HierarchyBoundsAdapter = null
            override protected def closeResource = source.removeHierarchyBoundsListener(l)
            override protected def openResource(f: HierarchyEvent => Unit) {
                l = new HierarchyBoundsAdapter {
                    override def ancestorResized(e: HierarchyEvent) = f(e)
                }
                source.addHierarchyBoundsListener(l)
            }
        }
        def Resized: Resource[HierarchyEvent] = new Resized

    }


// InputMethodEvent

    import java.awt.event.{InputMethodEvent, InputMethodListener}

    private
    class InputMethodAdapter extends InputMethodListener {
        override def caretPositionChanged(e: InputMethodEvent) = ()
        override def inputMethodTextChanged(e: InputMethodEvent) = ()
    }

    case class InputMethod(source: AComponent) {

        class Event extends Resource[InputMethodEvent] {
            private[this] var l: InputMethodAdapter = null
            override protected def closeResource = source.removeInputMethodListener(l)
            override protected def openResource(f: InputMethodEvent => Unit) {
                l = new InputMethodAdapter {
                        override def caretPositionChanged(e: InputMethodEvent) = f(e)
                        override def inputMethodTextChanged(e: InputMethodEvent) = f(e)
                }
                source.addInputMethodListener(l)
            }
        }
        def Event: Resource[InputMethodEvent] = new Event

        class CaretPositionChanged extends Resource[InputMethodEvent] {
            private[this] var l: InputMethodAdapter = null
            override protected def closeResource = source.removeInputMethodListener(l)
            override protected def openResource(f: InputMethodEvent => Unit) {
                l = new InputMethodAdapter {
                    override def caretPositionChanged(e: InputMethodEvent) = f(e)
                }
                source.addInputMethodListener(l)
            }
        }
        def CaretPositionChanged: Resource[InputMethodEvent] = new CaretPositionChanged

        class TextChanged extends Resource[InputMethodEvent] {
            private[this] var l: InputMethodAdapter = null
            override protected def closeResource = source.removeInputMethodListener(l)
            override protected def openResource(f: InputMethodEvent => Unit) {
                l = new InputMethodAdapter {
                    override def inputMethodTextChanged(e: InputMethodEvent) = f(e)
                }
                source.addInputMethodListener(l)
            }
        }
        def TextChanged: Resource[InputMethodEvent] = new TextChanged

    }


// KeyEvent

    import java.awt.event.{KeyEvent, KeyAdapter}

    case class Key(source: AComponent) {

        class Event extends Resource[KeyEvent] {
            private[this] var l: KeyAdapter = null
            override protected def closeResource = source.removeKeyListener(l)
            override protected def openResource(f: KeyEvent => Unit) {
                l = new KeyAdapter {
                    override def keyPressed(e: KeyEvent) = f(e)
                    override def keyReleased(e: KeyEvent) = f(e)
                    override def keyTyped(e: KeyEvent) = f(e)
                }
                source.addKeyListener(l)
            }
        }
        def Event: Resource[KeyEvent] = new Event

        class Pressed extends Resource[KeyEvent] {
            private[this] var l: KeyAdapter = null
            override protected def closeResource = source.removeKeyListener(l)
            override protected def openResource(f: KeyEvent => Unit) {
                l = new KeyAdapter {
                    override def keyPressed(e: KeyEvent) = f(e)
                }
                source.addKeyListener(l)
            }
        }
        def Pressed: Resource[KeyEvent] = new Pressed

        class Released extends Resource[KeyEvent] {
            private[this] var l: KeyAdapter = null
            override protected def closeResource = source.removeKeyListener(l)
            override protected def openResource(f: KeyEvent => Unit) {
                l = new KeyAdapter {
                    override def keyReleased(e: KeyEvent) = f(e)
                }
                source.addKeyListener(l)
            }
        }
        def Released: Resource[KeyEvent] = new Released

        class Typed extends Resource[KeyEvent] {
            private[this] var l: KeyAdapter = null
            override protected def closeResource = source.removeKeyListener(l)
            override protected def openResource(f: KeyEvent => Unit) {
                l = new KeyAdapter {
                    override def keyTyped(e: KeyEvent) = f(e)
                }
                source.addKeyListener(l)
            }
        }
        def Typed: Resource[KeyEvent] = new Typed

    }


// MouseEvent

    import java.awt.event.{MouseEvent, MouseWheelEvent}
    import javax.swing.event.MouseInputAdapter

    case class Mouse(source: AComponent) {

        // MouseEvent

        class Event extends Resource[MouseEvent] {
            private[this] var l: MouseInputAdapter = null
            override protected def closeResource = source.removeMouseListener(l)
            override protected def openResource(f: MouseEvent => Unit) {
                l = new MouseInputAdapter {
                    override def mouseClicked(e: MouseEvent) = f(e)
                    override def mouseEntered(e: MouseEvent) = f(e)
                    override def mouseExited(e: MouseEvent) = f(e)
                    override def mousePressed(e: MouseEvent) = f(e)
                    override def mouseReleased(e: MouseEvent) = f(e)
                }
                source.addMouseListener(l)
            }
        }
        def Event: Resource[MouseEvent] = new Event

        class Clicked extends Resource[MouseEvent] {
            private[this] var l: MouseInputAdapter = null
            override protected def closeResource = source.removeMouseListener(l)
            override protected def openResource(f: MouseEvent => Unit) {
                l = new MouseInputAdapter {
                    override def mouseClicked(e: MouseEvent) = f(e)
                }
                source.addMouseListener(l)
            }
        }
        def Clicked: Resource[MouseEvent] = new Clicked

        class Entered extends Resource[MouseEvent] {
            private[this] var l: MouseInputAdapter = null
            override protected def closeResource = source.removeMouseListener(l)
            override protected def openResource(f: MouseEvent => Unit) {
                l = new MouseInputAdapter {
                    override def mouseEntered(e: MouseEvent) = f(e)
                }
                source.addMouseListener(l)
            }
        }
        def Entered: Resource[MouseEvent] = new Entered

        class Exited extends Resource[MouseEvent] {
            private[this] var l: MouseInputAdapter = null
            override protected def closeResource = source.removeMouseListener(l)
            override protected def openResource(f: MouseEvent => Unit) {
                l = new MouseInputAdapter {
                    override def mouseExited(e: MouseEvent) = f(e)
                }
                source.addMouseListener(l)
            }
        }
        def Exited: Resource[MouseEvent] = new Exited

        class Pressed extends Resource[MouseEvent] {
            private[this] var l: MouseInputAdapter = null
            override protected def closeResource = source.removeMouseListener(l)
            override protected def openResource(f: MouseEvent => Unit) {
                l = new MouseInputAdapter {
                    override def mousePressed(e: MouseEvent) = f(e)
                }
                source.addMouseListener(l)
            }
        }
        def Pressed: Resource[MouseEvent] = new Pressed

        class Released extends Resource[MouseEvent] {
            private[this] var l: MouseInputAdapter = null
            override protected def closeResource = source.removeMouseListener(l)
            override protected def openResource(f: MouseEvent => Unit) {
                l = new MouseInputAdapter {
                    override def mouseReleased(e: MouseEvent) = f(e)
                }
                source.addMouseListener(l)
            }
        }
        def Released: Resource[MouseEvent] = new Released

        // Motion MouseEvent

        class Motion extends Resource[MouseEvent] {
            private[this] var l: MouseInputAdapter = null
            override protected def closeResource = source.removeMouseMotionListener(l)
            override protected def openResource(f: MouseEvent => Unit) {
                l = new MouseInputAdapter {
                    override def mouseDragged(e: MouseEvent) = f(e)
                    override def mouseMoved(e: MouseEvent) = f(e)
                }
                source.addMouseMotionListener(l)
            }
        }
        def Motion: Resource[MouseEvent] = new Motion

        class Dragged extends Resource[MouseEvent] {
            private[this] var l: MouseInputAdapter = null
            override protected def closeResource = source.removeMouseMotionListener(l)
            override protected def openResource(f: MouseEvent => Unit) {
                l = new MouseInputAdapter {
                    override def mouseDragged(e: MouseEvent) = f(e)
                }
                source.addMouseMotionListener(l)
            }
        }
        def Dragged: Resource[MouseEvent] = new Dragged

        class Moved extends Resource[MouseEvent] {
            private[this] var l: MouseInputAdapter = null
            override protected def closeResource = source.removeMouseMotionListener(l)
            override protected def openResource(f: MouseEvent => Unit) {
                l = new MouseInputAdapter {
                    override def mouseMoved(e: MouseEvent) = f(e)
                }
                source.addMouseMotionListener(l)
            }
        }
        def Moved: Resource[MouseEvent] = new Moved

        // MouseWheelEvent

        class WheelMoved extends Resource[MouseWheelEvent] {
            private[this] var l: MouseInputAdapter = null
            override protected def closeResource = source.removeMouseWheelListener(l)
            override protected def openResource(f: MouseWheelEvent => Unit) {
                l = new MouseInputAdapter {
                    override def mouseWheelMoved(e: MouseWheelEvent) = f(e)
                }
                source.addMouseWheelListener(l)
            }
        }
        def WheelMoved: Resource[MouseEvent] = new WheelMoved

    }

}
