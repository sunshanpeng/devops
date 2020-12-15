<template>
  <el-menu
    @select="handleSelect"
    :default-active="env"
    :default-openeds="openeds">
    <el-submenu v-for="item in envs" :key="item.value" :index="item.value">
      <template slot="title">
        <i class="eboss-icon-menu"></i>
        <span>{{item.label}}</span>
      </template>
      <template v-if="item.children" v-for="(child , index2) in item.children">
        <el-menu-item :index="child.value" :key="child.value">
          <a>
            {{child.label}}
          </a>
        </el-menu-item>
      </template>
    </el-submenu>
  </el-menu>
</template>

<script>
  import {envClusterTree} from "@/api/cmdb"

  export default {
    name: "EnvClusterTree",
    data() {
      return {
        openeds: [],    //当前展示菜单id
        envs: [],
        env: '',
        cluster: '',
        appName: '',
      }
    },
    mounted() {
      this.init()
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log("select:", key, keyPath)
        this.env = keyPath[0]
        this.cluster = keyPath[1]
      },
      init() {
        this.appName = this.$route.params.appName
        envClusterTree().then(response => {
          this.envs = response.model
          this.openeds = response.model.map((item) => {
            return item.value
          })
        })
      }
    }
  }
</script>

<style scoped>

</style>
