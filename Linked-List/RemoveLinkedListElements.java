/*
Problem: 203. Remove Linked List Elements
Platform: LeetCode
Difficulty: Easy
Pattern: Linked List

Time Complexity: O(n)
Space Complexity: O(1)
*/

class RemoveLinkedListElements {
    public ListNode removeElements(ListNode head, int val) {

        // Remove matching nodes from the beginning
        while (head != null && head.val == val) {
            head = head.next;
        }

        ListNode current = head;

        while (current != null && current.next != null) {

            if (current.next.val == val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        return head;
    }
}
