/*
 *  DCircLinkList.java   ****BETA VERSION****
 *  A doubly linked, circular list abstract data type. Elements of a generic
 *  type (E).
 *
 *  by R. Heise
 *  Mar. 6, 2021 (mod. Mar. 13, 2023)
 *
 *  Public Methods:
 *  Constructors
 *  getSize() --> int
 *      Returns number of nodes in the list
 *  isEmpty() --> boolean
 *      Returns true if the list is empty; false otherwise
 *  setEntry(DNode<E>) --> void
 *      Changes the point of entry into the list
 *  getFirstElement() --> E
 *      Gives the element located at the point of entry.  No change to
 *      the list
 *  getFirstNode() --> DNode<E>
 *      Gives the node located at the point of entry.  No change to the list
 *  getLastElement() --> E
 *      Gives the element located counter-clockwise from the point of entry.
 *      No change to the list
 *  getLastNode() --> DNode<E>
 *      Gives the nod located counter-clockwise from the point of entry.
 *      No change to the list
 *  rotateCCW() --> void
 *      Rotates the list counter-clockwise, with respect to the point of entry
 *  rotateCW() --> void
 *      Rotates the list clockwise, with respect to the point of entry
 *  addFirst(E) --> void
 *      Adds a new node into the list at the point of entry.  The information
 *      stored at the node is given in the parameter
 *  addNodeAsFirst(DNode<E>) --> void
 *      Adds the given node into the list at the point of entry
 *  addLast(E) --> void
 *      Adds a new node into the list, just counter-clockwise to the point
 *      of entry.  The information stored at the node is given in the
 *      parameter
 *  removeFirstGetElement() --> E
 *      Removes the node at the point of entry.  Returns the information
 *      stored there
 *  removeFirstNode() --> DNode<E>
 *      Removes the node at the point of entry.  Returns that node
 *  toString() --> String
 *      The elements in the list, as per a clockwise traversal, starting
 *      at the point of entry
 *  toStringCCW() --> String
 *      The elements in the list, as per a counter-clockwise traversal,
 *      starting at the point of entry
 *
 *  Private method:
 *  inList(DNode<E>) --> boolean
 */

/**
 * Doubly Linked Circular List.
 * @param <E> - the data type of the information to be stored in a node
 */
public class DCircLinkList<E> {
    // Data ===================================================================
    private DNode<E> entry; //entry point into list, like a "cursor"
    private int size; //number of nodes in the list

    // Constructor ============================================================
    // an initially empty list
    public DCircLinkList() {
        entry = null;
        size = 0;
    }//constructor

    // Accessor methods =======================================================
    // Getters & Setters
    /**
     * Returns the number of elements in the list.
     * @return - number of elements in the list
     */
    public int getSize() {
        return size;
    }//getSize

    /**
     * Determines whether the list is empty.
     * @return - true if the list is empty; false otherwise
     */
    public boolean isEmpty() {
        return (size == 0);  //or entry == null
    }//isEmpty

    /**
     * Sets the starting location in the linked list, provided the newEntry
     * is in the list.  If the new entry is NOT in the list, no change is
     * made and an error is reported to standard out.
     * @param newEntry - the new node to mark as the entry into the list
     *
     * ***Future work - return something to indicate error?***
     */
    public void setEntry(DNode<E> newEntry){
        if (inList(newEntry)){
            entry = newEntry;
        }
        else{
            System.out.println("ERROR NODE " + newEntry.getElement() +
                    " NOT IN LIST");
        }
    }//setEntry

    /**
     * Returns true if aNode is in the list.
     * @param aNode - check for this node
     * @return - true if the parameter is in the list; false otherwise
     */
    private boolean inList(DNode<E> aNode){
        if (entry == null) {
            return false;
        }

        //Walk around the list, checking nodes, in clockwise direction
        DNode<E> walk = entry;
        while (walk != entry.getPrevious()){
            if (walk == aNode){//Proper use of equality - same address required
                return true;
            }
            walk = walk.getNext();
        }

        if (walk == aNode){//check last node (not a redundant check)
            return true;
        }

        return false;
    }//inList

    /**
     * Returns (but does not remove) the "first" (entry) element of the list.
     * @return - element at the front of the list (or null if list is empty)
     */
    public E getFirstElement() {
        if (isEmpty()) {
            return null;
        }
        return entry.getElement();
    }//getFirstElement

    /**
     * Returns (but does not remove) the "first" (entry) node of the list.
     * @return - "first" node; or null if list is empty
     */
    public DNode<E> getFirstNode() {
        return entry;
    }//getFirstNode

    /**
     * Returns (but does not remove) the "last" (counter clockwise from entry)
     * element of the list.
     * @return - "last" element of the list; or null if empty
     */
    public E getLastElement() {
        if (isEmpty()) {
            return null;
        }
        return entry.getPrevious().getElement(); //"tail" is just before entry
    }//getLastElement

