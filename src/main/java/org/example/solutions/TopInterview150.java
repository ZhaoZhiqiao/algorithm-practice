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
        int num1Index = m - 1, num2Index = n - 1, totalIndex = m + n - 1;
        while (num2Index >= 0 && num1Index >= 0) {
            nums1[totalIndex--] = nums1[num1Index] > nums2[num2Index] ? nums1[num1Index--] : nums2[num2Index--];
        }
        while (num1Index >= 0) nums1[totalIndex--] = nums1[num1Index--];
        while (num2Index >= 0) nums1[totalIndex--] = nums2[num2Index--];
    }

    /**
     * 27. 移除元素
     */
    public int removeElement(int[] nums, int val) {
        int validIndex = 0, lastIndex = nums.length - 1;
        while (validIndex <= lastIndex) {
            if (nums[validIndex] == val) {
                nums[validIndex] = nums[lastIndex--];
                continue;
            }
            validIndex++;
        }
        return validIndex;
    }

    /**
     * 26. 删除有序数组中的重复项
     */
    public int removeDuplicates(int[] nums) {
        int validIndex = 0, currentNum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != currentNum) {
                currentNum = nums[i];
                nums[++validIndex] = nums[i];
            }
        }
        return validIndex + 1;
    }

    /**
     * 80. 删除有序数组中的重复项 II
     */
    public int removeDuplicates2(int[] nums) {
        final int REPEAT_TIMES = 2;
        int validIndex = 0, repeatTimes = 1, currentNum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != currentNum) {
                currentNum = nums[i];
                nums[++validIndex] = nums[i];
                repeatTimes = 1;
            } else if (nums[i] == currentNum && repeatTimes < REPEAT_TIMES) {
                nums[++validIndex] = nums[i];
                repeatTimes++;
            }
        }
        return validIndex + 1;
    }

    /**
     * 169. 多数元素
     */
    public int majorityElement(int[] nums) {
        int currentNum = nums[0], counter = 1;
        for (int i = 1; i < nums.length; i++) {
            if (counter == 0) {
                currentNum = nums[i];
                counter = 1;
            } else {
                counter += nums[i] == currentNum ? 1 : -1;
            }
        }
        return currentNum;
    }

    /**
     * 189. 轮转数组
     */
    public void rotate(int[] nums, int k) {
        int factor = nums.length, a = k;
        while (a != 0) {
            int temp = a;
            a = factor % a;
            factor = temp;
        }

        int beforeIndex, nextIndex, before, next;
        for (int startIndex = 0; startIndex < factor; startIndex++) {
            beforeIndex = startIndex;
            nextIndex = (beforeIndex + k) % nums.length;
            before = nums[beforeIndex];
            next = nums[nextIndex];

            while (startIndex != nextIndex) {
                nums[nextIndex] = before;
                before = next;
                beforeIndex = nextIndex;
                nextIndex = (beforeIndex + k) % nums.length;
                next = nums[nextIndex];
            }
            nums[startIndex] = before;
        }
    }

}
