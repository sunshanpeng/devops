<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="filterItem" placeholder="用户名/手机号" style="width: 200px;" class="filter-item"
                @keyup.enter.native="handleFilter()"/>
      <el-button v-waves class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-search" @click="handleFilter">
        搜索
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
        新增
      </el-button>
    </div>
    <el-table
      :data="tableData"
      stripe
      border="true"
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
  </div>
</template>

<script>
  import {list} from "@/api/member";

  export default {
    name: "index",
    data() {
      return {
        tableData: [],
        filterItem: ''
      }
    },
    mounted() {
      list().then(response => {
        console.log(response)
        this.tableData = response.model
      })
    },
    methods: {
      handleFilter() {

      },
      handleCreate() {

      }
    }
  }
</script>

<style scoped>

</style>
