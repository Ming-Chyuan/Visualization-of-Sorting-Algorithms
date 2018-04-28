
public class BubbleSort extends Thread {
	public Array array;
	private boolean swapped;
		
	public BubbleSort(Array arr) {
		array = new Array(arr.datas.clone());
	}
	
	public void run() {
		int[] arr = array.datas;
		boolean[] focus = array.focus;
		boolean[] done = array.done;
		
		for(int i = 0; i < arr.length; i++) {
			swapped = false;
			for(int j = 0; j < arr.length - 1 - i; j++) {
				if(arr[j] > arr[j + 1]) {
					swap(arr, j , j + 1);
				}

				try {
					focus[j + 1] = true;
					Thread.sleep(Sketch.delay);
					focus[j + 1] = false;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			done[arr.length - 1 - i] = true;
			if(!swapped) {
				for(int j = 0; j < arr.length - 1 - i; j++) {
					done[j] = true;
				}
				
				break;
			}
		}
	}
	
	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		swapped = true;
	}
}
