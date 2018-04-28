
public class ShellSort extends Thread {
	public Array array;
	
	public ShellSort(Array arr) {
		array = new Array(arr.datas.clone());
	}
	
	public void run() {
		boolean[] focus = array.focus;
		boolean[] hold = array.hold;
		boolean[] done = array.done;
		int[] arr = array.datas;
		
		int gap = arr.length / 2;
		
		while(gap > 0) {
			if(gap == 1) done[0] = true;
			for(int i = gap; i < arr.length; i++) {
				focus[i] = true;
				boolean finished = false;
				if(gap == 1 && i == arr.length - 1) finished = true;
				
				int temp = arr[i];
				for(int j = i; j >= gap; j -= gap) {
					if(gap == 1) done[j] = true;
					
					if(temp <  arr[j - gap]) {
						swap(arr, j, j - gap);

						if(gap == 1 && !finished) done[j] = false;
						try {
							hold[j - gap] = true;
							Thread.sleep(Sketch.delay);
							hold[j - gap] = false;
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				focus[i] = false;
			}
			gap /= 2;
		}
	}
	
	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
