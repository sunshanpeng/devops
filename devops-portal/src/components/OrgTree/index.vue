<template>
  <div class="app-container">
    <el-tree
      :highlight-current="true"
      :expand-on-click-node="false"
      :data="orgList"
      :props="defaultProps"
      @node-click="handleNodeClick"
      class="filter-tree"
      default-expand-all
    />
  </div>
</template>

<script>
  import {orgTree} from "@/api/member";

  export default {
    name: "OrgTree",
    data() {
      return {
        orgList: [],
        defaultProps: {
          children: 'children',
          label: 'label'
        }
      }
    },
    mounted() {
      this.init();
    },
    methods: {
      handleNodeClick(node) {
        this.$emit('changeNode', node);
      },
      init() {
        orgTree().then(response => {
          this.orgList = response.model
        })
      }
    }
  }
</script>

<style scoped>

</style>
