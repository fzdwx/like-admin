<template>
  <base-content>
    <template>
      <!--    选择表格切割方式  -->
      <div class="q-pa-md">
        <q-option-group
            v-model="separator"
            inline
            class="q-mb-md"
            :options="[
        { label: 'Horizontal', value: 'horizontal' },
        { label: 'Vertical', value: 'vertical' },
        { label: 'Cell', value: 'cell' },
        { label: 'None', value: 'none' }
      ]"
        />
        <q-markup-table :separator="separator" flat bordered>
          <thead>
          <tr>
            <th class="text-center">用户编码</th>
            <th class="text-center">姓名</th>
            <th class="text-center">昵称</th>
            <th class="text-center">性别</th>
            <th class="text-center">手机号码</th>
            <th class="text-center">邮箱</th>
            <th class="text-center">头像</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="user in userList">
            <td class="text-center q-td--no hover">{{ user.id }}</td>
            <td class="text-center q-td--no hover">{{ user.username }}</td>
            <td class="text-center q-td--no hover">{{ user.nikename }}</td>
            <td class="text-center q-td--no hover">{{ user.sex }}</td>
            <td class="text-center q-td--no hover">{{ user.phone }}</td>
            <td class="text-center q-td--no hover">{{ user.email }}</td>
            <td class="text-center q-td--no hover">{{ user.avatar }}</td>
          </tr>
          </tbody>
        </q-markup-table>

      </div>
    </template>
  </base-content>
</template>

<script>
import BaseContent from '../../components/BaseContent/BaseContent'
import {getUserList} from "@/api/UserApi";


export default {
  name: 'list',
  components: {BaseContent},
  data() {
    return {
      msg: '欢迎！',
      separator: 'vertical',
      userList: "",
      loading: false,
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
        total: null,
        isHasNextPage: null,
        isHasPreviousPage: null
      }
    }
  },
  created() {
    let data1 = this.query;
    this.loading = !this.loading
    getUserList(JSON.stringify(data1))
        .then(res => {
          this.userList = res.list
          res.list = null
          Object.assign(this.query, res);
          this.loading = !this.loading
        })
  }
}
</script>
<style>

</style>
