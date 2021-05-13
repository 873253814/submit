package com.example.demo.node;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode first = new ListNode();
        ListNode second = new ListNode();
        first = head;
        second = head;
        while (second.next != null) {
            if (second.val != second.next.val) {
                first.next = second.next;
                first = second.next;
            }
            second = second.next;
        }
        first.next = null;
        return head;
    }


    public static ListNode deleteDuplicates1(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode first = new ListNode(0, head);
        ListNode second = first;
        while (head.next != null) {
            if (head.val != head.next.val) {
                first.next = head.next;
                first = first.next;
            }
            head = head.next;
        }
        return second.next;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3,  null))));
        //ListNode node = new ListNode();
        ListNode node1 = deleteDuplicates1(node);
        while (node1 != null) {
            System.out.println(node1.val);
            node1 = node1.next;
        }
    }
}
