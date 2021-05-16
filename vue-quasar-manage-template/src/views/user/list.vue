<template>
  <base-content>
    <h1>{{ msg }}</h1>
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
      userList: "",
      query: {
        userId: null,
        username: null,
        nickname: null,
        sex: null,
        email: null,
        phone: null,
        ids: null,
        pageNum:1,
        pageSize: 20,
      }
    }
  },
  created() {
    let data1 = this.query;
    console.log("tostring:"+JSON.stringify(data1));
    getUserList(JSON.stringify(data1))
    .then(res=>{
      console.log(res);
    })
  }
}
</script>
