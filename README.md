<div align=center><img src="https://github.com/snpmyn/Amap/raw/master/image.png"/></div>

[![SNAPSHOT](https://jitpack.io/v/Jaouan/Revealator.svg)](https://jitpack.io/#snpmyn/Amap)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/a1c9a1b1d1ce4ca7a201ab93492bf6e0)](https://app.codacy.com/project/snpmyn/Amap/dashboard)
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)
[![API](https://img.shields.io/badge/API-19%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=19)

### 介绍
高德地图。

### 架构

| 模块 | 说明 | 补充 |
|:-:|:-:|:-:|
| 示例app | 用法举例 | 无 |
| 一方库Location | 定位集成实现 | 无 |

### 依赖、权限

| 模块 | 依赖 |
|:-:|:-:|
| 示例app | implementation project(path: ':location') |
| 一方库Location | api 'com.github.snpmyn:*Util*:master-SNAPSHOT'（避重）|
| 一方库Location | api 'com.amap.api:location:4.7.0'（避重）|
| 二方库Util-UtilOne | api 'com.github.bumptech.glide:glide:4.10.0'（避重）|
| 二方库Util-UtilOne | api 'com.google.android.material:material:1.2.0-alpha01'（避重）|
| 二方库Util-UtilOne | api 'io.reactivex:rxandroid:1.2.1'（避重）|
| 二方库Util-UtilOne | api 'io.reactivex:rxjava:1.3.8'（避重）|
| 二方库Util-UtilOne | api 'com.jakewharton.timber:timber:4.7.1'（避重）|
| 二方库Util-UtilOne | api 'com.tencent:mmkv-static:1.0.23'（避重）|
| 二方库Util-UtilOne | implementation 'com.getkeepsafe.relinker:relinker:1.3.1' |
| 二方库Util-UtilOne | implementation 'com.qw:soulpermission:1.2.2_x' |
| 二方库Util-UtilOne | implementation 'org.apache.commons:commons-lang3:3.9' |
| 二方库Util-UtilTwo | implementation 'androidx.core:core-ktx:1.2.0-beta01' |
| 二方库Util-UtilTwo | implementation "org.jetbrains.kotlin:*kotlin-stdlib-jdk7*:$kotlin_version" |

| 模块 | 权限 |
|:-:|:-:|
| 示例app | 无 |
| 一方库Location | android:name="android.permission.ACCESS_COARSE_LOCATION"（避重）|
| 一方库Location | android:name="android.permission.ACCESS_FINE_LOCATION"（避重）|
| 一方库Location | android:name="android.permission.ACCESS_NETWORK_STATE"（避重）|
| 一方库Location | android:name="android.permission.ACCESS_WIFI_STATE"（避重）|
| 一方库Location | android:name="android.permission.CHANGE_WIFI_STATE"（避重）|
| 一方库Location | android:name="android.permission.INTERNET"（避重）|
| 一方库Location | android:name="android.permission.READ_PHONE_STATE"（避重）|
| 一方库Location | android:name="android.permission.ACCESS_LOCATION_EXTRA_COMMANDS"（避重）|
| 一方库Location | android:name="android.permission.BLUETOOTH"（避重）|
| 一方库Location | android:name="android.permission.BLUETOOTH_ADMIN"（避重）|
| 二方库Util-app | android:name="android.permission.WRITE_EXTERNAL_STORAGE"（避重）|
| 二方库Util-app | android:name="android.permission.READ_EXTERNAL_STORAGE"（避重）|
| 二方库Util-UtilOne | 无 |
| 二方库Util-UtilTwo | 无 |

### 使用
build.gradle(module)
```
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {  
    repositories {
        google()
        jcenter()
                
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.5.2'           

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()

    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}
```
build.gradle(app)
```
apply plugin: 'com.android.application'

android {
    signingConfigs {
        debug {
            storeFile file('../amap.jks')
            storePassword 'xxx'
            keyPassword 'xxx'
            keyAlias = 'key0'
        }
        release {
            storeFile file('../amap.jks')
            storePassword 'xxx'
            keyPassword 'xxx'
            keyAlias = 'key0'
        }
    }
    compileSdkVersion 29
    defaultConfig {
        ...
        ndk {
            abiFilters 'armeabi-v7a'//,'x86','armeabi','arm64-v8a','x86_64','mips','mips64'
        }
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
            signingConfig signingConfigs.release
        }
        debug {
            signingConfig signingConfigs.debug
        }
    }
    compileOptions {
        sourceCompatibility 1.8
        targetCompatibility 1.8
    }
    configurations.all {
        resolutionStrategy.cacheChangingModulesFor 0, 'seconds'
    }
}

dependencies {
    implementation 'com.github.snpmyn:Amap:master-SNAPSHOT'
}
```

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
