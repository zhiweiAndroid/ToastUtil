# ToastUtil

本项目是用来实践Android 上传自己的开源库到Bintray

### Android 上传自己的开源库到Bintray（超简单哦）

分享地址:自己实践成功了

https://github.com/zhiweiAndroid/ToastUtil



# 背景

我们在项目中引用过各路大神的开源库，但是如何将自己的Library发布到Maven仓库呢，搜索了半天，知道了[jcenter](http://jcenter.bintray.com/)是由 [bintray.com](https://bintray.com/)维护的Maven服务器，该服务器是现在最通用的，可以试一下，但是网上的方法各种复杂，不想搞，终于找到了一种超级简单的方法，在此做个记录分享给大家。

# 注册

打开 bintray.com，点击右上角“Sign In”，进入登录页面。

[![bintray首页](http://upload-images.jianshu.io/upload_images/5228061-c90fadec05203f58.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)](http://upload-images.jianshu.io/upload_images/5228061-c90fadec05203f58.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

bintray首页

[![bintray登录页面](http://upload-images.jianshu.io/upload_images/5228061-4c35c3952dfaf674.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)](http://upload-images.jianshu.io/upload_images/5228061-4c35c3952dfaf674.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

bintray登录页面

点击

Sign Up to an Open Source account

进入注册页面。

千万注意不要直接点首页的 `START YOUR FREE TRIAL` 直接注册，官网默认的注册网站是给组织使用的，并非个人，否则后续会导致各种问题。

[![bintray注册页面](http://upload-images.jianshu.io/upload_images/5228061-6aaac8daef331750.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)](http://upload-images.jianshu.io/upload_images/5228061-6aaac8daef331750.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

bintray注册页面

具体注册方法就不多说了，大家应该都会，建议关联github账号，据说如果通过邮箱注册，需要国外的邮箱，gitHub亲测，好使。

# 创建Maven仓库

点击`Add New Repository`按钮创建Maven仓库

创建Maven仓库时，

```
Name
```

是你自己的仓库名可以随意填写，我就随便取了个MyMoven的名字，

```
Type
```

一定要选择Maven，

```
Default Licenses
```

需要选择Apache-2.0，至于为什么选Apache-2.0没有具体研究。

[![创建Maven仓库页面](http://upload-images.jianshu.io/upload_images/5228061-9ce238b6f0df2605.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)](http://upload-images.jianshu.io/upload_images/5228061-9ce238b6f0df2605.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

创建Maven仓库页面

点击

```
Create
```

按钮创建MyMoven仓库成功。

到这里我们的第一步已经完成了，注册Bintray个人账号，创建Moven仓库，下面介绍如何将我们的Library上传到这个仓库中。

------

# 工程配置

- 主工程根目录下的build.gradle文件中加入上传开源库的依赖，这里要注意一下gradle版本需要与bintray-release版本保持一致。

```
build script {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.2.2'
        classpath 'com.novoda:bintray-release:0.4.0'
    }
}

all projects {
    repositories {
        jcenter()
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}


```

- Library工程的build.gradle配置
  1 添加apply

```
apply plugin: 'com.novoda.bintray-release'

```

2 忽略错误信息

```
android {
    ***
    ***
    lintOptions {
        abortOnError false
    }
}

```

3 如果有中文注释加入如下代码

```
android {
    ***
    ***
}

all projects {
    repositories {
        jcenter()
    }
    tasks.withType(Javadoc) {
        options{ encoding "UTF-8"
            charSet 'UTF-8'
            links "http://docs.oracle.com/javase/7/docs/api"
        }
    }
}

```

4 配置binary上传基本信息，具体配置和参考[Bintray Configuration](https://github.com/novoda/bintray-release/wiki/Configuration-of-the-publish-closure)，下面是我的配置

```
publish {
    repoName = 'MyMoven'           //bintray仓库名
    userOrg = 'wyshikai'           //bintray注册的用户名
    groupId = 'com.sky'            //compile引用时的第1部分groupId
    artifactId = 'CustomTab'       //compile引用时的第2部分项目名
    publishVersion = '1.0.0'       //compile引用时的第3部分版本号
    desc = 'This is a simple custom tab library for Android'
    website = 'https://github.com/wyshikai837/CustomTab'
}

```

配置就大功告成了

------

# 发布到Binary

1 首先需要找到API KEY，点击账号管理中的Edit Profile

[![Edit Profile](http://upload-images.jianshu.io/upload_images/5228061-e81177bdd410c20f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)](http://upload-images.jianshu.io/upload_images/5228061-e81177bdd410c20f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

Edit Profile

[![API KEY](http://upload-images.jianshu.io/upload_images/5228061-742e6e28a30a4645.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)](http://upload-images.jianshu.io/upload_images/5228061-742e6e28a30a4645.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

API KEY

[![复制API KEY](http://upload-images.jianshu.io/upload_images/5228061-433cf44025eaa422.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)](http://upload-images.jianshu.io/upload_images/5228061-433cf44025eaa422.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

复制API KEY

2 在Android Studio的terminal或者cmd（注意要先进入工程根目录）中，执行命令

```
./gradlew clean build bintrayUpload -PbintrayUser=BINTRAY_USERNAME -PbintrayKey=BINTRAY_KEY -PdryRun=false

```

其中`BINTRAY_USERNAME`是账号名，`BINTRAY_KEY`是API KEY
*注意这个命令是在MAC系统下的，如果在Windows系统需要去掉./*

```
gradlew clean build bintrayUpload -PbintrayUser=BINTRAY_USERNAME -PbintrayKey=BINTRAY_KEY -PdryRun=false

```

如果最后显示，表示发布成功了

```
BUILD SUCCESSFUL
Total time: *** secs

```

打开刚刚我们创建的仓库，就可以看到我们上传的Library

[![Library](http://upload-images.jianshu.io/upload_images/5228061-d81c7d13effb9cac.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)](http://upload-images.jianshu.io/upload_images/5228061-d81c7d13effb9cac.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

Library

点击Library查看详情，如果看到Maven build settings和Add to JCenter，那么恭喜你最重要的一步已经完成了。

[![详细信息](http://upload-images.jianshu.io/upload_images/5228061-82c01b6ae8a57f6a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)](http://upload-images.jianshu.io/upload_images/5228061-82c01b6ae8a57f6a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

详细信息

3 发布到Center

点击上图中的Add to JCenter按钮，填写说明，并提交审核，就可以静静的等待了，基本上几个小时就可以通过

[![提交审核](http://upload-images.jianshu.io/upload_images/5228061-135edc77d5f6e23c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)](http://upload-images.jianshu.io/upload_images/5228061-135edc77d5f6e23c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

提交审核

发布工作已经完成了，是不是很简单啊，这里还想啰嗦一句，大家在配置信息的时候一定要注意Gradle与Bintray版本对应，否则会导致所有步骤都没有问题，并且提交JCenter审核也通过了，就是引用不到库，我查了好久才查到是这个问题。

------

# 依赖使用

在审核通过前可以配置私有仓库来使用

[![私有仓库地址](http://upload-images.jianshu.io/upload_images/5228061-2bb1daa6e7431969.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)](http://upload-images.jianshu.io/upload_images/5228061-2bb1daa6e7431969.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

私有仓库地址

在根目录的build.gradle下配置，私有仓库地址

```
allprojects {
    repositories {
        jcenter()
        maven {url 'https://dl.bintray.com/wyshikai/MyMoven/'}
    }
}

```

在使用到仓库的工程中加入依赖即可

```
compile 'com.sky:CustomTab:1.0.0'

```

等待审核通过后，将私有仓库地址删除即可。