    /**
     * Returns (but does not remove) the "last" (counter clockwise from entry)
     * node of the list.
     * @return - "last" node of the list; or null if empty
     */
    public DNode<E> getLastNode() {
        if (isEmpty()) {
            return null;
        }
        return entry.getPrevious();
    }//getLastNode

    // Update methods =========================================================
    /**
     * Rotate (counter clockwise with respect to entry) the "first" node to
     * the "back" of the list.
     */
    public void rotateCCW() {
        if (entry != null){ // if empty, do nothing
            entry = entry.getNext(); // entry moves clockwise by one, so the
            // list spins counter clockwise wrt entry
        }
    }//rotateCCW

    /**
     * Rotate (clockwise with respect to entry) the "last" node to
     * the "front" of the list.
     */
    public void rotateCW() {
        if (entry != null){ // if empty, do nothing
            entry = entry.getPrevious(); // entry moves counter clockwise by
            // one, so the list spins clockwise
            // wrt entry
        }
    }//rotateCW

    /**
     * Adds a node with an element to the "front" of the list.
     *
     * @param element - the new information to store in the new node
     */
    public void addFirst(E element) {
        if (size == 0) { // if list is empty, then this node is the only
            entry = new DNode<E>(element); //can't link to self until made
            entry.setPrevious(entry); // link it to itself
            entry.setNext(entry);     // circularly
        }
        else {
            //put node in at entry  (between the first and last nodes)
            DNode<E> newNode = new DNode<E>(element);
            newNode.setPrevious(entry.getPrevious()); //order matters
            newNode.setNext(entry);
            entry.getPrevious().setNext(newNode);
            entry.setPrevious(newNode);
            entry = newNode;
        }

        size++;
    }//addFirst

    /**
     * Adds a node with an element to the "front" of the list.
     *
     * @param newNode - the new node to add
     */
    public void addNodeAsFirst(DNode<E> newNode) {
        if (size == 0) { // if list is empty, then this node is the only
            entry = newNode;
            entry.setPrevious(entry); // link it to itself
            entry.setNext(entry);     // circularly
        }
        else {
            //put node in at entry  (between the first and last nodes)
            newNode.setPrevious(entry.getPrevious()); //order matters
            newNode.setNext(entry);
            entry.getPrevious().setNext(newNode);
            entry.setPrevious(newNode);
            entry = newNode;
        }

        size++;
    }//addFirstNode

    /**
     * Adds a node with an element to the "end" of the list, so before
     * entry.
     *
     * @param element -  the new information to store in the new node
     */
    public void addLast(E element) {
        addFirst(element); // insert new node at front of list
        // size increases in addFirst
        entry = entry.getNext(); // now new node becomes back
        //No size++, because addFirst has that
    }//addLast

    /**
     * Returns the "first" element of the list and removes that node.
     *
     * @return - the element that was removed (or null if empty)
     */
    public E removeFirstGetElement() {
        if (isEmpty()){ // nothing to remove
            return null;
        }
        // No size-- because removeFirstNode will do that
        return removeFirstNode().getElement();
    }//removeFirstGetElement

    /**
     * Returns the "first" node of the list and removes that node.
     *
     * @return - the node that was removed (or null if empty)
     */
    public DNode<E> removeFirstNode(){
        if (isEmpty()){ // nothing to remove
            return null;
        }
        DNode<E> head = entry; //save node to return result at end
        if (size == 1) { // single node
            entry = null;
        }
        else {
            //remove the "first" element
            entry = entry.getNext();
            head.getPrevious().setNext(entry);
            entry.setPrevious(head.getPrevious());
        }

        size--;
        return head;
    }

    // String Reps ============================================================
    /**
     * Produces a string representation of the clockwise contents of the list.
     * This can be very useful for debugging or developing.  This string is
     * obtained by moving through the list in a clockwise direction.
     * @return - String of the list showing clockwise order
     */
    @Override
    public String toString() {
        if (entry == null) {
            return "()";
        }

        //Walk around the list, collecting elements, in clockwise direction
        //Use StringBuilder to save on run time
        StringBuilder theList = new StringBuilder("(");
        DNode<E> walk = entry;
        while (walk != entry.getPrevious()){
            theList.append(walk.getElement());
            theList.append(", ");
            walk = walk.getNext();
        }

        theList.append(walk.getElement());
        theList.append(")");
        return theList.toString();
    }//toString

    /**
     * Produces a string representation of the counter-clockwise contents of
     * the list. This can be very useful for debugging or developing.
     * Produces the String by walking in a counter-clockwise direction.
     * @return - String of the list in counter-clockwise order
     */
    public String toStringCCW() {
        if (entry == null) {
            return "()";
        }

        //Walk around the list, collecting elements, in counter-clockwise
        //direction
        //Use StringBuilder to save on run time
        StringBuilder theList = new StringBuilder("(");
        DNode<E> walk = entry;
        while (walk != entry.getNext()){
            theList.append(walk.getElement());
            theList.append(", ");
            walk = walk.getPrevious();
        }

        theList.append(walk.getElement());
        theList.append(")");
        return theList.toString();
    }//toString
}//class
