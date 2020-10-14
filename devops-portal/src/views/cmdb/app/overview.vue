<template>
  <div>
    <el-row>
    </el-row>

    <el-row>
      <el-col :span="6">
        <env-cluster-tree></env-cluster-tree>
      </el-col>
      <el-col :span="18">
        <div class="app-container">

        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import EnvClusterTree from "./components/EnvClusterTree"

  export default {
    name: "overview",
    components: {EnvClusterTree},
  }
</script>

<style scoped>

</style>
