'use strict';
import React, { Component, PropTypes, requireNativeComponent } from 'react-native';


export default class SliderAndroid extends Component {
    constructor() {
        super();
        this._onChange = this._onChange.bind(this);
    }

    _onChange(event:Event) {
        if (this.props.onValueChange && event.nativeEvent.name === "onChange") {
            return this.props.onValueChange(event.nativeEvent.progress);
        }
        if (this.props.onStartTrack && event.nativeEvent.name === "onStart") {
            return this.props.onStartTrack();
        }
        if (this.props.onStopTrack && event.nativeEvent.name === "onStop") {
            return this.props.onStopTrack();
        }
    }

    render() {
        return <RNSlider {...this.props} onChange={this._onChange}/>;
    }
}

SliderAndroid.propTypes = {
    onValueChange:                  PropTypes.func,
    viewFinderBorderLength:         PropTypes.number,
    minimumValue:                   PropTypes.number,
    maximumValue:                   PropTypes.number,
    value:                          PropTypes.number,
    rotation:                       PropTypes.number,
    scaleX:                         PropTypes.number,
    scaleY:                         PropTypes.number,
    translateX:                     PropTypes.number,
    translateY:                     PropTypes.number,
    importantForAccessibility:      PropTypes.string,
    accessibilityLabel:             PropTypes.string,
    testID:                         PropTypes.string,
    renderToHardwareTextureAndroid: PropTypes.string,
    accessibilityLiveRegion:        PropTypes.string,
    accessibilityComponentType:     PropTypes.string,
    onLayout:                       PropTypes.bool
};

var RNSlider = requireNativeComponent(`RNSlider`, SliderAndroid, {
    nativeOnly: {onChange: true}
});
