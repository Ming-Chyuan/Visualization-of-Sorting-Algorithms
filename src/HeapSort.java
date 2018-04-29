
public class HeapSort extends Thread {
	public Array array;
	private int[] heap;
	
	public HeapSort(Array arr) {
		array = new Array(arr.datas.clone());
	}
	
	public void run() {
		boolean[] focus = array.focus;
		boolean[] hold = array.hold;
		boolean[] done = array.done;
		
		buildMaxHeap();
		for(int i = heap.length - 1; i >= 2; i--) {
			hold[i - 1] = false;
			done[i - 1] = true;
			try {
				focus[0] = true;
				focus[i - 1] = true;
				Thread.sleep(Sketch.delay);
				focus[0] = false;
				focus[i - 1] = false;
				showHeap();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			swap(heap, 1, i);
			try {
				focus[0] = true;
				focus[i - 1] = true;
				Thread.sleep(Sketch.delay);
				focus[0] = false;
				focus[i - 1] = false;
				showHeap();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			maxHeapify(heap, 1, i);
		}
		
		hold[0] = false;
		done[0] = true;
	}
	
	private void showHeap() {
		for(int i = 1; i < heap.length; i++) {
			array.datas[i - 1] = heap[i];
		}
	}
	
	private void showHeap(int end) {
		for(int i = 1; i < i; i++) {
			array.datas[i - 1] = heap[i];
		}
	}
	
	private void buildMaxHeap() {
		boolean[] focus = array.focus;
		boolean[] hold = array.hold;
		
		heap = new int[array.datas.length + 1];
		for(int i = 1; i < heap.length; i++) {
			heap[i] = array.datas[i - 1];
			
			try {
				focus[i - 1] = true;
				hold[i - 1] = true;
				Thread.sleep(Sketch.delay);
				focus[i - 1] = false;
				showHeap(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for(int i = heap.length / 2; i >= 1; i--) {
			maxHeapify(heap, i, heap.length);
		}
	}
	
	private void maxHeapify(int[] heap, int root, int length) {
		boolean[] focus = array.focus;
		
		int left = root * 2;
		int right = root * 2 + 1;
		int largest;
		
		if(left <= length - 1 && heap[left] > heap[root]) {
			largest = left;
		} else {
			largest = root;
		}
		
		if(right <= length - 1 && heap[right] > heap[largest]) {
			largest = right;
		}
		
		if(largest != root) {
			try {
				showHeap();
				focus[largest - 1] = true;
				focus[root - 1] = true;
				Thread.sleep(Sketch.delay);
				focus[largest - 1] = false;
				focus[root - 1] = false;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			swap(heap, root, largest);
			maxHeapify(heap, largest, length);
		}

	}
	
	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
