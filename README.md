![Android-InstallReferrer](https://raw.githubusercontent.com/SimonMarquis/Android-InstallReferrer/master/resources/Feature%20graphic%20-%20resized.png "Android-InstallReferrer") 

### ⚠ Deprecated: [Still Using InstallBroadcast? Switch to the Play Referrer API by March 1, 2020](https://android-developers.googleblog.com/2019/11/still-using-installbroadcast-switch-to.html)

#### Install Referrer is an Open Source application that allows you to test the referrer attribute on the Google Play Store.

You can test <b>referrer</b> attribute using these links below:

<a href="https://play.google.com/store/apps/details?id=fr.simon.marquis.installreferrer&referrer=myReferrerValue">Play Store url</a>  
`https://play.google.com/store/apps/details?id=fr.simon.marquis.installreferrer&referrer=myReferrerValue`

<a href="market://details?id=fr.simon.marquis.installreferrer&referrer=myReferrerValue">Market url scheme</a>  
`market://details?id=fr.simon.marquis.installreferrer&referrer=myReferrerValue`

<a href="intent:#Intent;package=fr.simon.marquis.installreferrer&referrer=myReferrerValue;end">Chrome Intent</a>  
`intent:#Intent;package=fr.simon.marquis.installreferrer;S.market_referrer=myReferrerValue;end`

The referrer attribute will be broadcasted by the Play Store to your app <b>after the first launch only</b>.

[![Android-InstallReferrer on Google Play Store](http://developer.android.com/images/brand/en_generic_rgb_wo_60.png)](https://play.google.com/store/apps/details?id=fr.simon.marquis.installreferrer)

## Screenshots

![Screenshot][screen1]
![Screenshot][screen2]
![Screenshot][screen3]
![Screenshot][screen4]

## Video

[![Youtube Video](http://img.youtube.com/vi/W_M9F7m57vA/0.jpg)](http://www.youtube.com/watch?v=W_M9F7m57vA)

## Pull requests

Feel free to contribute to InstallReferrer.  
Either you found a bug or have created a new and awesome feature, just create a pull request.  
If you want to start to create a new feature or have any other questions regarding InstallReferrer, [file an issue](https://github.com/SimonMarquis/Android-InstallReferrer/issues/new).

## Developed By

* [Simon Marquis][1]

## License

	Copyright (C) 2014 Simon Marquis (http://www.simon-marquis.fr)
	
	Licensed under the Apache License, Version 2.0 (the "License"); you may not
	use this file except in compliance with the License. You may obtain a copy of
	the License at
	
	http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
	License for the specific language governing permissions and limitations under
	the License.


 [1]: http://www.simon-marquis.fr
 
 [screen1]: https://raw.github.com/SimonMarquis/Android-InstallReferrer/master/resources/framed/1%20-%20resized.png "Undefined"
 [screen2]: https://raw.github.com/SimonMarquis/Android-InstallReferrer/master/resources/framed/2%20-%20resized.png "Simple data"
 [screen3]: https://raw.github.com/SimonMarquis/Android-InstallReferrer/master/resources/framed/3%20-%20resized.png "Special character"
 [screen4]: https://raw.github.com/SimonMarquis/Android-InstallReferrer/master/resources/framed/4%20-%20resized.png "utm"
