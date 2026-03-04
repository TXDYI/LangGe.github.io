<template>
  <div class="chart-root">
    <!-- 顶部筛选 + 切换图表类型 -->
    <el-card class="filter-card" shadow="hover">
      <div class="filter-row">
        <el-form :inline="true" class="filter-form">
          <el-form-item label="游戏区服">
            <el-select
              v-model="selectedRegion"
              placeholder="选择游戏"
              clearable
              @clear="handleRegionClear"
              style="width: 180px"
            >
              <el-option
                v-for="item in games"
                :key="item.gameallName"
                :label="item.gameallName"
                :value="item.gameallName"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="图表类型">
            <el-radio-group v-model="chartType">
              <el-radio-button label="pie">饼图</el-radio-button>
              <el-radio-button label="line">折线图</el-radio-button>
            </el-radio-group>
          </el-form-item>
        </el-form>
      </div>
    </el-card>

    <!-- 四个统计卡片 -->
    <el-row :gutter="20" class="stats-row">
      <el-col :xs="24" :sm="12" :md="6" v-for="(label, idx) in TrueChartData.labels" :key="idx">
        <el-card class="stat-card" shadow="hover">
          <div class="stat-label">{{ label }}</div>
          <div class="stat-value">{{ TrueChartData.values[idx] }}</div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 图表区域 -->
    <el-card class="chart-card" shadow="hover">
      <div ref="chart" class="chart-container"></div>
    </el-card>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import { getPieChart } from '@/tool/managementapi';
import { getGameform } from '@/tool/backgroundapi';

export default {
  name: 'PieChartWithToggle',
  data() {
    return {
      TrueChartData: {
        labels: ['在租', '空闲', '待审核', '已完成'],
        values: [0, 0, 0, 0]
      },
      selectedRegion: '',
      selectedRegion2: '',
      games: [],
      chartType: 'pie' // 'pie' 或 'line'
    };
  },
  mounted() {
    this.fetchGames();
    this.updateChart();
  },
  watch: {
    selectedRegion() {
      this.updateChart();
    },
    chartType() {
      this.renderChart();
    }
  },
  methods: {
    handleRegionClear() {
      this.selectedRegion = '';
      this.updateChart();
    },
    async fetchGames() {
      try {
        const res = await getGameform();
        this.games = res.data.data;
      } catch (e) {
        this.$message.error('游戏列表获取失败');
      }
    },
    async updateChart() {
      try {
        const res = await getPieChart({ gameName: this.selectedRegion });
        if(!this.selectedRegion){
          this.selectedRegion2=res.data.gameallName;
        }else {
          this.selectedRegion2=this.selectedRegion;
        }
        this.TrueChartData.values = [
          res.data.known,
          res.data.leisure,
          res.data.authorization,
          res.data.endOrderNub
        ];
        this.renderChart();
      } catch (e) {
        this.$message.error('统计数据加载失败');
      }
    },
    renderChart() {
      if (!this.$refs.chart) return;
      const chart = echarts.init(this.$refs.chart);
      let option;
      if (this.chartType === 'pie') {
        option = {
          title: { text: this.selectedRegion2, left: 'center', textStyle: { fontSize: 16 } },
          tooltip: { trigger: 'item' },
          legend: { orient: 'vertical', left: 10 },
          series: [
            {
              name: '状态',
              type: 'pie',
              radius: '55%',
              center: ['60%', '50%'],
              data: this.TrueChartData.labels.map((l, i) => ({ name: l, value: this.TrueChartData.values[i] })),
              emphasis: { itemStyle: { shadowBlur: 10, shadowOffsetX: 0, shadowColor: 'rgba(0,0,0,0.3)' } }
            }
          ]
        };
      } else {
        option = {
          title: { text: '状态趋势', left: 'center', textStyle: { fontSize: 16 } },
          tooltip: { trigger: 'axis' },
          xAxis: { type: 'category', data: this.TrueChartData.labels },
          yAxis: { type: 'value' },
          series: [
            {
              name: '数量',
              type: 'line',
              smooth: true,
              data: this.TrueChartData.values
            }
          ]
        };
      }
      chart.setOption(option);
    }
  }
};
</script>

<style scoped>
.chart-root {
  padding: 16px;
  background: #f0f2f5;
}
.filter-card,
.chart-card {
  margin-bottom: 16px;
}
.filter-form {
  align-items: center;
}
.stats-row {
  margin-bottom: 16px;
}
.stat-card {
  text-align: center;
  padding: 12px 0;
  border-radius: 8px;
  background: #fff;
}
.stat-label {
  color: #909399;
  font-size: 14px;
  margin-bottom: 8px;
}
.stat-value {
  font-size: 28px;
  font-weight: bold;
  color: #409EFF;
}
.chart-container {
  width: 100%;
  height: 400px;
}
</style>
