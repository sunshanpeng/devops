<template>
  <el-menu
    @open="handleOpen"
    @close="handleClose"
    :default-openeds="openeds">
    <el-submenu v-for="item in envs" :key="item.envCode" :index="item.envCode">
      <template slot="title">
        <i class="eboss-icon-menu"></i>
        <span>{{item.envName}}</span>
      </template>
      <template v-if="item.clusters" v-for="(child , index2) in item.clusters">
        <el-menu-item :index="child.clusterCode" :key="child.clusterCode">
          <a>
            {{child.clusterName}}
          </a>
        </el-menu-item>
      </template>
    </el-submenu>
  </el-menu>
</template>

<script>
  export default {
    name: "EnvClusterTree",
    data() {
      return {
        openeds: ["dev","test"],    //当前展示菜单id
        envs: [{
          envCode: "dev",
          envName: "DEV",
          clusters: [{
            clusterCode: "huawei1",
            clusterName: "华为开发集群"
          }]
        },{
          envCode: "test",
          envName: "TEST",
          clusters: [{
            clusterCode: "huawei2",
            clusterName: "华为测试集群"
          }]
        }],
      }
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>

<style scoped>

</style>
