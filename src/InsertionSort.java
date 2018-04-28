
public class InsertionSort extends Thread {
	public Array array;
	
	public InsertionSort(Array arr) {
		array = new Array(arr.datas.clone());
	}
	
	public void run() {
		int[] arr = array.datas;
		boolean[] focus = array.focus;
		boolean[] done = array.done;
		done[0] = true;
		
		for(int i = 1; i < arr.length; i++) {
			done[i] = true;
			int temp = arr[i];
			
			for(int j = i; j > 0; j--) {
				try {
					focus[j] = true;
					Thread.sleep(Sketch.delay);
					focus[j] = false;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
					
				if(arr[j - 1] > temp) {
					arr[j] = arr[j - 1];
					arr[j - 1] = temp;
				} else {
					break;
				}
			}
		}
	}
}
