package com.leecode.mysolution;

import org.junit.Test;

public class TwoSum {

	public int[] twoSum(int[] nums, int target) {
		int[] result = { -1 };
		int flag = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (target == (nums[i] + nums[j])) {
					flag++;
					int[] temp = new int[flag * 2];
					System.arraycopy(result, 0, temp, 0, result.length);
					result = temp;
					result[2 * flag - 2] = i;
					result[2 * flag - 1] = j;
				}
			}
		}
		return result;
	}

	@Test
	public void test() {
		int[] nums = {2,7,11,15,3,1,8,5,3,15,4,2,3,6,8};
		nums = twoSum(nums, 50);
		for(int i : nums){
			System.out.print(i+" ");
		}
	}
}
