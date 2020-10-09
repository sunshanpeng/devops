<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="应用名称">
        <el-input v-model="form.appName"></el-input>
      </el-form-item>
      <el-form-item label="应用描述">
        <el-input v-model="form.appDescription"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">立即创建</el-button>
        <el-button @click="onCancel">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  export default {
    name: "AppDetail",
    data() {
      return {
        form: {
          appName: '',
        }
      }
    },
    methods: {
      onSubmit() {
        console.log('submit!');
      },
      onCancel() {
        console.log('cancel!');
      }
    }
  }
</script>

<style scoped>

</style>
