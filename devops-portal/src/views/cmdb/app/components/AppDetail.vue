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
            <el-select v-model="form.appType" placeholder="请选择">
              <el-option
                v-for="item in appType"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="应用等级">
            <el-select v-model="form.appLevel" placeholder="请选择">
              <el-option
                v-for="item in appLevel"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="应用状态">
            <el-select v-model="form.status" placeholder="请选择">
              <el-option
                v-for="item in appStatus"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
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
            <el-cascader v-model="form.orgValue"
                         ref="orgCascader"
                         :options="orgList"
                         filterable
                         clearable
                         @change="orgChangeHandle">
            </el-cascader>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="主要负责人">
            <el-select
              v-model="form.primary.username"
              filterable
              remote
              placeholder="请输入主要负责人"
              :remote-method="remoteUserMethod"
              @change="primaryChangeHandle">
              <el-option
                v-for="item in searchUsers"
                :key="item.username"
                :label="item.fullName"
                :value="item.username">
                <span>{{item.fullName}}_{{item.username}}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="次要负责人">
            <el-select
              v-model="form.secondary"
              filterable
              multiple
              remote
              placeholder="请输入次要负责人"
              :remote-method="remoteUserMethod"
              @change="secondaryChangeHandle">
              <el-option
                v-for="item in searchUsers"
                :key="item.username"
                :label="item.fullName"
                :value="item.username">
                <span>{{item.fullName}}_{{item.username}}</span>
              </el-option>
            </el-select>
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
          <el-form-item label="就绪探针端口">
            <el-input v-model="form.readiness.monitorPort"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="就绪探针路径">
            <el-input v-model="form.readiness.monitorPath"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="存活探针端口">
            <el-input v-model="form.liveness.monitorPort"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="存活探针路径">
            <el-input v-model="form.liveness.monitorPath"></el-input>
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
  import {dict, createApp} from "@/api/cmdb";
  import {search, orgTree} from "@/api/member";

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
          orgValue: [],
          organization: [],
          ports: '',
          primary: {
            username: '',
            fullName: ''
          },
          secondary: [],
          secondaryCopy: [],
          codeUrl: '',
          artifactPath: '',
          liveness: {
            monitorPort: 8080,
            monitorPath: '/actuator/info',
          },
          readiness: {
            monitorPort: 8080,
            monitorPath: '/actuator/health',
          },
        },
        appType: [],
        appLevel: [],
        appStatus: [],
        searchUsers: [],
        orgList: [],
      }
    },
    mounted() {
      this.init()
    },
    methods: {
      onSubmit() {
        this.$confirm('确定要保存吗？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消'
        }).then(() => {
          this.form.secondary = this.form.secondaryCopy
          this.form.organization = this.form.organization.join("/")
          createApp(this.form).then(response => {
            this.$alert('保存成功!', '', {
              showCancelButton: false, callback: action => {
                this.$router.push({
                  path: '/cmdb/app'
                })
              }
            })
          })
        }).catch(() => {
          //do nothing
        })
      },
      onCancel() {
        this.$confirm('信息未保存，确定取消本次编辑吗？', '提示', {
          confirmButtonText: '继续编辑',
          cancelButtonText: '返回列表页'
        }).then(() => {
        }).catch(() => {
          this.$router.push({
            path: '/cmdb/app'
          })
        })
      },
      remoteUserMethod(keyword) {
        search(keyword).then(response => {
          this.searchUsers = response.model
        })
      },
      primaryChangeHandle(value) {
        let temp = this.searchUsers.filter(person => person.username === value)
        if (temp.length) {
          this.$set(this.form.primary, 'username', temp[0].username)
          this.$set(this.form.primary, 'fullName', temp[0].fullName)
          this.remoteUserMethod('');
        }
      },
      secondaryChangeHandle(value) {
        this.form.secondaryCopy = []
        this.remoteUserMethod('');
        for (let i = 0; i < value.length; i++) {
          let username = value[i]
          let member = this.searchUsers.filter(item => item.username === username)
          if (member.length) {
            this.form.secondaryCopy.push(member[0])
          }
        }
      },
      orgChangeHandle(value) {
        this.form.organization = []
        let arr = this.$refs.orgCascader.getCheckedNodes()[0].pathLabels
        for (let i = 0; i < arr.length; i++) {
          let org = arr[i]
          if (org.length) {
            this.form.organization.push(org)
          }
        }
      },
      init() {
        let dicts = ["appType", "appLevel", "appStatus"]
        dict(dicts).then(response => {
          this.appType = response.model.appType
          this.appLevel = response.model.appLevel
          this.appStatus = response.model.appStatus
        })
        orgTree().then(response => {
          this.orgList = response.model
        })
        this.remoteUserMethod('');
      }
    }
  }
</script>

<style scoped>
</style>
