import processing.core.PApplet;

public class Sketch extends PApplet {
	private Array array;
	private final int rectW = 3;
	private final int rectDiffH = 2;
	private BubbleSort bubbleSort;
	private SelectionSort selectionSort;
	private CocktailSort cocktailSort;
	private MergeSort mergeSort;
	private QuickSort quickSort;
	private RadixSort radixSort;
	private InsertionSort insertionSort;
	private ShellSort shellSort;
	private HeapSort heapSort;
	private final int DatasLength = 100;
	private final int margin = 30;
	public static int delay = 32;
	
	public static void main(String[] args) {
		PApplet.main("Sketch");
	}

	public void settings() {
		size(3 * (margin + rectW * DatasLength) + margin, 3 * (margin + rectDiffH * DatasLength) + margin);
	}

	public void setup() {
		background(0);
		surface.setTitle("Visualization of Sorting Algorithms");
		
		int arr[] = new int[DatasLength];
		initializeArray(arr);
		shuffleArray(arr);
		array = new Array(arr);
		
		bubbleSort = new BubbleSort(array);
		bubbleSort.start();
		selectionSort = new SelectionSort(array);
		selectionSort.start();
		cocktailSort = new CocktailSort(array);
		cocktailSort.start();
		mergeSort = new MergeSort(array);
		mergeSort.start();
		quickSort = new QuickSort(array);
		quickSort.start();
		radixSort = new RadixSort(array, DatasLength * rectDiffH);
		radixSort.start();
		insertionSort = new InsertionSort(array);
		insertionSort.start();
		shellSort = new ShellSort(array);
		shellSort.start();
		heapSort = new HeapSort(array);
		heapSort.start();
	}
	
	public void draw() {
		background(0);
		drawDatas(bubbleSort.array, 0, 0, "Bubble Sort");
		drawDatas(selectionSort.array, 1, 0, "Selection Sort");
		drawDatas(cocktailSort.array, 2, 0, "Cocktail Sort");
		drawDatas(mergeSort.array, 0, 1, "Merge Sort");
		drawDatas(quickSort.array, 1, 1, "Quick Sort");
		drawDatas(radixSort.array, 2, 1, "Radix Sort");
		drawDatas(insertionSort.array, 0, 2, "Insertion Sort");
		drawDatas(shellSort.array, 1, 2, "Shell Sort");
		drawDatas(heapSort.array, 2, 2, "Heap Sort");

		text("delay: " + delay + "ms", 5, 15);
		text("Press R to reset", 100, 15);
		text("Press +/- to speed up/down", 100, 30);
	}
	
	public void keyTyped() {
		if(key == 'r') setup();
		if(key == '-' && delay < 1024) delay *= 2;
		if(key == '+' && delay > 1) delay /= 2;
	}
	
	private void initializeArray(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			arr[i] = (i + 1) * rectDiffH;
		}
	}
	
	private void shuffleArray(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			int s = (i + (int)random(arr.length)) % arr.length;
			int temp = arr[i];
			arr[i] = arr[s];
			arr[s] = temp;
		}
	}
	
	private void drawDatas(Array arr, int row, int col, String s) {
		for(int i = 0; i < arr.datas.length; i++) {
			if(arr.focus[i]) {
				fill(255, 0, 0);
			} else if(arr.hold[i]) { 
				fill(0, 0, 255);
			} else if(arr.done[i]) {
				fill(0, 255, 0);
			} else {
				fill(255);	
			}
			rect(col * (margin + rectW * DatasLength) + i * rectW + margin,
				(row + 1) * (margin + rectDiffH * DatasLength) - arr.datas[i],
				rectW,
				arr.datas[i]);
		}
		fill(255);
		strokeWeight(1);
		text(s,
			col * (margin + rectW * DatasLength) + rectW * DatasLength / 2,
			(row + 1) * (margin + rectDiffH * DatasLength) + margin / 2);
	}
}
