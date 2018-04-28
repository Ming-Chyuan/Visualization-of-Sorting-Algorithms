
public class CocktailSort extends Thread {
	public Array array;

	public CocktailSort(Array arr) {
		array = new Array(arr.datas.clone());
	}
	
	public void run() {
		boolean[] focus = array.focus;
		boolean[] done = array.done;
		int[] arr = array.datas;
		
		int left = 0;
		int right = arr.length - 1;
		while (left < right) {
			boolean swapped = false;
			for(int i = left; i < right; i++) {
				if (arr[i] > arr[i + 1]) {
					swap(arr, i, i+1);
					swapped = true;
				}
				try {
					focus[i + 1] = true;
					Thread.sleep(Sketch.delay);
					focus[i + 1] = false;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			done[right] = true;
			right--;
			for(int i = right; i > left; i--) {
				if (arr[i] < arr[i - 1]) {
					swap(arr, i, i-1);
					swapped = true;
				}
				try {
					focus[i - 1] = true;
					Thread.sleep(Sketch.delay);
					focus[i - 1] = false;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			done[left] = true;
			left++;
			if(!swapped) {
				for(int i = left; i <= right; i++) {
					done[i] = true;
				}
				break;
			}
		}
	}
	
	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
