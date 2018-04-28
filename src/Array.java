
public class Array {
	public int[] datas;
	public boolean[] focus;
	public boolean[] hold;
	public boolean[] done;
	
	public Array(int[] datas) {
		this.datas = datas;
		focus = new boolean[datas.length];
		hold = new boolean[datas.length];
		done = new boolean[datas.length];
		for(int i = 0; i < datas.length; i++) {
			focus[i] = false;
			hold[i] = false;
			done[i] = false;
		}
	}
	
	public int getLength() {
		return datas.length;
	}
}
