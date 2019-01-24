package commandline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.common.primitives.Ints;
import com.mysql.fabric.xmlrpc.base.Array;

public class testArrayList {
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		ArrayList<Integer> l =  new ArrayList<Integer>();
		int[] a = {-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6};
		System.out.println(threeSum(a));
		
		long end = System.currentTimeMillis();
		System.out.println("It takes " + (end-start) + "ms");

	}
	public static List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> all = new LinkedList<>();
		for(int i=0;i<nums.length-2;i++) {
			if(i==0||(i>0&&nums[i]!=nums[i-1])) {
				int low = i+1, high = nums.length-1, sum = 0-nums[i];
				while(low<high) {
					if(nums[low] + nums[high] == sum) {
						all.add(Arrays.asList(nums[i],nums[low],nums[high]));
						do{
							low++;
						}while(low<high&&nums[low] == nums[low+1]);
						do{
							high--;
						}while(low<high&&nums[high] == nums[high-1]);
						
					}else if(nums[low] + nums[high] < sum) {
							low++;
					}else {
						high--;
					}
				}
			}
		}
		return all;
	}
	
	
}
