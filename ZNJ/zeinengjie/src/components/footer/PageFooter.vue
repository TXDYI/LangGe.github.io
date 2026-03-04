<template>
    <footer class="footer">
      <div class="footer-top">
        <div class="feature">
          <img src="@/assets/footer_img/foot-item1.png" alt="安全">
          <p>精准屏蔽外挂</p>
          <span>全程监控 有效拦截</span>
        </div>
        <div class="feature">
          <img src="@/assets/footer_img/foot-item2.png" alt="加密">
          <p>高新技术加密</p>
          <span>账号加密 保障安全</span>
        </div>
        <div class="feature">
          <img src="@/assets/footer_img/foot-item3.png" alt="仲裁">
          <p>公正售后仲裁</p>
          <span>售后机制 公平互利</span>
        </div>
        <div class="feature">
          <img src="@/assets/footer_img/foot-item4.png" alt="注册">
          <p>免费快捷注册</p>
          <span>轻松发布 高额盈利</span>
        </div>
      </div>
  
      <div class="footer-main">
        <div class="footer-links">
          <div class="link-group">
            <h3>关于我们</h3>
            <ul>
              <li><a href="#">关于我们</a></li>
              <li><a href="#">联系我们</a></li>
              <li><a href="#">免责声明</a></li>
              <li><a href="#">家长监护</a></li>
            </ul>
          </div>
          <div class="link-group">
            <h3>新手上路</h3>
            <ul>
              <li><a href="#">实名认证</a></li>
              <li><a href="#">如何租号</a></li>
              <li><a href="#">租号须知</a></li>
              <li><a href="#">搜索账号</a></li>
            </ul>
          </div>
          <div class="link-group">
            <h3>买家帮助</h3>
            <ul>
              <li><a href="#">上号指引</a></li>
              <li><a href="#">封号规则</a></li>
              <li><a href="#">维权撤单</a></li>
              <li><a href="#">平台会员</a></li>
            </ul>
          </div>
          <div class="link-group">
            <h3>卖家帮助</h3>
            <ul>
              <li><a href="#">卖家规则</a></li>
              <li><a href="#">合同查看</a></li>
              <li><a href="#">商品发布</a></li>
              <li><a href="#">认证商户</a></li>
            </ul>
          </div>
          <div class="link-group">
            <h3>账户与资产</h3>
            <ul>
              <li><a href="#">提现说明</a></li>
              <li><a href="#">手续费</a></li>
              <li><a href="#">手机号绑定</a></li>
              <li><a href="#">密码重置</a></li>
            </ul>
          </div>
        </div>
  
        <div class="footer-contact">
          <h3>客服电话</h3>
          <p class="phone-number">400-0000-000</p>
          <p class="service-time">服务时间 10:00 - 19:00</p>
          <button class="customer-service-btn">在线客服</button>
        </div>
      </div>
  
      <div class="footer-bottom">
        <p> 2024-2025 賊能借平台. 版权所有.</p>
      </div>
    </footer>
</template>
  
<script>
  export default {
    name: "PageFooter"
  };
  </script>
  
  <style scoped>
  .footer {
    background-color: #f8f8f8;
    padding: 40px 0;
    font-family: Arial, sans-serif;
    margin: 0 auto;
    margin-top: 20px;
  }
  
  .footer-top {
    display: flex;
    justify-content: center;
    gap: 40px;
    margin-bottom: 30px;
  }
  
  .feature {
    text-align: center;
  }
  
  .feature img {
    width: 40px;
    height: 40px;
  }
  
  .feature p {
    font-weight: bold;
    margin: 5px 0;
  }
  
  .feature span {
    font-size: 12px;
    color: #666;
  }
  
  .footer-main {
    display: flex;
    justify-content: space-between;
    padding: 0 10%;
  }
  
  .footer-links {
    display: flex;
    gap: 30px;
  }
  
  .link-group h3 {
    font-size: 16px;
    margin-bottom: 10px;
  }
  
  .link-group ul {
    list-style: none;
    padding: 0;
  }
  
  .link-group ul li {
    margin: 5px 0;
  }
  
  .link-group ul li a {
    text-decoration: none;
    color: #333;
    font-size: 14px;
  }
  
  .footer-contact {
    text-align: center;
  }
  
  .phone-number {
    font-size: 24px;
    font-weight: bold;
  }
  
  .service-time {
    font-size: 14px;
    color: #666;
  }
  
  .customer-service-btn {
    background-color: black;
    color: white;
    padding: 10px 20px;
    border-radius: 5px;
    margin-top: 10px;
  }
  
  .footer-bottom {
    text-align: center;
    font-size: 12px;
    color: #666;
    margin-top: 20px;
  }
  </style>
  