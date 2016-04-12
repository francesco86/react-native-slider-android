package io.mezon.rnslider;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.SeekBar;

/**
 * Created by pinterlaszlo on 02/03/16.
 * This class is just a trick to fake slider functions using a SeekBar.
 * The SeekBar always starts from 0 value but this slider starts from a given min value.
 */
public class RNSlider extends SeekBar{

    RNSliderChangeListener sliderListener;
    private int minValue = 9;
    private int maxValue = 80;

    interface RNSliderChangeListener {
        void onProgressChanged(RNSlider slider, int progress, boolean fromUser);
        void onStartTrackingTouch(RNSlider slider);
        void onStopTrackingTouch(RNSlider slider);
    }

    public RNSlider(Context context) {
        super(context);
    }

    public RNSlider(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RNSlider(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public synchronized void setMax(int max) {
        maxValue = max;
        super.setMax(max-minValue);
    }

    public synchronized void setMin(int min){
        minValue = min;
        super.setMax(maxValue-min);
    }

    @Override
    public synchronized void setProgress(int progress) {
        super.setProgress(progress-minValue);
    }

    public void setOnRNSliderChangeListener(RNSliderChangeListener l){
            sliderListener = l;
            super.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    sliderListener.onProgressChanged(RNSlider.this, progress+minValue, fromUser);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    sliderListener.onStartTrackingTouch(RNSlider.this);
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    sliderListener.onStopTrackingTouch(RNSlider.this);
                }
            });
    }

    @Override
    public void setOnSeekBarChangeListener(OnSeekBarChangeListener l) {
        //Sorry.
        Log.e(getClass().getSimpleName(), "ERROR! Don't use setOnSeekBarChangeListener! Use setOnRNSliderChangeListener instead!");
    }

}
