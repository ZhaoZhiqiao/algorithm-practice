package org.example.solutions;

import java.util.*;

/**
 * 力扣热门面试题150题
 */
@SuppressWarnings({"unused", "Duplicates"})
public class TopInterview150 {

    /**
     * 88. 合并两个有序数组
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int num1Index = m - 1,num2Index = n - 1, totalIndex = m + n - 1;
        while(num2Index >=0 && num1Index >= 0){
            nums1[totalIndex--] = nums1[num1Index] > nums2[num2Index] ? nums1[num1Index--] : nums2[num2Index--];
        }
        while(num1Index >= 0) nums1[totalIndex--] = nums1[num1Index--];
        while(num2Index >= 0) nums1[totalIndex--] = nums2[num2Index--];
    }
}
