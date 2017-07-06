# ScreenShotAndQRCode
 截屏监听在大APP里有全局监听，通过Service来实现，这里只是通过工具类ScreenShotListenManager来管理截屏监听
 
 
 ShareDealDetailScreenQRCodeUtils工具类是抽离出来的具体实现，包括二维码的生产，图片拼接，dialog弹出，分享等...
 
 
 之所以没写成静态方法是因为dialog需要用到Activity，而Activity最好不要声明成static，避免内存泄漏。（ps:另dialog可以实现全局弹出，这里的需求用不到，只是单个页面）
# 使用方法
  在Activity或者fragment初始化截屏监听，并且start监听
  showShare()为具体要操作的方法，imagePath为截屏的图片路径，mWebUrl为需要生产的二维码地址
```java
  manager = ScreenShotListenManager.newInstance(this);
  manager.setListener(
                new ScreenShotListenManager.OnScreenShotListener() {
                    public void onShot(String imagePath) {
                        // do something 
                        ShareDealDetailScreenQRCodeUtils listener = new ShareDealDetailScreenQRCodeUtils();
                        listener.showShare(DealDetailActivity.this, imagePath, mWebUrl);
                    }
                }
        );
  manager.startListen();
```
###  在onDestory()方法中要停止监听
```java
    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.stopListen();
    }
```
### 效果图
![image](https://github.com/yangzhanga/ScreenShotAndQRCode/raw/master/image/image.jpg)
