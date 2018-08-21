# sgs-smp



#### 前端使用框架

1. [Bootstrap3官网:](http://v3.bootcss.com/)http://v3.bootcss.com/
2. [echarts2官网:](http://echarts.baidu.com/echarts2/)http://echarts.baidu.com/echarts2/
3. [Bootstrap Table官网:](http://bootstrap-table.wenzhixin.net.cn/)http://bootstrap-table.wenzhixin.net.cn/
4. [bootstap3-dialog Github地址:](https://github.com/nakupanda/bootstrap3-dialog)https://github.com/nakupanda/bootstrap3-dialog
5. [select2 Github地址:](https://select2.github.io/)https://select2.github.io/
6. [bootstrap-datepicker API文档:](http://bootstrap-datepicker.readthedocs.io/en/latest/markup.html)http://bootstrap-datepicker.readthedocs.io/en/latest/markup.html

####页面布局
1. 当前页面为子菜单,content布局为

`<div id="inner-content" class=" container-fluid">
    <div id="inner-row" >
    </div>
     <div> `
     
#### 约定
1.系统编码(systemCode): 涅槃的系统编码为SGS
2.组件编码(componentName): 网关的编码为GW, 接入机的编码为AS, 微服务的编码为 MS
3.特殊应用名(applicationCode): 涅槃网关的应用名为API-GATEWAY，涅槃接入机的应用名为SGS。**如果其他系统要接入，也需要约定接入机和网关的应用名，并且保持唯一。**