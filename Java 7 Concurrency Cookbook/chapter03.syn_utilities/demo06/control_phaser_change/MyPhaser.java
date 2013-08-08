package demo06.control_phaser_change;

import java.util.concurrent.Phaser;

/**
	实现Phaser类的子类.覆盖onAdvance()方法去控制phase的改变.
 **/
public class MyPhaser extends Phaser {

	/**
	 * 这个方法会在arriveAndAwaitAdvance()方法中被调用.
	 * 根据phase属性的值,调用不同的辅助方法.
	 */
	@Override
	protected boolean onAdvance(int phase, int registeredParties) {
		switch (phase) {
			case 0 :
				return studentsArrived();
			case 1:
				return finishFirstExercise();
			case 2:
				return finishSecondExercise();
			case 3:
				return finishExam();
			default:
				return true;
		}
		
//		return super.onAdvance(phase, registeredParties);
	}

	/**
	 * 这个方法在阶段0到阶段1改变的时候调用
	 */
	private boolean studentsArrived() {
		System.out.printf("Phaser: The exam are going to start. The students are ready.\n");
		System.out.printf("Phaser: We have %d students.\n",getRegisteredParties());
		return false;
	}
	
	/**
	 * 这个方法在阶段1到阶段2改变的时候调用
	 */
	private boolean finishFirstExercise() {
		System.out.printf("Phaser: All the students has finished the first exercise.\n");
		System.out.printf("Phaser: It's turn for the second one.\n");
		return false;
	}
	
	/**
	 * 这个方法在阶段2到阶段3改变的时候调用
	 */
	private boolean finishSecondExercise() {
		System.out.printf("Phaser: All the students has finished the second exercise.\n");
		System.out.printf("Phaser: It's turn for the third one.\n");
		return false;
	}
	
	/**
	 * 这个方法在阶段3到阶段4改变的时候调用
	 */	
	private boolean finishExam() {
		System.out.printf("Phaser: All the students has finished the exam.\n");
		System.out.printf("Phaser: Thank you for your time.\n");
		return true;
	}
}

