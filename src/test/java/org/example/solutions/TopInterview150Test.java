package org.example.solutions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

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
        int[] nums = {0};
        int val = 0;
        int k = solution.removeElement(nums, val);
        assertArrayEquals(new int[]{}, java.util.Arrays.copyOf(nums, k));
    }

}
