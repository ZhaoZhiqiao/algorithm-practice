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

    /**
     * 121. 买卖股票的最佳时机
     */
    public int maxProfit(int[] prices) {
        int finalProfit = 0, minBefore = prices[0];
        for (int i = 1; i < prices.length; i++) {
            finalProfit = Math.max(finalProfit, prices[i] - minBefore);
            minBefore = Math.min(minBefore, prices[i]);
        }
        return finalProfit;
    }

    /**
     * 122. 买卖股票的最佳时机 II
     */
    public int maxProfit2(int[] prices) {
        int finalProfit = 0, minBefore = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > minBefore) {
                finalProfit += prices[i] - minBefore;
            }
            minBefore = prices[i];
        }
        return finalProfit;
    }

    /**
     * 55. 跳跃游戏
     */
    public boolean canJump(int[] nums) {
        int maxDis = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (maxDis < i) {
                break;
            }
            maxDis = Math.max(maxDis, i + nums[i]);

        }
        return maxDis + 1 >= nums.length;
    }

    /**
     * 45. 跳跃游戏 II
     */
    public int jump(int[] nums) {
        int length = nums.length;
        int steps = 0, end = 0, maxDis = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxDis = Math.max(maxDis, i + nums[i]);
            if (i == end) {
                end = maxDis;
                steps++;
            }
        }
        return steps;
    }

    /**
     * 274. H 指数
     */
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0;
        for (int i = citations.length - 1; i >= 0; i--, h++) {
            if (h >= citations[i])
                break;
        }
        return h;
    }

    /**
     * 380. O(1) 时间插入、删除和获取随机元素
     */
    static class RandomizedSet {
        List<Integer> arrayList;
        Map<Integer, Integer> hashmap;
        Random random;

        public RandomizedSet() {
            arrayList = new ArrayList<>();
            hashmap = new HashMap<>();
            random = new Random();
        }

        public boolean insert(int val) {
            if (!hashmap.containsKey(val)) {
                arrayList.add(val);
                hashmap.put(val, arrayList.size() - 1);
                return true;
            } else {
                return false;
            }

        }

        public boolean remove(int val) {
            if (hashmap.containsKey(val)) {
                hashmap.put(arrayList.getLast(), hashmap.get(val));
                Collections.swap(arrayList, hashmap.get(val), arrayList.size() - 1);
                hashmap.remove(val);
                arrayList.removeLast();
                return true;
            } else {
                return false;
            }

        }

        public int getRandom() {
            int randomIndex = random.nextInt(arrayList.size());
            return arrayList.get(randomIndex);
        }
    }

    /**
     * 238. 除自身以外数组的乘积
     */
    public int[] productExceptSelf(int[] nums) {
        int[] answer = new int[nums.length];
        answer[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            answer[i] = nums[i + 1] * answer[i + 1];
        }
        for (int i = 0, right = 1; i < nums.length; i++) {
            answer[i] = right * answer[i];
            right *= nums[i];
        }
        return answer;
    }
}
