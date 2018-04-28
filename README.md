## Chrome Extension
[GitHub with MathJax](https://chrome.google.com/webstore/detail/github-with-mathjax/ioemnmodlmafdkllaclgeombjnmnbima) : 方便顯示數學公式  

## Project Environment
[Processing in Eclipse](https://processing.org/tutorials/eclipse/)

## Screenshot
![](https://raw.githubusercontent.com/Ming-Chyuan/Visualization-of-Sorting-Algorithms/master/img/screenshot1.png)

# Divide and Conquer

## Merge Sort
![](https://upload.wikimedia.org/wikipedia/commons/c/cc/Merge-sort-example-300px.gif)

```
array Sort(array) { // T(n)
	if length of array = 1 // T(1)
    	return array
    
	left = Sort(left of array) // T(n/2)
	right = Sort(right of array) // T(n/2)
    arr = Merge(left, right) // n
    return arr
}

array Merge(left, right) {
	leftIndex = rightIndex = 0
	l = left.length + right.length
	for 1 to l
    	if left[leftIndex] <= right[rightIndex]
        	arr.add(left[leftIndex])
            leftIndex++
        else
        	arr.add(right[rightIndex])
			rightIndex++
	return arr
}
```
if length of array is $n$
$$T(n) = 2T(\frac{n}{2}) + n$$
$$ = 2 * (2T(\frac{n}{4}) + \frac{n}{2})) + n$$
$$ = 4T(\frac{n}{4}) + 2n$$
$$ = 8T(\frac{n}{8}) + 3n$$
$$ = 2^kT(\frac{n}{2^k}) + kn$$
$T(1) = 1$, assume $n = 2^k$, then $k = \log_2^n$
$$T(n) = n + n\log n = O(n\log n)$$

## Quick Sort
