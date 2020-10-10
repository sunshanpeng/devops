<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="100px">
      <el-row>
        <el-col :span="8">
          <el-form-item label="应用名称">
            <el-input v-model="form.appName"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="应用描述">
            <el-input v-model="form.appDescription"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="应用类型">
            <el-input v-model="form.appType"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="应用等级">
            <el-input v-model="form.appLevel"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="应用状态">
            <el-input v-model="form.status"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="应用端口">
            <el-input v-model="form.ports"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="部门/组">
            <el-input v-model="form.organization"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="主要负责人">
            <el-input v-model="form.primary"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="次要负责人">
            <el-input v-model="form.secondary"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="代码地址">
            <el-input v-model="form.codeUrl"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="制品路径">
            <el-input v-model="form.artifactPath"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="存活探针">
            <el-input v-model="form.liveness"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="就绪探针">
            <el-input v-model="form.readiness"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <div class="text-center">
        <el-button type="primary" @click="onSubmit">保存</el-button>
        <el-button @click="onCancel">取消</el-button>
      </div>
    </el-form>
  </div>
</template>

<script>
  export default {
    name: "AppDetail",
    props: {
      isEdit: {
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        form: {
          appName: '',
          appDescription: '',
          appType: '',
          appLevel: '',
          status: '',
          organization: '',
          ports: '',
          primary: '',
          secondary: '',
          codeUrl: '',
          artifactPath: '',
          liveness: '',
          readiness: '',
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
