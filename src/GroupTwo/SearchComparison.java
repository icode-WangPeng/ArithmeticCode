package GroupTwo;

import java.util.Random;

public class SearchComparison {
    // 生成有序数组
    public static int[] generateArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        return array;
    }

    // 二分查找算法
    public static int binarySearch(int[] array, int target,int r) {
        int left = 0;
        int right = r;
        int count=0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            count++;
            if (array[mid] == target) {
                return count;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return count;
    }

    // 三分查找算法
    public static int ternarySearch(int[] array, int target,int r) {
        int left = 0;
        int right = r;
        int count=0;

        while (left <= right) {
            count++;
            int mid1 = left + (right - left) / 3;
            int mid2 = right - (right - left) / 3;

            if (array[mid1] >target) {
                right = mid1 - 1;
            } else if (array[mid2] < target) {
                count++;
                left = mid2 + 1;
            } else if (target == array[mid1]) {
                return count;
            } else if (target == array[mid2]) {
                count++;
                return count;
            } else {
                count++;
                left = mid1 + 1;
                right = mid2 - 1;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Random random=new Random();
        int[] array = generateArray(300000); // 生成有序数组，数组大小为 1000000
        for(int size=3000;size<=300000;size+=1000){
            int binaryCount=0,ternaryCount=0;
            for(int i=0;i<50;i++){
                int target = random.nextInt(size); // 目标元素

                // 二分查找算法
                binaryCount+=binarySearch(array,target,size-1);

                // 三分查找算法
                ternaryCount+=ternarySearch(array,target,size-1);
            }

            System.out.println( binaryCount+" "+ternaryCount);
        }
    }

}

