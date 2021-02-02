/*
현재 위치에서부터 끝까지 숫자를 비교해서
제일 작은 숫자를 찾아 현재 위치와 찾은 숫자의 위치를 바꾼다
*/

public class SelectedSort {
 public void al() {
  int temp;
  int min;

  int arr[10] = new int[1, 10, 5, 9, 8, 2, 4, 6, 3, 7];

  for (int i=0; i < arr.size(); i++) {
    min = 999;
    if (arr[i] < min) {
     min = arr[i];
    }
    for (int j=i; j<arr.size(); j++) {
     if (min>arr[j+1]) {
      min = arr[j+1];
      temp = j+1;
     }
    arr[temp] = arr[i];
    arr[i] = min;
    }
  }

  for (int k=0;k<arr.size();k++){
   System.out.println(arr[k]);
  }
 }
}
