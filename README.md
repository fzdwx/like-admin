# like-admin
后台

~~~
1.后台Mysql执行
  查看 SqlLogInterceptor#getSql()该方法 
~~~


上传头像
  
    约定访问路径问 前端url/image/{userId}
~~~js
 let formData = new FormData();
      formData.append("file",this.file)
      postUpload("/upload",formData ).then(res => {
        console.log(res);
      })
~~~
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
