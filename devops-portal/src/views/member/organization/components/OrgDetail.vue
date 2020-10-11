<template>
  <el-dialog title="组织结构"
             :visible.sync="option.dialogVisible"
             :modal-append-to-body="false"
             :close-on-click-modal="false"
             width="30%">
    <el-form :model="form"
             label-position="right"
             label-width="80px">
      <el-form-item label="组织名称">
        <el-input v-model="form.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="上级组织">
        <el-input v-model="form.parentId" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="onSubmit">保存</el-button>
      <el-button @click="option.dialogVisible = false">取消</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import {createOrg} from "@/api/member";

  export default {
    name: "MemberDetail",
    props: {
      option: {
        type: Object,
        default: {
          dialogVisible: false,
          editModel: null
        }
      }
    },
    data() {
      return {
        form : {
          name:'',
          parentId:'',
        }
      }
    },
    methods: {
      onSubmit() {
        createOrg(this.form).then(response => {
          this.$emit('refresh');
          this.option.dialogVisible = false
          this.$message({
            message: '操作成功！',
            type: 'success'
          })
        })
      }
    }
  }
</script>

<style scoped>

</style>
