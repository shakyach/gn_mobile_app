package com.golfnationsmob.GolfView.NewRound.Gps;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class TouchableWrapper extends FrameLayout {



	public TouchableWrapper(Context context) {
		super(context);
		// Force the host activity to implement the TouchActionDown Interface

	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {

		if (mOnDragListener != null) {
			mOnDragListener.onDrag(event);
		}
		return super.dispatchTouchEvent(event);
	}

	public interface OnDragListener {
		public void onDrag(MotionEvent motionEvent);
	}

	private OnDragListener mOnDragListener;
	public void setOnDragListener(OnDragListener mOnDragListener) {
		this.mOnDragListener = mOnDragListener;
	}



}