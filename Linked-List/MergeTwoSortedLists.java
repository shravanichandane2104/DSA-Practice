/*
Problem: 21. Merge Two Sorted Lists
Platform: LeetCode
Difficulty: Easy
Pattern: Linked List + Recursion

Time Complexity: O(n + m)
Space Complexity: O(n + m) due to recursion stack
*/

class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        if (list1 == null) {
            return list2;
        }

        if (list2 == null) {
            return list1;
        }

        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        }

        list2.next = mergeTwoLists(list1, list2.next);
        return list2;
    }
}
