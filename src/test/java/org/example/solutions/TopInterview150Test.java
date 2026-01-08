package org.example.solutions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings({"SpellCheckingInspection"})
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


    @Test
    @DisplayName("134. 加油站")
    void testCanCompleteCircuit() {
        int[] gas = new int[]{1, 2, 3, 4, 5};
        int[] cost = new int[]{3, 4, 5, 1, 2};
        int result = solution.canCompleteCircuit(gas, cost);
        assertEquals(3, result);
    }

    @Test
    @DisplayName("135. 分发糖果")
    void testCandy() {
        int[] ratings = new int[]{1, 0, 2};
        int result = solution.candy(ratings);
        assertEquals(5, result);
    }

    @Test
    @DisplayName("42. 接雨水")
    void testTrap() {
        int[] height = new int[]{4, 2, 0, 3, 2, 5};
        int result = solution.trap(height);
        assertEquals(9, result);
    }

    @Test
    @DisplayName("13. 罗马数字转整数")
    void testRomanToInt() {
        String s = "MCMXCIV";
        int result = solution.romanToInt(s);
        assertEquals(1994, result);
    }

    @Test
    @DisplayName("12. 整数转罗马数字")
    void testIntToRoman() {
        int num = 3749;
        String result = solution.intToRoman(num);
        assertEquals("MMMDCCXLIX", result);
    }

    @Test
    @DisplayName("58. 最后一个单词的长度")
    void testLengthOfLastWord() {
        String s = "   fly me   to   the moon  ";
        int result = solution.lengthOfLastWord(s);
        assertEquals(4, result);
    }

    @Test
    @DisplayName("14. 最长公共前缀")
    void testLongestCommonPrefix() {
        String[] strs = new String[]{"flower", "flow", "flight"};
        String result = solution.longestCommonPrefix(strs);
        assertEquals("fl", result);
    }

    @Test
    @DisplayName("151. 反转字符串中的单词")
    void testReverseWords() {
        String s = "a good   example ";
        String result = solution.reverseWords(s);
        assertEquals("example good a", result);
    }

    @Test
    @DisplayName("6. Z 字形变换")
    void testConvert() {
        String s = "PAYPALISHIRING";
        int numRows = 3;
        String result = solution.convert(s, numRows);
        assertEquals("PAHNAPLSIIGYIR", result);
    }

    @Test
    @DisplayName("28. 找出字符串中第一个匹配项的下标")
    void testStrStr() {
        String haystack = "sadbutsad";
        String needle = "sad";
        int result = solution.strStr(haystack, needle);
        assertEquals(0, result);
    }

    @Test
    @DisplayName("68. 文本左右对齐")
    void testFullJustify() {
        String[] words = new String[]{"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth = 16;
        List<String> result = solution.fullJustify(words, maxWidth);
        List<String> expected = List.of(new String[]{"This    is    an", "example  of text", "justification.  "});
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("125. 验证回文串")
    void testIsPalindrome() {
        String s = "A man, a plan, a canal: Panama";
        boolean result = solution.isPalindrome(s);
        assertTrue(result);
    }

    @Test
    @DisplayName("392. 判断子序列")
    void testIsSubsequence() {
        String s = "abc";
        String t = "ahbgdc";
        boolean result = solution.isSubsequence(s, t);
        assertTrue(result);
    }

    @Test
    @DisplayName("167. 两数之和 II - 输入有序数组")
    void testTwoSum() {
        int[] numbers = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] result = solution.twoSum(numbers, target);
        assertArrayEquals(new int[]{1, 2}, result);
    }

    @Test
    @DisplayName("11. 盛最多水的容器")
    void testMaxArea() {
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        int result = solution.maxArea(height);
        assertEquals(49, result);
    }

    @Test
    @DisplayName("15. 三数之和")
    void testThreeSum() {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = solution.threeSum(nums);
        List<List<Integer>> expected = List.of(
                List.of(-1, -1, 2),
                List.of(-1, 0, 1)
        );
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("209. 长度最小的子数组")
    void testMinSubArrayLen() {
        int target = 7;
        int[] nums = new int[]{2, 3, 1, 2, 4, 3};
        int result = solution.minSubArrayLen(target, nums);
        assertEquals(2, result);
    }

    @Test
    @DisplayName("3. 无重复字符的最长子串")
    void testLengthOfLongestSubstring() {
        String s = "abcabcbb";
        int result = solution.lengthOfLongestSubstring(s);
        assertEquals(3, result);
    }

    @Test
    @DisplayName("30. 串联所有单词的子串")
    void testFindSubstring() {
        String s = "barfoofoobarthefoobarman";
        String[] words = new String[]{"bar", "foo", "the"};
        List<Integer> result = solution.findSubstring(s, words);
        List<Integer> expected = List.of(6, 9, 12);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("76. 最小覆盖子串")
    void testMinWindow() {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        String result = solution.minWindow(s, t);
        assertEquals("BANC", result);
    }

    @Test
    @DisplayName("36. 有效的数独")
    void testIsValidSudoku() {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        boolean result = solution.isValidSudoku(board);
        assertTrue(result);
    }

    @Test
    @DisplayName("54. 螺旋矩阵")
    void testSpiralOrder() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        List<Integer> result = solution.spiralOrder(matrix);
        List<Integer> expected = List.of(1, 2, 3, 6, 9, 8, 7, 4, 5);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("48. 旋转图像")
    void testRotate2() {
        int[][] matrix = {
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };
        solution.rotate2(matrix);
        int[][] expected = {
                {15, 13, 2, 5},
                {14, 3, 4, 1},
                {12, 6, 8, 9},
                {16, 7, 10, 11}
        };
        assertArrayEquals(expected, matrix);
    }

    @Test
    @DisplayName("73. 矩阵置零")
    void testSetZeroes() {
        int[][] matrix = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        solution.setZeroes(matrix);
        int[][] expected = {
                {1, 0, 1},
                {0, 0, 0},
                {1, 0, 1}
        };
        assertArrayEquals(expected, matrix);
    }

    @Test
    @DisplayName("289. 生命游戏")
    void testGameOfLife() {
        int[][] board = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };
        solution.gameOfLife(board);
        int[][] expected = {
                {0, 0, 0},
                {1, 0, 1},
                {0, 1, 1},
                {0, 1, 0}
        };
        assertArrayEquals(expected, board);
    }

    @Test
    @DisplayName("383. 赎金信")
    void testCanConstruct() {
        String ransomNote = "a";
        String magazine = "b";
        boolean result = solution.canConstruct(ransomNote, magazine);
        assertFalse(result);
    }

}
