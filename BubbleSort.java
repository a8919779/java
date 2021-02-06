/*
버블정렬
현재위치에서 바로 옆 숫자와 비교하여 작은 숫자를 앞으로 보낸다
결과적으로 사이클을 돌때마다 큰숫자가 가장 끝으로 이동한다
*/
public class BubbleSort {
 
 int[] arr = new int[]{1, 10, 5, 9, 8, 2, 4, 6, 3, 7};
 int temp = 0;

 for (int i=0; i<arr.length; i++) {
  for (int j=0; j<(arr.length-i-1); j++) {
   if (arr[j] > arr[j+1]) {
    temp = arr[j];
    arr[j] = arr[j+1];
    arr[j+1] = temp;
   }
  }
 }
}
