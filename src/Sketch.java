import processing.core.PApplet;

public class Sketch extends PApplet {
	public static int delay = 32;
	
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
	
	private int dataShape = 1;
	private final int RANDOM = 1;
	private final int FEW_UNIQUE = 2;
	private final int REVERSED = 3;
	private final int ALMOST_SORTED= 4;
	
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
		if(dataShape == RANDOM) {
			shuffleArray(arr);
		} else if(dataShape == FEW_UNIQUE) {
			fewUnique(arr);
		} else if(dataShape == REVERSED) {
			reversed(arr);
		} else if(dataShape == ALMOST_SORTED) {
			almostSorted(arr);
		}
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
		text("Press R to reset", 105, 15);
		text("Press +/- to speed up/down", 105, 30);
		int margin = 15;
		int h = 15;
		text("1: random", 305, h);
		text("2: few unique", 305, h += margin);
		h = 15;
		text("3: reversed", 405, h);
		text("4: almost sorted", 405, h += margin);
	}
	
	public void keyTyped() {
		if(key == 'r') setup();
		if(key == '-' && delay < 1024) delay *= 2;
		if(key == '+' && delay > 1) delay /= 2;
		if(key == '1') {
			dataShape = RANDOM;
			setup();
		}
		if(key == '2') {
			dataShape = FEW_UNIQUE;
			setup();
		}
		if(key == '3') {
			dataShape = REVERSED;
			setup();
		}
		if(key == '4') {
			dataShape = ALMOST_SORTED;
			setup();
		}
		
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
	
	private void fewUnique(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			arr[i] = (i * 4 / arr.length + 1) * (arr.length / 4) * rectDiffH;
		}
	}
	
	private void reversed(int[] arr) {
		int j = arr.length;
		for(int i = 0; i < arr.length; i++) {
			arr[i] = (j + 1) * rectDiffH;
			j--;
		}
	}
	
	private void almostSorted(int[] arr) {
		int j = 0;
		for(int i = 0; i < arr.length - 1; i++) {
			if(i == arr.length / 2) j = 1;
			arr[i] = (i + j + 1) * rectDiffH;
		}
		arr[arr.length - 1] = (arr.length / 2 + 1) * rectDiffH;
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
