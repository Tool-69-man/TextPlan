<!--<template>-->

    <!--<ul v-infinite-scroll="load"   class="infinite-list" style="overflow: auto">-->
      <!--<li v-for="i in count" :key="i" class="infinite-list-item">{{ i }}</li>-->
    <!--</ul>-->


<!--</template>-->

<!--<script>-->
    <!--export default {-->
        <!--name: "Scroll",-->
      <!--data(){-->
          <!--return{-->
            <!--count:50-->
        <!--}-->
      <!--},-->
      <!--methods:{-->
          <!--load(){-->
              <!--this.count += 2-->
          <!--},-->


      <!--}-->
    <!--}-->
<!--</script>-->

<!--<style scoped>-->

  <!--.infinite-list {-->
    <!--height: 300px;-->
    <!--padding: 0;-->
    <!--margin: 0;-->
    <!--list-style: none;-->
  <!--}-->
  <!--.infinite-list .infinite-list-item {-->
    <!--display: flex;-->
    <!--align-items: center;-->
    <!--justify-content: center;-->
    <!--height: 50px;-->
    <!--background: var(&#45;&#45;el-color-primary-light-9);-->
    <!--margin: 10px;-->
    <!--color: var(&#45;&#45;el-color-primary);-->
  <!--}-->
  <!--.infinite-list .infinite-list-item + .list-item {-->
    <!--margin-top: 10px;-->
  <!--}-->
<!--</style>-->




<!--下面是控制进度条     ts-->
<!--<template>-->
  <!--<el-scrollbar ref="scrollbarRef" height="400px" always @scroll="scroll">-->
    <!--<div ref="innerRef">-->
      <!--<p v-for="item in 20" :key="item" class="scrollbar-demo-item">-->
        <!--{{ item }}-->
      <!--</p>-->
    <!--</div>-->
  <!--</el-scrollbar>-->

  <!--<el-slider-->
    <!--v-model="value"-->
    <!--:max="max"-->
    <!--:format-tooltip="formatTooltip"-->
    <!--@input="inputSlider"-->
  <!--&gt;</el-slider>-->
<!--</template>-->

<!--<script lang="ts" setup>-->
  <!--import { onMounted, ref } from 'vue'-->
  <!--import type { ElScrollbar } from 'element-plus'-->

  <!--const max = ref(0)-->
  <!--const value = ref(0)-->
  <!--const innerRef = ref<HTMLDivElement>()-->
  <!--const scrollbarRef = ref<InstanceType<typeof ElScrollbar>>()-->

  <!--onMounted(() => {-->
    <!--max.value = innerRef.value!.clientHeight - 380-->
  <!--})-->

  <!--const inputSlider = (value: number) => {-->
    <!--scrollbarRef.value!.setScrollTop(value)-->
  <!--}-->
  <!--const scroll = ({ scrollTop }) => {-->
    <!--value.value = scrollTop-->
  <!--}-->
  <!--const formatTooltip = (value: number) => {-->
    <!--return `${value} px`-->
  <!--}-->
<!--</script>-->
<!--<template>-->
  <!--<el-scrollbar ref="scrollbarRef" height="400px" always @scroll="scroll">-->
    <!--<div ref="innerRef">-->
      <!--<p v-for="item in 20" :key="item" class="scrollbar-demo-item">-->
        <!--{{ item }}-->
      <!--</p>-->
    <!--</div>-->
  <!--</el-scrollbar>-->

  <!--<el-slider-->
    <!--v-model="value"-->
    <!--:max="max"-->
    <!--:format-tooltip="formatTooltip"-->
    <!--@input="inputSlider"-->
  <!--&gt;</el-slider>-->
<!--</template>-->

<!--<script lang="ts" setup>-->
  <!--import { onMounted, ref } from 'vue'-->
  <!--import type { ElScrollbar } from 'element-plus'-->

  <!--const max = ref(0)-->
  <!--const value = ref(0)-->
  <!--const innerRef = ref<HTMLDivElement>()-->
  <!--const scrollbarRef = ref<InstanceType<typeof ElScrollbar>>()-->

  <!--onMounted(() => {-->
    <!--max.value = innerRef.value!.clientHeight - 380-->
  <!--})-->

  <!--const inputSlider = (value: number) => {-->
    <!--scrollbarRef.value!.setScrollTop(value)-->
  <!--}-->
  <!--const scroll = ({ scrollTop }) => {-->
    <!--value.value = scrollTop-->
  <!--}-->
  <!--const formatTooltip = (value: number) => {-->
    <!--return `${value} px`-->
  <!--}-->
<!--</script>-->
