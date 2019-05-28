package puzzle.addtwonumbers;

public class AddTwoNumbers
{

	private void add(ListNode res, ListNode l1, ListNode l2, int carry)
	{
		if (carry == 0 && l1 == null && l2 == null)
		{
			return;
		}

		int sum = carry;
		if (l1 != null)
		{
			sum += l1.val;
			l1 = l1.next;
		}
		if (l2 != null)
		{
			sum += l2.val;
			l2 = l2.next;
		}

		res.next = new ListNode(sum%10);
		carry = sum/10;
		add(res.next, l1, l2, carry);
	}


	public ListNode addTwoNumbers(ListNode l1, ListNode l2)
	{
		ListNode result = new ListNode(-1);
		add(result, l1, l2, 0);
		return result.next;
	}
}
