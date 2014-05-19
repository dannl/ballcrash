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

/**
 * Main of conservationOfMomentum.
 * @author danliu
 *
 */
public class Main {

    private static final double BALL_MASS = 10;

    private static final double BALL_VELOCITY = 5;

    private static final long LEFT_SIDE_STEP_IN = 3000;

    private static final long RIGHT_SIDE_STEP_IN = 3000;

    private static final int LEFT_SIDE_BALLS_COUNT = 16;

    private static final int RIGHT_SIDE_BALLS_COUNT = 16;

    private static final double BALL_SYSTEM_STARTING_DISTANCE = 1000;

    public static final void main(final String[] args) {
        final Ball[] leftSideBalls = new Ball[LEFT_SIDE_BALLS_COUNT];
        final Ball[] rightSideBalls = new Ball[RIGHT_SIDE_BALLS_COUNT];
        long current = System.currentTimeMillis();
        for (int i = 0; i < LEFT_SIDE_BALLS_COUNT; i++) {
            leftSideBalls[i] = new Ball(current + i * LEFT_SIDE_STEP_IN, BALL_VELOCITY, 0);
        }

        for (int i = 0; i < RIGHT_SIDE_BALLS_COUNT; i++) {
            rightSideBalls[i] = new Ball(current + i * RIGHT_SIDE_STEP_IN, - BALL_VELOCITY, BALL_SYSTEM_STARTING_DISTANCE);
        }

        final Ball[] allBalls = new Ball[LEFT_SIDE_BALLS_COUNT + RIGHT_SIDE_BALLS_COUNT];

        for (int i = LEFT_SIDE_BALLS_COUNT - 1; i >= 0; i++) {
            allBalls[i] = leftSideBalls[i];
        }

        for (int i = 0; i < RIGHT_SIDE_BALLS_COUNT; i++) {
            allBalls[i + LEFT_SIDE_BALLS_COUNT] = rightSideBalls[i];
        }

        while (true) {

            current = System.currentTimeMillis();

            for (int i = 0; i < allBalls.length; i++) {
                if (i == 0) {

                } else if (i == allBalls.length - 1) {

                } else {

                }
            }

        }


    }

    private static final class Ball {
        private double mVelocity;
        private double mX;
        private long mLastTrackedTime;
        private double mLastTrackedX;

        Ball (final long startingTime, final double startingVelocity, final double startingX) {
            mLastTrackedTime = startingTime;
            mVelocity = startingVelocity;
            mLastTrackedX = startingX;
        }

        void go(final long current) {
            mX = mLastTrackedX + mVelocity * (current - mLastTrackedTime);
            mLastTrackedX = mX;
            mLastTrackedTime = current;
        }

        double testGo(final long current) {
            return mLastTrackedX + mVelocity * (current - mLastTrackedTime);
        }
    }

    private static final void crash(final Ball ballLeft, final Ball ballMiddle, final Ball ballRight, final long current) {
        final double leftX = ballLeft.testGo(current);
        final double middleX = ballMiddle.testGo(current);
        final double rightX = ballRight.testGo(current);

    }

}
