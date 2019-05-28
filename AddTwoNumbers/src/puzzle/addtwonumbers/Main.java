package puzzle.addtwonumbers;

public class Main {

    private static ListNode createList(int val)
    {
        ListNode head = null;
        ListNode p = null;
        do
        {
            if (head == null)
            {
                head = new ListNode(val%10);
                p = head;
            }
            else
            {
                p.next = new ListNode(val%10);
                p = p.next;
            }
            val = val/10;
        }
        while (val > 0);
        return head;
    }

    private static int getVal(ListNode l)
    {
        if (l == null)
        {
            return 0;
        }

        return l.val + getVal(l.next)*10;
    }

    public static void main(String[] args) {
            AddTwoNumbers addTwoNumbers = new AddTwoNumbers();

            ListNode l1 = createList(465);
            ListNode l2 = createList(342);
            ListNode result = addTwoNumbers.addTwoNumbers(l1, l2);
            assert getVal(result) == 807;
            assert getVal(addTwoNumbers.addTwoNumbers(createList(999), createList(0))) == 999;
            assert getVal(addTwoNumbers.addTwoNumbers(createList(9), createList(1))) == 10;
            assert getVal(addTwoNumbers.addTwoNumbers(createList(9999), createList(1))) == 10000;
            assert getVal(addTwoNumbers.addTwoNumbers(createList(9), createList(9))) == 18;

    }
}
