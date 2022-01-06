package iterator;

import java.util.Iterator;

public class Combined<E> implements Iterable<E> {
	private Iterable<E> first;
	private Iterable<E> second;

	public Combined(Iterable<E> first, Iterable<E> second) {
		this.first = first;
		this.second = second;
	}

	private class Itrator2 implements Iterator<E> {
		private int turn = 0;
		private Iterator<E> firstIt = first.iterator();
		private Iterator<E> secondIt = second.iterator();

		@Override
		public boolean hasNext() {
			return (firstIt.hasNext() || secondIt.hasNext());
		}

		@Override
		public E next() {
			E tempVal;
			if (turn % 2 == 0 && firstIt.hasNext() || (!secondIt.hasNext())) {// if that the first array turn and the array is not
				tempVal = firstIt.next(); // finished yet
				turn++;

			} else {// if that's the second array turn
				tempVal = secondIt.next();
				turn++;
			}
			return tempVal;
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new Itrator2();
	}

}
