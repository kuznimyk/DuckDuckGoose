/**
 *  DNode.java   ****BETA VERSION****
 *  Doubly linked node, which stores a reference to its element,
 *  to the following node, and to the previous node when part of a list.
 *
 *  by R. Heise
 *  Mar. 6, 2021
 *
 *  Public methods:
 *  Constructors
 *  getElement() --> E
 *  getPrevious() --> DNode<E>
 *  getNext() --> DNodw<E>
 *  setElement(E) --> void
 *  setPrevious(DNode<E>) --> void
 *  setNext(DNode<E>) --> void
 *  toString() --> String
 *
 */

public class DNode<E> {
    // Data ===================================================================

    private E element; //Node information that is stored
    private DNode<E> previous; //link to previous node
    private DNode<E> next; //link to following node


    //Constructors ============================================================
    /**
     * Creates a node with the given element, previous and next nodes.
     *
     * @param element -  the element to be stored
     * @param previous - reference to node coming before
     * @param next -  reference to node coming after
     */
    public DNode(E element, DNode<E> previous, DNode<E> next){
        this.element = element;
        this.previous = previous;
        this.next = next;
    }
    public DNode(E element){
        this(element, null, null); //only has info, no links set
    }
    public DNode(){
        this(null, null, null); //empty DNode
    }

    // Accessor methods =======================================================
    // Getters
    /**
     * Returns the element stored at the node.
     * @return - the element stored at the node
     */
    public E getElement() {
        return element;
    }

    /**
     * Returns the node before this one (or null if no such node).
     * @return - the node before
     */
    public DNode<E> getPrevious() {
        return previous;
    }

    /**
     * Returns the node after this one (or null if no such node).
     * @return - the node after
     */
    public DNode<E> getNext() {
        return next;
    }

    // Modifier methods =======================================================
    // Setters
    /**
     * Sets the node's element
     * @param element - information stored in the node
     */
    public void setElement(E element) {
        this.element = element;
    }

    /**
     * Sets the node's previous reference to point to node before.
     * @param previous - the node before
     */
    public void setPrevious(DNode<E> previous) {
        this.previous = previous;
    }

    /**
     * Sets the node's next reference to point to Node n.
     * @param next - the node that should follow this one
     */
    public void setNext(DNode<E> next) {
        this.next = next;
    }

    /**
     * String representation of the node.
     * @return = word "Node" followed by element, as String
     */
    @Override
    public String toString(){
        return "(Node " + element + ")";
    }
}//class
