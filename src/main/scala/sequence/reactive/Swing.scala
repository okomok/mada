

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
        override protected def closeResource() = source.removeActionListener(l)
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

        private
        class Event extends Resource[AncestorEvent] {
            private[this] var l: AncestorAdapter = null
            override protected def closeResource() = source.removeAncestorListener(l)
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

        private
        class Added extends Resource[AncestorEvent] {
            private[this] var l: AncestorAdapter = null
            override protected def closeResource() = source.removeAncestorListener(l)
            override protected def openResource(f: AncestorEvent => Unit) {
                l = new AncestorAdapter {
                    override def ancestorAdded(e: AncestorEvent) = f(e)
                }
                source.addAncestorListener(l)
            }
        }
        def Added: Resource[AncestorEvent] = new Added

        private
        class Moved extends Resource[AncestorEvent] {
            private[this] var l: AncestorAdapter = null
            override protected def closeResource() = source.removeAncestorListener(l)
            override protected def openResource(f: AncestorEvent => Unit) {
                l = new AncestorAdapter {
                    override def ancestorMoved(e: AncestorEvent) = f(e)
                }
                source.addAncestorListener(l)
            }
        }
        def Moved: Resource[AncestorEvent] = new Moved

        private
        class Removed extends Resource[AncestorEvent] {
            private[this] var l: AncestorAdapter = null
            override protected def closeResource() = source.removeAncestorListener(l)
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
        override protected def closeResource() = source.removeChangeListener(l)
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

        private
        class Event extends Resource[ComponentEvent] {
            private[this] var l: ComponentAdapter = null
            override protected def closeResource() = source.removeComponentListener(l)
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

        private
        class Hidden extends Resource[ComponentEvent] {
            private[this] var l: ComponentAdapter = null
            override protected def closeResource() = source.removeComponentListener(l)
            override protected def openResource(f: ComponentEvent => Unit) {
                l = new ComponentAdapter {
                    override def componentHidden(e: ComponentEvent) = f(e)
                }
                source.addComponentListener(l)
            }
        }
        def Hidden: Resource[ComponentEvent] = new Hidden

        private
        class Moved extends Resource[ComponentEvent] {
            private[this] var l: ComponentAdapter = null
            override protected def closeResource() = source.removeComponentListener(l)
            override protected def openResource(f: ComponentEvent => Unit) {
                l = new ComponentAdapter {
                    override def componentMoved(e: ComponentEvent) = f(e)
                }
                source.addComponentListener(l)
            }
        }
        def Moved: Resource[ComponentEvent] = new Moved

        private
        class Resized extends Resource[ComponentEvent] {
            private[this] var l: ComponentAdapter = null
            override protected def closeResource() = source.removeComponentListener(l)
            override protected def openResource(f: ComponentEvent => Unit) {
                l = new ComponentAdapter {
                    override def componentResized(e: ComponentEvent) = f(e)
                }
                source.addComponentListener(l)
            }
        }
        def Resized: Resource[ComponentEvent] = new Resized

        private
        class Shown extends Resource[ComponentEvent] {
            private[this] var l: ComponentAdapter = null
            override protected def closeResource() = source.removeComponentListener(l)
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

        private
        class Event extends Resource[ContainerEvent] {
            private[this] var l: ContainerAdapter = null
            override protected def closeResource() = source.removeContainerListener(l)
            override protected def openResource(f: ContainerEvent => Unit) {
                l = new ContainerAdapter {
                    override def componentAdded(e: ContainerEvent) = f(e)
                    override def componentRemoved(e: ContainerEvent) = f(e)
                }
                source.addContainerListener(l)
            }
        }
        def Event: Resource[ContainerEvent] = new Event

        private
        class Added extends Resource[ContainerEvent] {
            private[this] var l: ContainerAdapter = null
            override protected def closeResource() = source.removeContainerListener(l)
            override protected def openResource(f: ContainerEvent => Unit) {
                l = new ContainerAdapter {
                    override def componentAdded(e: ContainerEvent) = f(e)
                }
                source.addContainerListener(l)
            }
        }
        def Added: Resource[ContainerEvent] = new Added

        private
        class Removed extends Resource[ContainerEvent] {
            private[this] var l: ContainerAdapter = null
            override protected def closeResource() = source.removeContainerListener(l)
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

        private
        class Event extends Resource[FocusEvent] {
            private[this] var l: FocusAdapter = null
            override protected def closeResource() = source.removeFocusListener(l)
            override protected def openResource(f: FocusEvent => Unit) {
                l = new FocusAdapter {
                    override def focusGained(e: FocusEvent) = f(e)
                    override def focusLost(e: FocusEvent) = f(e)
                }
                source.addFocusListener(l)
            }
        }
        def Event: Resource[FocusEvent] = new Event

        private
        class Gained extends Resource[FocusEvent] {
            private[this] var l: FocusAdapter = null
            override protected def closeResource() = source.removeFocusListener(l)
            override protected def openResource(f: FocusEvent => Unit) {
                l = new FocusAdapter {
                    override def focusGained(e: FocusEvent) = f(e)
                }
                source.addFocusListener(l)
            }
        }
        def Gained: Resource[FocusEvent] = new Gained

        private
        class Lost extends Resource[FocusEvent] {
            private[this] var l: FocusAdapter = null
            override protected def closeResource() = source.removeFocusListener(l)
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

        private
        class Changed extends Resource[HierarchyEvent] {
            private[this] var l: HierarchyListener = null
            override protected def closeResource() = source.removeHierarchyListener(l)
            override protected def openResource(f: HierarchyEvent => Unit) {
                l = new HierarchyListener {
                    override def hierarchyChanged(e: HierarchyEvent) = f(e)
                }
                source.addHierarchyListener(l)
            }
        }
        def Changed: Resource[HierarchyEvent] = new Changed

        private
        class Bounds extends Resource[HierarchyEvent] {
            private[this] var l: HierarchyBoundsAdapter = null
            override protected def closeResource() = source.removeHierarchyBoundsListener(l)
            override protected def openResource(f: HierarchyEvent => Unit) {
                l = new HierarchyBoundsAdapter {
                    override def ancestorMoved(e: HierarchyEvent) = f(e)
                    override def ancestorResized(e: HierarchyEvent) = f(e)
                }
                source.addHierarchyBoundsListener(l)
            }
        }
        def Bounds: Resource[HierarchyEvent] = new Bounds

        private
        class Moved extends Resource[HierarchyEvent] {
            private[this] var l: HierarchyBoundsAdapter = null
            override protected def closeResource() = source.removeHierarchyBoundsListener(l)
            override protected def openResource(f: HierarchyEvent => Unit) {
                l = new HierarchyBoundsAdapter {
                    override def ancestorMoved(e: HierarchyEvent) = f(e)
                }
                source.addHierarchyBoundsListener(l)
            }
        }
        def Moved: Resource[HierarchyEvent] = new Moved

        private
        class Resized extends Resource[HierarchyEvent] {
            private[this] var l: HierarchyBoundsAdapter = null
            override protected def closeResource() = source.removeHierarchyBoundsListener(l)
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

        private
        class Event extends Resource[InputMethodEvent] {
            private[this] var l: InputMethodAdapter = null
            override protected def closeResource() = source.removeInputMethodListener(l)
            override protected def openResource(f: InputMethodEvent => Unit) {
                l = new InputMethodAdapter {
                        override def caretPositionChanged(e: InputMethodEvent) = f(e)
                        override def inputMethodTextChanged(e: InputMethodEvent) = f(e)
                }
                source.addInputMethodListener(l)
            }
        }
        def Event: Resource[InputMethodEvent] = new Event

        private
        class CaretPositionChanged extends Resource[InputMethodEvent] {
            private[this] var l: InputMethodAdapter = null
            override protected def closeResource() = source.removeInputMethodListener(l)
            override protected def openResource(f: InputMethodEvent => Unit) {
                l = new InputMethodAdapter {
                    override def caretPositionChanged(e: InputMethodEvent) = f(e)
                }
                source.addInputMethodListener(l)
            }
        }
        def CaretPositionChanged: Resource[InputMethodEvent] = new CaretPositionChanged

        private
        class TextChanged extends Resource[InputMethodEvent] {
            private[this] var l: InputMethodAdapter = null
            override protected def closeResource() = source.removeInputMethodListener(l)
            override protected def openResource(f: InputMethodEvent => Unit) {
                l = new InputMethodAdapter {
                    override def inputMethodTextChanged(e: InputMethodEvent) = f(e)
                }
                source.addInputMethodListener(l)
            }
        }
        def TextChanged: Resource[InputMethodEvent] = new TextChanged

    }


// ItemEvent

    import java.awt.event.{ItemEvent, ItemListener}

    type ItemEventSource = {
        def addItemListener(l: ItemListener)
        def removeItemListener(l: ItemListener)
    }

    case class ItemStateChanged(source: ItemEventSource) extends Resource[ItemEvent] {
        private[this] var l: ItemListener = null
        override protected def closeResource() = source.removeItemListener(l)
        override protected def openResource(f: ItemEvent => Unit) {
            l = new ItemListener {
                override def itemStateChanged(e: ItemEvent) = f(e)
            }
            source.addItemListener(l)
        }
    }


// KeyEvent

    import java.awt.event.{KeyEvent, KeyAdapter}

    case class Key(source: AComponent) {

        private
        class Event extends Resource[KeyEvent] {
            private[this] var l: KeyAdapter = null
            override protected def closeResource() = source.removeKeyListener(l)
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

        private
        class Pressed extends Resource[KeyEvent] {
            private[this] var l: KeyAdapter = null
            override protected def closeResource() = source.removeKeyListener(l)
            override protected def openResource(f: KeyEvent => Unit) {
                l = new KeyAdapter {
                    override def keyPressed(e: KeyEvent) = f(e)
                }
                source.addKeyListener(l)
            }
        }
        def Pressed: Resource[KeyEvent] = new Pressed

        private
        class Released extends Resource[KeyEvent] {
            private[this] var l: KeyAdapter = null
            override protected def closeResource() = source.removeKeyListener(l)
            override protected def openResource(f: KeyEvent => Unit) {
                l = new KeyAdapter {
                    override def keyReleased(e: KeyEvent) = f(e)
                }
                source.addKeyListener(l)
            }
        }
        def Released: Resource[KeyEvent] = new Released

        private
        class Typed extends Resource[KeyEvent] {
            private[this] var l: KeyAdapter = null
            override protected def closeResource() = source.removeKeyListener(l)
            override protected def openResource(f: KeyEvent => Unit) {
                l = new KeyAdapter {
                    override def keyTyped(e: KeyEvent) = f(e)
                }
                source.addKeyListener(l)
            }
        }
        def Typed: Resource[KeyEvent] = new Typed

    }


// MenuEvent

    import javax.swing.event.{MenuEvent, MenuListener}

    private
    class MenuAdapter extends MenuListener {
        override def menuCanceled(e: MenuEvent) = ()
        override def menuDeselected(e: MenuEvent) = ()
        override def menuSelected(e: MenuEvent) = ()
    }

    type MenuEventSource = {
        def addMenuListener(l: MenuListener)
        def removeMenuListener(l: MenuListener)
    }

    case class Menu(source: MenuEventSource) {

        private
        class Event extends Resource[MenuEvent] {
            private[this] var l: MenuAdapter = null
            override protected def closeResource() = source.removeMenuListener(l)
            override protected def openResource(f: MenuEvent => Unit) {
                l = new MenuAdapter {
                    override def menuCanceled(e: MenuEvent) = f(e)
                    override def menuDeselected(e: MenuEvent) = f(e)
                    override def menuSelected(e: MenuEvent) = f(e)
                }
                source.addMenuListener(l)
            }
        }
        def Event: Resource[MenuEvent] = new Event

        private
        class Canceled extends Resource[MenuEvent] {
            private[this] var l: MenuAdapter = null
            override protected def closeResource() = source.removeMenuListener(l)
            override protected def openResource(f: MenuEvent => Unit) {
                l = new MenuAdapter {
                    override def menuCanceled(e: MenuEvent) = f(e)
                }
                source.addMenuListener(l)
            }
        }
        def Canceled: Resource[MenuEvent] = new Canceled

        private
        class Deselected extends Resource[MenuEvent] {
            private[this] var l: MenuAdapter = null
            override protected def closeResource() = source.removeMenuListener(l)
            override protected def openResource(f: MenuEvent => Unit) {
                l = new MenuAdapter {
                    override def menuDeselected(e: MenuEvent) = f(e)
                }
                source.addMenuListener(l)
            }
        }
        def Deselected: Resource[MenuEvent] = new Deselected

        private
        class Selected extends Resource[MenuEvent] {
            private[this] var l: MenuAdapter = null
            override protected def closeResource() = source.removeMenuListener(l)
            override protected def openResource(f: MenuEvent => Unit) {
                l = new MenuAdapter {
                    override def menuSelected(e: MenuEvent) = f(e)
                }
                source.addMenuListener(l)
            }
        }
        def Selected: Resource[MenuEvent] = new Selected

    }


// MenuDragMouseEvent

    import javax.swing.event.{MenuDragMouseEvent, MenuDragMouseListener}

    private
    class MenuDragMouseAdapter extends MenuDragMouseListener {
        override def menuDragMouseDragged(e: MenuDragMouseEvent) = ()
        override def menuDragMouseEntered(e: MenuDragMouseEvent) = ()
        override def menuDragMouseExited(e: MenuDragMouseEvent) = ()
        override def menuDragMouseReleased(e: MenuDragMouseEvent) = ()
    }

    type MenuDragMouseEventSource = {
        def addMenuDragMouseListener(l: MenuDragMouseListener)
        def removeMenuDragMouseListener(l: MenuDragMouseListener)
    }

    case class MenuDragMouse(source: MenuDragMouseEventSource) {

        private
        class Event extends Resource[MenuDragMouseEvent] {
            private[this] var l: MenuDragMouseAdapter = null
            override protected def closeResource() = source.removeMenuDragMouseListener(l)
            override protected def openResource(f: MenuDragMouseEvent => Unit) {
                l = new MenuDragMouseAdapter {
                    override def menuDragMouseDragged(e: MenuDragMouseEvent) = f(e)
                    override def menuDragMouseEntered(e: MenuDragMouseEvent) = f(e)
                    override def menuDragMouseExited(e: MenuDragMouseEvent) = f(e)
                    override def menuDragMouseReleased(e: MenuDragMouseEvent) = f(e)
                }
                source.addMenuDragMouseListener(l)
            }
        }
        def Event: Resource[MenuDragMouseEvent] = new Event

        private
        class Dragged extends Resource[MenuDragMouseEvent] {
            private[this] var l: MenuDragMouseAdapter = null
            override protected def closeResource() = source.removeMenuDragMouseListener(l)
            override protected def openResource(f: MenuDragMouseEvent => Unit) {
                l = new MenuDragMouseAdapter {
                    override def menuDragMouseDragged(e: MenuDragMouseEvent) = f(e)
                }
                source.addMenuDragMouseListener(l)
            }
        }
        def Dragged: Resource[MenuDragMouseEvent] = new Dragged

        private
        class Entered extends Resource[MenuDragMouseEvent] {
            private[this] var l: MenuDragMouseAdapter = null
            override protected def closeResource() = source.removeMenuDragMouseListener(l)
            override protected def openResource(f: MenuDragMouseEvent => Unit) {
                l = new MenuDragMouseAdapter {
                    override def menuDragMouseEntered(e: MenuDragMouseEvent) = f(e)
                }
                source.addMenuDragMouseListener(l)
            }
        }
        def Entered: Resource[MenuDragMouseEvent] = new Entered

        private
        class Exited extends Resource[MenuDragMouseEvent] {
            private[this] var l: MenuDragMouseAdapter = null
            override protected def closeResource() = source.removeMenuDragMouseListener(l)
            override protected def openResource(f: MenuDragMouseEvent => Unit) {
                l = new MenuDragMouseAdapter {
                    override def menuDragMouseExited(e: MenuDragMouseEvent) = f(e)
                }
                source.addMenuDragMouseListener(l)
            }
        }
        def Exited: Resource[MenuDragMouseEvent] = new Exited

        private
        class Released extends Resource[MenuDragMouseEvent] {
            private[this] var l: MenuDragMouseAdapter = null
            override protected def closeResource() = source.removeMenuDragMouseListener(l)
            override protected def openResource(f: MenuDragMouseEvent => Unit) {
                l = new MenuDragMouseAdapter {
                    override def menuDragMouseReleased(e: MenuDragMouseEvent) = f(e)
                }
                source.addMenuDragMouseListener(l)
            }
        }
        def Released: Resource[MenuDragMouseEvent] = new Released

    }


// MenuKeyEvent

    import javax.swing.event.{MenuKeyEvent, MenuKeyListener}

    private
    class MenuKeyAdapter extends MenuKeyListener {
        override def menuKeyPressed(e: MenuKeyEvent) = ()
        override def menuKeyReleased(e: MenuKeyEvent) = ()
        override def menuKeyTyped(e: MenuKeyEvent) = ()
    }

    type MenuKeyEventSource = {
        def addMenuKeyListener(l: MenuKeyListener)
        def removeMenuKeyListener(l: MenuKeyListener)
    }

    case class MenuKey(source: MenuKeyEventSource) {

        private
        class Event extends Resource[MenuKeyEvent] {
            private[this] var l: MenuKeyAdapter = null
            override protected def closeResource() = source.removeMenuKeyListener(l)
            override protected def openResource(f: MenuKeyEvent => Unit) {
                l = new MenuKeyAdapter {
                    override def menuKeyPressed(e: MenuKeyEvent) = f(e)
                    override def menuKeyReleased(e: MenuKeyEvent) = f(e)
                    override def menuKeyTyped(e: MenuKeyEvent) = f(e)
                }
                source.addMenuKeyListener(l)
            }
        }
        def Event: Resource[MenuKeyEvent] = new Event

        private
        class Pressed extends Resource[MenuKeyEvent] {
            private[this] var l: MenuKeyAdapter = null
            override protected def closeResource() = source.removeMenuKeyListener(l)
            override protected def openResource(f: MenuKeyEvent => Unit) {
                l = new MenuKeyAdapter {
                    override def menuKeyPressed(e: MenuKeyEvent) = f(e)
                }
                source.addMenuKeyListener(l)
            }
        }
        def Pressed: Resource[MenuKeyEvent] = new Pressed

        private
        class Released extends Resource[MenuKeyEvent] {
            private[this] var l: MenuKeyAdapter = null
            override protected def closeResource() = source.removeMenuKeyListener(l)
            override protected def openResource(f: MenuKeyEvent => Unit) {
                l = new MenuKeyAdapter {
                    override def menuKeyReleased(e: MenuKeyEvent) = f(e)
                }
                source.addMenuKeyListener(l)
            }
        }
        def Released: Resource[MenuKeyEvent] = new Released

        private
        class Typed extends Resource[MenuKeyEvent] {
            private[this] var l: MenuKeyAdapter = null
            override protected def closeResource() = source.removeMenuKeyListener(l)
            override protected def openResource(f: MenuKeyEvent => Unit) {
                l = new MenuKeyAdapter {
                    override def menuKeyTyped(e: MenuKeyEvent) = f(e)
                }
                source.addMenuKeyListener(l)
            }
        }
        def Typed: Resource[MenuKeyEvent] = new Typed

    }


// MouseEvent

    import java.awt.event.{MouseEvent, MouseWheelEvent}
    import javax.swing.event.MouseInputAdapter

    case class Mouse(source: AComponent) {

        private
        class Event extends Resource[MouseEvent] {
            private[this] var l: MouseInputAdapter = null
            override protected def closeResource() = source.removeMouseListener(l)
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

        private
        class Clicked extends Resource[MouseEvent] {
            private[this] var l: MouseInputAdapter = null
            override protected def closeResource() = source.removeMouseListener(l)
            override protected def openResource(f: MouseEvent => Unit) {
                l = new MouseInputAdapter {
                    override def mouseClicked(e: MouseEvent) = f(e)
                }
                source.addMouseListener(l)
            }
        }
        def Clicked: Resource[MouseEvent] = new Clicked

        private
        class Entered extends Resource[MouseEvent] {
            private[this] var l: MouseInputAdapter = null
            override protected def closeResource() = source.removeMouseListener(l)
            override protected def openResource(f: MouseEvent => Unit) {
                l = new MouseInputAdapter {
                    override def mouseEntered(e: MouseEvent) = f(e)
                }
                source.addMouseListener(l)
            }
        }
        def Entered: Resource[MouseEvent] = new Entered

        private
        class Exited extends Resource[MouseEvent] {
            private[this] var l: MouseInputAdapter = null
            override protected def closeResource() = source.removeMouseListener(l)
            override protected def openResource(f: MouseEvent => Unit) {
                l = new MouseInputAdapter {
                    override def mouseExited(e: MouseEvent) = f(e)
                }
                source.addMouseListener(l)
            }
        }
        def Exited: Resource[MouseEvent] = new Exited

        private
        class Pressed extends Resource[MouseEvent] {
            private[this] var l: MouseInputAdapter = null
            override protected def closeResource() = source.removeMouseListener(l)
            override protected def openResource(f: MouseEvent => Unit) {
                l = new MouseInputAdapter {
                    override def mousePressed(e: MouseEvent) = f(e)
                }
                source.addMouseListener(l)
            }
        }
        def Pressed: Resource[MouseEvent] = new Pressed

        private
        class Released extends Resource[MouseEvent] {
            private[this] var l: MouseInputAdapter = null
            override protected def closeResource() = source.removeMouseListener(l)
            override protected def openResource(f: MouseEvent => Unit) {
                l = new MouseInputAdapter {
                    override def mouseReleased(e: MouseEvent) = f(e)
                }
                source.addMouseListener(l)
            }
        }
        def Released: Resource[MouseEvent] = new Released

        // Motion MouseEvent

        private
        class Motion extends Resource[MouseEvent] {
            private[this] var l: MouseInputAdapter = null
            override protected def closeResource() = source.removeMouseMotionListener(l)
            override protected def openResource(f: MouseEvent => Unit) {
                l = new MouseInputAdapter {
                    override def mouseDragged(e: MouseEvent) = f(e)
                    override def mouseMoved(e: MouseEvent) = f(e)
                }
                source.addMouseMotionListener(l)
            }
        }
        def Motion: Resource[MouseEvent] = new Motion

        private
        class Dragged extends Resource[MouseEvent] {
            private[this] var l: MouseInputAdapter = null
            override protected def closeResource() = source.removeMouseMotionListener(l)
            override protected def openResource(f: MouseEvent => Unit) {
                l = new MouseInputAdapter {
                    override def mouseDragged(e: MouseEvent) = f(e)
                }
                source.addMouseMotionListener(l)
            }
        }
        def Dragged: Resource[MouseEvent] = new Dragged

        private
        class Moved extends Resource[MouseEvent] {
            private[this] var l: MouseInputAdapter = null
            override protected def closeResource() = source.removeMouseMotionListener(l)
            override protected def openResource(f: MouseEvent => Unit) {
                l = new MouseInputAdapter {
                    override def mouseMoved(e: MouseEvent) = f(e)
                }
                source.addMouseMotionListener(l)
            }
        }
        def Moved: Resource[MouseEvent] = new Moved

        // MouseWheelEvent

        private
        class WheelMoved extends Resource[MouseWheelEvent] {
            private[this] var l: MouseInputAdapter = null
            override protected def closeResource() = source.removeMouseWheelListener(l)
            override protected def openResource(f: MouseWheelEvent => Unit) {
                l = new MouseInputAdapter {
                    override def mouseWheelMoved(e: MouseWheelEvent) = f(e)
                }
                source.addMouseWheelListener(l)
            }
        }
        def WheelMoved: Resource[MouseEvent] = new WheelMoved

    }


// PopupMenuEvent

    import javax.swing.event.{PopupMenuEvent, PopupMenuListener}

    private
    class PopupMenuAdapter extends PopupMenuListener {
        override def popupMenuCanceled(e: PopupMenuEvent) = ()
        override def popupMenuWillBecomeInvisible(e: PopupMenuEvent) = ()
        override def popupMenuWillBecomeVisible(e: PopupMenuEvent) = ()
    }

    type PopupMenuEventSource = {
        def addPopupMenuListener(l: PopupMenuListener)
        def removePopupMenuListener(l: PopupMenuListener)
    }

    case class PopupMenu(source: PopupMenuEventSource) {

        private
        class Event extends Resource[PopupMenuEvent] {
            private[this] var l: PopupMenuAdapter = null
            override protected def closeResource() = source.removePopupMenuListener(l)
            override protected def openResource(f: PopupMenuEvent => Unit) {
                l = new PopupMenuAdapter {
                    override def popupMenuCanceled(e: PopupMenuEvent) = f(e)
                    override def popupMenuWillBecomeInvisible(e: PopupMenuEvent) = f(e)
                    override def popupMenuWillBecomeVisible(e: PopupMenuEvent) = f(e)
                }
                source.addPopupMenuListener(l)
            }
        }
        def Event: Resource[PopupMenuEvent] = new Event

        private
        class Canceled extends Resource[PopupMenuEvent] {
            private[this] var l: PopupMenuAdapter = null
            override protected def closeResource() = source.removePopupMenuListener(l)
            override protected def openResource(f: PopupMenuEvent => Unit) {
                l = new PopupMenuAdapter {
                    override def popupMenuCanceled(e: PopupMenuEvent) = f(e)
                }
                source.addPopupMenuListener(l)
            }
        }
        def Canceled: Resource[PopupMenuEvent] = new Canceled

        private
        class WillBecomeInvisible extends Resource[PopupMenuEvent] {
            private[this] var l: PopupMenuAdapter = null
            override protected def closeResource() = source.removePopupMenuListener(l)
            override protected def openResource(f: PopupMenuEvent => Unit) {
                l = new PopupMenuAdapter {
                    override def popupMenuWillBecomeVisible(e: PopupMenuEvent) = f(e)
                }
                source.addPopupMenuListener(l)
            }
        }
        def WillBecomeInvisible: Resource[PopupMenuEvent] = new WillBecomeInvisible

        private
        class WillBecomeVisible extends Resource[PopupMenuEvent] {
            private[this] var l: PopupMenuAdapter = null
            override protected def closeResource() = source.removePopupMenuListener(l)
            override protected def openResource(f: PopupMenuEvent => Unit) {
                l = new PopupMenuAdapter {
                    override def popupMenuWillBecomeVisible(e: PopupMenuEvent) = f(e)
                }
                source.addPopupMenuListener(l)
            }
        }
        def WillBecomeVisible: Resource[PopupMenuEvent] = new WillBecomeVisible

    }

}
