<div align=center><img src="https://github.com/snpmyn/Amap/raw/master/image.png"/></div>

[![SNAPSHOT](https://jitpack.io/v/Jaouan/Revealator.svg)](https://jitpack.io/#snpmyn/Amap)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/a1c9a1b1d1ce4ca7a201ab93492bf6e0)](https://app.codacy.com/project/snpmyn/Amap/dashboard)
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)
[![API](https://img.shields.io/badge/API-19%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=19)

### 介绍
高德地图

### 依赖
#### 自身
##### AndroidLibrary - Library
* api 'com.github.snpmyn:*Util*:master-SNAPSHOT'（避重）
* api 'com.amap.api:location:4.7.0'（避重）

#### com.github.snpmyn:Util(api)
##### AndroidLibrary - UtilOne
* api 'com.github.bumptech.glide:glide:4.9.0'（避重）
* api 'com.google.android.material:material:1.1.0-alpha10'（避重）
* api 'io.reactivex:rxandroid:1.2.1'（避重）
* api 'io.reactivex:rxjava:1.3.8'（避重）
* api 'com.jakewharton.timber:timber:4.7.1'（避重）
* api 'com.tencent:mmkv-static:1.0.23'（避重）
* implementation 'com.getkeepsafe.relinker:relinker:1.3.1'
* implementation 'com.qw:soulpermission:1.2.1_x'
* implementation 'org.apache.commons:commons-lang3:3.9'

##### AndroidLibrary - UtilTwo
* implementation 'androidx.core:core-ktx:1.2.0-alpha04'
* implementation "org.jetbrains.kotlin:*kotlin-stdlib-jdk7*:$kotlin_version"

### 权限
#### AndroidLibrary - Library
待补充
#### com.github.snpmyn:Util
##### app
* android:name="android.permission.WRITE_EXTERNAL_STORAGE"（避重）
* android:name="android.permission.READ_EXTERNAL_STORAGE"（避重）

### License
```
Copyright 2019 snpmyn

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
