<template>
  <el-dialog title="用户信息"
             :visible.sync="option.dialogVisible"
             :modal-append-to-body="false"
             :close-on-click-modal="false"
             width="30%">
    <el-form :model="form"
             label-position="right"
             label-width="60px">
      <el-form-item label="用户名">
        <el-input v-model="form.username" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="姓名">
        <el-input v-model="form.fullName" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="手机号">
        <el-input v-model="form.phone" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="form.email" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="onSubmit">保存</el-button>
      <el-button @click="option.dialogVisible = false">取消</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import {createMember} from "@/api/member";

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
          username:'',
          fullName:'',
          phone:'',
          email:'',
        }
      }
    },
    methods: {
      onSubmit() {
        createMember(this.form).then(response => {
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
