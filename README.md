# EasyMall
卖东西啦， 走过路过不要错过 <br />
项目展示：http://www.wochi.xin/easymall <br />
项目后台页面：http://www.wochi.xin/easymall/backend/manage.jsp (需用户名admin，密码admim登录) <br />
项目部署环境：jdk1.7 + tomcat7.0 <br />

这是一个使用基础的web+jsp搭建的电商网站，实现了注册登录功能，并利用filter实现用户自动登录，利用cookie技术实现“记住用户名功能”； <br />
后台功能：添加商品，商品修改模块；<br />
前台功能：商品列表展示，商品详情展示，商品查找与购物车功能；<br />

项目结构：<br />
pro.sunhao.domain包：提供项目所需对象的类；<br />
pro.sunhao.web包：控制层代码，调用service服务层；<br />
pro.sunhao.backend.web包：后台控制层；<br />
pro.sunhao.service包：服务层代码，由web层调用，通过工厂模式连接dao层；<br />
pro.sunhao.dao包：由service层调用，对数据库进行操作；<br />
pro.sunhao.util包：项目所需要的工具包，主要为数据库连接jdbc，数据库事务控制，验证码等工具类；<br />
pro.sunhao.factory包：基于工厂模式，为service层与dao层提供解耦的工具类；<br />
pro.sunhao.filter包：提供乱码处理，自动登录，和管理员进入后台的过滤功能；<br />
pro.sunhao.listener包：提供项目监听器；<br />
pro.sunhao.exception包：提供自定义异常；<br />

数据库结构：<br />
用户表user，商品表prod，商品种类表prod_category，用户购物车表username_cart(用户注册时自动创建)<br />
