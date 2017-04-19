package daita.daita;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * This code was followed from
 * @reference http://stackoverflow.com/questions/4139288/android-how-to-handle-right-to-left-swipe-gestures
 * Numerous changes have been made
 *
 * Date: 15/03/2017
 *
 * @author Josh Reynolds x15389521
 * @author Gavin Mulvany x15448892
 */


public class Swiper implements View.OnTouchListener {


    private GestureDetector mySwipe;


    public Swiper(Context resultContext) {
        mySwipe = new GestureDetector(resultContext, new GestureListener());
    }





    public boolean onTouch(View v, MotionEvent touchEvent) {
        return mySwipe.onTouchEvent(touchEvent);
    }


    public void onSwipeLeft() {
        //leave empty because functionality is in the display class
    }

    public void onSwipeRight() {
        //leave empty because functionality is in the display class
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private int userSwipeLength = 100;
        private int userSwipeSpeed = 100;

        private float horizontalLength, verticalLength;

        @Override
        public boolean onDown(MotionEvent downEvent) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float horizontalVelocity, float verticalVelocity) {


            horizontalLength = event2.getX() - event1.getX();

            verticalLength = event2.getY() - event2.getY();

            if (Math.abs(horizontalLength) > Math.abs(verticalLength) && Math.abs(horizontalLength) > userSwipeLength && Math.abs(horizontalVelocity) > userSwipeSpeed) {

                if (horizontalLength > 0)
                    onSwipeRight();

                else
                    onSwipeLeft();

                return true;
            }
            return false;
        }
    }



}
