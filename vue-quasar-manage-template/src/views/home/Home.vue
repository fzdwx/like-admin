<template>

  <base-content>
    <div>
      <h1 class="my-card income">Hello </h1>
    </div>
  </base-content>

</template>

<script>
import countTo from 'vue-count-to'
import BaseContent from '../../components/BaseContent/BaseContent'
import {thumbStyle} from '@/components/BaseContent/ThumbStyle'
import chartPie from '../../assets/js/echarts-1'
import charts2Option from '../../assets/js/echarts-2'
import {income, expense, total} from '@/assets/js/echarts-3'
import chartZ from '../../assets/js/echarts-4'

export default {
  name: 'Home',
  components: {
    BaseContent,
    countTo
  },
  data() {
    return {
      expanded: false,

    }
  },
  methods: {
    handleTableClick(e) {
      this.$router.push({
        path: 'tableDetail',
        query: {
          id: e.name
        }
      })
    }
  }
}
</script>
<style lang="css" scoped>
.my-card {
  width: 100%;
  min-height: 390px;
  height: 100%;
  /*max-width: 350px;*/
}

.income {
  width: 100%;
  background: linear-gradient(to right, #68E4BC 0%, #4AD0D1 99%);
  border-radius: 5px;
  font-size: 14px;
  padding: 11px 15px;
  font-weight: bold;
  width: 100%;
  cursor: pointer;
  transition: all 0.3s ease-in-out;
  color: #ffffff;
  box-shadow: 0 12px 12px -11px #0db4afb8;
  background-size: 200% auto;
}

.income:hover {
  background-position: right center;
  box-shadow: 0 12px 20px -11px #0db4afb8;
}

.expense {
  width: 100%;
  background: linear-gradient(to left, #FCAC94 0%, #f3a183 98%);
  border-radius: 5px;
  font-size: 14px;
  padding: 11px 15px;
  font-weight: bold;
  width: 100%;
  cursor: pointer;
  transition: all 0.3s ease-in-out;
  color: #ffffff;
  box-shadow: 0 12px 12px -11px #FCA76C;
  background-size: 200% auto;
}

.expense:hover {
  background-position: right center;
  box-shadow: 0 12px 20px -11px #FCA76C;
}

.total {
  width: 100%;
  background: linear-gradient(90deg, #F073C8 0%, #FF6A95 99%);
  border-radius: 5px;
  font-size: 14px;
  padding: 11px 15px;
  font-weight: bold;
  width: 100%;
  cursor: pointer;
  transition: all 0.3s ease-in-out;
  color: #ffffff;
  box-shadow: 0 12px 12px -11px rgba(240, 115, 200, 0.73);
  background-size: 200% auto;
}

.total:hover {
  background-position: right center;
  box-shadow: 0 12px 20px -11px rgba(240, 115, 200, 0.73);
}

.btn-table {
  background: linear-gradient(to right, #36d1dc, #5b86e5);
  transition: all 0.3s ease-in-out;
}

.btn-table:hover {
  background-position: right center;
  box-shadow: 0 12px 20px -11px #5b86e5;
}
</style>
