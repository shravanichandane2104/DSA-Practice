/*
Problem: 160. Intersection of Two Linked Lists
Platform: LeetCode
Difficulty: Easy
Pattern: Linked List + Two Pointers

Time Complexity: O(m + n)
Space Complexity: O(1)
*/

class IntersectionOfTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode a = headA;
        ListNode b = headB;

        while (a != b) {

            if (a == null) {
                a = headB;
            } else {
                a = a.next;
            }

            if (b == null) {
                b = headA;
            } else {
                b = b.next;
            }
        }

        return a;
    }
}
