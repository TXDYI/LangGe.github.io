<template>
  <div ref="chart" style="width: 100%; height: 400px;"></div>
</template>

<script>
import * as echarts from 'echarts';

export default {
  name: "LineChart",
  props: {
    lineChartData: Object
  },
  mounted() {
    console.log(this.lineChartData);
    this.renderChart();
  },
  methods: {
    renderChart() {
      if (!this.lineChartData || !this.lineChartData.months) {
        console.error('lineChartData 或 lineChartData.months 未定义');
        return;
      }
      const chart = echarts.init(this.$refs.chart);
      const option = {
        title: {
          text: '租号趋势'
        },
        tooltip: {
          trigger: 'axis'
        },
        xAxis: {
          type: 'category',
          data: this.lineChartData.months
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          name: '租号数',
          type: 'line',
          smooth: true,
          data: this.lineChartData.counts
        }]
      };
      chart.setOption(option);
    }
  }
};
</script>