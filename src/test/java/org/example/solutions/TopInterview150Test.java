package org.example.solutions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.image.Kernel;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TopInterview150Test {

    private TopInterview150 solution;

    @BeforeEach
    void setUp() {
        solution = new TopInterview150();
    }

    @Test
    @DisplayName("88. 合并两个有序数组")
    void testMerge() {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        solution.merge(nums1, 3, nums2, 3);
        assertArrayEquals(new int[]{1, 2, 2, 3, 5, 6}, nums1);
    }

    @Test
    @DisplayName("27. 移除元素")
    void testRemoveElement() {
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        int val = 2;
        int k = solution.removeElement(nums, val);
        assertEquals(5, k);
        assertArrayEquals(new int[]{0, 1, 4, 0, 3}, java.util.Arrays.copyOf(nums, k));
    }

    @Test
    @DisplayName("26. 删除有序数组中的重复项")
    void testRemoveDuplicates() {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int k = solution.removeDuplicates(nums);
        assertEquals(5, k);
        assertArrayEquals(new int[]{0, 1, 2, 3, 4}, java.util.Arrays.copyOf(nums, k));
    }

    @Test
    @DisplayName("80. 删除有序数组中的重复项 II")
    void testRemoveDuplicates2() {
        int[] nums = {0, 0, 1, 1, 1, 1, 2, 3, 3};
        int k = solution.removeDuplicates2(nums);
        assertEquals(7, k);
        assertArrayEquals(new int[]{0, 0, 1, 1, 2, 3, 3}, java.util.Arrays.copyOf(nums, k));
    }

    @Test
    @DisplayName("169. 多数元素")
    void testMajorityElement() {
        int[] nums = {2, 2, 1, 1, 2, 2, 2, 3, 2, 3};
        int result = solution.majorityElement(nums);
        assertEquals(2, result);
    }

    @Test
    @DisplayName("189. 轮转数组")
    void testRotate() {
        int[] nums = {1, 2, 3, 4, 5, 6};
        int k = 2;
        solution.rotate(nums, k);
        assertArrayEquals(new int[]{5, 6, 1, 2, 3, 4}, java.util.Arrays.copyOf(nums, nums.length));
    }

    @Test
    @DisplayName("121. 买卖股票的最佳时机")
    void testMaxProfit() {
        int[] nums = {7, 1, 5, 3, 6, 4};
        int result = solution.maxProfit(nums);
        assertEquals(5, result);
    }

    @Test
    @DisplayName("122. 买卖股票的最佳时机 II")
    void testMaxProfit2() {
        int[] nums = {7, 1, 5, 3, 6, 4};
        int result = solution.maxProfit2(nums);
        assertEquals(7, result);
    }
}
