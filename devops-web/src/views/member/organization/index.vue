<template>
  <div>
    <el-row>
      <el-col :span="6">
        <org-tree @changeNode="changeNode"></org-tree>
      </el-col>
      <el-col :span="18">
        <div class="app-container">
          <div class="filter-container">
            <el-input v-model="filterItem" placeholder="部门名称" style="width: 200px;" class="filter-item"
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
                prop="name"
                label="部门名称"
                width="180">
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
          <org-detail :option="detailOption" @refresh="searchHandle"></org-detail>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import {orgList} from "@/api/member";
  import OrgDetail from './components/OrgDetail'
  import OrgTree from '@/components/OrgTree/index'

  export default {
    name: "index",
    components: {OrgDetail, OrgTree},
    data() {
      return {
        tableData: [],
        filterItem: '',
        rootNode: null,
        pageIndex: 1,
        pageSize: 10,
        pageSizes: [10, 20, 50, 100],
        totalCount: 100,
        detailOption: {
          dialogVisible: false,
        }
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
          name: this.filterItem,
          parentId: this.rootNode,
        });
        orgList(params).then(response => {
          console.log(response)
          this.tableData = response.model
          this.totalCount = response.totalCount
        })
      },
      handleCreate() {
        this.detailOption.dialogVisible = true
      },
      changeNode(node) {
        this.rootNode = node.id
        this.searchHandle()
      },
      handleSizeChange(pageSize) {
        this.pageSize = pageSize
        this.pageIndex = 1
        this.searchHandle()
      },
      handleCurrentChange(pageIndex) {
        this.pageIndex = pageIndex
        this.searchHandle()
      },
    }
  }
</script>

<style scoped>

</style>
