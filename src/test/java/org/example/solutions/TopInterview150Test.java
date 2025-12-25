package org.example.solutions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.image.Kernel;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    @DisplayName("55. 跳跃游戏I")
    void testCanJump() {
        boolean result = solution.canJump(new int[]{2, 3, 1, 1, 4});
        boolean result2 = solution.canJump(new int[]{3, 2, 1, 0, 4});
        assertTrue(result);
        assertFalse(result2);
    }

    @Test
    @DisplayName("45. 跳跃游戏 II")
    void testJump() {
        int result = solution.jump(new int[]{2, 3, 1, 1, 4});
        assertEquals(2, result);
    }

    @Test
    @DisplayName("274. H 指数")
    void testHIndex() {
        int result = solution.hIndex(new int[]{3, 0, 6, 1, 5});
        int result2 = solution.hIndex(new int[]{1, 3, 1});
        assertEquals(3, result);
        assertEquals(1, result2);
    }

    @Test
    @DisplayName("O(1) 时间插入、删除和获取随机元素")
    void testRandomizedSet() {
        TopInterview150.RandomizedSet randomizedSet = new TopInterview150.RandomizedSet();
        assertTrue(randomizedSet.insert(1));
        assertFalse(randomizedSet.remove(2));
        assertTrue(randomizedSet.insert(2));
        assertTrue(java.util.Arrays.asList(1, 2).contains(randomizedSet.getRandom()));
        assertTrue(randomizedSet.remove(1));
        assertFalse(randomizedSet.insert(2));
        assertEquals(2, randomizedSet.getRandom());
    }

    @Test
    @DisplayName("238. 除自身以外数组的乘积")
    void testProductExceptSelf() {
        int[] nums = new int[]{1, 2, 3, 4};
        int[] result = solution.productExceptSelf(nums);
        assertArrayEquals(new int[]{24, 12, 8, 6}, result);
    }
}
