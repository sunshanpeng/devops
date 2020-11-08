<template>
  <div class="app-container">
    实例列表
    <div class="table-container">
      <el-table
        :data="tableData"
        stripe
        :border="true"
        style="width: 100%">
        <el-table-column
          prop="podName"
          label="容器名"
          width="180">
        </el-table-column>
        <el-table-column
          prop="podIp"
          label="容器IP">
        </el-table-column>
        <el-table-column
          prop="hostIp"
          label="节点IP">
        </el-table-column>
        <el-table-column
          prop="podStatus"
          label="状态">
        </el-table-column>
        <el-table-column
          prop="restartCount"
          label="重启次数">
        </el-table-column>
        <el-table-column
          prop="createTime"
          label="创建时间">
        </el-table-column>
        <el-table-column label="操作" width="160">
          <template slot-scope="scope">
            <el-button type="text" size="small">终端</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
  import { pods } from '@/api/cmdb'

  export default {
    name: 'K8sInstance',
    props: {
      form: {
        type: Object,
        default: {
          envCode: '',
          clusterCode: '',
          appName: ''
        }
      }
    },
    data() {
      return {
        tableData: []
      }
    },
    mounted() {
      this.init()
    },
    watch: {
      'form.clusterCode': function (newValue, oldValue){
        this.getPods()
      }
    },
    methods: {
      getPods() {
        pods(this.form.appName, this.form.clusterCode).then(response => {
          this.tableData = response.model
        })
      },
      init() {

      }
    }
  }
</script>

<style scoped>

</style>
