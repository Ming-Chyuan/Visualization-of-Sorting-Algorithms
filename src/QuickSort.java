
public class QuickSort extends Thread {
	public Array array;

	public QuickSort(Array arr) {
		array = new Array(arr.datas.clone());
	}
	
	public void run() {
		sort(0, array.datas.length - 1);
	}
	
	private void sort(int lb, int rb) {
		boolean[] focus = array.focus;
		boolean[] hold = array.hold;
		boolean[] done = array.done;
		int[] arr = array.datas;
		
		if(lb >= rb) return;
		int p = arr[rb];
		int l = lb;
		int r = rb - 1;
		
		while(true) {
			while(arr[l] < p) {
				if(l == rb) break;
				l++;
			}
			while(arr[r] >= p) {
				if(r == lb) break;
				r--;
			}
			
			if(l < r) {
				try {
					focus[l] = true;
					focus[r] = true;
					hold[rb] = true;
					Thread.sleep(Sketch.delay);
					focus[l] = false;
					focus[r] = false;
					hold[rb] = false;
					swap(arr, l, r);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				break;
			}
		}
		swap(arr, rb, l);
		done[l] = true;
		
		sort(lb, l - 1);
		for(int i = lb; i <= l - 1; i++) {
			done[i] = true;
		}
		sort(l + 1, rb);
		for(int i = l + 1; i <= rb; i++) {
			done[i] = true;
		}
	}
	
	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
