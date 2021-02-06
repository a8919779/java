package sort;

/*
선택정렬
현재 위치에서부터 끝까지 숫자를 비교해서
제일 작은 숫자를 찾아 현재 위치와 찾은 숫자의 위치를 바꾼다
사이클이 돌때마다 가장 작은 숫자가 앞으로 오게 된다
*/

public class SelectionSort {
	public static void main(String[] args) {
		int temp = 0;
		int min;
		
		int[] arr = new int[]{1, 10, 5, 9, 8, 2, 4, 6, 3, 7};
		
		for (int i=0; i < arr.length; i++) {
			min = 999;
			if (arr[i] < min) {
				min = arr[i];
			}
			for (int j=i; j<arr.length; j++) {
				if (min>arr[j]) {
					min = arr[j];
					temp = j;
				}
			
			}
			arr[temp] = arr[i];
			arr[i] = min;
		}
		
		for (int k=0;k<arr.length;k++) {
			System.out.println(arr[k]);
		}
	}
}
