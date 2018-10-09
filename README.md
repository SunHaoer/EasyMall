# EasyMall
卖东西啦， 走过路过不要错过

这是一个使用基础的web+jsp搭建的电商网站，实现了注册登录功能，并利用filter实现用户自动登录，利用cookie技术实现“记住用户名功能”；
实现；
后台功能：添加商品，商品修改模块；
前台功能：商品列表展示，商品详情展示，商品查找与购物车功能；

项目结构：
pro.sunhao.domain包：提供项目所需对象的类；
pro.sunhao.web包：控制层代码，调用service服务层；
pro.sunhao.backend.web包：后台控制层；
pro.sunhao.service包：服务层代码，由web层调用，通过工厂模式连接dao层；
pro.sunhao.dao包：由service层调用，对数据库进行操作；
pro.sunhao.util包：项目所需要的工具包，主要为数据库连接jdbc，数据库事务控制，验证码等工具类；
pro.sunhao.factory包：基于工厂模式，为service层与dao层提供解耦的工具类；
pro.sunhao.filter包：提供乱码处理，自动登录，和管理员进入后台的过滤功能；
pro.sunhao.listener包：提供项目监听器；
pro.sunhao.exception包：提供自定义异常；

数据库结构：
用户表user，商品表prod，商品种类表prod_category，用户购物车表username_cart(用户注册时自动创建)
