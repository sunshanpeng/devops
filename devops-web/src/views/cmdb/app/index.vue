<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="filterItem" placeholder="应用名" style="width: 200px;" class="filter-item"
                clearable @keyup.enter.native="searchHandle()"/>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-search"
                 @click="searchHandle">
        搜索
      </el-button>
      <router-link :to="{path: '/cmdb/app/create'}">
        <el-button class="filter-item" style="margin-left: 10px;"
                   v-permission="['admin']"
                   type="primary" icon="el-icon-edit">
          新增
        </el-button>
      </router-link>
    </div>
    <el-table
      :data="tableData"
      stripe
      :border="true"
      style="width: 100%">
      <el-table-column
        label="应用名"
        width="180">
        <template slot-scope="scope">
          <router-link :to="{path: '/cmdb/app/overview/'+scope.row.appName}">
            <el-link type="primary">
              {{scope.row.appName}}
            </el-link>
          </router-link>
        </template>
      </el-table-column>
      <el-table-column
        prop="appDescription"
        label="应用描述"
        width="180">
      </el-table-column>
      <el-table-column
        prop="organization"
        label="部门/组"
        width="160">
      </el-table-column>
      <el-table-column
        prop="createTime"
        label="创建时间">
      </el-table-column>
      <el-table-column
        prop="modifyTime"
        label="修改时间">
      </el-table-column>
      <el-table-column label="操作" width="160">
        <template slot-scope="scope">
          <router-link :to="{path: '/cmdb/app/view/'+scope.row.appName}">
            <el-button type="text" size="small">详情</el-button>
          </router-link>
          <router-link :to="{path: '/cmdb/app/edit/'+scope.row.appName}">
            <el-button v-if="isAdmin(scope.row)" type="text" size="small">编辑</el-button>
          </router-link>
        </template>
      </el-table-column>
    </el-table>

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
  import {list} from "@/api/cmdb";
  import checkPermission from '@/utils/permission' // 权限判断函数
  import permission from '@/directive/permission/index' // 权限判断指令
  import store from '@/store'

  export default {
    name: "index",
    directives: { permission },
    data() {
      return {
        tableData: [],
        filterItem: '',
        pageIndex: 1,
        pageSize: 10,
        pageSizes: [10, 20, 50, 100],
        totalCount: 100,
      }
    },
    mounted() {
      this.searchHandle()
    },
    methods: {
      isAdmin(app) {
        if (checkPermission(['admin'])) {
          return true
        }
        let username = store.getters && store.getters.name
        if (username && username === app.primary.username) {
          return true
        }
        return app.secondary.some(member => {
          return member.username === username
        });
      },
      searchHandle() {
        let params = Object.assign({}, {
          pageIndex: this.pageIndex,
          pageSize: this.pageSize,
          appName: this.filterItem
        });
        list(params).then(response => {
          console.log(response)
          this.tableData = response.model
          this.totalCount = response.totalCount
        })
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
