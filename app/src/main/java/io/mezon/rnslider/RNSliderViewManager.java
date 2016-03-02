package io.mezon.rnslider;

import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import javax.annotation.Nullable;

public class RNSliderViewManager extends SimpleViewManager<FrameLayout> {
    public static final String REACT_CLASS = "RNSlider";
    public static final String PROP_PROGRESS = "value";
    public static final String PROP_MIN = "minimumValue";
    public static final String PROP_MAX = "maximumValue";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @ReactProp(name = PROP_PROGRESS)
    public void setProgress(FrameLayout view, @Nullable Integer value) {
        if(view.getChildCount() == 0) {
            throw new IllegalStateException("Slider not yet created");
        }

        RNSlider slider = (RNSlider)view.getChildAt(0);
        if(slider.getProgress() == value) {
            return;
        }
        slider.setProgress(value);
    }

    @ReactProp(name = PROP_MAX)
    public void setMax(FrameLayout view, @Nullable Integer max) {
        if(view.getChildCount() == 0) {
            throw new IllegalStateException("Slider not yet created");
        }

        RNSlider slider = (RNSlider)view.getChildAt(0);
        slider.setMax(max);

    }

    @ReactProp(name = PROP_MIN)
    public void setMin(FrameLayout view, @Nullable Integer min) {
        if(view.getChildCount() == 0) {
            throw new IllegalStateException("Slider not yet created");
        }

        RNSlider slider = (RNSlider)view.getChildAt(0);
        slider.setMin(min);

    }

    @Override
    protected FrameLayout createViewInstance(ThemedReactContext context) {
        final FrameLayout view = new FrameLayout(context);
        RNSlider slider = new RNSlider(view.getContext(), null);
        slider.setOnRNSliderChangeListener(new RNSlider.RNSliderChangeListener() {
            @Override
            public void onProgressChanged(RNSlider slider, int progress, boolean fromUser) {
                WritableMap event = Arguments.createMap();
                event.putInt("progress", progress);
                ReactContext reactContext = (ReactContext) view.getContext();
                reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                        view.getId(),
                        "topChange",
                        event);
            }
        });

        view.addView(
                slider,
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }

    @Override
    public void updateExtraData(FrameLayout root, Object extraData) {
    }
}
