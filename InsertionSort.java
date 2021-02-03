/*
삽입 정렬
현재 인덱스를 앞에 위치한 값들과 비교하여 해당하는 구간에 삽입한다.
*/
public class InsertionSort {
 public method main() {
  int[] arr = new int[]{1, 10, 5, 9, 8, 2, 4, 6, 3, 7};
  int min = 0;
  int max = 0;
  int temp = 0;

  if (arr[0] > arr[1]) {
   temp = arr[0];
   arr[0] = arr[1];
   arr[1] = temp;
  }
  for (int i=2; i<arr.length; i++) {
   for (int j=0; j<i; j++) {
    if (arr[i] < arr[j]) {
    }
   }
  }
 }
}
