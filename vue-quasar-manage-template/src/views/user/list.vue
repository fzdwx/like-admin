<template>
  <base-content>
    <template>
      <!--   表格  -->
      <div class="q-pa-md">
        <div>
          <q-btn label="搜索" @click="loadUsers()"/>
        </div>
        <q-markup-table :separator="separator" flat bordered>
          <thead>
          <tr>
            <th class="text-center">用户编码</th>
            <th class="text-center">姓名</th>
            <th class="text-center">昵称</th>
            <th class="text-center">性别</th>
            <th class="text-center">手机号码</th>
            <th class="text-center">邮箱</th>
            <th class="text-center">注册时间</th>
            <th class="text-center">用户类型</th>
            <th class="text-center">头像</th>
            <th class="">操作</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="user in userList">
            <td class="text-center q-td--no hover">{{ user.id }}</td>
            <td class="text-center q-td--no hover">{{ user.username }}</td>
            <td class="text-center q-td--no hover">{{ user.nickname }}</td>
            <td class="text-center q-td--no hover">{{ user.sex }}</td>
            <td class="text-center q-td--no hover">{{ user.phone }}</td>
            <td class="text-center q-td--no hover">{{ user.email }}</td>
            <td class="text-center q-td--no hover">{{ user.createDate }}</td>
            <td class="text-center q-td--no hover">{{ user.type }}</td>
            <td class="text-center q-td--no hover">
              <q-avatar rounded>
                <img :src="user.avatar">
              </q-avatar>
            </td>
            <td class="content-center">
              <q-btn class="btn-table text-white" icon="edit" @click="edit(user)" :ripple="{ center: true }"
                     label="编 辑"/>
            </td>
          </tr>
          </tbody>
        </q-markup-table>
        <q-pagination class="float-left" input
                      v-model="query.pageNum"
                      color="cyan"
                      :max="query.pageCount"
                      boundary-numbers
        />
      </div>

      <!-- 编辑对话框 -->
      <q-dialog v-model="editDialog" persistent>
        <q-card style="min-width: 350px">
          <q-card-section>
            <div class="text-h6"> 编辑用户信息</div>
          </q-card-section>

          <q-card-section class="q-pt-none q-gutter-y-md column" style="width: 500px;">
            <q-input rounded type="" standout="bg-teal text-white" label="姓名" v-model="editUser.username" autofocus/>
            <q-input rounded standout="bg-teal text-white" label="昵称" v-model="editUser.nickname" autofocus/>
            <q-input type="tel" rounded standout="bg-teal text-white" label="手机号码" v-model="editUser.phone" autofocus/>
            <q-input type="email" rounded standout="bg-teal text-white" label="邮箱" v-model="editUser.email" autofocus/>
            <div class="q-gutter-sm">
              <q-radio color="cyan" v-model="editUser.sex" val="1" label="男"/>
              <q-radio color="cyan" v-model="editUser.sex" val="2" label="女"/>
            </div>
            <q-input disable rounded standout="bg-teal text-white" label="用户类别" v-model="editUser.type" autofocus/>
            <q-file name="file" :filter="check" color="teal" filled v-model="file" label="上传头像">
              <template v-slot:prepend>
                <q-icon name="cloud_upload"/>
              </template>
            </q-file>
            <q-avatar rounded>
              <q-img :src="editUser.avatar"/>
            </q-avatar>
          </q-card-section>

          <q-card-actions align="right" class="text-primary">
            <q-btn flat label="取消" v-close-popup @keyup.enter="editDlog = !editDlog"/>
            <q-btn flat label="确认" @click="doEdit" v-close-popup/>
          </q-card-actions>
        </q-card>
      </q-dialog>
    </template>
  </base-content>
</template>

<script>
import BaseContent from '../../components/BaseContent/BaseContent'
import {getUserList} from "@/api/UserApi";
import CommonUtil from '@/utils/commonUtil'
import {post, postUpload} from "@/axios/FetchData";

export default {
  name: 'list',
  components: {BaseContent},
  data() {
    return {
      msg: '欢迎！',
      separator: 'horizontal',
      userList: "",
      file: null,
      query: {
        userId: null,
        username: null,
        nickname: null,
        sex: null,
        email: null,
        phone: null,
        ids: null,
        pageNum: 1,
        pageSize: 20,
        pageCount: 0,
        total: null,
        isHasNextPage: null,
        isHasPreviousPage: null
      },
      editDialog: false,
      editUser: Object,
    }
  },
  methods: {
    // 检查文件格式
    check(file) {
      if (CommonUtil.uploadFileCheckImage(file[0])) {
        return file
      }
      return null;
    },
    // 加载用户数据
    loadUsers() {
      // alert(JSON.stringify(this.query))
      CommonUtil.showLoading()
      getUserList(JSON.stringify(this.query)).then(res => {
        this.userList = res.list
        res.list = null
        Object.assign(this.query, res);
        CommonUtil.hideLoading()
      }).catch(e => {
        CommonUtil.hideLoading()
      });
    },
    // 编辑用户
    edit(user) {
      this.editDialog = !this.editDialog
      this.editUser = user
    },
    doEdit() {
      let formData = new FormData();
      formData.append("file",this.file)
      formData.append("username",this.file)
      postUpload("/uploadUserImage",formData ).then(res => {
        console.log(res);
      })
      post("/user/update_by_id", JSON.stringify(this.editUser))
          .then(res => {
            if (typeof res === "number") {
              if (res > 0) {
                CommonUtil.notifySuccess("修改成功");
              } else {
                CommonUtil.notifySuccess("修改失败，请重试");
              }
            }
            this.loadUsers()
          });
    }
  },
  created() {
    this.loadUsers()
  }
}
</script>
<style>

.btn-table {
  background: linear-gradient(to right, #36d1dc, #5b86e5);
  transition: all 0.3s ease-in-out;
}
</style>
