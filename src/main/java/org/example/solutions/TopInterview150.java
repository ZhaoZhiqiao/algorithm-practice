package org.example.solutions;

import java.util.*;
import java.util.stream.Collectors;


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
            if (h >= citations[i]) break;
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

    /**
     * 134. 加油站
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int start = 0, currentGas, currentPos;
        boolean flag = false;
        while (start < gas.length) {
            currentGas = gas[start];
            currentPos = start;
            while (currentGas >= cost[currentPos]) {
                currentGas -= cost[currentPos];
                currentPos = (currentPos + 1) % cost.length;
                currentGas += gas[currentPos];
                if (currentPos == start) {
                    flag = true;
                    break;
                }
            }
            if (!flag && currentPos + 1 > start) {
                start = currentPos + 1;
            } else {
                break;
            }

        }
        return flag ? start : -1;
    }

    /**
     * 135. 分发糖果
     */
    public int candy(int[] ratings) {
        int length = ratings.length, result = 0;
        int[] right = new int[length], left = new int[length];
        right[0] = 1;
        left[length - 1] = 1;
        for (int i = 1; i < length; i++) {
            right[i] = ratings[i] > ratings[i - 1] ? right[i - 1] + 1 : 1;
        }
        for (int i = length - 2; i >= 0; i--) {
            left[i] = ratings[i] > ratings[i + 1] ? left[i + 1] + 1 : 1;
        }
        for (int i = 0; i < length; i++) {
            result += Math.max(right[i], left[i]);
        }
        return result;
    }

    /**
     * 42. 接雨水
     */
    public int trap(int[] height) {
        int length = height.length, result = 0, RMax = height[0], LMax = height[length - 1];
        int[] right = new int[length], left = new int[length];
        right[0] = 0;
        left[length - 1] = 0;
        for (int i = 1; i < length; i++) {
            RMax = Math.max(RMax, height[i - 1]);
            right[i] = RMax;
        }
        for (int i = length - 2; i >= 0; i--) {
            LMax = Math.max(LMax, height[i + 1]);
            left[i] = LMax;
        }
        for (int i = 1; i < length - 1; i++) {
            result += Math.max(Math.min(right[i], left[i]) - height[i], 0);
        }

        return result;
    }

    /**
     * 13. 罗马数字转整数
     */
    public int romanToInt(String s) {
        Map<Character, Integer> dict = Map.of('I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100, 'D', 500, 'M', 1000);
        int result = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length() - 1; i++) {
            int cur = dict.get(chars[i]), next = dict.get(chars[i + 1]);
            result += cur < next ? -cur : cur;
        }
        result += dict.get(chars[chars.length - 1]);

        return result;
    }

    /**
     * 12. 整数转罗马数字
     */
    public String intToRoman(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        Map<Integer, String> dict = new HashMap<>();
        dict.putAll(Map.of(1, "I", 4, "IV", 5, "V", 9, "IX", 10, "X", 40, "XL", 50, "L"));
        dict.putAll(Map.of(90, "XC", 100, "C", 400, "CD", 500, "D", 900, "CM", 1000, "M"));

        List<Integer> keys = new ArrayList<>(dict.keySet());
        keys.sort(Collections.reverseOrder());

        for (Integer key : keys) {
            while (num >= key) {
                num -= key;
                stringBuilder.append(dict.get(key));
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 58. 最后一个单词的长度
     */
    public int lengthOfLastWord(String s) {
        String[] words = s.trim().split("\\s");
        return words[words.length - 1].length();
    }

    /**
     * 14. 最长公共前缀
     */
    public String longestCommonPrefix(String[] strs) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean flag = true;
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (String word : strs) {
                if (i >= word.length() || word.charAt(i) != c) {
                    flag = false;
                    break;
                }
            }
            if (flag) stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    /**
     * 151. 反转字符串中的单词
     */
    public String reverseWords(String s) {
        return String.join(" ", Arrays.asList(s.trim().split("\\s+")).reversed());
    }

    /**
     * 6. Z 字形变换
     */
    public String convert(String s, int numRows) {
        if (numRows < 2) return s;
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++)
            rows.add(new StringBuilder());
        int i = 0, flag = -1;
        for (char c : s.toCharArray()) {
            rows.get(i).append(c);
            if (i == 0 || i == numRows - 1) flag = -flag;
            i += flag;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows)
            res.append(row);
        return res.toString();
    }

    /**
     * 28. 找出字符串中第一个匹配项的下标
     */
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    /**
     * 68. 文本左右对齐
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int right = 0, n = words.length;
        while (true) {
            int left = right;
            int sumLen = 0;
            while (right < n && sumLen + words[right].length() + right - left <= maxWidth) {
                sumLen += words[right++].length();
            }

            if (right == n) {
                StringBuilder stringBuilder = new StringBuilder(words[left]);
                for (int i = left + 1; i < n; ++i) {
                    stringBuilder.append(" ");
                    stringBuilder.append(words[i]);
                }
                int remainSpace = maxWidth - stringBuilder.length();
                stringBuilder.append(" ".repeat(Math.max(0, remainSpace)));
                ans.add(stringBuilder.toString());
                return ans;
            }

            int numWords = right - left;
            int numSpaces = maxWidth - sumLen;

            if (numWords == 1) {
                ans.add(words[left] + " ".repeat(Math.max(0, numSpaces)));
                continue;
            }

            int avgSpaces = numSpaces / (numWords - 1);
            int extraSpaces = numSpaces % (numWords - 1);
            StringBuilder sb = new StringBuilder();


            StringBuilder extraSpaceStr = new StringBuilder();
            extraSpaceStr.append(" ".repeat(Math.max(0, avgSpaces + 1)));
            StringBuilder extraPart = new StringBuilder(words[left]);
            for (int i = left + 1; i < left + extraSpaces + 1; ++i) {
                extraPart.append(extraSpaceStr);
                extraPart.append(words[i]);
            }
            sb.append(extraPart);

            StringBuilder normalSpaceStr = new StringBuilder();
            normalSpaceStr.append(" ".repeat(Math.max(0, avgSpaces)));
            sb.append(normalSpaceStr);

            if (left + extraSpaces + 1 < right) {
                StringBuilder normalPart = new StringBuilder(words[left + extraSpaces + 1]);
                for (int i = left + extraSpaces + 2; i < right; ++i) {
                    normalPart.append(normalSpaceStr);
                    normalPart.append(words[i]);
                }
                sb.append(normalPart);
            }

            ans.add(sb.toString());
        }
    }

    /**
     * 125. 验证回文串
     */
    public boolean isPalindrome(String s) {
        int front = 0, behind = s.length() - 1;
        boolean flag = true;
        while (front < behind && flag) {
            char frontChar = s.charAt(front);
            char behindChar = s.charAt(behind);
            if (!Character.isDigit(frontChar) && !Character.isLetter(frontChar)) {
                front++;
                continue;
            }
            if (!Character.isDigit(behindChar) && !Character.isLetter(behindChar)) {
                behind--;
                continue;
            }
            if (Character.toLowerCase(frontChar) != Character.toLowerCase(behindChar)) {
                flag = false;
            } else {
                front++;
                behind--;
            }
        }
        return flag;
    }

    /**
     * 392. 判断子序列
     */
    public boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        while (i < t.length() && j < s.length()) {
            j += t.charAt(i) == s.charAt(j) ? 1 : 0;
            i++;
        }
        return j == s.length();
    }

    /**
     * 167. 两数之和 II - 输入有序数组
     */
    public int[] twoSum(int[] numbers, int target) {
        int ahead = 0, behind = numbers.length - 1;
        while (ahead < behind) {
            int sum = numbers[ahead] + numbers[behind];
            if (sum > target) behind--;
            else if (sum < target) ahead++;
            else break;
        }
        return new int[]{ahead + 1, behind + 1};
    }

    /**
     * 11. 盛最多水的容器
     */
    public int maxArea(int[] height) {
        int ahead = 0, behind = height.length - 1, result = 0;
        while (ahead < behind) {
            result = Math.max(result, Math.min(height[ahead], height[behind]) * (behind - ahead));
            if (height[ahead] > height[behind]) behind--;
            else ahead++;
        }
        return result;
    }

    /**
     * 15. 三数之和
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int last = nums[0];
        for (int first = 0; first < nums.length - 2 && nums[first] <= 0; first++) {
            if (first != 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            int second = first + 1, third = nums.length - 1;
            while (second < third) {
                if (second != first + 1 && nums[second] == nums[second - 1]) {
                    second++;
                    continue;
                }
                int sum = nums[first] + nums[second] + nums[third];
                if (sum > 0) third--;
                else if (sum < 0) second++;
                else {
                    result.add(List.of(nums[first], nums[second], nums[third]));
                    third--;
                    second++;
                }
            }
        }
        return result;
    }

    /**
     * 209. 长度最小的子数组
     */
    public int minSubArrayLen(int target, int[] nums) {
        int minLen = Integer.MAX_VALUE, start = 0, end = 0, sum = nums[0];
        if (sum > target) {
            return 1;
        }
        while (end < nums.length - 1) {
            if (sum < target) {
                sum += nums[++end];
            }
            while (sum >= target) {
                minLen = Math.min(minLen, end - start + 1);
                sum -= nums[start++];
            }
        }
        return minLen != Integer.MAX_VALUE ? minLen : 0;
    }

    /**
     * 3. 无重复字符的最长子串
     */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        char[] chars = s.toCharArray();
        int result = 0;
        for (int right = 0, left = 0; left < chars.length; left++) {
            while (set.contains(chars[left])) {
                set.remove(chars[right++]);
            }
            set.add(chars[left]);
            result = Math.max(result, left - right + 1);
        }
        return result;
    }

    /**
     * 30. 串联所有单词的子串
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int wordNums = words.length, wordLength = words[0].length(), strLength = s.length();
        for (int i = 0; i < wordLength; i++) {
            if (i + wordNums * wordLength > strLength) {
                break;
            }
            Map<String, Integer> differ = new HashMap<>();
            for (int j = 0; j < wordNums; j++) {
                String word = s.substring(i + j * wordLength, i + (j + 1) * wordLength);
                differ.put(word, differ.getOrDefault(word, 0) + 1);
            }
            for (String word : words) {
                differ.put(word, differ.getOrDefault(word, 0) - 1);
                if (differ.get(word) == 0) {
                    differ.remove(word);
                }
            }
            for (int start = i; start < strLength - wordNums * wordLength + 1; start += wordLength) {
                if (start != i) {
                    String word = s.substring(start + (wordNums - 1) * wordLength, start + wordNums * wordLength);
                    differ.put(word, differ.getOrDefault(word, 0) + 1);
                    if (differ.get(word) == 0) {
                        differ.remove(word);
                    }
                    word = s.substring(start - wordLength, start);
                    differ.put(word, differ.getOrDefault(word, 0) - 1);
                    if (differ.get(word) == 0) {
                        differ.remove(word);
                    }
                }
                if (differ.isEmpty()) {
                    res.add(start);
                }
            }
        }
        return res;
    }

    /**
     * 76. 最小覆盖子串
     */
    public String minWindow(String s, String t) {
        int[] result = {-1, s.length()};

        HashMap<Character, Integer> count = new HashMap<>();
        for (Character letter : t.toCharArray()) {
            count.put(letter, count.getOrDefault(letter, 0) + 1);
        }
        int lack = count.size();

        int left = 0, right = 0;
        while (right < s.length()) {
            Character letter = s.charAt(right);
            count.put(letter, count.getOrDefault(letter, 0) - 1);
            if (count.getOrDefault(letter, 0) == 0) {
                lack -= 1;
            }
            while (lack == 0) {
                if (right - left < result[1] - result[0]) {
                    result[0] = left;
                    result[1] = right;
                }
                letter = s.charAt(left);
                if (count.getOrDefault(letter, 0) == 0) {
                    lack++;
                }
                count.put(letter, count.getOrDefault(letter, 0) + 1);
                left++;
            }

            right++;
        }
        return result[0] < 0 ? "" : s.substring(result[0], result[1] + 1);
    }

    /**
     * 36. 有效的数独
     */
    public boolean isValidSudoku(char[][] board) {
        Set<Character> row = new HashSet<>(9);
        Map<Integer, Set<Character>> columns = new HashMap<>(9);
        Map<Integer, Set<Character>> blocks = new HashMap<>(9);
        for (int index = 0; index < 9; index++) {
            columns.put(index, new HashSet<>(9));
            blocks.put(index, new HashSet<>(9));
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char currentChar = board[i][j];
                int blockIndex = (i / 3) * 3 + j / 3;
                if (currentChar == '.') {
                    continue;
                }
                if (row.contains(currentChar) || columns.get(j).contains(currentChar) || blocks.get(blockIndex).contains(currentChar)) {
                    return false;
                }
                row.add(currentChar);
                columns.get(j).add(currentChar);
                blocks.get(blockIndex).add(currentChar);
            }
            row.clear();
        }
        return true;
    }

    /**
     * 54. 螺旋矩阵
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int column = left; column <= right; column++) {
                order.add(matrix[top][column]);
            }
            for (int row = top + 1; row <= bottom; row++) {
                order.add(matrix[row][right]);
            }
            if (left < right && top < bottom) {
                for (int column = right - 1; column > left; column--) {
                    order.add(matrix[bottom][column]);
                }
                for (int row = bottom; row > top; row--) {
                    order.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }

    /**
     * 48. 旋转图像
     */
    public void rotate2(int[][] matrix) {
        int rowLength = matrix.length - 1, colLength = matrix[0].length - 1;
        for (int row = 0; row <= rowLength; row++) {
            for (int col = 0; col <= colLength / 2; col++) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[row][colLength - col];
                matrix[row][colLength - col] = temp;
            }
        }

        for (int row = 0; row < rowLength; row++) {
            for (int col = 0; col < colLength - row; col++) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[colLength - col][rowLength - row];
                matrix[colLength - col][rowLength - row] = temp;
            }
        }
    }

    /**
     * 73. 矩阵置零
     */
    public void setZeroes(int[][] matrix) {
        int zeroRow = -1, zeroCol = -1;
        for (int row = 0; row < matrix.length && zeroRow == -1; row++) {
            for (int col = 0; col < matrix[0].length && zeroCol == -1; col++) {
                if (matrix[row][col] == 0) {
                    zeroRow = row;
                    zeroCol = col;
                }
            }
        }
        if (zeroRow == -1) return;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == 0) {
                    matrix[zeroRow][col] = 0;
                    matrix[row][zeroCol] = 0;
                }
            }
        }

        for (int row = 0; row < matrix.length; row++) {
            if (row == zeroRow) continue;
            if (matrix[row][zeroCol] == 0) {
                for (int col = 0; col < matrix[0].length; col++) {
                    matrix[row][col] = 0;
                }
            }
        }

        for (int col = 0; col < matrix[0].length; col++) {
            if (col == zeroCol) continue;
            if (matrix[zeroRow][col] == 0) {
                for (int row = 0; row < matrix.length; row++) {
                    matrix[row][col] = 0;
                }
            }
        }

        for (int row = 0; row < matrix.length; row++) {
            matrix[row][zeroCol] = 0;
        }

        for (int col = 0; col < matrix[0].length; col++) {
            matrix[zeroRow][col] = 0;
        }
    }

    /**
     * 289. 生命游戏
     */
    public void gameOfLife(int[][] board) {
        int[] neighbors = {0, 1, -1};
        int rows = board.length;
        int cols = board[0].length;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int liveNeighbors = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {

                        if (!(neighbors[i] == 0 && neighbors[j] == 0)) {
                            int r = (row + neighbors[i]);
                            int c = (col + neighbors[j]);
                            if ((r < rows && r >= 0) && (c < cols && c >= 0) && (Math.abs(board[r][c]) == 1)) {
                                liveNeighbors += 1;
                            }
                        }
                    }
                }
                if ((board[row][col] == 1) && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    board[row][col] = -1;
                }
                if (board[row][col] == 0 && liveNeighbors == 3) {
                    board[row][col] = 2;
                }
            }
        }
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col] > 0) {
                    board[row][col] = 1;
                } else {
                    board[row][col] = 0;
                }
            }
        }
    }

    /**
     * 383. 赎金信
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> dict = new HashMap<>();
        for (Character c : ransomNote.toCharArray()) {
            dict.put(c, dict.getOrDefault(c, 0) + 1);
        }
        for (Character c : magazine.toCharArray()) {
            if (dict.containsKey(c)) {
                dict.put(c, dict.get(c) - 1);
                if (dict.get(c) == 0) {
                    dict.remove(c);
                }
            }
        }
        return dict.isEmpty();
    }

    /**
     * 205. 同构字符串
     */
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> dict = new HashMap<>();
        int index = 0;
        while (index < s.length()) {
            if (dict.containsKey(s.charAt(index))) {
                if (dict.get(s.charAt(index)) != t.charAt(index)) break;
            } else {
                dict.put(s.charAt(index), t.charAt(index));
            }
            index++;
        }
        return index == s.length() && new HashSet<>(dict.values()).size() == dict.size();
    }

    /**
     * 290. 单词规律
     */
    public boolean wordPattern(String pattern, String s) {
        HashMap<Character, String> dict = new HashMap<>();
        String[] words = s.split("\\s");
        if (words.length != pattern.length()) return false;
        int index = 0;
        while (index < pattern.length()) {
            if (dict.containsKey(pattern.charAt(index))) {
                if (!dict.get(pattern.charAt(index)).equals(words[index])) break;
            } else dict.put(pattern.charAt(index), words[index]);
            index++;
        }
        return index == pattern.length() && new HashSet<>(dict.values()).size() == dict.size();
    }

    /**
     * 有效的字母异位词
     */
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (Character c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) - 1);
            if (map.get(c) == 0) map.remove(c);
        }
        return map.isEmpty();
    }

    /**
     * 49. 字母异位词分组
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        StringBuilder stringBuilder;
        for (String str : strs) {
            char[] s = str.toCharArray();
            Arrays.sort(s);
            map.put(Arrays.toString(s), map.getOrDefault(Arrays.toString(s), new ArrayList<>()));
            map.get(Arrays.toString(s)).add(str);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 1. 两数之和
     */
    public int[] twoSumUnsorted(int[] nums, int target) {
        Map<Integer, Integer> dict = new HashMap<>();
        for (int index = 0; index < nums.length; index++) {
            int need = target - nums[index];
            if (dict.containsKey(need)) return new int[]{index, dict.get(need)};
            else dict.put(nums[index], index);
        }
        return new int[]{-1, -1};
    }

    /**
     * 202. 快乐数
     */
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        int sum = n, temp;
        while (!set.contains(sum)) {
            set.add(sum);
            temp = 0;
            while (sum != 0) {
                temp += (int) Math.pow((sum % 10), 2);
                sum = sum / 10;
            }
            sum = temp;
        }
        return sum == 1;
    }

    /**
     * 219. 存在重复元素 II
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> dict = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (dict.containsKey(nums[i])) {
                if (i - dict.get(nums[i]) <= k) return true;
                else dict.put(nums[i], i);
            } else dict.put(nums[i], i);
        }
        return false;
    }

    /**
     * 128. 最长连续序列
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int maxLongest = 0;
        for (int start : nums) {
            int end = start;
            if (set.contains(start - 1)) {
                continue;
            }
            while (set.contains(end + 1)) {
                set.remove(end++);
            }
            maxLongest = Math.max(maxLongest, end - start + 1);
        }
        return maxLongest;
    }

    /**
     * 228. 汇总区间
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums.length == 0) return result;
        int start = nums[0], end = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == end + 1) {
                end = nums[i];
            } else {
                if (start == end) {
                    result.add(String.valueOf(start));
                } else {
                    result.add(start + "->" + end);
                }
                start = nums[i];
                end = nums[i];
            }
        }
        if (start == end) {
            result.add(String.valueOf(start));
        } else {
            result.add(start + "->" + end);
        }
        return result;
    }

    /**
     * 56. 合并区间
     */
    public int[][] mergeRanges(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> merged = new ArrayList<>();
        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.getLast()[1] < interval[0])
                merged.add(interval);
            else
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
        }
        return merged.toArray(new int[merged.size()][]);
    }
}
