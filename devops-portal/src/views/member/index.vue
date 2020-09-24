<template>
  <el-table
    :data="tableData"
    stripe
    style="width: 100%">
    <el-table-column
      prop="username"
      label="用户名"
      width="180">
    </el-table-column>
    <el-table-column
      prop="phone"
      label="手机号"
      width="160">
    </el-table-column>
    <el-table-column
      prop="email"
      label="邮箱"
      width="200">
    </el-table-column>
    <el-table-column
      prop="createTime"
      label="创建时间">
    </el-table-column>
    <el-table-column
      prop="modifyTime"
      label="修改时间">
    </el-table-column>
  </el-table>
</template>

<script>
  import {list} from "@/api/member.js";

  export default {
      name: "index",
      data() {
        return {
          tableData: []
        }
      },
      mounted() {
        list().then(response => {
          console.log(response)
          this.tableData = response.model
        })
      }
    }
</script>

<style  scoped>

</style>
