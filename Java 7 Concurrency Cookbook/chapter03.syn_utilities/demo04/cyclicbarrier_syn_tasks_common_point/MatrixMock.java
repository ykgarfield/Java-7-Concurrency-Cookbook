package demo04.cyclicbarrier_syn_tasks_common_point;

import java.util.Random;

/**
 * 生成1到10之间的随机矩阵
 **/
public class MatrixMock {

	/**
	 * 随机数的二维数组
	 */
	private int data[][];

	
	/**
	 * 统计要查找的数字出现的数字
	 * @param size		数组的行数
	 * @param length	数组的列数
	 * @param number	要查找的数字
	 */
	public MatrixMock(int size, int length, int number) {
		int counter = 0;	// 要查找的数字的个数
		data = new int[size][length];

		Random random = new Random();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < length; j++) {
				data[i][j] = random.nextInt(10);
				// 统计要查找的数字出现的数字
				if (data[i][j] == number) {
					counter++;
				}
			}
		}

		System.out.printf("Mock: There are %d ocurrences of number in generated data.\n", counter, number);
	}

	/**
	 * 返回二维数组的一行
	 */
	public int[] getRow(int row) {
		if ((row >= 0) && (row < data.length)) {
			return data[row];
		}

		return null;
	}
}
