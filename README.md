# like-admin
后台

~~~
1.后台Mysql执行
  查看 SqlLogInterceptor#getSql()该方法 
~~~





## 上传头像

    1.约定访问路径问 http://localhost:8868/image/{sign}.jpg  =>http://localhost:8868/image/1621312619057.jpg 
    2.登陆的时候在session中存放http://localhost:8868/image/1621312619057.jpg
    3.上传头像=> 删除 userAvatarViewPath + imageName
    4.保存新的头像名称 userAvatarViewPath + newImageName
    5.更新session
    6.更新数据库

### 配置

~~~yml
upload:
  imageDir: D:\Java\project\stduyproject\like-admin\vue-quasar-manage-template\public\image\
  userAvatarViewPath: http://localhost:8868/image/
~~~

### 前端代码示例  

~~~js
 let formData = new FormData();
      formData.append("file",this.file)
      postUpload("/upload",formData ).then(res => {
        console.log(res);
      })
~~~
### 后端代码示例

~~~java
    @PostMapping("/upload")
    public String handleFileUpload(@RequestPart(value = "file") final MultipartFile uploadfile) throws IOException {
            return saveUploadedFiles(uploadfile);
            }
    
    private String saveUploadedFiles(final MultipartFile file) throws IOException {
    final byte[] bytes = file.getBytes();
            // TODO: 2021/5/18 type check
            String type = file.getContentType();
    
            // image Name
            String name = file.getOriginalFilename();
            name = new Date().getTime() + name.substring(file.getOriginalFilename().indexOf('.'));
    final Path path = Paths
            .get(uploadConstant.getImageDir() + name);
            Files.write(path, bytes);
    
            // 将用户图片名称保存的session中
            SaSession session = StpUtil.getSession();
            String avatarAddr = session.get(SESSION_KEY_AVATAR).toString();
            String imageName = avatarAddr.replace(uploadConstant.getUserAvatarViewPath(), "");
            // 删除原来的图片
            Files.delete(Paths.get(uploadConstant.getImageDir() + imageName));
            avatarAddr = uploadConstant.getUserAvatarViewPath() + name;
            session.set(SESSION_KEY_AVATAR, avatarAddr);
    
            userService.updateSelectiveById(new UserQuery().setAvatar(avatarAddr).setUserId(StpUtil.getLoginIdAsLong()));
            return name;
            }
~~~

### todo

~~~
使用fdfs
~~~

