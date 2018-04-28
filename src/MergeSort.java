
public class MergeSort extends Thread {
	public Array array;
	
	public MergeSort(Array arr) {
		array = new Array(arr.datas.clone());
	}

	public void run() {
		int[] arr = array.datas;
		arr = sort(arr, 0);
	}
	
	private int[] sort(int[] arr, int index) {
		if(arr.length == 1) return arr;
		
		int mid = arr.length / 2;
		int[] left = new int[mid];
		int[] right = new int[arr.length - mid];
		for(int i = 0; i < left.length; i++) {
			left[i] = arr[i];
		}
		for(int i = 0; i < right.length; i++) {
			right[i] = arr[mid + i];
		}
		
		int leftIndex = index;
		int rightIndex = index + mid;
		return merge(sort(left, leftIndex), leftIndex, sort(right, rightIndex), rightIndex);
	}
	
	private int[] merge(int[] l, int leftIndex, int[] r, int rightIndex) {
		boolean[] focus = array.focus;
		boolean[] hold = array.hold;
		boolean[] done = array.done;
		int[] arr = array.datas;
		int[] newArr = new int[l.length + r.length];
		int leftCount = 0;
		int rightCount = 0;

		for(int i = 0; i < newArr.length; i++) {
			if(leftCount == l.length) {
				newArr[i] = r[rightCount++];
			} else if(rightCount == r.length) {
				newArr[i] = l[leftCount++];
			} else if(l[leftCount] < r[rightCount]) {
				newArr[i] = l[leftCount++];
			} else {
				newArr[i] = r[rightCount++];
			}

			arr[leftIndex + i] = newArr[i];
			
			int j = leftCount;
			int k = 1;
			while(j < l.length) {
				arr[leftIndex + i + k] = l[j];
				j++;
				k++;
			}
			j = rightCount;
			while(j < r.length) {
				arr[leftIndex + i + k] = r[j];
				j++;
				k++;
			}
			try {
				focus[leftIndex + i] = true;
				if(leftCount < l.length) hold[leftIndex + i + 1] = true;
				if(rightCount < r.length) hold[leftIndex + i + (l.length - leftCount) + 1] = true;
				done[leftIndex + i] = true;
				Thread.sleep(Sketch.delay);
				if(leftCount < l.length) hold[leftIndex + i + 1] = false;
				if(rightCount < r.length) hold[leftIndex + i + (l.length - leftCount) + 1] = false;
				focus[leftIndex + i] = false;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for(int i = 0; i < newArr.length && newArr.length < arr.length; i++) {
			done[leftIndex + i] = false;
		}
		
		return newArr;
	}
}
