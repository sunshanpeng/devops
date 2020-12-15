<template>
  <div>
    <el-container>
      <el-aside>
        <env-cluster-tree @select="treeHandleClick"></env-cluster-tree>
      </el-aside>
      <el-main>
        <el-tabs v-model="activeTab" type="card" @tab-click="tabHandleClick">
          <el-tab-pane label="应用实例" name="instance">
            <k8s-instance :form="form"></k8s-instance>
          </el-tab-pane>
          <el-tab-pane label="应用域名" name="domain">应用域名</el-tab-pane>
          <el-tab-pane label="应用配置" name="config">应用配置</el-tab-pane>
        </el-tabs>
      </el-main>
    </el-container>
  </div>
</template>

<script>
  import EnvClusterTree from "./components/EnvClusterTree"
  import K8sInstance from "./components/K8sInstance"

  export default {
    name: "overview",
    components: {EnvClusterTree, K8sInstance},
    data() {
      return {
        form: {
          envCode: '',
          clusterCode: '',
          appName: '',
        },
        activeTab: 'instance'
      };
    },
    mounted() {
      this.init()
      this.form.appName = this.$route.params.appName
    },
    methods: {
      tabHandleClick(tab, event) {
        console.log(tab, event);
      },
      treeHandleClick(envCode, clusterCode) {
        this.form.envCode = envCode
        this.form.clusterCode = clusterCode
      },
      init() {

      },
    }
  }
</script>

<style scoped>

</style>
