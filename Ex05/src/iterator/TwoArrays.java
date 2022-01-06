package iterator;

import java.util.Iterator;

public class TwoArrays implements Iterable<Integer> {
	private int[] a1;
	private int[] a2;

	public TwoArrays(int[] a1, int[] a2) {
		this.a1 = a1;
		this.a2 = a2;
	}

	private class Itrator1 implements Iterator<Integer> {
		private int curIndexFirstArray=0, curIndexSecondArray=0, turn = 0;

		@Override
		public boolean hasNext() {
			return ((curIndexFirstArray + curIndexSecondArray) < (a1.length + a2.length));
		}

		@Override
		public Integer next() {
			int tempVal=0;
			if ((turn % 2 == 0 && curIndexFirstArray < a1.length)||(curIndexSecondArray>=a2.length)) {// if that the first array turn and the array is not
																	// finished yet
				turn++;
				tempVal=a1[curIndexFirstArray++];
			}
			else {//if that's the second array turn
				turn++;
				tempVal=a2[curIndexSecondArray++];
			}
			return tempVal;
		}

	}

	@Override
	public Iterator<Integer> iterator() {
		return new Itrator1();
	}

}
