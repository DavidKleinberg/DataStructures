//David Kleinberg
//dkleinb1@jhu.edu

public final class ArrayDequeue<T> implements Dequeue<T> {
	
	private SimpleArray<T> deck;
	private int front;
	private int back;
	private int length;

	/**
	 * Make Checkstyle happy.
	 */
	public ArrayDequeue() {
		this.deck = new SimpleArray<T>(1, (T) new Object());
	} 

    /**
     *  No elements?
     *  @return True if no elements, false otherwise.
     */
    public boolean empty() {
    	if (this.length == 0) {
    		return true;
    	}
    	return false;
    }

    /**
     *  Has the array reached its limits?
     *  @return True if yes, false otherwise.
     */
    private boolean full() {
    	if (this.length == this.deck.length()) {
    		return true;
    	}
    	return false;
    }

    /**
     *  How many elements?
     *  @return Number of elements in the queue.
     */
    public int length() {
    	//return (this.deck.length() - this.front + this.back) % this.deck.length();
    	return this.length;
    }

    /**
     *  Front element?
     *  @throws EmptyException If queue is empty.
     *  @return First element in the queue.
     */
    public T front() throws EmptyException {
    	if (this.empty()) {
    		throw new EmptyException();
    	}
    	return this.deck.get(this.front);
    }

    /**
     *  Back element?
     *  @throws EmptyException If queue is empty.
     *  @return Last element in the queue.
     */
    public T back() throws EmptyException {
    	if (this.empty()) {
    		throw new EmptyException();
    	}
    	return this.deck.get((this.back - 1 + this.deck.length()) % this.deck.length());
    }

    /**
     *  Insert a new front element.
     *  @param t Element to insert.
     */
    public void insertFront(T t) {
		if (this.full()) {
			this.grow();
		}
		this.front = (this.front - 1 + this.deck.length()) % this.deck.length();
		this.deck.put(this.front, t);
		this.length++;
    }

    /**
     *  Insert a new back element.
     *  @param t Element to insert.
     */
    public void insertBack(T t) {
    	if (this.full()) {
    		this.grow();
    	}
    	this.deck.put(this.back, t);
    	this.back = (this.back + 1) % this.deck.length();
    	this.length++;

    }

    private void grow() {
    	SimpleArray<T> temp = new SimpleArray<T>(this.length * 2, (T) new Object());
    	for (int i = 0; i < this.length; i++) {
    		temp.put(i, this.deck.get(this.front));
    		this.front = (this.front + 1) % this.deck.length();
    	}
    	this.deck = temp;
		this.front = 0;
		this.back = this.length;
    }

    /**
     *  Remove front element.
     *  @throws EmptyException If queue is empty.
     */
    public void removeFront() throws EmptyException {
    	if (this.empty()) {
    		throw new EmptyException();
    	}
    	this.front = (this.front + 1) % this.deck.length();
    	this.length--;
    }

    /**
     *  Remove back element.
     *  @throws EmptyException If queue is empty.
     */
    public void removeBack() throws EmptyException {
    	if (this.empty()) {
    		throw new EmptyException();
    	}
    	this.back = (this.back - 1 + this.deck.length()) % this.deck.length();
    	this.length--;
    }

    public String toString() {
    	String string = "[";
    	int f = this.front;
    	for (int i = 0; i < this.length; i++) {
    		string += this.deck.get(f);
    		f = (f + 1) % this.deck.length();
    		if (i != this.length - 1) {
    			string += ", ";
    		}
    	}
    	string += "]";
    	return string;
    }
}