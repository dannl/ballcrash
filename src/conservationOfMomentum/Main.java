/*******************************************************************************
 *
 *    Copyright (c) Baina Info Tech Co. Ltd
 *
 *    conservationOfMomentum
 *
 *    Main
 *    TODO File description or class description.
 *
 *    @author: danliu
 *    @since:  May 19, 2014
 *    @version: 1.0
 *
 ******************************************************************************/
package conservationOfMomentum;

import com.sun.org.apache.bcel.internal.generic.I2F;

/**
 * Main of conservationOfMomentum.
 * @author danliu
 *
 */
public class Main {
	
	private enum Side {
		LEFT, RIGHT
	} 
    
    private static final double BALL_VELOCITY_LEFT = 4;
    
    private static final double BALL_VELOCITY_RIGHT = 8;
    
    private static final double LEFT_BALL_DISTANCE = 40;
    
    private static final double RIGHT_BALL_DISTANCE = 80;

    private static final int BALL_COUNT = 16;


    private static final double BALL_SYSTEM_STARTING_DISTANCE = 1000;

    public static final void main(final String[] args) {
        final Ball[] leftSideBalls = new Ball[BALL_COUNT];
        final Ball[] rightSideBalls = new Ball[BALL_COUNT];
        for (int i = 0; i < BALL_COUNT; i++) {
            leftSideBalls[i] = new Ball(BALL_COUNT - i);
        }

        for (int i = 0; i < BALL_COUNT; i++) {
            rightSideBalls[i] = new Ball(BALL_COUNT - i);
        }
        
        double velocitySum = BALL_VELOCITY_LEFT + BALL_VELOCITY_RIGHT;
        leftSideBalls[0].mTimeGaps[0] = 0;
        rightSideBalls[0].mTimeGaps[0] = 0;
        for (int i = 0; i < BALL_COUNT - 1; i++) {
        		leftSideBalls[i].mTimeGaps[1] = LEFT_BALL_DISTANCE / velocitySum;
        		rightSideBalls[i].mTimeGaps[1] = RIGHT_BALL_DISTANCE / velocitySum;
		}
        
        for (int j = 0; j < BALL_COUNT * 2 - 1; j++) {
        	if (j == 1) {
				continue;
			}
        	for (int i = 0; i < BALL_COUNT; i++) {
        		if (j >= (BALL_COUNT - i) * 2 - 1) {
					break;
				}
				if (i == 0) {
					if (j == 0) {
						break;
					}
					int k = j / 2;
					if (j % 2 == 0) {
						leftSideBalls[i].mTimeGaps[2 * k] = (leftSideBalls[i].mTimeGaps[2 * k - 1] * BALL_VELOCITY_RIGHT + rightSideBalls[i].mTimeGaps[2 * k - 1] * BALL_VELOCITY_LEFT) / velocitySum;
						rightSideBalls[i].mTimeGaps[2 * k] = (rightSideBalls[i].mTimeGaps[2 * k - 1] * BALL_VELOCITY_LEFT + leftSideBalls[i].mTimeGaps[2 * k - 1] * BALL_VELOCITY_RIGHT) / velocitySum;
					} else {
						leftSideBalls[i].mTimeGaps[2 * k + 1] = (leftSideBalls[i + 1].mTimeGaps[2 * k] * BALL_VELOCITY_LEFT + leftSideBalls[i].mTimeGaps[2 * k - 2] * BALL_VELOCITY_RIGHT) / velocitySum;
						rightSideBalls[i].mTimeGaps[2 * k + 1] = (rightSideBalls[i + 1].mTimeGaps[2 * k] * BALL_VELOCITY_RIGHT + rightSideBalls[i].mTimeGaps[2 * k - 2] * BALL_VELOCITY_LEFT) / velocitySum;
					}
				} else {
					if (j == 0) {
						leftSideBalls[i].mTimeGaps[j] = leftSideBalls[i - 1].mTimeGaps[1] + leftSideBalls[i - 1].mTimeGaps[0]; 
						rightSideBalls[i].mTimeGaps[j] = rightSideBalls[i - 1].mTimeGaps[1] + rightSideBalls[i - 1].mTimeGaps[0]; 
					} else {
						int k = j / 2;
						if (j % 2 == 0) {
							leftSideBalls[i].mTimeGaps[2 * k] = (leftSideBalls[i].mTimeGaps[2 * k - 1] * BALL_VELOCITY_RIGHT + leftSideBalls[i + 1].mTimeGaps[2 * k - 1] * BALL_VELOCITY_LEFT) / velocitySum;
							rightSideBalls[i].mTimeGaps[2 * k] = (rightSideBalls[i].mTimeGaps[2 * k - 1] * BALL_VELOCITY_LEFT + rightSideBalls[i + 1].mTimeGaps[2 * k - 1] * BALL_VELOCITY_RIGHT) / velocitySum;
						} else {
							leftSideBalls[i].mTimeGaps[2 * k + 1] = (leftSideBalls[i + 1].mTimeGaps[2 * k] * BALL_VELOCITY_LEFT + leftSideBalls[i].mTimeGaps[2 * k - 2] * BALL_VELOCITY_RIGHT) / velocitySum;
							rightSideBalls[i].mTimeGaps[2 * k + 1] = (rightSideBalls[i + 1].mTimeGaps[2 * k] * BALL_VELOCITY_RIGHT + rightSideBalls[i].mTimeGaps[2 * k - 2] * BALL_VELOCITY_LEFT) / velocitySum;
						}
					}
				}
			}
		}
        System.out.println("left");
        for (int i = 0; i < leftSideBalls.length; i++) {
        	System.out.println(leftSideBalls[i].toString());
		}
        System.out.println("right");
        for (int i = 0; i < rightSideBalls.length; i++) {
        	System.out.println(rightSideBalls[i].toString());
		}
        
        

    }
    

    private static final class Ball {

    	private double[] mTimeGaps;
    	
        Ball (final int inversedIndex) {
        	mTimeGaps = new double[inversedIndex * 2 - 1];
        }
        
        void draw() {
        	
        }
        
        @Override
        public String toString() {
        	StringBuilder builder = new StringBuilder();
        	final double[] timeGaps = mTimeGaps;
        	for (double d : timeGaps) {
				builder.append(d).append(",");
			}
        	return builder.toString();
        }

    }

}
