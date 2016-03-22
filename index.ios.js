'use strict';

import React, { Component, Text } from 'react-native';

let ERROR = 'SliderAndroid is not available in iOS - use SliderIOS.';

export default class SliderAndroid extends Component {
  constructor (props) {
    super(props);
    console.log(ERROR);
  }

  zoomOnMarkers () { console.error(ERROR) }

  render () {
    return ( <Text>{ERROR}</Text> );
  }
}
