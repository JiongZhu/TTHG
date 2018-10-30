/*
Navicat MySQL Data Transfer

Source Server         : db_con
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : db_tthg

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2017-02-28 15:23:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_accessories
-- ----------------------------
DROP TABLE IF EXISTS `tb_accessories`;
CREATE TABLE `tb_accessories` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `AcceNo` varchar(50) NOT NULL,
  `AcceName` varchar(50) NOT NULL,
  `AccessoriesType` varchar(50) NOT NULL,
  `CarArea` varchar(50) NOT NULL,
  `CarStyle` varchar(50) NOT NULL,
  `ProducTime` date NOT NULL,
  `Price` double(10,2) NOT NULL,
  `Photo` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_accessories
-- ----------------------------
INSERT INTO `tb_accessories` VALUES ('1', 'A101', '大众', '后座', '德国', '流线型', '2016-12-30', '1000.00', '暂无');
INSERT INTO `tb_accessories` VALUES ('2', 'A102', '布加迪威龙', '车窗', '日本', '前置型', '2017-01-20', '800.90', '暂无');
INSERT INTO `tb_accessories` VALUES ('3', 'A103', '开瑞', '轮胎', '美国', '配件', '2017-01-06', '500.30', '暂无');
INSERT INTO `tb_accessories` VALUES ('4', 'A104', '雷克萨斯', '天窗', '法国', '配件', '2017-01-23', '500.80', '暂无');
INSERT INTO `tb_accessories` VALUES ('6', '106', '宝马', '化油器', '中国', '配件', '2017-01-06', '890.90', '暂无');
INSERT INTO `tb_accessories` VALUES ('7', 'A108', '轮胎', '轮胎', '德国', '大型车', '2017-01-17', '60.90', '暂无');
INSERT INTO `tb_accessories` VALUES ('8', 'A112', '宝马', '配件', '国产', '大型车', '2016-12-27', '500.90', '暂无');
INSERT INTO `tb_accessories` VALUES ('9', 'A112', '布加迪威龙', '核心配件', '德国', '大型车', '2017-01-03', '500.00', '暂无');

-- ----------------------------
-- Table structure for tb_accessories_brand
-- ----------------------------
DROP TABLE IF EXISTS `tb_accessories_brand`;
CREATE TABLE `tb_accessories_brand` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `BrandNo` varchar(20) NOT NULL,
  `BrandName` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_accessories_brand
-- ----------------------------
INSERT INTO `tb_accessories_brand` VALUES ('1', 'AB1011', '大众');
INSERT INTO `tb_accessories_brand` VALUES ('2', 'AB1012', '布加迪威龙');
INSERT INTO `tb_accessories_brand` VALUES ('4', 'AB1013', '雷克萨斯');
INSERT INTO `tb_accessories_brand` VALUES ('5', 'AB1014', '威麟');
INSERT INTO `tb_accessories_brand` VALUES ('8', 'AB1016', '宝马');
INSERT INTO `tb_accessories_brand` VALUES ('9', 'AB122', '宝马');

-- ----------------------------
-- Table structure for tb_appointment
-- ----------------------------
DROP TABLE IF EXISTS `tb_appointment`;
CREATE TABLE `tb_appointment` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `AppointmentNo` varchar(20) DEFAULT NULL,
  `CustomerName` varchar(50) DEFAULT NULL,
  `Telephone` varchar(50) DEFAULT NULL,
  `CarStyleID` int(11) NOT NULL,
  `ScheduledTime` date DEFAULT NULL,
  `ScheduledPersonNum` int(10) DEFAULT NULL,
  `ConsultantName` varchar(50) DEFAULT NULL,
  `Remarks` varchar(255) DEFAULT NULL,
  `IntentionID` int(11) NOT NULL,
  `State` char(10) DEFAULT '未审核',
  PRIMARY KEY (`id`),
  KEY `FK_app_int` (`IntentionID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_appointment
-- ----------------------------
INSERT INTO `tb_appointment` VALUES ('1', 'M0001', '刘二', '18181818183', '4', '2016-11-10', '3', '李四', '无', '5', '已审核');
INSERT INTO `tb_appointment` VALUES ('2', 'M0002', '张武', '18888888881', '6', '2016-11-10', '2', '李四', '无', '3', '已审核');
INSERT INTO `tb_appointment` VALUES ('3', 'M0003', '张柳', '18888888888', '12', '2016-11-10', '2', '王五', '无', '4', '已审核');
INSERT INTO `tb_appointment` VALUES ('4', 'M0004', '刘三', '18181818184', '14', '2016-11-10', '2', '王五', '无', '6', '已审核');
INSERT INTO `tb_appointment` VALUES ('7', 'M0007', '刘思', '18454878451', '15', '2016-11-11', '2', '王五', '无', '7', '未审核');
INSERT INTO `tb_appointment` VALUES ('9', 'M1008', '小明', '1234567891', '22', '2016-12-20', '3', '李四', '无', '8', '未审核');
INSERT INTO `tb_appointment` VALUES ('10', 'M1009', '猪悟能', '12345678900', '5', '2016-12-13', '3', '李四', '无', '9', '已审核');

-- ----------------------------
-- Table structure for tb_callback
-- ----------------------------
DROP TABLE IF EXISTS `tb_callback`;
CREATE TABLE `tb_callback` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `CallbackNo` varchar(20) DEFAULT NULL,
  `CustomerName` varchar(50) DEFAULT NULL,
  `Telephone` varchar(50) DEFAULT NULL,
  `OrderID` int(11) NOT NULL,
  `CallbackTime` date DEFAULT NULL,
  `Evaluation` char(20) DEFAULT NULL,
  `Feedback` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_callback
-- ----------------------------
INSERT INTO `tb_callback` VALUES ('1', 'B0001', '小明', '18181818102', '2', '2016-12-26', '无', '无');
INSERT INTO `tb_callback` VALUES ('2', 'B0002', '小羽', '18188818109', '9', '2016-12-26', '无', '无');
INSERT INTO `tb_callback` VALUES ('3', 'B0003', '小葛', '18181818110', '10', '2016-12-27', '无', '无');
INSERT INTO `tb_callback` VALUES ('4', 'B0004', '小吴', '18181818112', '12', '2016-12-27', '无', '无');
INSERT INTO `tb_callback` VALUES ('5', 'B0005', '小李', '18181818105', '5', '2016-12-27', '无', '无');

-- ----------------------------
-- Table structure for tb_car
-- ----------------------------
DROP TABLE IF EXISTS `tb_car`;
CREATE TABLE `tb_car` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `CarNo` varchar(20) NOT NULL,
  `VehicleID` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_car
-- ----------------------------
INSERT INTO `tb_car` VALUES ('1', 'C0001', '3');
INSERT INTO `tb_car` VALUES ('2', 'C0002', '4');
INSERT INTO `tb_car` VALUES ('3', 'C0003', '5');
INSERT INTO `tb_car` VALUES ('4', 'C0004', '6');
INSERT INTO `tb_car` VALUES ('5', 'C0005', '7');
INSERT INTO `tb_car` VALUES ('6', 'C0006', '8');
INSERT INTO `tb_car` VALUES ('7', 'C0007', '9');
INSERT INTO `tb_car` VALUES ('8', 'C0008', '10');
INSERT INTO `tb_car` VALUES ('9', 'C0009', '11');
INSERT INTO `tb_car` VALUES ('10', 'C0010', '12');
INSERT INTO `tb_car` VALUES ('11', 'C0011', '13');
INSERT INTO `tb_car` VALUES ('12', 'C0012', '14');
INSERT INTO `tb_car` VALUES ('13', 'C0013', '15');
INSERT INTO `tb_car` VALUES ('14', 'C0006', '3');

-- ----------------------------
-- Table structure for tb_component
-- ----------------------------
DROP TABLE IF EXISTS `tb_component`;
CREATE TABLE `tb_component` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `CompNo` varchar(255) DEFAULT NULL,
  `CompName` varchar(255) DEFAULT NULL,
  `ModelNum` varchar(255) DEFAULT NULL,
  `CDate` varchar(255) DEFAULT NULL,
  `CNum` int(11) DEFAULT NULL,
  `UnitPri` double DEFAULT NULL,
  `Brokerage` varchar(255) DEFAULT NULL,
  `UName` varchar(255) DEFAULT NULL,
  `TroId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_component
-- ----------------------------
INSERT INTO `tb_component` VALUES ('1', 'c1001', '减震器', 'z109', '2016-12-01 00:34:26', '1', '4400', '韵语', '云聪', '1');
INSERT INTO `tb_component` VALUES ('3', 'c1003', '排气管', 't1003', '2016-12-11 00:37:56', '1', '1500', '韵语', '云雪', '3');
INSERT INTO `tb_component` VALUES ('7', 'c207', '传动轴', 't1004', '2017-01-02 22:31:34', '2', '5400', '云聪', '云风', '4');
INSERT INTO `tb_component` VALUES ('9', 'c8838', '排气管', '3221', '2017-01-03 14:35:40', '2', '1212', '韵语', '云雪', '8');
INSERT INTO `tb_component` VALUES ('10', 'c0102', '减震器', '1313', '2017-01-03 15:12:31', '1', '1323', '韵语', '云风', '9');
INSERT INTO `tb_component` VALUES ('11', 'c4785874', '减震器', 'j3427s', '2017-01-04 11:10:58', '1', '155000', '韵语', '云风', '11');
INSERT INTO `tb_component` VALUES ('12', 'c13887', '排气管', '121w', '2017-01-04 15:40:31', '2', '14500', '韵语', '云雪', '8');
INSERT INTO `tb_component` VALUES ('13', 'cn2324', '减震器', 'j89887a', '2017-01-05 21:08:20', '2', '89000', '云聪', '云雪', '13');

-- ----------------------------
-- Table structure for tb_contract
-- ----------------------------
DROP TABLE IF EXISTS `tb_contract`;
CREATE TABLE `tb_contract` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `ContractNo` varchar(20) DEFAULT NULL,
  `ContractTime` date DEFAULT NULL,
  `OrderID` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_contract
-- ----------------------------
INSERT INTO `tb_contract` VALUES ('1', 'C0001', '2016-10-21', '1');
INSERT INTO `tb_contract` VALUES ('2', 'C0002', '2016-10-20', '2');
INSERT INTO `tb_contract` VALUES ('3', 'C0003', '2016-10-22', '3');
INSERT INTO `tb_contract` VALUES ('4', 'C0004', '2016-10-22', '4');
INSERT INTO `tb_contract` VALUES ('5', 'C0005', '2016-10-23', '5');
INSERT INTO `tb_contract` VALUES ('6', 'C0006', '2016-10-23', '6');
INSERT INTO `tb_contract` VALUES ('7', 'C0007', '2016-10-24', '7');
INSERT INTO `tb_contract` VALUES ('8', 'C0008', '2016-10-24', '8');
INSERT INTO `tb_contract` VALUES ('9', 'C0009', '2016-10-25', '9');
INSERT INTO `tb_contract` VALUES ('10', 'C0010', '2016-10-25', '10');
INSERT INTO `tb_contract` VALUES ('11', 'C0011', '2016-10-26', '11');
INSERT INTO `tb_contract` VALUES ('12', 'C0012', '2016-10-26', '12');
INSERT INTO `tb_contract` VALUES ('13', 'C0013', '2016-10-27', '13');
INSERT INTO `tb_contract` VALUES ('14', 'C0014', '2016-10-27', '14');

-- ----------------------------
-- Table structure for tb_customer_intention
-- ----------------------------
DROP TABLE IF EXISTS `tb_customer_intention`;
CREATE TABLE `tb_customer_intention` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `CustomerNo` varchar(20) DEFAULT NULL,
  `CustomerName` varchar(50) DEFAULT NULL,
  `Sex` char(10) DEFAULT NULL,
  `Age` int(10) DEFAULT NULL,
  `Telephone` varchar(50) DEFAULT NULL,
  `Address` varchar(50) DEFAULT NULL,
  `PC` varchar(50) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `Hobby` varchar(50) DEFAULT NULL,
  `CardID` varchar(50) DEFAULT NULL,
  `DriverLicense` varchar(50) DEFAULT NULL,
  `Payment` varchar(50) DEFAULT NULL,
  `CarStyleID` int(11) NOT NULL,
  `Attention` varchar(50) DEFAULT NULL,
  `OtherRequirements` varchar(50) DEFAULT NULL,
  `Negotiation` varchar(255) DEFAULT NULL,
  `Result` varchar(50) DEFAULT NULL,
  `ConsultantName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_customer_intention
-- ----------------------------
INSERT INTO `tb_customer_intention` VALUES ('3', 'Z1003', '张武', '男', '28', '18888888881', '伦敦', '211111', '44444444@qq.com', '阅读', '320412198812221111', '320412198812221111', '现金', '14', '空间', '造型', '其他原因', '流失', '李四');
INSERT INTO `tb_customer_intention` VALUES ('4', 'Z1004', '张柳', '女', '28', '18888888888', '巴黎', '232322', '55555555@qq.com', '跳舞', '320412198802020101', '320412198802020101', '现金', '4', '舒适性', '油耗', '一般', '跟踪', '王五');
INSERT INTO `tb_customer_intention` VALUES ('5', 'Z1005', '刘二', '男', '32', '18181818183', '迪拜', '213101', '22252222@qq.com', '度假', '320412198401016656', '320412198401016666', '现金', '6', '价格', '口碑', '其他原因', '流失', '李四');
INSERT INTO `tb_customer_intention` VALUES ('6', 'Z1006', '刘三', '男', '33', '18181818184', '悉尼', '222223', '33334333@qq.com', '健身', '320412198311110100', '320412198311110000', '贷款', '8', '知名度', '配置', '车型不合适', '流失', '王五');
INSERT INTO `tb_customer_intention` VALUES ('7', 'Z1007', '刘思', '女', '25', '18454878451', '杭州', '245154', '66666666@qq.com', '唱歌', '320154199210113333', '320154199210113333', '网银', '22', '油耗', '舒适性', '良好', '预约试乘', '王五');
INSERT INTO `tb_customer_intention` VALUES ('8', 'Z1008', '小明', '男', '28', '12123243455', '西安', '132324', '35454656@qq.com', '度假', '323232199912120101', '323232199912120101', '现金', '16', '空间', '配置', '良好', '预约试乘', '李四');
INSERT INTO `tb_customer_intention` VALUES ('9', 'Z1009', '小雪', '女', '29', '15153546655', '南京', '232321', '12132324@qq.com', '健身', '321321199301011212', '321321199301011212', '贷款', '16', '配置', '舒适性', '车型不合适', '流失', '王五');
INSERT INTO `tb_customer_intention` VALUES ('10', 'Z1010', '小刚', '男', '29', '15132324354', '杭州', '234344', '12343545@qq.com', '阅读', '32101210213232', '32101210213232', '现金', '13', '空间', '油耗', '其他原因', '流失', '李四');
INSERT INTO `tb_customer_intention` VALUES ('11', 'Z1011', '张三', '男', '33', '18151545154', '北京', '145451', '11115458@qq.com', '度假', '320412198412120103', '320412198412120103', '现金', '4', '价格', '空间', '一般', '跟踪', '李四');
INSERT INTO `tb_customer_intention` VALUES ('12', 'Z1012', '张四', '女', '33', '18181818182', '悉尼', '222222', '33333333@qq.com', '健身', '320412198311110000', '320412198311110000', '贷款', '21', '知名度', '配置', '良好', '预约试乘', '李四');
INSERT INTO `tb_customer_intention` VALUES ('13', 'Z1013', '张武', '男', '28', '18888888881', '伦敦', '211111', '44444444@qq.com', '阅读', '320412198812221111', '320412198812221111', '现金', '14', '空间', '造型', '车型不合适', '流失', '李四');
INSERT INTO `tb_customer_intention` VALUES ('14', 'Z1014', '张柳', '女', '28', '18888888888', '巴黎', '232322', '55555555@qq.com', '跳舞', '320412198802020101', '320412198802020101', '现金', '4', '舒适性', '油耗', '一般', '跟踪', '王五');
INSERT INTO `tb_customer_intention` VALUES ('15', 'Z1015', '刘二', '男', '32', '18181818183', '迪拜', '213101', '22252222@qq.com', '度假', '320412198401016656', '320412198401016666', '现金', '6', '价格', '口碑', '好', '预约试乘', '李四');
INSERT INTO `tb_customer_intention` VALUES ('16', 'Z1016', '刘三', '男', '33', '18181818184', '悉尼', '222223', '33334333@qq.com', '健身', '320412198311110100', '320412198311110000', '贷款', '8', '知名度', '配置', '预购资金不足', '流失', '王五');
INSERT INTO `tb_customer_intention` VALUES ('17', 'Z1017', '刘思', '女', '25', '18454878451', '杭州', '245154', '66666666@qq.com', '唱歌', '320154199210113333', '320154199210113333', '网银', '22', '油耗', '舒适性', '预购资金不足', '流失', '王五');
INSERT INTO `tb_customer_intention` VALUES ('18', 'Z1018', '小明', '男', '28', '12123243455', '西安', '132324', '35454656@qq.com', '度假', '323232199912120101', '323232199912120101', '现金', '16', '空间', '配置', '良好', '预约试乘', '李四');
INSERT INTO `tb_customer_intention` VALUES ('19', 'Z1019', '小雪', '女', '29', '15153546655', '南京', '232321', '12132324@qq.com', '健身', '321321199301011212', '321321199301011212', '贷款', '16', '配置', '舒适性', '车型不合适', '流失', '王五');
INSERT INTO `tb_customer_intention` VALUES ('20', 'Z1020', '小刚', '男', '29', '15132324354', '杭州', '234344', '12343545@qq.com', '阅读', '32101210213232', '32101210213232', '现金', '13', '空间', '油耗', '其他原因', '流失', '李四');
INSERT INTO `tb_customer_intention` VALUES ('21', 'Z1021', '小雪', '女', '29', '15153546655', '南京', '232321', '12132324@qq.com', '健身', '321321199301011212', '321321199301011212', '贷款', '16', '配置', '舒适性', '好', '预约试乘', '王五');
INSERT INTO `tb_customer_intention` VALUES ('22', 'Z1022', '小刚', '男', '29', '15132324354', '杭州', '234344', '12343545@qq.com', '阅读', '32101210213232', '32101210213232', '现金', '13', '空间', '油耗', '车型不合适', '流失', '李四');
INSERT INTO `tb_customer_intention` VALUES ('23', 'Z1023', '刘思', '女', '25', '18454878451', '杭州', '245154', '66666666@qq.com', '唱歌', '320154199210113333', '320154199210113333', '网银', '22', '油耗', '舒适性', '良好', '预约试乘', '王五');
INSERT INTO `tb_customer_intention` VALUES ('24', 'Z1007', '刘思', '女', '25', '18454878451', '杭州', '245154', '66666666@qq.com', '唱歌', '320154199210113333', '320154199210113333', '网银', '22', '油耗', '舒适性', '良好', '预约试乘', '王五');
INSERT INTO `tb_customer_intention` VALUES ('25', 'Z1006', '刘三', '男', '33', '18181818184', '悉尼', '222223', '33334333@qq.com', '健身', '320412198311110100', '320412198311110000', '贷款', '8', '知名度', '配置', '其他原因', '流失', '王五');
INSERT INTO `tb_customer_intention` VALUES ('26', 'Z1010', '小刚', '男', '29', '15132324354', '杭州', '234344', '12343545@qq.com', '阅读', '32101210213232', '32101210213232', '现金', '13', '空间', '油耗', '其他原因', '流失', '李四');
INSERT INTO `tb_customer_intention` VALUES ('27', 'Z1007', '刘思', '女', '25', '18454878451', '杭州', '245154', '66666666@qq.com', '唱歌', '320154199210113333', '320154199210113333', '网银', '22', '油耗', '舒适性', '良好', '预约试乘', '王五');

-- ----------------------------
-- Table structure for tb_customer_vehicle
-- ----------------------------
DROP TABLE IF EXISTS `tb_customer_vehicle`;
CREATE TABLE `tb_customer_vehicle` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `CustomerNo` varchar(20) DEFAULT NULL,
  `CustomerName` varchar(50) DEFAULT NULL,
  `Sex` char(10) DEFAULT NULL,
  `Age` int(10) DEFAULT NULL,
  `Telephone` varchar(50) DEFAULT NULL,
  `Address` varchar(50) DEFAULT NULL,
  `PC` varchar(50) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `Hobby` varchar(50) DEFAULT NULL,
  `CardID` varchar(50) DEFAULT NULL,
  `DriverLicense` varchar(50) DEFAULT NULL,
  `CarID` int(11) NOT NULL,
  `ConsultantName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_customer_vehicle
-- ----------------------------
INSERT INTO `tb_customer_vehicle` VALUES ('1', 'V1001', '小刚', '男', '20', '18181818101', '纽约', '222221', '1234567@qq.com', '跳舞', '320320199712120101', '320320199712120101', '1007', '张三');
INSERT INTO `tb_customer_vehicle` VALUES ('2', 'V1002', '小明', '男', '21', '18181818102', '伦敦', '222222', '1234568@qq.com', '足球', '320320199612120102', '320320199612120102', '1004', '李四');
INSERT INTO `tb_customer_vehicle` VALUES ('3', 'V1003', '小花', '女', '21', '18181818103', '悉尼', '222223', '1234569@qq.com', '游泳', '320320199712120103', '320320199712120103', '1008', '张三');
INSERT INTO `tb_customer_vehicle` VALUES ('4', 'V1004', '小华', '男', '21', '18181818104', '北京', '222224', '1234570@qq.com', '健身', '320320199612120104', '320320199612120104', '1001', '李四');
INSERT INTO `tb_customer_vehicle` VALUES ('5', 'V1005', '小李', '女', '21', '18181818105', '迪拜', '222225', '1234571@qq.com', '度假', '320320199612120105', '320320199612120105', '1005', '张三');
INSERT INTO `tb_customer_vehicle` VALUES ('6', 'V1006', '小梅', '女', '22', '18181818106', '阿姆斯特丹', '222226', '1234572@qq.com', '唱歌', '320320199512120106', '320320199512120106', '1009', '李四');
INSERT INTO `tb_customer_vehicle` VALUES ('7', 'V1007', '小朱', '男', '22', '18181818107', '比勒陀利亚', '222227', '1234573@qq.com', '跳舞', '320320199512120107', '320320199512120107', '1006', '张三');
INSERT INTO `tb_customer_vehicle` VALUES ('8', 'V1008', '小轩', '男', '21', '18181818108', '开普敦', '222228', '1234574@qq.com', '足球', '320320199612120108', '320320199612120108', '1002', '李四');
INSERT INTO `tb_customer_vehicle` VALUES ('9', 'V1009', '小羽', '男', '20', '18181818109', '布隆方丹', '222229', '1234575@qq.com', '游泳', '320320199712120109', '320320199712120109', '1003', '张三');
INSERT INTO `tb_customer_vehicle` VALUES ('10', 'V1010', '小葛', '男', '22', '18181818110', '赫尔辛基', '222230', '1234576@qq.com', '健身', '320320199512120110', '320320199512120110', '1015', '李四');
INSERT INTO `tb_customer_vehicle` VALUES ('11', 'V1011', '小康', '女', '20', '18181818111', '阿布扎比', '222231', '1234577@qq.com', '度假', '320320199712120111', '320320199712120111', '1013', '张三');
INSERT INTO `tb_customer_vehicle` VALUES ('12', 'V1012', '小吴', '女', '21', '18181818112', '科威特城', '222232', '1234578@qq.com', '唱歌', '320320199612120112', '320320199612120112', '1012', '李四');
INSERT INTO `tb_customer_vehicle` VALUES ('13', 'V1013', '小王', '男', '19', '18181818113', '上海', '222233', '1234579@qq.com', '跳舞', '320320199812120113', '320320199812120113', '1011', '张三');
INSERT INTO `tb_customer_vehicle` VALUES ('14', 'V1014', '小可', '女', '20', '18181818114', '杭州', '222234', '1234580@qq.com', '足球', '320320199712120114', '320320199712120114', '1010', '李四');

-- ----------------------------
-- Table structure for tb_department
-- ----------------------------
DROP TABLE IF EXISTS `tb_department`;
CREATE TABLE `tb_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `DeNo` varchar(255) DEFAULT NULL,
  `DeName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_department
-- ----------------------------
INSERT INTO `tb_department` VALUES ('1', 'd1001', '行政部');
INSERT INTO `tb_department` VALUES ('2', 'd1002', '销售部');
INSERT INTO `tb_department` VALUES ('3', 'd1003', '维修部');
INSERT INTO `tb_department` VALUES ('12', 'd1004', '后勤部');
INSERT INTO `tb_department` VALUES ('13', 'd1005', '普通');

-- ----------------------------
-- Table structure for tb_engstate
-- ----------------------------
DROP TABLE IF EXISTS `tb_engstate`;
CREATE TABLE `tb_engstate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `UNo` varchar(255) DEFAULT NULL,
  `UName` varchar(255) DEFAULT NULL,
  `EDate` varchar(255) DEFAULT NULL,
  `State` tinyint(11) DEFAULT NULL,
  `ReId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_engstate
-- ----------------------------
INSERT INTO `tb_engstate` VALUES ('1', 'u1010', '云聪', '2017-01-03 00:11:03', '1', '1');
INSERT INTO `tb_engstate` VALUES ('3', 'u1012', '云雪', '2017-01-03 01:08:39', '1', '3');
INSERT INTO `tb_engstate` VALUES ('4', 'u1011', '云风', '2017-01-03 11:09:31', '1', '4');
INSERT INTO `tb_engstate` VALUES ('5', 'u1012', '云雪', '2017-01-03 18:18:08', '1', '5');
INSERT INTO `tb_engstate` VALUES ('10', 'u1012', '云雪', '2017-01-04 15:41:45', '1', '10');
INSERT INTO `tb_engstate` VALUES ('11', 'u1011', '云风', '2017-01-04 15:43:32', '0', '11');
INSERT INTO `tb_engstate` VALUES ('13', 'u1011', '云风', '2017-01-04 11:12:17', '1', '13');
INSERT INTO `tb_engstate` VALUES ('14', 'u1012', '云雪', '0000-00-00 00:00:00', '0', '14');
INSERT INTO `tb_engstate` VALUES ('15', 'u1012', '云雪', '2017-01-05 21:10:17', '1', '15');

-- ----------------------------
-- Table structure for tb_evaluate
-- ----------------------------
DROP TABLE IF EXISTS `tb_evaluate`;
CREATE TABLE `tb_evaluate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `EvNo` varchar(255) DEFAULT NULL,
  `BDate` varchar(255) DEFAULT NULL,
  `EDate` varchar(255) DEFAULT NULL,
  `PlateNo` varchar(255) DEFAULT NULL,
  `TroContent` varchar(255) DEFAULT NULL,
  `Remarks` varchar(255) DEFAULT NULL,
  `UName` varchar(255) DEFAULT NULL,
  `ReId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_evaluate
-- ----------------------------
INSERT INTO `tb_evaluate` VALUES ('1', 'e1001', '2016-05-11 19:38:13', '2016-11-28 16:41:38', '1007', '减震器,传动轴振动', null, '云聪', '1');
INSERT INTO `tb_evaluate` VALUES ('3', 'e1003', '2016-01-05 08:37:11', '2016-12-06 13:44:10', '1005', '排气管排出蓝色烟雾', null, '云雪', '3');
INSERT INTO `tb_evaluate` VALUES ('4', 'e1004', '2016-11-09 13:36:20', '2016-12-09 13:36:20', '1013', '传动轴振动', null, '云风', '4');
INSERT INTO `tb_evaluate` VALUES ('5', 'e1005', '2016-11-17 13:37:44', '2016-12-17 13:37:44', '1002', '减震器', '无', '云雪', '5');
INSERT INTO `tb_evaluate` VALUES ('7', 'e2435', '2017-01-03 15:09:58', '2017-01-03 11:08:49', '1007', '减震器', '无', '云风', '11');
INSERT INTO `tb_evaluate` VALUES ('8', 'e1342', '2017-01-04 15:24:39', '2017-01-04 15:25:56', '1007', '排气管', '无', '云雪', '14');
INSERT INTO `tb_evaluate` VALUES ('9', 'e676376', '2017-01-05 21:00:54', '2017-01-04 21:02:28', '1007', '减震器', '无', '云雪', '15');

-- ----------------------------
-- Table structure for tb_objection
-- ----------------------------
DROP TABLE IF EXISTS `tb_objection`;
CREATE TABLE `tb_objection` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ObjectionNo` varchar(20) DEFAULT NULL,
  `CustomerName` varchar(50) DEFAULT NULL,
  `Telephone` varchar(50) DEFAULT NULL,
  `CarID` int(11) DEFAULT NULL,
  `Problems` varchar(255) DEFAULT NULL,
  `Processing` varchar(255) DEFAULT NULL,
  `ObTime` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_objection
-- ----------------------------
INSERT INTO `tb_objection` VALUES ('1', 'N0001', '小刚', '18181818101', '1007', '无', '无', '2016-12-21');
INSERT INTO `tb_objection` VALUES ('2', 'N0002', '小梅', '18181818222', '1009', '无', '无', '2016-12-20');
INSERT INTO `tb_objection` VALUES ('3', 'N0003', '小可', '18181818114', '1010', '无', '无', '2016-12-23');

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `OrderNo` varchar(20) DEFAULT NULL,
  `CustomerName` varchar(50) DEFAULT NULL,
  `CardID` varchar(50) DEFAULT NULL,
  `Telephone` varchar(50) DEFAULT NULL,
  `CarID` int(11) DEFAULT NULL,
  `SubscriptionID` int(11) NOT NULL,
  `OrTime` date DEFAULT NULL,
  `State` char(10) DEFAULT '未审核',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES ('1', 'D1001', '小刚', '320320199712120101', '18181818101', '1007', '1', '2016-10-21', '已审核');
INSERT INTO `tb_order` VALUES ('2', 'D1002', '小明', '320320199612120102', '18181918102', '1004', '2', '2016-10-21', '已审核');
INSERT INTO `tb_order` VALUES ('3', 'D1003', '小花', '320320199712120103', '18181818103', '1008', '3', '2016-10-22', '已审核');
INSERT INTO `tb_order` VALUES ('4', 'D1004', '小华', '320320199612120104', '18181818104', '1001', '4', '2016-10-22', '已审核');
INSERT INTO `tb_order` VALUES ('5', 'D1005', '小李', '320320199612120105', '18181818105', '1005', '5', '2016-10-23', '已审核');
INSERT INTO `tb_order` VALUES ('6', 'D1006', '小梅', '320320199512120106', '18181818106', '1009', '6', '2016-10-23', '已审核');
INSERT INTO `tb_order` VALUES ('7', 'D1007', '小朱', '320320199512120107', '18181818107', '1006', '7', '2016-10-24', '已审核');
INSERT INTO `tb_order` VALUES ('8', 'D1008', '小轩', '320320199612120108', '18181818108', '1002', '8', '2016-10-24', '已审核');
INSERT INTO `tb_order` VALUES ('9', 'D1009', '小羽', '320320199712120109', '18181818109', '1003', '9', '2016-10-25', '已审核');
INSERT INTO `tb_order` VALUES ('10', 'D1010', '小葛', '320320199512120110', '18181818110', '1015', '10', '2016-10-25', '已审核');
INSERT INTO `tb_order` VALUES ('11', 'D1011', '小康', '320320199712120111', '18181818111', '1013', '11', '2016-10-26', '已审核');
INSERT INTO `tb_order` VALUES ('12', 'D1012', '小吴', '320320199612120112', '18181818112', '1012', '12', '2016-10-26', '已审核');
INSERT INTO `tb_order` VALUES ('13', 'D1013', '小王', '320320199812120113', '18181818112', '1011', '13', '2016-10-27', '未审核');
INSERT INTO `tb_order` VALUES ('14', 'D1016', '小小', '3243435454', '3545466233', '1010', '16', '2016-12-20', '已审核');
INSERT INTO `tb_order` VALUES ('15', 'D1015', '小牛', '320320199712120116', '18181818115', '1019', '15', '2016-11-15', '未审核');
INSERT INTO `tb_order` VALUES ('16', 'D1017', '小小', '320320199912121212', '12345678900', '1020', '14', '2016-12-28', '未审核');

-- ----------------------------
-- Table structure for tb_payment
-- ----------------------------
DROP TABLE IF EXISTS `tb_payment`;
CREATE TABLE `tb_payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ManNo` varchar(255) DEFAULT NULL,
  `CName` varchar(255) DEFAULT NULL,
  `CostPrice` double DEFAULT NULL,
  `PFee` double DEFAULT NULL,
  `Tatle` double DEFAULT NULL,
  `PDate` varchar(255) DEFAULT NULL,
  `State` tinyint(4) DEFAULT NULL,
  `ReId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_payment
-- ----------------------------
INSERT INTO `tb_payment` VALUES ('1', 'm1001', '朱孙宇', '5000', '1000', '6000', '2016-12-30 00:40:23', '1', '1');
INSERT INTO `tb_payment` VALUES ('3', 'm1003', '吴玉吉', '2000', '1000', '3000', '2016-12-28 00:42:18', '1', '3');

-- ----------------------------
-- Table structure for tb_purchase_invoice
-- ----------------------------
DROP TABLE IF EXISTS `tb_purchase_invoice`;
CREATE TABLE `tb_purchase_invoice` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `InvoiceNo` varchar(20) DEFAULT NULL,
  `OrderID` int(11) NOT NULL,
  `InvTime` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_purchase_invoice
-- ----------------------------
INSERT INTO `tb_purchase_invoice` VALUES ('1', 'I0001', '1', '2016-11-02');
INSERT INTO `tb_purchase_invoice` VALUES ('2', 'I0002', '2', '2016-11-01');
INSERT INTO `tb_purchase_invoice` VALUES ('3', 'I0003', '3', '2016-11-03');
INSERT INTO `tb_purchase_invoice` VALUES ('4', 'I0004', '4', '2016-11-03');
INSERT INTO `tb_purchase_invoice` VALUES ('5', 'I0005', '5', '2016-11-04');
INSERT INTO `tb_purchase_invoice` VALUES ('6', 'I0006', '6', '2016-11-04');
INSERT INTO `tb_purchase_invoice` VALUES ('7', 'I0007', '7', '2016-11-05');
INSERT INTO `tb_purchase_invoice` VALUES ('8', 'I0008', '8', '2016-11-05');
INSERT INTO `tb_purchase_invoice` VALUES ('9', 'I0009', '9', '2016-11-06');
INSERT INTO `tb_purchase_invoice` VALUES ('10', 'I0010', '10', '2016-11-06');
INSERT INTO `tb_purchase_invoice` VALUES ('11', 'I0011', '11', '2016-11-07');
INSERT INTO `tb_purchase_invoice` VALUES ('12', 'I0012', '12', '2016-11-07');
INSERT INTO `tb_purchase_invoice` VALUES ('13', 'I0013', '13', '2016-11-08');
INSERT INTO `tb_purchase_invoice` VALUES ('14', 'I0014', '14', '2016-11-08');

-- ----------------------------
-- Table structure for tb_reception
-- ----------------------------
DROP TABLE IF EXISTS `tb_reception`;
CREATE TABLE `tb_reception` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `ReceptionNo` varchar(20) DEFAULT NULL,
  `CustomerName` varchar(50) DEFAULT NULL,
  `Age` int(10) DEFAULT NULL,
  `Sex` char(10) DEFAULT NULL,
  `Telephone` varchar(50) DEFAULT NULL,
  `Address` varchar(50) DEFAULT NULL,
  `CarStyleID` int(11) NOT NULL,
  `Rank` int(11) DEFAULT NULL,
  `Negotiation` varchar(50) DEFAULT NULL,
  `RankAftern` int(11) DEFAULT NULL,
  `ClosedCondition` varchar(50) DEFAULT NULL,
  `Receiver` varchar(50) DEFAULT NULL,
  `Remarks` varchar(50) DEFAULT NULL,
  `ReceptionTime` date DEFAULT NULL,
  `State` char(10) DEFAULT '未审核',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_reception
-- ----------------------------
INSERT INTO `tb_reception` VALUES ('1', 'R001', '小华', '30', '女', '18888888811', '上海', '4', '1', '好', '3', '预约试乘', '朱晓萱', '无', '2016-12-29', '已审核');
INSERT INTO `tb_reception` VALUES ('2', 'R002', '密码', '111', '男', '123454565656', '常州', '7', '3', '一般', '3', '预约试乘', '朱晓宇', '无', '2016-12-22', '未审核');
INSERT INTO `tb_reception` VALUES ('3', 'R003', '小明', '32', '男', '18888888813', '广州', '9', '2', '很好', '4', '预约试乘', '朱晓吉', '无', '2016-12-29', '已审核');
INSERT INTO `tb_reception` VALUES ('4', 'R004', '小刚', '30', '男', '18888888814', '深圳', '22', '2', '很好', '4', '预约试乘', '朱晓宇', '无', '2016-12-29', '已审核');
INSERT INTO `tb_reception` VALUES ('5', 'R005', '小红', '28', '女', '18888888815', '杭州', '25', '3', '好', '4', '预约试乘', '朱晓萱', '无', '2016-12-29', '已审核');
INSERT INTO `tb_reception` VALUES ('6', 'R006', '小花', '26', '女', '18888888816', '成都', '20', '1', '一般', '2', '跟踪', '朱晓吉', '无', '2016-12-29', '已审核');
INSERT INTO `tb_reception` VALUES ('7', 'R007', '小鸡', '31', '女', '18888888817', '重庆', '13', '3', '差', '3', '跟踪', '朱晓萱', '无', '2016-12-29', '已审核');
INSERT INTO `tb_reception` VALUES ('8', 'R008', '小猫', '27', '女', '18888888818', '南京', '17', '1', '好', '3', '跟踪', '朱晓宇', '无', '2016-12-28', '已审核');
INSERT INTO `tb_reception` VALUES ('9', 'R009', '小狗', '33', '女', '18888888819', '西安', '6', '2', '好', '3', '预约试乘', '朱晓吉', '无', '2016-12-29', '已审核');
INSERT INTO `tb_reception` VALUES ('10', 'R010', '小猪', '25', '男', '18888888820', '苏州', '5', '1', '好', '3', '预约试乘', '朱晓吉', '无', '2016-11-11', '未审核');
INSERT INTO `tb_reception` VALUES ('11', 'R011', '小羊', '27', '女', '18888888821', '悉尼', '6', '4', '好', '4', '预约试乘', '朱晓宇', '无', '2016-11-11', '未审核');
INSERT INTO `tb_reception` VALUES ('12', 'R012', '小牛', '28', '男', '18888888822', '伦敦', '8', '2', '一般', '3', '跟踪', '朱晓萱', '无', '2016-11-12', '未审核');
INSERT INTO `tb_reception` VALUES ('13', 'R013', '小王', '30', '男', '18888888823', '东京', '4', '2', '好', '4', '预约试乘', '朱晓吉', '无', '2016-11-12', '未审核');
INSERT INTO `tb_reception` VALUES ('14', 'R014', '小葛', '31', '女', '18888888824', '纽约', '24', '1', '很好', '3', '预约试乘', '朱晓萱', '无', '2016-12-29', '未审核');
INSERT INTO `tb_reception` VALUES ('15', 'R015', '小吴', '33', '女', '18888888825', '首尔', '7', '2', '好', '3', '跟踪', '朱晓吉', '无', '2016-11-12', '未审核');
INSERT INTO `tb_reception` VALUES ('16', 'R016', '小可', '27', '男', '18888888826', '平壤', '5', '1', '好', '2', '跟踪', '朱晓萱', '无', '2016-12-29', '未审核');
INSERT INTO `tb_reception` VALUES ('17', 'R018', '小小', '33', '男', '12345667890', '平壤', '9', '2', '一般', '3', '跟踪', '朱晓吉', '无', '2016-12-20', '未审核');

-- ----------------------------
-- Table structure for tb_repair
-- ----------------------------
DROP TABLE IF EXISTS `tb_repair`;
CREATE TABLE `tb_repair` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ReNo` varchar(255) DEFAULT NULL,
  `ReDate` varchar(255) DEFAULT NULL,
  `PlateNo` varchar(255) DEFAULT NULL,
  `Kilometres` double DEFAULT NULL,
  `ReContent` varchar(255) DEFAULT NULL,
  `ReOperate` varchar(255) DEFAULT NULL,
  `CName` varchar(255) DEFAULT NULL,
  `UName` varchar(255) DEFAULT NULL,
  `State` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_repair
-- ----------------------------
INSERT INTO `tb_repair` VALUES ('1', 'r1001', '2016-05-11 19:38:13', '1007', '500', '减震器,传动轴振动', '云兮', '小刚', '云聪', '1');
INSERT INTO `tb_repair` VALUES ('3', 'r1003', '2016-01-05 08:37:11', '1005', '344', '排气管排出蓝色烟雾', '云兮', '小李', '云雪', '0');
INSERT INTO `tb_repair` VALUES ('4', 'r1004', '2016-11-09 13:36:20', '1013', '233', '传动轴振动', '云兮', '张三', '云风', '1');
INSERT INTO `tb_repair` VALUES ('5', 'r1005', '2016-11-17 13:37:44', '1002', '421', '减震器', '云兮', '李四', '云雪', '0');
INSERT INTO `tb_repair` VALUES ('10', 'r908', '2017-01-03 14:33:16', '1005', '231', '排气管排出蓝色烟雾', '云兮', '小李', '云雪', '1');
INSERT INTO `tb_repair` VALUES ('11', 'r6765', '2017-01-03 15:09:58', '1007', '236', '减震器', '云兮', '小刚', '云风', '0');
INSERT INTO `tb_repair` VALUES ('13', 'r84766', '2017-01-04 11:07:51', '1007', '3242', '减震器', '云兮', '小刚', '云风', '1');
INSERT INTO `tb_repair` VALUES ('14', 'r376453', '2017-01-04 15:24:39', '1007', '1231', '排气管', '云兮', ' 小刚', '云雪', '0');
INSERT INTO `tb_repair` VALUES ('15', 'r98983', '2017-01-05 21:00:54', '1007', '343', '减震器', '云兮', '小刚', '云雪', '1');

-- ----------------------------
-- Table structure for tb_repairnum
-- ----------------------------
DROP TABLE IF EXISTS `tb_repairnum`;
CREATE TABLE `tb_repairnum` (
  `id` int(11) NOT NULL,
  `januarynum` int(11) NOT NULL,
  `februarynum` int(11) NOT NULL,
  `marchnum` int(11) NOT NULL,
  `aprilnum` int(11) NOT NULL,
  `maynum` int(11) NOT NULL,
  `junenum` int(11) NOT NULL,
  `julynum` int(11) NOT NULL,
  `augustnum` int(11) NOT NULL,
  `septembernum` int(11) NOT NULL,
  `octobernum` int(11) NOT NULL,
  `novembernum` int(11) NOT NULL,
  `decembernum` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_repairnum
-- ----------------------------
INSERT INTO `tb_repairnum` VALUES ('2013', '23', '34', '66', '23', '88', '90', '188', '89', '88', '89', '35', '80');
INSERT INTO `tb_repairnum` VALUES ('2014', '4', '189', '5', '88', '2', '80', '16', '60', '66', '35', '89', '66');
INSERT INTO `tb_repairnum` VALUES ('2015', '4', '8', '6', '23', '30', '65', '170', '175', '40', '19', '8', '4');
INSERT INTO `tb_repairnum` VALUES ('2016', '52', '16', '66', '178', '98', '58', '89', '96', '88', '89', '88', '66');

-- ----------------------------
-- Table structure for tb_restate
-- ----------------------------
DROP TABLE IF EXISTS `tb_restate`;
CREATE TABLE `tb_restate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ProNo` varchar(255) DEFAULT NULL,
  `PlateNo` varchar(255) DEFAULT NULL,
  `Execution` tinyint(1) DEFAULT NULL,
  `TroId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_restate
-- ----------------------------
INSERT INTO `tb_restate` VALUES ('1', 'p1001', '1007', '1', '1');
INSERT INTO `tb_restate` VALUES ('3', 'p1003', '1005', '1', '3');
INSERT INTO `tb_restate` VALUES ('4', 'p1004', '1013', '1', '4');
INSERT INTO `tb_restate` VALUES ('5', 'p1005', '1002', '1', '5');
INSERT INTO `tb_restate` VALUES ('7', 'p8237', '1005', '0', '8');
INSERT INTO `tb_restate` VALUES ('8', 'p356', '1007', '1', '9');
INSERT INTO `tb_restate` VALUES ('9', 'p2362', '1007', '1', '11');
INSERT INTO `tb_restate` VALUES ('10', 'p32221', '1005', '1', '8');
INSERT INTO `tb_restate` VALUES ('11', 'p77656', '1007', '1', '13');

-- ----------------------------
-- Table structure for tb_salesnum
-- ----------------------------
DROP TABLE IF EXISTS `tb_salesnum`;
CREATE TABLE `tb_salesnum` (
  `id` int(11) NOT NULL,
  `januarynum` int(11) NOT NULL,
  `februarynum` int(11) NOT NULL,
  `marchnum` int(11) NOT NULL,
  `aprilnum` int(11) NOT NULL,
  `Maynum` int(11) NOT NULL,
  `junenum` int(11) NOT NULL,
  `julynum` int(11) NOT NULL,
  `augustnum` int(11) NOT NULL,
  `septembernum` int(11) NOT NULL,
  `octobernum` int(11) NOT NULL,
  `novembernum` int(11) NOT NULL,
  `decembernum` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_salesnum
-- ----------------------------
INSERT INTO `tb_salesnum` VALUES ('2012', '66', '168', '26', '68', '88', '30', '66', '2', '56', '1', '23', '10');
INSERT INTO `tb_salesnum` VALUES ('2013', '5', '5', '8', '16', '61', '68', '86', '104', '112', '8', '8', '10');
INSERT INTO `tb_salesnum` VALUES ('2014', '5', '56', '95', '96', '61', '33', '10', '50', '40', '60', '10', '30');
INSERT INTO `tb_salesnum` VALUES ('2015', '4', '8', '14', '20', '50', '26', '60', '100', '63', '60', '10', '30');
INSERT INTO `tb_salesnum` VALUES ('2016', '63', '186', '8', '58', '78', '250', '85', '86', '72', '183', '86', '121');

-- ----------------------------
-- Table structure for tb_subscription
-- ----------------------------
DROP TABLE IF EXISTS `tb_subscription`;
CREATE TABLE `tb_subscription` (
  `id` int(10) NOT NULL,
  `SubscriptionNo` varchar(20) DEFAULT NULL,
  `CustomerName` varchar(50) DEFAULT NULL,
  `Sex` char(10) DEFAULT NULL,
  `Age` int(11) DEFAULT NULL,
  `CardID` varchar(50) DEFAULT NULL,
  `Address` varchar(50) DEFAULT NULL,
  `Telephone` varchar(50) DEFAULT NULL,
  `PC` varchar(50) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `CarStyleID` int(11) NOT NULL,
  `SubTime` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_subscription
-- ----------------------------
INSERT INTO `tb_subscription` VALUES ('1', 'S1001', '小刚', '男', '20', '320320199712120101', '纽约', '18181818101', '222221', '1234567@qq.com', '6', '2016-10-12');
INSERT INTO `tb_subscription` VALUES ('2', 'S1002', '小明', '男', '21', '320320199612120102', '伦敦', '18181818102', '222222', '1234568@qq.com', '7', '2016-10-11');
INSERT INTO `tb_subscription` VALUES ('3', 'S1003', '小花', '女', '21', '320320199712120103', '悉尼', '18181818103', '222223', '1234569@qq.com', '9', '2016-10-13');
INSERT INTO `tb_subscription` VALUES ('4', 'S1004', '小华', '男', '21', '320320199612120104', '北京', '18181818104', '222224', '1234570@qq.com', '9', '2016-10-13');
INSERT INTO `tb_subscription` VALUES ('5', 'S1005', '小李', '女', '21', '320320199612120105', '迪拜', '18181818105', '222225', '1234571@qq.com', '14', '2016-10-14');
INSERT INTO `tb_subscription` VALUES ('6', 'S1006', '小梅', '女', '22', '320320199512120106', '阿姆斯特丹', '18181818106', '222226', '1234572@qq.com', '13', '2016-10-14');
INSERT INTO `tb_subscription` VALUES ('7', 'S1007', '小朱', '男', '22', '320320199512120107', '比勒陀利亚', '18181818107', '222227', '1234573@qq.com', '16', '2016-10-15');
INSERT INTO `tb_subscription` VALUES ('8', 'S1008', '小轩', '男', '21', '320320199612120108', '开普敦', '18181818108', '222228', '1234574@qq.com', '23', '2016-10-15');
INSERT INTO `tb_subscription` VALUES ('9', 'S1009', '小羽', '男', '20', '320320199712120109', '布隆方丹', '18181818109', '222229', '1234575@qq.com', '18', '2016-10-16');
INSERT INTO `tb_subscription` VALUES ('10', 'S1010', '小葛', '男', '22', '320320199512120110', '赫尔辛基', '18181818110', '222230', '1234576@qq.com', '12', '2016-10-16');
INSERT INTO `tb_subscription` VALUES ('11', 'S1011', '小康', '女', '20', '320320199712120111', '阿布扎比', '18181818111', '222231', '1234577@qq.com', '11', '2016-10-17');
INSERT INTO `tb_subscription` VALUES ('12', 'S1012', '小吴', '女', '21', '320320199612120112', '科威特城', '18181818112', '222232', '1234578@qq.com', '18', '2016-10-17');
INSERT INTO `tb_subscription` VALUES ('13', 'S1013', '小王', '男', '19', '320320199812120113', '上海', '18181818113', '222233', '1234579@qq.com', '7', '2016-10-18');
INSERT INTO `tb_subscription` VALUES ('14', 'S1014', '小可', '女', '20', '320320199712120114', '杭州', '18181818114', '222234', '1234580@qq.com', '5', '2016-10-18');
INSERT INTO `tb_subscription` VALUES ('15', 'S1015', '小牛', '男', '27', '320320199712120116', '平壤', '18181818115', '121332', '1234580@sina.com', '4', '2016-11-07');
INSERT INTO `tb_subscription` VALUES ('16', 'S1016', '小小', '女', '30', '320320199710120909', '平壤', '18181818122', '313332', '4645455@sian.cn', '19', '2016-12-28');

-- ----------------------------
-- Table structure for tb_tree
-- ----------------------------
DROP TABLE IF EXISTS `tb_tree`;
CREATE TABLE `tb_tree` (
  `id` int(11) NOT NULL,
  `pid` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `open` tinyint(1) DEFAULT NULL,
  `file` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_tree
-- ----------------------------
INSERT INTO `tb_tree` VALUES ('1', '0', '基本信息维护', '0', null);
INSERT INTO `tb_tree` VALUES ('2', '1', '车型品牌维护', '0', 'VehicleBrand/vehicleBrand.jsp');
INSERT INTO `tb_tree` VALUES ('3', '1', '车型信息维护', '0', 'car/car.jsp');
INSERT INTO `tb_tree` VALUES ('4', '1', '配件品牌维护', '0', 'AccessoriesBrand/accessoriesBrand.jsp');
INSERT INTO `tb_tree` VALUES ('5', '1', '配件信息维护', '0', 'Accessories/accessories.jsp');
INSERT INTO `tb_tree` VALUES ('6', '1', '车辆信息维护', '0', 'Vehiclemanger/vehiclemanger.jsp');
INSERT INTO `tb_tree` VALUES ('7', '1', '供应商信息维护', '0', 'Vendor/vendor.jsp');
INSERT INTO `tb_tree` VALUES ('8', '0', '销售管理', '0', '');
INSERT INTO `tb_tree` VALUES ('9', '8', '展厅接待', '0', '');
INSERT INTO `tb_tree` VALUES ('10', '9', '展厅来电/店登记', '0', 'sales/exhibition/exhibition_reception.jsp');
INSERT INTO `tb_tree` VALUES ('11', '9', '接待日志审核', '0', 'sales/exhibition/exhibition_reception_review.jsp');
INSERT INTO `tb_tree` VALUES ('12', '8', '预约管理', '0', '');
INSERT INTO `tb_tree` VALUES ('13', '12', '试乘试驾预约', '0', 'sales/appointment/appointment_register.jsp');
INSERT INTO `tb_tree` VALUES ('14', '12', '试乘试驾预约审核', '0', 'sales/appointment/appointment_register_review.jsp');
INSERT INTO `tb_tree` VALUES ('15', '8', '订购管理', '0', '');
INSERT INTO `tb_tree` VALUES ('16', '15', '签订订购协议', '0', 'sales/order/subscription_sign.jsp');
INSERT INTO `tb_tree` VALUES ('17', '15', '销售订单登记', '0', 'sales/order/order_register.jsp');
INSERT INTO `tb_tree` VALUES ('18', '15', '销售订单审核', '0', 'sales/order/order_register_review.jsp');
INSERT INTO `tb_tree` VALUES ('19', '8', '成交管理', '0', null);
INSERT INTO `tb_tree` VALUES ('20', '19', '签订购车合同', '0', 'sales/deal/contract_sign.jsp');
INSERT INTO `tb_tree` VALUES ('21', '19', '开具购车发票', '0', 'sales/deal/purchase_invoice_draw.jsp');
INSERT INTO `tb_tree` VALUES ('22', '8', '售后管理', '0', null);
INSERT INTO `tb_tree` VALUES ('23', '22', '售后回访登记', '0', 'sales/service/callback_register.jsp');
INSERT INTO `tb_tree` VALUES ('24', '22', '异议协调处理', '0', 'sales/service/objection_disposal.jsp');
INSERT INTO `tb_tree` VALUES ('25', '0', '维修管理', '0', null);
INSERT INTO `tb_tree` VALUES ('26', '25', '工程师状态', '0', 'manager/engstate.html');
INSERT INTO `tb_tree` VALUES ('27', '25', '维修登记', '0', 'manager/repair.html');
INSERT INTO `tb_tree` VALUES ('28', '25', '维修评估', '0', 'manager/evaluate.html');
INSERT INTO `tb_tree` VALUES ('29', '25', '故障检修', '0', 'operator/trouble.html');
INSERT INTO `tb_tree` VALUES ('30', '25', '零件调用', '0', 'operator/component.html');
INSERT INTO `tb_tree` VALUES ('31', '25', '检修状态', '0', 'operator/restate.html');
INSERT INTO `tb_tree` VALUES ('32', '25', '客户支付', '0', 'manager/payment.html');
INSERT INTO `tb_tree` VALUES ('33', '0', '客户管理', '0', null);
INSERT INTO `tb_tree` VALUES ('34', '33', '意向客户跟踪', '0', 'customer/customer_intention.jsp');
INSERT INTO `tb_tree` VALUES ('35', '33', '意向客户流失分析', '0', 'customer/customer_intention_loss.jsp');
INSERT INTO `tb_tree` VALUES ('36', '33', '购车客户管理', '0', 'customer/customer_vehicle.jsp');
INSERT INTO `tb_tree` VALUES ('37', '0', '统计分析', '0', null);
INSERT INTO `tb_tree` VALUES ('38', '37', '报表分析', '0', 'account/account.jsp');
INSERT INTO `tb_tree` VALUES ('39', '37', '年度销售报表', '0', 'Salesnummanger/salesnummanger.jsp');
INSERT INTO `tb_tree` VALUES ('40', '37', '年度维修报表', '0', 'Repairnummanger/repairnummanger.jsp');
INSERT INTO `tb_tree` VALUES ('41', '37', '营业额报表', '0', 'Turnovermanger/turnovermanger.jsp');
INSERT INTO `tb_tree` VALUES ('42', '0', '用户管理', '0', null);
INSERT INTO `tb_tree` VALUES ('43', '42', '用户管理', '0', 'gm/user.html');
INSERT INTO `tb_tree` VALUES ('44', '42', '部门管理', '0', 'gm/depart.html');
INSERT INTO `tb_tree` VALUES ('45', '0', '个人信息', '0', '');
INSERT INTO `tb_tree` VALUES ('46', '45', '个人信息', '0', 'person/person.html');

-- ----------------------------
-- Table structure for tb_trouble
-- ----------------------------
DROP TABLE IF EXISTS `tb_trouble`;
CREATE TABLE `tb_trouble` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `TroNo` varchar(255) DEFAULT NULL,
  `TroPart` varchar(255) DEFAULT NULL,
  `TroContent` varchar(255) DEFAULT NULL,
  `Handle` varchar(255) DEFAULT NULL,
  `PlateNo` varchar(255) DEFAULT NULL,
  `UName` varchar(255) DEFAULT NULL,
  `ReId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_trouble
-- ----------------------------
INSERT INTO `tb_trouble` VALUES ('1', 't1001', '传动轴，减压器', '减震器,传动轴振动', '更换减压器，紧固传动轴', '1007', '云聪', '1');
INSERT INTO `tb_trouble` VALUES ('3', 't1003', '排气管', '排气管排出蓝色烟雾', '检查发动机', '1005', '云雪', '3');
INSERT INTO `tb_trouble` VALUES ('4', 't1004', '传动轴', '传动轴振动', '更换传动轴', '1013', '云风', '4');
INSERT INTO `tb_trouble` VALUES ('5', 't1005', '减震器', '减震器', '更换减震器', '1002', '云雪', '5');
INSERT INTO `tb_trouble` VALUES ('8', 't1232', '排气管', '排气管排出蓝色烟雾', '检查发动机', '1005', '云雪', '10');
INSERT INTO `tb_trouble` VALUES ('9', 't23242', '减震器', '减震器', '更换减震器', '1007', '云风', '11');
INSERT INTO `tb_trouble` VALUES ('11', 't23211', '减震器', '减震器', '更换减震器', '1007', '云风', '13');
INSERT INTO `tb_trouble` VALUES ('12', 't1341', '排气管', '排气管', '更换排气管', '1007', '云雪', '14');
INSERT INTO `tb_trouble` VALUES ('13', 't2372', '减震器', '减震器', '更换减震器', '1007', '云雪', '15');

-- ----------------------------
-- Table structure for tb_turnover
-- ----------------------------
DROP TABLE IF EXISTS `tb_turnover`;
CREATE TABLE `tb_turnover` (
  `id` int(11) NOT NULL,
  `januarynum` int(11) NOT NULL,
  `februarynum` int(11) NOT NULL,
  `marchnum` int(11) NOT NULL,
  `aprilnum` int(11) NOT NULL,
  `maynum` int(11) NOT NULL,
  `junenum` int(11) NOT NULL,
  `julynum` int(11) NOT NULL,
  `augustnum` int(11) NOT NULL,
  `septembernum` int(11) NOT NULL,
  `octobernum` int(11) NOT NULL,
  `novembernum` int(11) NOT NULL,
  `decembernum` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_turnover
-- ----------------------------
INSERT INTO `tb_turnover` VALUES ('2013', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2');
INSERT INTO `tb_turnover` VALUES ('2014', '56', '59', '189', '65', '36', '56', '48', '59', '56', '48', '59', '112');
INSERT INTO `tb_turnover` VALUES ('2015', '2', '1', '3', '5', '8', '18', '22', '5', '10', '8', '2', '15');
INSERT INTO `tb_turnover` VALUES ('2016', '2', '16', '4', '15', '6', '7', '8', '9', '3', '10', '12', '11');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `UNo` varchar(255) DEFAULT NULL,
  `Uname` varchar(255) DEFAULT NULL,
  `Passward` varchar(255) DEFAULT NULL,
  `Sex` varchar(255) DEFAULT NULL,
  `CertificateNo` varchar(255) DEFAULT NULL,
  `Birthday` date DEFAULT NULL,
  `Telephone` varchar(255) DEFAULT NULL,
  `UState` varchar(255) DEFAULT NULL,
  `Power` int(11) DEFAULT NULL,
  `DeId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'u1001', '王朝安', '123456', '男', '320320199609092222', '1996-09-09', '15215209090', '在职', '0', '1');
INSERT INTO `tb_user` VALUES ('2', 'u1002', '诸葛名雪', 'cccccc', '女', '320324199412280655', '1994-12-28', '18151283070', '在职', '1', '2');
INSERT INTO `tb_user` VALUES ('3', 'u1003', '云兮', '135790', '女', '433101197611048983', '1995-09-07', '15215207090', '在职', '2', '3');
INSERT INTO `tb_user` VALUES ('4', 'u1004', '李四', '111111', '男', '320320199509045122', '1995-09-04', '15215206090', '在职', '3', '2');
INSERT INTO `tb_user` VALUES ('5', 'u1005', '王五', '222222', '男', '320320199509055122', '1995-09-05', '15215205090', '在职', '3', '2');
INSERT INTO `tb_user` VALUES ('6', 'u1006', '马六', '333333', '男', '320320199509065122', '1995-09-06', '15215204090', '在职', '3', '2');
INSERT INTO `tb_user` VALUES ('7', 'u1007', '朱晓宇', '444444', '男', '320320199509035122', '1995-09-03', '15215203090', '在职', '4', '2');
INSERT INTO `tb_user` VALUES ('8', 'u1008', '朱晓萱', '555555', '女', '320320199509025122', '1995-09-02', '15215202090', '在职', '4', '2');
INSERT INTO `tb_user` VALUES ('9', 'u1009', '朱晓吉', '666666', '男', '320320199509015122', '1995-09-01', '15215201090', '在职', '4', '2');
INSERT INTO `tb_user` VALUES ('10', 'u1010', '云聪', '777777', '男', '320320199508015122', '1995-08-01', '15215101090', '在职', '5', '3');
INSERT INTO `tb_user` VALUES ('11', 'u1011', '云风', '888888', '男', '320320199508025122', '1995-08-02', '15215301090', '在职', '5', '3');
INSERT INTO `tb_user` VALUES ('12', 'u1012', '云雪', '666666', '女', '320320199508035122', '1995-08-01', '15215401090', '事假', '5', '3');

-- ----------------------------
-- Table structure for tb_vehicle
-- ----------------------------
DROP TABLE IF EXISTS `tb_vehicle`;
CREATE TABLE `tb_vehicle` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `VehicleNo` varchar(20) DEFAULT NULL,
  `Fletter` char(1) DEFAULT NULL,
  `Brand` varchar(50) DEFAULT NULL,
  `Series` varchar(50) DEFAULT NULL,
  `VehicleName` varchar(50) DEFAULT NULL,
  `GuidePrice` double(20,2) DEFAULT NULL,
  `MJ` varchar(20) DEFAULT NULL,
  `Manufacturea` varchar(50) DEFAULT NULL,
  `Rank` varchar(20) DEFAULT NULL,
  `Engine` varchar(50) DEFAULT NULL,
  `Gearbox` varchar(50) DEFAULT NULL,
  `Lwh` varchar(50) DEFAULT NULL,
  `MaxSpeed` double(20,0) DEFAULT NULL,
  `FuelConsumption` varchar(50) DEFAULT NULL,
  `Warranty` varchar(20) DEFAULT NULL,
  `Color` char(10) DEFAULT NULL,
  `Displacement` double(10,0) DEFAULT NULL,
  `NumberofCylinders` int(10) DEFAULT NULL,
  `Photo` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_vehicle
-- ----------------------------
INSERT INTO `tb_vehicle` VALUES ('3', '1486', 'B', '宝马', '07', '宝马7系01', '55.00', '2016', '宝马(进口)', '大型车', '4.8L 367马力 V8', '6挡手自一体', '5179*1902*1484', '120', '6', '3', '黑色', '2', '11', '701');
INSERT INTO `tb_vehicle` VALUES ('4', '1487', 'B', '宝马', '07', '宝马7系02', '47.00', '2016', '宝马（进口）', '大型车', '6.0L 445马力 V12', '6档手自一体', '5179*1902*1484', '120', '10', '3', '白色', '2', '10', '702');
INSERT INTO `tb_vehicle` VALUES ('5', '1488', 'B', '宝马', '01', '宝马1系', '60.00', '2016', '宝马（进口）', '大型车', '3.0L 231马力 L6', '6档手动档', '5169*1902*1492', '120', '8', '3', '白色', '2', '12', '101');
INSERT INTO `tb_vehicle` VALUES ('6', '1489', 'B', '宝马', '02', '宝马2系', '49.00', '2016', '宝马（进口）', '大型车', '3.6L 272马力 V8', '6档自动挡', '5169*1902*1492', '120', '9', '3', '灰色', '2', '13', '201');
INSERT INTO `tb_vehicle` VALUES ('7', '1490', 'B', '宝马', '03', '宝马3系', '66.00', '2016', '宝马（进口）', '大型车', '4.4L 333马力 V8', '5档手动档', '5169*1902*1492', '120', '5', '3', '蓝色', '2', '14', '301');
INSERT INTO `tb_vehicle` VALUES ('8', '1491', 'B', '宝马', '04', '宝马4系01', '66.00', '2016', '宝马（进口）', '大型车', '3.0L 258马力 L6', '4档自动挡', '4454*1798*1545', '120', '10', '3', '白色', '2', '15', '401');
INSERT INTO `tb_vehicle` VALUES ('9', '1492', 'B', '宝马', '04', '宝马4系02', '66.00', '2016', '宝马（进口）', '小型车', '2.0T 184马力 L4', '6档手动挡', '4665*1890*1690', '120', '10', '3', '黑色', '2', '16', '402');
INSERT INTO `tb_vehicle` VALUES ('10', '1493', 'B', '宝马', '05', '宝马5系01', '88.00', '2016', '（进口）', '小型车', '2.0T 184马力 L4', '6档手动挡', '4665*1890*16', '120', '10', '3', '巧克力色', '2', '17', '501');
INSERT INTO `tb_vehicle` VALUES ('11', '1494', 'B', '宝马', '05', '宝马5系02', '88.00', '2016', '（进口）', '小型车', '3.0L 218马力 L6', '6档手动挡', '4665*1890*16', '120', '10', '3', '银灰色', '2', '18', '502');
INSERT INTO `tb_vehicle` VALUES ('12', '1495', 'B', '宝马', '06', '宝马6系01', '88.00', '2016', '（进口）', '大型车', '4.8L 367马力 V8', '电动档', '4006*1775*1600', '120', '10', '3', '红色', '2', '19', '601');
INSERT INTO `tb_vehicle` VALUES ('13', '1496', 'B', '宝马', '06', '宝马6系02', '88.00', '2016', '（进口）', '中型车', '1.5T 231马力 L3', '6档自动挡', '4454*1798*1545', '120', '10', '3', '蓝色', '2', '20', '602');
INSERT INTO `tb_vehicle` VALUES ('14', '1497', 'B', '宝马', 'i8', '宝马i8', '80.00', '2016', '（进口）', '大型车', '2.0L  150马力 L4', '6档自动挡', '4454*1798*1545', '120', '10', '3', '黑色', '2', '21', 'i8');
INSERT INTO `tb_vehicle` VALUES ('15', '1498', 'B', '宝马', 'm4', '宝马m4', '77.00', '2016', '（进口）', '大型车', '3.0L 218马力 L6', '8档手动挡', '4454*1798*1545', '120', '10', '3', '黄色', '2', '23', 'm4');
INSERT INTO `tb_vehicle` VALUES ('16', '1499', 'B', '宝马', 'm6', '宝马m6', '77.00', '2016', '（进口）', '大型车', '3.0L 258马力 L6', '10档自动挡', '4454*1798*1545', '120', '10', '3', '红色', '2', '24', 'm6');
INSERT INTO `tb_vehicle` VALUES ('17', '1450', 'B', '宝马', 'x1', '宝马x1', '77.00', '2016', '（进口）', '中型车', '2.0T 245马力 L4', '8档自动挡', '4665*1890*1690', '100', '10', '3', '咖啡色', '2', '25', 'x1');
INSERT INTO `tb_vehicle` VALUES ('18', '1451', 'B', '宝马', 'x3', '宝马x3', '77.00', '2016', '（进口）', '中型车', '2.0T 245马力 L4', '8档自动挡', '4665*1890*1690', '100', '10', '3', '白色', '2', '15', 'x3');
INSERT INTO `tb_vehicle` VALUES ('19', '1452', 'B', '宝马', 'x4', '宝马x4', '77.00', '2016', '（进口）', '小型车', '2.0T 245马力 L4', '8档自动挡', '4665*1890*1690', '100', '10', '3', '红色', '2', '15', 'x4');
INSERT INTO `tb_vehicle` VALUES ('20', '1453', 'B', '奔驰', '01', '奔驰', '88.00', '2016', '（进口）', '小型车', '2.0T 245马力 L4', '8档自动挡', '4665*1890*1690', '100', '10', '3', '银白色', '2', '15', 'b01');
INSERT INTO `tb_vehicle` VALUES ('21', '1454', 'A', '奥迪', 'a4', '奥迪a4', '88.00', '2016', '（进口）', '小型车', '2.0T 245马力 L4', '8档自动挡', '4665*1890*1690', '100', '10', '3', '灰白色', '2', '15', 'a4');
INSERT INTO `tb_vehicle` VALUES ('22', '1455', 'A', '奥迪', 'a6', '奥迪a6', '88.00', '2016', '（进口）', '小型车', '2.0T 245马力 L4', '8档自动挡', '4665*1890*1690', '100', '7', '3', '深灰色', '2', '15', 'a6');
INSERT INTO `tb_vehicle` VALUES ('23', '1456', 'A', '奥迪', 'q3', '奥迪q3', '88.00', '2016', '（进口）', '小型车', '2.0T 245马力 L4', '8档自动挡', '4665*1890*1690', '100', '10', '3', '黄色', '2', '15', 'aq3');
INSERT INTO `tb_vehicle` VALUES ('24', '1457', 'A', '奥迪', 'q54', '奥迪q54', '100.00', '2016', '（进口）', '小型车', '2.0T 245马力 L4', '8档自动挡', '4665*1890*1690', '100', '8', '3', '白色', '2', '15', 'aq54');
INSERT INTO `tb_vehicle` VALUES ('25', '1458', 'B', '奔驰', 'GLC', '奔驰GLC', '57.90', '2016', '（进口）', '中型SUV', '2.0T 245马力 L4', '8档自动挡', '4665*1890*1690', '100', '7.6', '3', '白色', '2', '15', 'bglc');
INSERT INTO `tb_vehicle` VALUES ('26', '1459', 'A', '奥迪', 'q5', '奥迪q5', '53.40', '2016', '（进口）', '中型SUV', '2.0T 245马力 L4', '8档自动/手动挡', '4665*1890*169', '100', '8.4', '3', '白色', '2', '10', 'aq5');
INSERT INTO `tb_vehicle` VALUES ('27', '1460', 'D', '大众', '380', '帕萨特', '30.49', '2016', '（国产）', '中型车', '1.4T 245马力 L4', '手动/双离合', '4665*1890*169', '100', '8.9', '3', '黑色', '1', '8', 'dpst');

-- ----------------------------
-- Table structure for tb_vehicles
-- ----------------------------
DROP TABLE IF EXISTS `tb_vehicles`;
CREATE TABLE `tb_vehicles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vehicleNo` varchar(255) NOT NULL,
  `fletter` char(255) NOT NULL,
  `brand` varchar(255) NOT NULL,
  `series` varchar(255) NOT NULL,
  `vehicleName` varchar(255) NOT NULL,
  `guidePrice` double NOT NULL,
  `mj` varchar(255) NOT NULL,
  `manufacturea` varchar(255) NOT NULL,
  `rank` varchar(255) NOT NULL,
  `engine` varchar(255) NOT NULL,
  `gearbox` varchar(255) NOT NULL,
  `LengthWH` varchar(255) NOT NULL,
  `maxSpeed` double NOT NULL,
  `fuelConsumption` varchar(255) NOT NULL,
  `warranty` varchar(255) NOT NULL,
  `color` char(255) NOT NULL,
  `displacement` double NOT NULL,
  `numberofCylinders` int(11) NOT NULL,
  `photo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_vehicles
-- ----------------------------
INSERT INTO `tb_vehicles` VALUES ('3', 'VBMA762', 'B', '宝马', '宝马740', '宝马', '500.4', '宝马', '宝马', '大型车', '2.5T涡轮增压', '6档自动挡', '5623*5956*5558', '300', '大', '三年', '黑色', '60.3', '10', '暂无');
INSERT INTO `tb_vehicles` VALUES ('4', 'VBMA763', 'B', '宝马', 'X3', '宝马', '800.8', '宝马', '宝马', '大型车', '3.0T', '8档手动挡', '4569*7986*6743', '300', '大', '三年', '黑色', '69', '10', '暂无');
INSERT INTO `tb_vehicles` VALUES ('6', 'VBM801', 'B', '宝马', '宝马X7', '宝马X7x1', '600.5', '宝马', '宝马', '中型车', '2.5T', '8档自动挡', '5632*9859*8956', '350', '大', '三年', '黑色', '0', '10', '暂无');
INSERT INTO `tb_vehicles` VALUES ('7', 'VBM888', 'B', '宝马', '宝马', '宝马X7', '600.2', '宝马', '宝马', '大型车', '6.0T', '6档手动挡', '5965*9859*5986', '300', '大', '三年', '黑色', '60', '10', '暂无');
INSERT INTO `tb_vehicles` VALUES ('8', 'VVV313', 'B', '宝马', '宝马XT系', '宝马XT51', '600', '宝马', '宝马公司', '大型车', '6.0T', '8档', '3432*43636*7886', '300', '大', '三年', '黑色', '79', '10', '暂无');
INSERT INTO `tb_vehicles` VALUES ('10', 'VBM899', 'B', '宝马', 'X1', '宝马X1', '800.9', '宝马', '宝马', '大型车', '2.5T', '6档手动挡', '3353*7843*9308', '600', '大', '三年', '黑色', '60', '10', '略');
INSERT INTO `tb_vehicles` VALUES ('11', 'VBMA116', 'B', '宝马', 'X', '宝马X1', '600', '宝马', '宝马', '大型车', '2.5T', '6挡手动挡', '3467*4793*6574', '300', '大', '一年', '黑色', '60', '8', '暂无');
INSERT INTO `tb_vehicles` VALUES ('12', 'VBM666', 'B', '宝马', 'G系', '宝马', '600', '宝马', '宝马', '大型车', '2.5T', '8挡自动挡', '3455*6866*6636', '300', '大', '一年', '黑色', '60', '8', '暂无');

-- ----------------------------
-- Table structure for tb_vehicle_brand
-- ----------------------------
DROP TABLE IF EXISTS `tb_vehicle_brand`;
CREATE TABLE `tb_vehicle_brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `BrandNo` varchar(20) NOT NULL,
  `BrandName` varchar(50) NOT NULL,
  `Sign` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_vehicle_brand
-- ----------------------------
INSERT INTO `tb_vehicle_brand` VALUES ('1', 'CB1', '大众', '暂无');
INSERT INTO `tb_vehicle_brand` VALUES ('2', 'CB10', '雷诺', '暂无');
INSERT INTO `tb_vehicle_brand` VALUES ('3', 'CB100', '科尼赛克', '暂无');
INSERT INTO `tb_vehicle_brand` VALUES ('6', 'CB101', '开瑞', '暂无');
INSERT INTO `tb_vehicle_brand` VALUES ('7', 'CB102', '威麟', '暂无');
INSERT INTO `tb_vehicle_brand` VALUES ('10', 'CB116', '宝马', '暂无');
INSERT INTO `tb_vehicle_brand` VALUES ('11', 'CB111', '宝马', '暂无');

-- ----------------------------
-- Table structure for tb_vendor
-- ----------------------------
DROP TABLE IF EXISTS `tb_vendor`;
CREATE TABLE `tb_vendor` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `VendorNo` varchar(20) NOT NULL,
  `VendorName` varchar(50) NOT NULL,
  `Address` varchar(50) NOT NULL,
  `Telephone` varchar(20) NOT NULL,
  `Reputation` char(10) NOT NULL,
  `Websites` varchar(50) NOT NULL,
  `HeaderName` varchar(50) NOT NULL,
  `HeaderSex` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_vendor
-- ----------------------------
INSERT INTO `tb_vendor` VALUES ('1', '101', 'Das Auto', '德国', '10086', '优秀', '一汽大众', '奥巴马', '男');
INSERT INTO `tb_vendor` VALUES ('2', '102', '奔驰', '法国', '10086', '优秀', '奔驰', '丘吉尔', '男');
INSERT INTO `tb_vendor` VALUES ('3', '111', '宝马', '德国', '10101', '良好', '奔驰', '王可', '男');
INSERT INTO `tb_vendor` VALUES ('4', '119', '雷克萨斯', '上海', '1982676733', '差', '雷克塞斯官网', '无', '女');
SET FOREIGN_KEY_CHECKS=1;
