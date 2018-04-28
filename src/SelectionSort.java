
public class SelectionSort extends Thread {
	public Array array;
	
	public SelectionSort(Array arr) {
		array = new Array(arr.datas.clone());
	}

	public void run() {
		int[] arr = array.datas;
		boolean[] focus = array.focus;
		boolean[] hold = array.hold;
		boolean[] done = array.done;
		
		for(int i = 0; i < arr.length - 1; i++) {
			int minIndex = i;
			hold[minIndex] = true;
			for(int j = i + 1; j < arr.length; j++) {
				if(arr[j] < arr[minIndex]) {
					hold[minIndex] = false;
					minIndex = j;
					hold[minIndex] = true;
				}
				
				try {
					focus[j] = true;
					Thread.sleep(Sketch.delay);
					focus[j] = false;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			hold[minIndex] = false;
			swap(arr, i, minIndex);
			done[i] = true;
		}
		done[arr.length - 1] = true;
	}
	
	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
