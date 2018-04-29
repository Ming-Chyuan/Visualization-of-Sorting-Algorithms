
public class RadixSort extends Thread {
	public Array array;
	private final int max;
	
	public RadixSort(Array arr, int max) {
		array = new Array(arr.datas.clone());
		this.max = max;
	}
	
	public void run() {
		boolean[] focus = array.focus;
		boolean[] done = array.done;
		int[] arr = array.datas;
		int radix = 1;
		
		while(radix <= max) {
			int[][] buckets = new int[10][arr.length];
			int[] count = new int[10];
			
			for(int i = 0; i < arr.length; i++) {
				try {
					focus[i] = true;
					Thread.sleep(Sketch.delay);
					focus[i] = false;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				int LSD = arr[i] / radix % 10;
				buckets[LSD][count[LSD]] = arr[i];
				count[LSD]++;
			}
			radix *= 10;
			
			int dataIndex = 0;
			for(int i = 0; i < count.length; i++) {
				if(count[i] != 0) {
					for(int j = 0; j < count[i]; j++) {
						try {
							focus[dataIndex] = true;
							if(radix > max) done[dataIndex] = true;
							Thread.sleep(Sketch.delay);
							focus[dataIndex] = false;
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						arr[dataIndex] = buckets[i][j];
						dataIndex++;
					}
				}
			}
		}
	}
}
