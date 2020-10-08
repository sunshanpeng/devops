<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="filterItem" placeholder="用户名/姓名" style="width: 200px;" class="filter-item"
                clearable @keyup.enter.native="searchHandle()"/>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-search"
                 @click="searchHandle">
        搜索
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit"
                 @click="handleCreate">
        新增
      </el-button>
    </div>
    <div class="table-container">
      <el-table
        :data="tableData"
        stripe
        :border="true"
        style="width: 100%">
        <el-table-column
          prop="username"
          label="用户名"
          width="180">
        </el-table-column>
        <el-table-column
          prop="fullName"
          label="姓名"
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
    <div class="page-container">
      <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageIndex"
        :page-sizes="pageSizes"
        :page-size="pageSize"
        :total="totalCount"
        layout="total, sizes, prev, pager, next">
      </el-pagination>
    </div>
  </div>
</template>

<script>
  import {list} from "@/api/member";
  export default {
    name: "index",
    data() {
      return {
        tableData: [],
        filterItem: '',
        pageIndex: 1,
        pageSize: 10,
        pageSizes: [10, 20, 50, 100],
        totalCount: 100,
        queryCount: true,
      }
    },
    mounted() {
      this.searchHandle()
    },
    methods: {
      searchHandle() {
        let params = Object.assign({}, {
          pageIndex: this.pageIndex,
          pageSize: this.pageSize,
          username: this.filterItem
        });
        list(params).then(response => {
          console.log(response)
          this.tableData = response.model
          this.totalCount = response.totalCount
        })
      },
      handleCreate() {

      },
      handleSizeChange (pageSize) {
        this.pageSize = pageSize
        this.pageIndex = 1
        this.searchHandle()
      },
      handleCurrentChange (pageIndex) {
        this.pageIndex = pageIndex
        this.searchHandle()
      },
    }
  }
</script>

<style scoped>

</style>
