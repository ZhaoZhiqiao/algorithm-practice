package org.example.dataStructure;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 单向链表节点定义
 */
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    /**
     * 从数组创建链表
     */
    public static ListNode fromArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        for (int num : nums) {
            current.next = new ListNode(num);
            current = current.next;
        }
        
        return dummy.next;
    }

    /**
     * 将链表转换为数组以便于测试
     */
    public static int[] toArray(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        
        java.util.List<Integer> result = new java.util.ArrayList<>();
        ListNode current = head;
        
        while (current != null) {
            result.add(current.val);
            current = current.next;
        }
        
        return result.stream().mapToInt(i -> i).toArray();
    }

    public static ListNode copy(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        ListNode currentNew = dummy;
        ListNode currentOld = head;

        while (currentOld != null) {
            currentNew.next = new ListNode(currentOld.val);
            currentNew = currentNew.next;
            currentOld = currentOld.next;
        }

        return dummy.next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode current = this;
        sb.append("[");
        
        while (current != null) {
            sb.append(current.val);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        
        sb.append("]");
        return sb.toString();
    }
}
