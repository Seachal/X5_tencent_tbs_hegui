<h1 align="center">TBS SDK Demo</h1>

<p align="center">
<a href="https://x5.tencent.com/">腾讯浏览服务</a>（Tencent Browsing Service, TBS）致力于提供优化移动端浏览体验的整套解决方案。
</p>

## 🌟 X5WebDemo

该仓库为TBS-SDK使用Demo，为开发者提供SDK接入、功能使用等指引，以及便捷的X5 WebView开发模版。  
同时，开发者可在该仓库 `议题` 中，提出TBS相关的issues。  
更多信息请参考[腾讯浏览服务官网](https://x5.tencent.com/)。

### 文档指引

产品相关
- [腾讯浏览服务官网](https://x5.tencent.com/)
- [《腾讯浏览服务SDK个人信息保护规则》](https://x5.tencent.com/docs/privacy.html)

常见开发相关
- [SDK快速接入](https://x5.tencent.com/docs/access.html)
- [WebView能力](https://docs.qq.com/doc/DSndPaG5SSkdDUmxZ)

demo相关问题、建议可通过[https://x5.tencent.com/tbs/faq.html](https://x5.tencent.com/tbs/faq.html)进行反馈

## TBS 合规说明

一，静态扫描代码
如果静态扫描代码发现TBS有调用imei，imsi的系统接口的代码存在
如果监控网络请求发现TBS存在多次向 cfg.** 域名发请求且请求字段含手机型号
请更新TBS SDK 44174或以上版本

二，动态运行时调用
如果检测发现运行时调用敏感接口的堆栈含有 tbs、smtt关键字，可以更新TBS版本，执行如下两步解决
TBS提供的版本包括sdk和x5内核两部分
第一步， sdk是app集成的，需更新官网 TBS 44136，msdk的话需咨询msdk负责人更新集成了 TBS SDK 44142 的版本，gcloud的话需咨询gcloud负责人使用更新了TBS SDK 44136 及以上的版本
第二步， x5内核是动态下发的，需禁用旧内核（45911及以下内核），不需app修改，我们配置即可，我们可以配置禁用旧内核切换使用系统内核， 同时下发新内核， 需app同意，请提供app提供包名和app版本号，我们配置大于等于app版本命中
腾讯公司内联系 cameralu，腾讯公司外邮件联系 cameralu@tencent.com

TBS版本判断方法
方法一，你的app打开网页debugtbs.qq.com，点击查看TBS版本信息
方法二，TBS SDK 接口，QbSdk的getTbsVersion获取内核版本号，QbSdk的getTbsSDKVersion获取SDK版本号
备注：内核版本号是零也可以，此时是系统内核（安卓源码内核）


如果App不希望TBS获取手机型号（Build.Model），请在使用TBS前调用QbSdk的setUserID传入手机型号
public static void setUserID(Context context, Bundle b)
key是model，值是手机型号Build.Model，否则TBS会自取一次手机型号，如果App传入假手机型号，可能影响到TBS的X5内核的针对机型的下发和禁用控制，可能影响到X5Webview的load网页对机型的适配

如果App不希望TBS获取手机序列号SN（Build.Serial），请在使用TBS前调用QbSdk的setUserID传入手机序列号
还是setUserID接口
key是serial，值是手机序列号Build.Serial，如果app不方便取手机序列号，可以改为传个随机数字符串，长度无要求，TBS内部生成ID标记用户


## x5 合规
* 隐私合规怎么弄？
* 腾讯说问题不在他那
  * 但是从堆栈上看，是先调用了 TBS SDK 然后又用的系统内核。

* 继续使用检测工具，看看能不能抓到获取 mac 地址的调用顺序。  检测工具还是值的试一试的，因为还统计调用的次数。
  * 但是现在的问题是，抓不到这个堆栈。
* 好好分析下检测工具，完全搞懂，然后自己能不能扩展功能。

