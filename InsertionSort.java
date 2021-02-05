/*
삽입 정렬
현재 인덱스를 앞에 위치한 값들과 비교하여 해당하는 구간에 삽입한다.
*/
public class InsertionSort {
 public method main() {
  int[] arr = new int[]{1, 10, 5, 9, 8, 2, 4, 6, 3, 7};
  int j = 0;
  int temp = 0;

  for (int i=0; i<arr.length-1; i++) {
   j = i;
   while (arr[j] > arr[j+1]) {
    temp = arr[j];
    arr[j] = arr[j+1];
    arr[j+1] = temp;
    j--;
   }
  }
  
  for (int k=0; k<arr.length; k++) {
   System.out.println(arr[k]);
  }
 }
}
