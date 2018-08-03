# 用于日志追踪
DROP TABLE IF EXISTS `job_execution_log`;
CREATE TABLE `job_execution_log` (
  `id` varchar(40) NOT NULL COMMENT '主键',
  `job_name` varchar(100) NOT NULL COMMENT '作业名称',
  `task_id` varchar(1000) NOT NULL COMMENT '任务名称,每次作业运行生成新任务',
  `hostname` varchar(255) NOT NULL COMMENT '主机名称',
  `ip` varchar(50) NOT NULL COMMENT '主机IP',
  `sharding_item` int(11) NOT NULL COMMENT '分片项',
  `execution_source` varchar(20) NOT NULL COMMENT '作业执行来源',
  `failure_cause` varchar(4000) DEFAULT NULL COMMENT '执行失败原因',
  `is_success` int(11) NOT NULL COMMENT '是否执行成功',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '作业开始执行时间',
  `complete_time` timestamp NULL DEFAULT NULL COMMENT '作业结束执行时间',
  `job_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '自定义时间，用于分片',
  PRIMARY KEY (`id`,`job_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `job_status_trace_log`;
CREATE TABLE `job_status_trace_log` (
  `id` varchar(40) NOT NULL COMMENT '主键',
  `job_name` varchar(100) NOT NULL COMMENT '作业名称',
  `original_task_id` varchar(255) NOT NULL COMMENT '原任务名称',
  `task_id` varchar(1000) NOT NULL COMMENT '任务名称',
  `slave_id` varchar(50) NOT NULL COMMENT '执行作业服务器的名称，Lite版本为服务器的IP地址',
  `source` varchar(50) NOT NULL COMMENT '任务执行源',
  `execution_type` varchar(20) NOT NULL COMMENT '任务执行类型',
  `sharding_item` varchar(100) NOT NULL COMMENT '分片项集合，多个分片项以逗号分隔',
  `state` varchar(20) NOT NULL  COMMENT '任务执行状态 TASK_STAGING, TASK_RUNNING, TASK_FINISHED, TASK_KILLED, TASK_LOST, TASK_FAILED, TASK_ERROR',
  `message` varchar(4000) DEFAULT NULL COMMENT '相关信息',
  `creation_time` timestamp NULL DEFAULT NULL COMMENT '记录创建时间',
  `job_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '自定义时间，用于分片',
  PRIMARY KEY (`id`,`job_time`),
  KEY `TASK_ID_STATE_INDEX` (`task_id`(100),`state`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

