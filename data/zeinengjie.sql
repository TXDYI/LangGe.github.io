/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80041 (8.0.41)
 Source Host           : localhost:3306
 Source Schema         : zeinengjie

 Target Server Type    : MySQL
 Target Server Version : 80041 (8.0.41)
 File Encoding         : 65001

 Date: 04/03/2026 16:20:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for exposure
-- ----------------------------
DROP TABLE IF EXISTS `exposure`;
CREATE TABLE `exposure`  (
  `id_exposure` int NOT NULL AUTO_INCREMENT COMMENT 'exposure_id',
  `title_text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '曝光标题-游戏名字',
  `explain_text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '曝光标题-说明',
  `exposure_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '曝光-图片',
  `exposure_show` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否展示在主页',
  PRIMARY KEY (`id_exposure`) USING BTREE,
  CONSTRAINT `exposure_chk_1` CHECK (`exposure_show` in (_utf8mb4'T',_utf8mb4'F'))
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exposure
-- ----------------------------
INSERT INTO `exposure` VALUES (1, '穿越火线', '6盘 6烈 幻神套装', 'https://img.uhaozu.com/zuhao/images/logo/2023/350/G68.png', 'T');
INSERT INTO `exposure` VALUES (2, 'CS:GO2', '6盘 6烈 幻神套装', 'https://img.uhaozu.com/zuhao/images/logo/2023/350/413850.png', 'T');
INSERT INTO `exposure` VALUES (3, '绝地求生', '6盘 6烈 幻神套装', 'https://img.uhaozu.com/zuhao/images/logo/2023/350/578080.png', 'T');
INSERT INTO `exposure` VALUES (4, '无畏契约', '6盘 6烈 幻神套装', 'https://img.uhaozu.com/zuhao/images/logo/2023/350/WWQY001.png', 'T');
INSERT INTO `exposure` VALUES (5, '黑神话：悟空', '重走一遍西游', 'https://img.uhaozu.com/zuhao/images/logo/2023/350/HSHWK002.png?x-oss-process=image/resize,m_fixed,h_80,w_80', 'T');
INSERT INTO `exposure` VALUES (6, '三角洲行动', '6盘 6烈 幻神套装', 'https://img.uhaozu.com/zuhao/images/logo/2023/350/SJZXDDY001.png?x-oss-process=image/resize,m_fixed,h_80,w_80', 'T');
INSERT INTO `exposure` VALUES (7, 'QQ飞车', '6盘 6烈 幻神套装', 'https://img.uhaozu.com/zuhao/images/logo/2023/350/G102.png?x-oss-process=image/resize,m_fixed,h_80,w_80', 'T');
INSERT INTO `exposure` VALUES (8, '测试', '测试数据', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/uploads/1747717121187_06676c3a-9889-42a6-b976-5d08499d0408.png', 'F');
INSERT INTO `exposure` VALUES (9, '测试2', '测试数据2', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/uploads/1747717405201_532d9bd3-98b8-4cdc-8130-a85773d581fe.png', 'F');
INSERT INTO `exposure` VALUES (10, '测试3', '测试数据3', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/uploads/1747717521620_0e82463a-6560-4924-9da3-f9b60394b53a.png', 'F');
INSERT INTO `exposure` VALUES (11, '测试4', '测试数据4', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/uploads/1747901354355_0c2a39cf-8eed-477f-ace7-97831c0bd227.png', 'F');

-- ----------------------------
-- Table structure for gameall
-- ----------------------------
DROP TABLE IF EXISTS `gameall`;
CREATE TABLE `gameall`  (
  `gameall_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `gameall_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '游戏名字',
  `gameall_class` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `os` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `known` bigint NULL DEFAULT NULL COMMENT '已租',
  `leisure` bigint NULL DEFAULT NULL COMMENT '待租',
  `authorization` bigint NULL DEFAULT NULL COMMENT '待上架',
  `endOrderNub` bigint NULL DEFAULT NULL COMMENT '已结束',
  PRIMARY KEY (`gameall_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gameall
-- ----------------------------
INSERT INTO `gameall` VALUES (1, '穿越火线', '射击', 'pc', 0, 6, 0, 1);
INSERT INTO `gameall` VALUES (2, '英雄联盟', '合作', 'pc', 0, 1, 1, 0);
INSERT INTO `gameall` VALUES (3, 'CS:GO2', '射击', 'pc', 0, 4, 0, 0);
INSERT INTO `gameall` VALUES (4, '绝地求生', '射击', 'pc', 0, 5, 0, 1);
INSERT INTO `gameall` VALUES (5, '生死狙击', '射击', 'pc', 0, 0, 0, 0);
INSERT INTO `gameall` VALUES (6, '无畏契约', '射击', 'pc', 0, 5, 0, 0);
INSERT INTO `gameall` VALUES (7, '星际争霸', '合作', 'pc', 0, 1, 0, 0);
INSERT INTO `gameall` VALUES (8, '阿尔登法环', '动作', 'pc', 0, 0, 0, 0);
INSERT INTO `gameall` VALUES (9, '腐烂国度', '丧尸', 'pc', 0, 0, 0, 0);
INSERT INTO `gameall` VALUES (10, '魔兽争霸', '竞技', 'pc', 0, 0, 0, 0);
INSERT INTO `gameall` VALUES (11, '燕云十六声', '探索', '双端', 0, 0, 0, 0);
INSERT INTO `gameall` VALUES (12, '原神', '探索', '双端', 0, 1, 2, 0);
INSERT INTO `gameall` VALUES (13, '三角洲:行动', '射击', '双端', 0, 1, 2, 0);
INSERT INTO `gameall` VALUES (14, '暗区突围', '射击', '双端', 0, 1, 0, 0);
INSERT INTO `gameall` VALUES (15, '王者荣耀', '合作', 'Android', 0, 0, 0, 0);
INSERT INTO `gameall` VALUES (16, '幻塔', '探索', 'Android', 0, 0, 0, 0);
INSERT INTO `gameall` VALUES (17, '黑神话：悟空', '动作', 'pc', 0, 1, 0, 0);
INSERT INTO `gameall` VALUES (18, '消失的光芒', '丧尸', 'pc', 0, 0, 0, 0);
INSERT INTO `gameall` VALUES (19, '求生之路2', '丧尸', 'pc', 0, 0, 0, 0);
INSERT INTO `gameall` VALUES (20, '求生之路', '丧尸', 'pc', 0, 0, 0, 0);
INSERT INTO `gameall` VALUES (21, '只狼：影逝二度', '动作', 'pc', 0, 1, 0, 0);
INSERT INTO `gameall` VALUES (22, '文明6', '策略', 'pc', 0, 0, 0, 0);
INSERT INTO `gameall` VALUES (23, '文明7', '策略', 'pc', 0, 0, 0, 0);
INSERT INTO `gameall` VALUES (24, '拳皇9', '动作', 'pc', 0, 0, 0, 0);
INSERT INTO `gameall` VALUES (25, '地下城勇士', '动作', 'pc', 0, 0, 0, 0);
INSERT INTO `gameall` VALUES (26, '永结无间', '动作', 'pc', 0, 0, 0, 0);
INSERT INTO `gameall` VALUES (27, '全面战争：法老', '策略', 'pc', 0, 0, 0, 0);
INSERT INTO `gameall` VALUES (28, '废土帝国：人类复兴', '策略', 'pc', 0, 0, 0, 0);
INSERT INTO `gameall` VALUES (29, 'X4：基石', '策略', 'pc', 0, 0, 0, 0);
INSERT INTO `gameall` VALUES (30, 'QQ飞车', '竞技', 'pc', 0, 0, 1, 0);

-- ----------------------------
-- Table structure for gameimg
-- ----------------------------
DROP TABLE IF EXISTS `gameimg`;
CREATE TABLE `gameimg`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `img_game_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '游戏名字',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '图片地址',
  `games_id` int NOT NULL COMMENT 'games_id_FK',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `games_id`(`games_id` ASC) USING BTREE,
  CONSTRAINT `gameimg_ibfk_1` FOREIGN KEY (`games_id`) REFERENCES `games` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 129 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gameimg
-- ----------------------------
INSERT INTO `gameimg` VALUES (1, '穿越火线', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742794580643_6de5bbaf-039d-4e58-93a2-61ede6248fc4.png', 1);
INSERT INTO `gameimg` VALUES (2, '穿越火线', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742794580643_6de5bbaf-039d-4e58-93a2-61ede6248fc4.png', 1);
INSERT INTO `gameimg` VALUES (3, '穿越火线', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742794580643_6de5bbaf-039d-4e58-93a2-61ede6248fc4.png', 1);
INSERT INTO `gameimg` VALUES (4, 'CS:GO2', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742794580643_6de5bbaf-039d-4e58-93a2-61ede6248fc4.png', 2);
INSERT INTO `gameimg` VALUES (5, 'CS:GO2', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742794580643_6de5bbaf-039d-4e58-93a2-61ede6248fc4.png', 2);
INSERT INTO `gameimg` VALUES (6, 'CS:GO2', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742794580643_6de5bbaf-039d-4e58-93a2-61ede6248fc4.png', 2);
INSERT INTO `gameimg` VALUES (7, '无畏契约', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872685434_36760936-159e-4996-9a66-b5e8c7124082.png', 4);
INSERT INTO `gameimg` VALUES (8, '无畏契约', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872685434_36760936-159e-4996-9a66-b5e8c7124082.png', 4);
INSERT INTO `gameimg` VALUES (9, '无畏契约', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872685434_36760936-159e-4996-9a66-b5e8c7124082.png', 4);
INSERT INTO `gameimg` VALUES (10, '无畏契约', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872685434_36760936-159e-4996-9a66-b5e8c7124082.png', 5);
INSERT INTO `gameimg` VALUES (11, '无畏契约', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872685434_36760936-159e-4996-9a66-b5e8c7124082.png', 5);
INSERT INTO `gameimg` VALUES (12, '无畏契约', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872685434_36760936-159e-4996-9a66-b5e8c7124082.png', 5);
INSERT INTO `gameimg` VALUES (13, '绝地求生', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742707329533_ab74629a-2f44-45f9-911b-02c730507fd6.png', 6);
INSERT INTO `gameimg` VALUES (14, '绝地求生', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742707330159_ab74629a-2f44-45f9-911b-02c730507fd6.png', 6);
INSERT INTO `gameimg` VALUES (15, '绝地求生', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742707330755_ab74629a-2f44-45f9-911b-02c730507fd6.png', 6);
INSERT INTO `gameimg` VALUES (16, '穿越火线', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742794578577_6de5bbaf-039d-4e58-93a2-61ede6248fc4.png', 7);
INSERT INTO `gameimg` VALUES (17, '穿越火线', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742794579442_6de5bbaf-039d-4e58-93a2-61ede6248fc4.png', 7);
INSERT INTO `gameimg` VALUES (18, '穿越火线', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742794579922_6de5bbaf-039d-4e58-93a2-61ede6248fc4.png', 7);
INSERT INTO `gameimg` VALUES (19, '穿越火线', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742794580643_6de5bbaf-039d-4e58-93a2-61ede6248fc4.png', 7);
INSERT INTO `gameimg` VALUES (20, '穿越火线', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742794581215_6de5bbaf-039d-4e58-93a2-61ede6248fc4.png', 7);
INSERT INTO `gameimg` VALUES (25, '绝地求生', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742801630581_7f2ad993-8c8d-46ad-8a3d-f683519f33dd.png', 9);
INSERT INTO `gameimg` VALUES (26, '绝地求生', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742801630873_7f2ad993-8c8d-46ad-8a3d-f683519f33dd.png', 9);
INSERT INTO `gameimg` VALUES (27, '绝地求生', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742801631085_7f2ad993-8c8d-46ad-8a3d-f683519f33dd.png', 9);
INSERT INTO `gameimg` VALUES (28, '绝地求生', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742801631269_7f2ad993-8c8d-46ad-8a3d-f683519f33dd.png', 9);
INSERT INTO `gameimg` VALUES (29, '穿越火线', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742871248091_36760936-159e-4996-9a66-b5e8c7124082.png', 10);
INSERT INTO `gameimg` VALUES (30, '穿越火线', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742871248715_36760936-159e-4996-9a66-b5e8c7124082.png', 10);
INSERT INTO `gameimg` VALUES (31, '穿越火线', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742871249438_36760936-159e-4996-9a66-b5e8c7124082.png', 10);
INSERT INTO `gameimg` VALUES (32, '穿越火线', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742871249919_36760936-159e-4996-9a66-b5e8c7124082.png', 10);
INSERT INTO `gameimg` VALUES (33, '穿越火线', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742871250463_36760936-159e-4996-9a66-b5e8c7124082.png', 10);
INSERT INTO `gameimg` VALUES (34, '穿越火线', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742871580990_36760936-159e-4996-9a66-b5e8c7124082.png', 11);
INSERT INTO `gameimg` VALUES (35, '穿越火线', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742871581715_36760936-159e-4996-9a66-b5e8c7124082.png', 11);
INSERT INTO `gameimg` VALUES (36, '穿越火线', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742871582188_36760936-159e-4996-9a66-b5e8c7124082.png', 11);
INSERT INTO `gameimg` VALUES (37, '穿越火线', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742871582617_36760936-159e-4996-9a66-b5e8c7124082.png', 11);
INSERT INTO `gameimg` VALUES (38, '穿越火线', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742871582976_36760936-159e-4996-9a66-b5e8c7124082.png', 11);
INSERT INTO `gameimg` VALUES (39, '穿越火线', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742871645299_36760936-159e-4996-9a66-b5e8c7124082.png', 12);
INSERT INTO `gameimg` VALUES (40, '穿越火线', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742871645767_36760936-159e-4996-9a66-b5e8c7124082.png', 12);
INSERT INTO `gameimg` VALUES (41, '穿越火线', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742871646111_36760936-159e-4996-9a66-b5e8c7124082.png', 12);
INSERT INTO `gameimg` VALUES (42, '穿越火线', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742871646316_36760936-159e-4996-9a66-b5e8c7124082.png', 12);
INSERT INTO `gameimg` VALUES (43, '穿越火线', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742871646989_36760936-159e-4996-9a66-b5e8c7124082.png', 12);
INSERT INTO `gameimg` VALUES (44, 'CS:GO2', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872046079_36760936-159e-4996-9a66-b5e8c7124082.png', 13);
INSERT INTO `gameimg` VALUES (45, 'CS:GO2', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872046428_36760936-159e-4996-9a66-b5e8c7124082.png', 13);
INSERT INTO `gameimg` VALUES (46, 'CS:GO2', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872046858_36760936-159e-4996-9a66-b5e8c7124082.png', 13);
INSERT INTO `gameimg` VALUES (47, 'CS:GO2', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872047156_36760936-159e-4996-9a66-b5e8c7124082.png', 13);
INSERT INTO `gameimg` VALUES (48, 'CS:GO2', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872103145_36760936-159e-4996-9a66-b5e8c7124082.png', 14);
INSERT INTO `gameimg` VALUES (49, 'CS:GO2', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872103578_36760936-159e-4996-9a66-b5e8c7124082.png', 14);
INSERT INTO `gameimg` VALUES (50, 'CS:GO2', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872127169_36760936-159e-4996-9a66-b5e8c7124082.png', 15);
INSERT INTO `gameimg` VALUES (51, 'CS:GO2', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872128057_36760936-159e-4996-9a66-b5e8c7124082.png', 15);
INSERT INTO `gameimg` VALUES (52, 'CS:GO2', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872128432_36760936-159e-4996-9a66-b5e8c7124082.png', 15);
INSERT INTO `gameimg` VALUES (53, '绝地求生', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872332774_36760936-159e-4996-9a66-b5e8c7124082.png', 16);
INSERT INTO `gameimg` VALUES (54, '绝地求生', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872333153_36760936-159e-4996-9a66-b5e8c7124082.png', 16);
INSERT INTO `gameimg` VALUES (55, '绝地求生', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872333389_36760936-159e-4996-9a66-b5e8c7124082.png', 16);
INSERT INTO `gameimg` VALUES (56, '绝地求生', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872358708_36760936-159e-4996-9a66-b5e8c7124082.png', 17);
INSERT INTO `gameimg` VALUES (57, '绝地求生', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872358976_36760936-159e-4996-9a66-b5e8c7124082.png', 17);
INSERT INTO `gameimg` VALUES (58, '绝地求生', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872359183_36760936-159e-4996-9a66-b5e8c7124082.png', 17);
INSERT INTO `gameimg` VALUES (59, '绝地求生', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872379272_36760936-159e-4996-9a66-b5e8c7124082.png', 18);
INSERT INTO `gameimg` VALUES (60, '绝地求生', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872379668_36760936-159e-4996-9a66-b5e8c7124082.png', 18);
INSERT INTO `gameimg` VALUES (61, '绝地求生', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872379916_36760936-159e-4996-9a66-b5e8c7124082.png', 18);
INSERT INTO `gameimg` VALUES (62, '无畏契约', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872685434_36760936-159e-4996-9a66-b5e8c7124082.png', 19);
INSERT INTO `gameimg` VALUES (63, '无畏契约', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872685739_36760936-159e-4996-9a66-b5e8c7124082.png', 19);
INSERT INTO `gameimg` VALUES (64, '无畏契约', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872685940_36760936-159e-4996-9a66-b5e8c7124082.png', 19);
INSERT INTO `gameimg` VALUES (65, '无畏契约', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872686110_36760936-159e-4996-9a66-b5e8c7124082.png', 19);
INSERT INTO `gameimg` VALUES (66, '无畏契约', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872717074_36760936-159e-4996-9a66-b5e8c7124082.png', 20);
INSERT INTO `gameimg` VALUES (67, '无畏契约', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872717383_36760936-159e-4996-9a66-b5e8c7124082.png', 20);
INSERT INTO `gameimg` VALUES (68, '无畏契约', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872717580_36760936-159e-4996-9a66-b5e8c7124082.png', 20);
INSERT INTO `gameimg` VALUES (69, '无畏契约', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872717816_36760936-159e-4996-9a66-b5e8c7124082.png', 20);
INSERT INTO `gameimg` VALUES (70, '无畏契约', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872762976_36760936-159e-4996-9a66-b5e8c7124082.png', 21);
INSERT INTO `gameimg` VALUES (71, '无畏契约', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742872763346_36760936-159e-4996-9a66-b5e8c7124082.png', 21);
INSERT INTO `gameimg` VALUES (72, '穿越火线', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742954448550_aae5868f-a425-42e3-a1d1-7152eab17b6d.png', 22);
INSERT INTO `gameimg` VALUES (73, '穿越火线', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742954449207_aae5868f-a425-42e3-a1d1-7152eab17b6d.png', 22);
INSERT INTO `gameimg` VALUES (74, '穿越火线', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742954449718_aae5868f-a425-42e3-a1d1-7152eab17b6d.png', 22);
INSERT INTO `gameimg` VALUES (75, '穿越火线', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1742954450112_aae5868f-a425-42e3-a1d1-7152eab17b6d.png', 22);
INSERT INTO `gameimg` VALUES (76, '星际争霸', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1744094725367_4897ee03-6a61-4995-8d28-5d89644f2c3c.png', 23);
INSERT INTO `gameimg` VALUES (77, '星际争霸', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1744094726121_4897ee03-6a61-4995-8d28-5d89644f2c3c.png', 23);
INSERT INTO `gameimg` VALUES (78, '星际争霸', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1744094726281_4897ee03-6a61-4995-8d28-5d89644f2c3c.png', 23);
INSERT INTO `gameimg` VALUES (83, '原神', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1744190462631_9b8f4e57-57fb-4f5a-8a53-cc8163b7b029.png', 25);
INSERT INTO `gameimg` VALUES (84, '原神', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1744190463371_9b8f4e57-57fb-4f5a-8a53-cc8163b7b029.png', 25);
INSERT INTO `gameimg` VALUES (85, '原神', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1744190463683_9b8f4e57-57fb-4f5a-8a53-cc8163b7b029.png', 25);
INSERT INTO `gameimg` VALUES (86, '原神', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1744190464012_9b8f4e57-57fb-4f5a-8a53-cc8163b7b029.png', 25);
INSERT INTO `gameimg` VALUES (87, '原神', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1744191215529_03e28925-e82a-4f08-8003-7211df973501.png', 26);
INSERT INTO `gameimg` VALUES (88, '原神', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1744191216131_03e28925-e82a-4f08-8003-7211df973501.png', 26);
INSERT INTO `gameimg` VALUES (89, '原神', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1744191216387_03e28925-e82a-4f08-8003-7211df973501.png', 26);
INSERT INTO `gameimg` VALUES (90, '原神', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1744191216685_03e28925-e82a-4f08-8003-7211df973501.png', 26);
INSERT INTO `gameimg` VALUES (91, '三角洲:行动', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1744191370348_03e28925-e82a-4f08-8003-7211df973501.png', 27);
INSERT INTO `gameimg` VALUES (92, '三角洲:行动', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1744191370679_03e28925-e82a-4f08-8003-7211df973501.png', 27);
INSERT INTO `gameimg` VALUES (93, '三角洲:行动', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1744191370892_03e28925-e82a-4f08-8003-7211df973501.png', 27);
INSERT INTO `gameimg` VALUES (94, '黑神话：悟空', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1744191513455_03e28925-e82a-4f08-8003-7211df973501.png', 28);
INSERT INTO `gameimg` VALUES (100, '英雄联盟', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1744943053985_1364255f-d089-4343-998a-6e2ddca00101.png', 30);
INSERT INTO `gameimg` VALUES (101, '英雄联盟', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1744943054227_1364255f-d089-4343-998a-6e2ddca00101.png', 30);
INSERT INTO `gameimg` VALUES (102, '英雄联盟', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1744943054508_1364255f-d089-4343-998a-6e2ddca00101.png', 30);
INSERT INTO `gameimg` VALUES (103, '英雄联盟', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1744943054829_1364255f-d089-4343-998a-6e2ddca00101.png', 30);
INSERT INTO `gameimg` VALUES (104, '英雄联盟', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1744943055139_1364255f-d089-4343-998a-6e2ddca00101.png', 30);
INSERT INTO `gameimg` VALUES (105, 'QQ飞车', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1748069131920_6012d47e-41c3-4de5-8d1a-5ed280daeb66.png', 31);
INSERT INTO `gameimg` VALUES (106, 'QQ飞车', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1748069132882_6012d47e-41c3-4de5-8d1a-5ed280daeb66.png', 31);
INSERT INTO `gameimg` VALUES (107, 'QQ飞车', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1748069133230_6012d47e-41c3-4de5-8d1a-5ed280daeb66.png', 31);
INSERT INTO `gameimg` VALUES (108, 'QQ飞车', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1748069133633_6012d47e-41c3-4de5-8d1a-5ed280daeb66.png', 31);
INSERT INTO `gameimg` VALUES (109, '只狼：影逝二度', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1748069664010_26968024-a55a-4c24-baf5-5eb20218c9d3.png', 32);
INSERT INTO `gameimg` VALUES (110, '只狼：影逝二度', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1748069664896_26968024-a55a-4c24-baf5-5eb20218c9d3.png', 32);
INSERT INTO `gameimg` VALUES (111, '暗区突围', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1749027882441_df67b0de-22b5-4f58-8d08-44dde477ba96.png', 33);
INSERT INTO `gameimg` VALUES (112, '暗区突围', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1749027882999_df67b0de-22b5-4f58-8d08-44dde477ba96.png', 33);
INSERT INTO `gameimg` VALUES (113, '暗区突围', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1749027883238_df67b0de-22b5-4f58-8d08-44dde477ba96.png', 33);
INSERT INTO `gameimg` VALUES (118, '原神', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1749044374971_59c9d362-26b7-4d65-8490-ad89013a3cba.png', 35);
INSERT INTO `gameimg` VALUES (119, '原神', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1749044375725_59c9d362-26b7-4d65-8490-ad89013a3cba.png', 35);
INSERT INTO `gameimg` VALUES (120, '原神', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1749044376072_59c9d362-26b7-4d65-8490-ad89013a3cba.png', 35);
INSERT INTO `gameimg` VALUES (121, '原神', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1749044376357_59c9d362-26b7-4d65-8490-ad89013a3cba.png', 35);
INSERT INTO `gameimg` VALUES (122, '三角洲:行动', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1749044849326_0fa6711b-f6cb-4546-ac3a-4c3e38096ce2.png', 36);
INSERT INTO `gameimg` VALUES (123, '三角洲:行动', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1749044849832_0fa6711b-f6cb-4546-ac3a-4c3e38096ce2.png', 36);
INSERT INTO `gameimg` VALUES (124, '三角洲:行动', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1749045053085_0fa6711b-f6cb-4546-ac3a-4c3e38096ce2.png', 37);
INSERT INTO `gameimg` VALUES (125, '三角洲:行动', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1749045053425_0fa6711b-f6cb-4546-ac3a-4c3e38096ce2.png', 37);
INSERT INTO `gameimg` VALUES (126, '英雄联盟', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1749119228694_e3dbbcbe-cd23-45a8-98c4-854f99df1675.png', 38);
INSERT INTO `gameimg` VALUES (127, '英雄联盟', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1749119229469_e3dbbcbe-cd23-45a8-98c4-854f99df1675.png', 38);
INSERT INTO `gameimg` VALUES (128, '英雄联盟', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/gameImg/1749119229877_e3dbbcbe-cd23-45a8-98c4-854f99df1675.png', 38);

-- ----------------------------
-- Table structure for games
-- ----------------------------
DROP TABLE IF EXISTS `games`;
CREATE TABLE `games`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `game_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '游戏名字',
  `game_character_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '游戏人物名称',
  `game_tag` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '游戏标签',
  `gamelv` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '等级',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文章标题',
  `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文章内容',
  `username` bigint NULL DEFAULT NULL,
  `games_status` int NULL DEFAULT NULL,
  `money` double NOT NULL DEFAULT 3 COMMENT '每小时租金',
  `uname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账号',
  `pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `game_show` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否展示在主页',
  `order_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_games_user`(`username` ASC) USING BTREE,
  CONSTRAINT `fk_games_user` FOREIGN KEY (`username`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `games_chk_1` CHECK (`game_show` in (_utf8mb4'T',_utf8mb4'F'))
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of games
-- ----------------------------
INSERT INTO `games` VALUES (1, '穿越火线', '白斩鸡神', '测试2', '81', '【允许排位】炽芒蝶刃6盘6烈蝴蝶✅传说影煞破晓圣灵光耀辉光✅竞技荣光王者星霆白虎蔷薇COP雷霆9音效卡', '[{主武器,M4A1-死神，AK47-麒麟，M4A1-死神，AK47-麒麟，M4A1-死神，AK47-麒麟，M4A1-死神，AK47-麒麟，M4A1-死神，AK47-麒麟，M4A1-死神，AK47-麒麟，M4A1-死神，AK47-麒麟，},{副武器,柯尔特-竞技，USP-神工天},]', 8, 1, 6, '1234567890', '0987654321', 'T', '2025-03-27 14:28:44');
INSERT INTO `games` VALUES (2, 'CS:GO2', '强丶非常强', '测试2', '253', '可5E授信✅蝴蝶刀⭐狂牙手套⭐官匹冷却⭐女火神之炽焰⭐阿努比斯⭐炎龙之焰⭐二西莫夫⭐大量皮肤', '[{主武器皮肤,巨龙，巨龙，巨龙，巨龙，巨龙，巨龙，巨龙，},{副武器皮肤,不知道，不知道，不知道，不知道，不知道，不知道，不知道，不知道，不知道，不知道，},]', 8, 1, 100, '234567890', '098765432', 'T', '2025-03-27 14:28:44');
INSERT INTO `games` VALUES (4, '无畏契约', '哈基米曼波', '测试2', '328', ' 【当前段位：黄金II】6w库存/离火刃/显卡爪/威龙刃/奇点2.0蝴蝶刀/剥皮小刀/混沌狂徒/盖亚狂徒/24冠军幻影/紫金', '[{随身武器,合金战车 正义，合金战车 正义，合金战车 正义，合金战车 正义，合金战车 正义，合金战车 正义，合金战车 正义，合金战车 正义，合金战车 正义，合金战车 正义，合金战车 正义，合金战车 正义，合金战车 正义，合金战车 正义，合金战车 正义，},{冲锋枪,合金战车 蜂刺，合金战车 蜂刺，合金战车 蜂刺，合金战车 蜂刺，合金战车 蜂刺，合金战车 蜂刺，合金战车 蜂刺，合金战车 蜂刺，合金战车 蜂刺，合金战车 蜂刺，合金战车 蜂刺，合金战车 蜂刺，合金战车 蜂刺，合金战车 蜂刺，合金战车 蜂刺，合金战车 蜂刺，合金战车 蜂刺，合金战车 蜂刺，合金战车 蜂刺，},]', 8, 1, 200, '135790', '0987654321', 'T', '2025-03-27 14:28:44');
INSERT INTO `games` VALUES (5, '无畏契约', '抹茶小杰酱#55', '测试2', '100', '【当前段位：铂金I】显卡爪21冠军套恶魔套掠影套威龙套奇点2套洪荒混沌双城弃王盖亚鱼缸狮子猴子AK侦查24冠军幻影', '[{随身武器,钢轨卫士 短，钢轨卫士 短，钢轨卫士 短，钢轨卫士 短，钢轨卫士 短，钢轨卫士 短，钢轨卫士 短，钢轨卫士 短，},{冲锋枪,天界神兵 蜂，天界神兵 蜂，天界神兵 蜂，天界神兵 蜂，天界神兵 蜂，天界神兵 蜂，天界神兵 蜂，},]', 8, 1, 999, '001199228833', '1234567890', 'T', '2025-03-27 14:28:44');
INSERT INTO `games` VALUES (6, '绝地求生', '天下第一', '测试2', '41', ' Y41✨黑猫✨小兔套装-朱莉套装【三成长型武器-恶魔ACE32-无信号M416-午夜惊魂98K】', '[{主武器,打算大苏打，打算大苏打，打算大苏打，打算大苏打，打算大苏打，打算大苏打，打算大苏打，打算大苏打，打算大苏打，},{副武器,啊撒大苏打实打实的，啊撒大苏打实打实的，啊撒大苏打实打实的，啊撒大苏打实打实的，啊撒大苏打实打实的，啊撒大苏打实打实的，啊撒大苏打实打实的，},]', 8, 1, 5, 'aaaaaaa', '1234567890', 'T', '2025-03-27 14:28:44');
INSERT INTO `games` VALUES (7, '穿越火线', '鱼子酱', '测试2', ' 102', '【允许排位】天花板序列7777⑩传说+灵眸影煞艾莉莎✨G36C宠儿冠幻⑦皮幻神星神音效✨炽芒蝶刃冠特⑥COP雷暴', '[{主武器,M200-幻神，传说灵眸，Kar98K-星神，汤姆逊-烈龙，斯泰尔-蝴蝶，王者火魄-觉，传说圣灵，黑骑士-海王，M200-幻神，传说灵眸，Kar98K-星神，汤姆逊-烈龙，斯泰尔-蝴蝶，王者火魄-觉，传说圣灵，黑骑士-海王，M200-幻神，传说灵眸，Kar98K-星神，汤姆逊-烈龙，斯泰尔-蝴蝶，王者火魄-觉，传说圣灵，黑骑士-海王，M200-幻神，传说灵眸，Kar98K-星神，汤姆逊-烈龙，斯泰尔-蝴蝶，王者火魄-觉，传说圣灵，黑骑士-海王，M200-幻神，传说灵眸，Kar98K-星神，汤姆逊-烈龙，斯泰尔-蝴蝶，王者火魄-觉，传说圣灵，黑骑士-海王，},{副武器,传说破晓，柯尔特-竞技，COP-堕天神x3，沙鹰-修罗，柯尔特-萌杀，传说破晓，柯尔特-竞技，COP-堕天神x3，沙鹰-修罗，柯尔特-萌杀，传说破晓，柯尔特-竞技，COP-堕天神x3，沙鹰-修罗，柯尔特-萌杀，传说破晓，柯尔特-竞技，COP-堕天神x3，沙鹰-修罗，柯尔特-萌杀，传说破晓，柯尔特-竞技，COP-堕天神x3，沙鹰-修罗，柯尔特-萌杀，传说破晓，柯尔特-竞技，COP-堕天神x3，沙鹰-修罗，柯尔特-萌杀，},{近身武器,麒麟刺，龙啸，破天斩魔剑，斩神刀，荣耀世冠-，龙啸-烈火龙，麒麟刺，龙啸，破天斩魔剑，斩神刀，荣耀世冠-，龙啸-烈火龙，麒麟刺，龙啸，破天斩魔剑，斩神刀，荣耀世冠-，龙啸-烈火龙，麒麟刺，龙啸，破天斩魔剑，斩神刀，荣耀世冠-，龙啸-烈火龙，麒麟刺，龙啸，破天斩魔剑，斩神刀，荣耀世冠-，龙啸-烈火龙，麒麟刺，龙啸，破天斩魔剑，斩神刀，荣耀世冠-，龙啸-烈火龙，},{投掷武器,烟雾弹-神工，闪光弹-雷暴，烟雾弹-幻灵，闪光弹-夜魔，恶棍-电竞，烟雾弹-神工，闪光弹-雷暴，烟雾弹-幻灵，闪光弹-夜魔，恶棍-电竞，烟雾弹-神工，闪光弹-雷暴，烟雾弹-幻灵，闪光弹-夜魔，恶棍-电竞，烟雾弹-神工，闪光弹-雷暴，烟雾弹-幻灵，闪光弹-夜魔，恶棍-电竞，烟雾弹-神工，闪光弹-雷暴，烟雾弹-幻灵，闪光弹-夜魔，恶棍-电竞，烟雾弹-神工，闪光弹-雷暴，烟雾弹-幻灵，闪光弹-夜魔，恶棍-电竞，},{道具,手枪弹匣，猫耳头盔，超级护目镜，M200-幻神-，超级背包，雷神-化蛇，天启-暗月，手枪弹匣，猫耳头盔，超级护目镜，M200-幻神-，超级背包，雷神-化蛇，天启-暗月，手枪弹匣，猫耳头盔，超级护目镜，M200-幻神-，超级背包，雷神-化蛇，天启-暗月，手枪弹匣，猫耳头盔，超级护目镜，M200-幻神-，超级背包，雷神-化蛇，天启-暗月，},]', 3, 1, 3, 'asbjfdknlv', '123456789009887', 'T', '2025-03-27 14:28:44');
INSERT INTO `games` VALUES (9, '绝地求生', '天下第一强', '测试2', '42', ' Y42✨黑猫✨龙之女皇套装-反光夹克【三成长型武器-恶魔ACE32-无信号M416-午夜惊魂98', '[{道具,黑猫，龙之女皇套装-反光夹克，三成长型武器-恶魔ACE32-无信号M416-午夜惊魂98K，孙悟空AKM},]', 9, 1, 99, 'dfgvlsduy', '1234567890', 'T', '2025-03-27 14:28:44');
INSERT INTO `games` VALUES (10, '穿越火线', '喜欢点个收藏', '测试2', ' 500', '【允许排位】🔥传说影煞双蝴蝶刀6盘6烈幻神七皮带卡/星神三件套/满副手 G36C幻影 冠军之特/刃/薇/星/虎', '[{主武器,M200-幻神，Kar98K-星神，Scar Light-白，RPK-盘龙，HK417-朱雀，王者霜心，黑武士-锦绣},{副武器,MAC-10-，柯尔特-萌杀，USP-雷暴，沙鹰-修罗，沙鹰-天神，雷霆之契，MAC-10-，柯尔特-萌杀，USP-雷暴，沙鹰-修罗，沙鹰-天神，雷霆之契，MAC-10-，柯尔特-萌杀，USP-雷暴，沙鹰-修罗，沙鹰-天神，雷霆之契，MAC-10-，柯尔特-萌杀，USP-雷暴，沙鹰-修罗，沙鹰-天神，雷霆之契，MAC-10-，柯尔特-萌杀，USP-雷暴，沙鹰-修罗，沙鹰-天神，雷霆之契，MAC-10-，柯尔特-萌杀，USP-雷暴，沙鹰-修罗，沙鹰-天神，雷霆之契，},{近身武器,炽芒蝶刃，麒麟刺，雷霆之锋，斩神刀，擎天，屠龙，迷你牛牛，云龙扇，炽芒蝶刃，麒麟刺，雷霆之锋，斩神刀，擎天，屠龙，迷你牛牛，云龙扇，炽芒蝶刃，麒麟刺，雷霆之锋，斩神刀，擎天，屠龙，迷你牛牛，云龙扇，炽芒蝶刃，麒麟刺，雷霆之锋，斩神刀，擎天，屠龙，迷你牛牛，云龙扇，炽芒蝶刃，麒麟刺，雷霆之锋，斩神刀，擎天，屠龙，迷你牛牛，云龙扇，炽芒蝶刃，麒麟刺，雷霆之锋，斩神刀，擎天，屠龙，迷你牛牛，云龙扇，},{投掷武器,迷你生化手雷，闪光弹-雷霆，高爆手雷，闪光弹-幻灵，迷你生化手雷，闪光弹-雷霆，高爆手雷，闪光弹-幻灵，迷你生化手雷，闪光弹-雷霆，高爆手雷，闪光弹-幻灵，迷你生化手雷，闪光弹-雷霆，高爆手雷，闪光弹-幻灵，迷你生化手雷，闪光弹-雷霆，高爆手雷，闪光弹-幻灵，迷你生化手雷，闪光弹-雷霆，高爆手雷，闪光弹-幻灵，},{道具,C4-红龙，超级护目镜，雷神-传奇N9，雷神-荣耀之，夺命之镰，C4-红龙，超级护目镜，雷神-传奇N9，雷神-荣耀之，夺命之镰，C4-红龙，超级护目镜，雷神-传奇N9，雷神-荣耀之，夺命之镰，C4-红龙，超级护目镜，雷神-传奇N9，雷神-荣耀之，夺命之镰，C4-红龙，超级护目镜，雷神-传奇N9，雷神-荣耀之，夺命之镰，C4-红龙，超级护目镜，雷神-传奇N9，雷神-荣耀之，夺命之镰，},]', 8, 1, 1, 'ajisodhj', '12345678909', 'T', '2025-03-27 14:28:44');
INSERT INTO `games` VALUES (11, '穿越火线', '爱卡卡', '测试2', '13', '【允许排位】允许打字，女帝炽芒蝶刃g36c幻影王者风神星神2幻神冠军皮白鲨皮音效qbz音效皮6烈蝴蝶满配副武器', '[{主武器,6盘6烈，6盘6烈，6盘6烈，6盘6烈，6盘6烈，6盘6烈，6盘6烈，6盘6烈，6盘6烈，6盘6烈，6盘6烈，6盘6烈，6盘6烈，6盘6烈，6盘6烈，6盘6烈，6盘6烈，6盘6烈，},{副武器,柯尔特-萌杀，柯尔特-萌杀，柯尔特-萌杀，柯尔特-萌杀，柯尔特-萌杀，柯尔特-萌杀，柯尔特-萌杀，柯尔特-萌杀，柯尔特-萌杀，柯尔特-萌杀，柯尔特-萌杀，柯尔特-萌杀，柯尔特-萌杀，柯尔特-萌杀，柯尔特-萌杀，},{刀,炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，},{道具,闪光弹-雷霆，闪光弹-雷霆，闪光弹-雷霆，闪光弹-雷霆，闪光弹-雷霆，闪光弹-雷霆，闪光弹-雷霆，闪光弹-雷霆，闪光弹-雷霆，闪光弹-雷霆，闪光弹-雷霆，闪光弹-雷霆，闪光弹-雷霆，闪光弹-雷霆，闪光弹-雷霆，},]', 8, 1, 3, '1323123', '123123123', 'T', '2025-03-27 14:28:44');
INSERT INTO `games` VALUES (12, '穿越火线', '爱卡卡', '测试2', '13', '【允许排位】允许打字，女帝炽芒蝶刃g36c幻影王者风神星神2幻神冠军皮白鲨皮音效qbz音效皮6烈蝴蝶满配副武器', '[{主武器,6盘6烈，6盘6烈，6盘6烈，6盘6烈，6盘6烈，6盘6烈，6盘6烈，6盘6烈，6盘6烈，6盘6烈，6盘6烈，6盘6烈，6盘6烈，6盘6烈，6盘6烈，6盘6烈，6盘6烈，6盘6烈，},{副武器,柯尔特-萌杀，柯尔特-萌杀，柯尔特-萌杀，柯尔特-萌杀，柯尔特-萌杀，柯尔特-萌杀，柯尔特-萌杀，柯尔特-萌杀，柯尔特-萌杀，柯尔特-萌杀，柯尔特-萌杀，柯尔特-萌杀，柯尔特-萌杀，柯尔特-萌杀，柯尔特-萌杀，},{刀,炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，炽芒蝶刃，},{道具,闪光弹-雷霆，闪光弹-雷霆，闪光弹-雷霆，闪光弹-雷霆，闪光弹-雷霆，闪光弹-雷霆，闪光弹-雷霆，闪光弹-雷霆，闪光弹-雷霆，闪光弹-雷霆，闪光弹-雷霆，闪光弹-雷霆，闪光弹-雷霆，闪光弹-雷霆，闪光弹-雷霆，},]', 8, 1, 3, 'asdasda', '123123123', 'T', '2025-03-27 14:28:44');
INSERT INTO `games` VALUES (13, 'CS:GO2', 'gg', '测试2', '456', ' 可完美5E✅蝴蝶刀⭐狂牙手套⭐官匹冷却⭐女火神之炽焰⭐霓虹革命⭐炎龙之焰⭐二西莫夫⭐大量皮肤', '[{描述,可完美5E✅蝴蝶刀⭐狂牙手套⭐可优先⭐女火神之炽焰⭐霓虹革命⭐炎龙之焰⭐二西莫夫⭐大量皮肤\n\n},]', 8, 1, 3, '1213544567', 'asdasdasdasd', 'T', '2025-03-27 14:28:44');
INSERT INTO `games` VALUES (14, 'CS:GO2', 'gg', '测试2', '456', ' 可完美5E✅蝴蝶刀⭐狂牙手套⭐官匹冷却⭐女火神之炽焰⭐霓虹革命⭐炎龙之焰⭐二西莫夫⭐大量皮肤', '[{描述,可完美5E✅蝴蝶刀⭐狂牙手套⭐可优先⭐女火神之炽焰⭐霓虹革命⭐炎龙之焰⭐二西莫夫⭐大量皮肤\n\n},]', 8, 1, 3, 'sdafasdf', 'asdasdasdasd', 'T', '2025-03-27 14:28:44');
INSERT INTO `games` VALUES (15, 'CS:GO2', 'gg', '测试2', '456', ' 可完美5E✅蝴蝶刀⭐狂牙手套⭐官匹冷却⭐女火神之炽焰⭐霓虹革命⭐炎龙之焰⭐二西莫夫⭐大量皮肤', '[{描述,可完美5E✅蝴蝶刀⭐狂牙手套⭐可优先⭐女火神之炽焰⭐霓虹革命⭐炎龙之焰⭐二西莫夫⭐大量皮肤\n\n},]', 8, 1, 3, '9458568', 'asdasdasdasd', 'T', '2025-03-27 14:28:44');
INSERT INTO `games` VALUES (16, '绝地求生', '小调皮', '测试2', '1000', '100%可排位⭐女团大厅⭐4级无信号M4⭐1级神器陶洛斯⭐金色成就762⭐彩糖M4⭐黑鬼⭐疯狂M24', '[{描述,严禁 ⭐个人家庭用户⭐商户⭐下单 ，后果自负！！！ 只支持⭐网芭用户⭐！！！ 严禁淘汰队友‼️‼️‼️♥喜欢请收藏♥账号库存价值巨大，已在公安部门做大额虚拟资产备案，开挂，淘汰队友分解皮肤等行为报案处理。上号器每隔1分钟屏幕截图一次，我这边也可以查看实时截图，你开过什么软件，有氵殳有分解皮肤，截图上很清楚，为了开挂和打队友爽一下进局子真氵殳必要，网络不是法外之地，你的IP 实名 手机号我们开商户的都有淘汰队友以及开挂打电话给你家人问候问候，请各位租客文明租号，共创美好市场环境},]', 8, 1, 4, 'lsidufhvgsu', '123124213423', 'T', '2025-03-27 14:28:44');
INSERT INTO `games` VALUES (17, '绝地求生', '小调皮', '测试2', '1000', '100%可排位⭐女团大厅⭐4级无信号M4⭐1级神器陶洛斯⭐金色成就762⭐彩糖M4⭐黑鬼⭐疯狂M24', '[{描述,严禁 ⭐个人家庭用户⭐商户⭐下单 ，后果自负！！！ 只支持⭐网芭用户⭐！！！ 严禁淘汰队友‼️‼️‼️♥喜欢请收藏♥账号库存价值巨大，已在公安部门做大额虚拟资产备案，开挂，淘汰队友分解皮肤等行为报案处理。上号器每隔1分钟屏幕截图一次，我这边也可以查看实时截图，你开过什么软件，有氵殳有分解皮肤，截图上很清楚，为了开挂和打队友爽一下进局子真氵殳必要，网络不是法外之地，你的IP 实名 手机号我们开商户的都有淘汰队友以及开挂打电话给你家人问候问候，请各位租客文明租号，共创美好市场环境},]', 8, 1, 4, 'hioughoi12431', '123124213423', 'T', '2025-03-27 14:28:44');
INSERT INTO `games` VALUES (18, '绝地求生', '小调皮', '测试2', '1000', '100%可排位⭐女团大厅⭐4级无信号M4⭐1级神器陶洛斯⭐金色成就762⭐彩糖M4⭐黑鬼⭐疯狂M24', '[{描述,严禁 ⭐个人家庭用户⭐商户⭐下单 ，后果自负！！！ 只支持⭐网芭用户⭐！！！ 严禁淘汰队友‼️‼️‼️♥喜欢请收藏♥账号库存价值巨大，已在公安部门做大额虚拟资产备案，开挂，淘汰队友分解皮肤等行为报案处理。上号器每隔1分钟屏幕截图一次，我这边也可以查看实时截图，你开过什么软件，有氵殳有分解皮肤，截图上很清楚，为了开挂和打队友爽一下进局子真氵殳必要，网络不是法外之地，你的IP 实名 手机号我们开商户的都有淘汰队友以及开挂打电话给你家人问候问候，请各位租客文明租号，共创美好市场环境},]', 8, 1, 4, '5678aosolidas', '123124213423', 'T', '2025-03-27 14:28:44');
INSERT INTO `games` VALUES (19, '无畏契约', '失败尽常态', '测试2', ' 222', '【当前段位：铂金I】可排，紫金爪，显卡爪，剥皮，龙刀，混沌紫金弃王掠夺娘炮龙ak，奇点正义鬼魅，天界侦察假面幻影24标配', '[{英雄,奇乐，黑梦，星礈，斯凯，奇乐，黑梦，星礈，斯凯，奇乐，黑梦，星礈，斯凯，奇乐，黑梦，星礈，斯凯，奇乐，黑梦，星礈，斯凯，},{随身武器,艺高胆大 ，珍珠棘贝 ，星云暗涌，艺高胆大 ，珍珠棘贝 ，星云暗涌，艺高胆大 ，珍珠棘贝 ，星云暗涌，艺高胆大 ，珍珠棘贝 ，星云暗涌，艺高胆大 ，珍珠棘贝 ，星云暗涌，艺高胆大 ，珍珠棘贝 ，星云暗涌，艺高胆大 ，珍珠棘贝 ，星云暗涌，},{冲锋枪,深海水族 蜂，战术小伙伴，流星兵团 骇，深海水族 蜂，战术小伙伴，流星兵团 骇，深海水族 蜂，战术小伙伴，流星兵团 骇，深海水族 蜂，战术小伙伴，流星兵团 骇，深海水族 蜂，战术小伙伴，流星兵团 骇，深海水族 蜂，战术小伙伴，流星兵团 骇，深海水族 蜂，战术小伙伴，流星兵团 骇，深海水族 蜂，战术小伙伴，流星兵团 骇，},{霰弹枪,模拟赫兹 ，复古浪潮 ，萌彩剪影，纳米打击 判，},{狙击步枪,纤云弄巧 ，荒土机兵 冥，模拟赫兹 ，纤云弄巧 ，荒土机兵 冥，模拟赫兹 ，纤云弄巧 ，荒土机兵 冥，模拟赫兹 ，纤云弄巧 ，荒土机兵 冥，模拟赫兹 ，纤云弄巧 ，荒土机兵 冥，模拟赫兹 ，},{近战武器,流星兵团 短，RGX 11z Pr，紫金爪刀，剥皮小刀，流星兵团 短，RGX 11z Pr，紫金爪刀，剥皮小刀，流星兵团 短，RGX 11z Pr，紫金爪刀，剥皮小刀，流星兵团 短，RGX 11z Pr，紫金爪刀，剥皮小刀，流星兵团 短，RGX 11z Pr，紫金爪刀，剥皮小刀，流星兵团 短，RGX 11z Pr，紫金爪刀，剥皮小刀，流星兵团 短，RGX 11z Pr，紫金爪刀，剥皮小刀，},]', 9, 1, 99, '098indsfvuisd', '1243213432', 'T', '2025-03-27 14:28:44');
INSERT INTO `games` VALUES (20, '无畏契约', '失败尽常态', '测试2', ' 222', '【当前段位：铂金I】可排，紫金爪，显卡爪，剥皮，龙刀，混沌紫金弃王掠夺娘炮龙ak，奇点正义鬼魅，天界侦察假面幻影24标配', '[{英雄,奇乐，黑梦，星礈，斯凯，奇乐，黑梦，星礈，斯凯，奇乐，黑梦，星礈，斯凯，奇乐，黑梦，星礈，斯凯，奇乐，黑梦，星礈，斯凯，},{随身武器,艺高胆大 ，珍珠棘贝 ，星云暗涌，艺高胆大 ，珍珠棘贝 ，星云暗涌，艺高胆大 ，珍珠棘贝 ，星云暗涌，艺高胆大 ，珍珠棘贝 ，星云暗涌，艺高胆大 ，珍珠棘贝 ，星云暗涌，艺高胆大 ，珍珠棘贝 ，星云暗涌，艺高胆大 ，珍珠棘贝 ，星云暗涌，},{冲锋枪,深海水族 蜂，战术小伙伴，流星兵团 骇，深海水族 蜂，战术小伙伴，流星兵团 骇，深海水族 蜂，战术小伙伴，流星兵团 骇，深海水族 蜂，战术小伙伴，流星兵团 骇，深海水族 蜂，战术小伙伴，流星兵团 骇，深海水族 蜂，战术小伙伴，流星兵团 骇，深海水族 蜂，战术小伙伴，流星兵团 骇，深海水族 蜂，战术小伙伴，流星兵团 骇，},{霰弹枪,模拟赫兹 ，复古浪潮 ，萌彩剪影，纳米打击 判，},{狙击步枪,纤云弄巧 ，荒土机兵 冥，模拟赫兹 ，纤云弄巧 ，荒土机兵 冥，模拟赫兹 ，纤云弄巧 ，荒土机兵 冥，模拟赫兹 ，纤云弄巧 ，荒土机兵 冥，模拟赫兹 ，纤云弄巧 ，荒土机兵 冥，模拟赫兹 ，},{近战武器,流星兵团 短，RGX 11z Pr，紫金爪刀，剥皮小刀，流星兵团 短，RGX 11z Pr，紫金爪刀，剥皮小刀，流星兵团 短，RGX 11z Pr，紫金爪刀，剥皮小刀，流星兵团 短，RGX 11z Pr，紫金爪刀，剥皮小刀，流星兵团 短，RGX 11z Pr，紫金爪刀，剥皮小刀，流星兵团 短，RGX 11z Pr，紫金爪刀，剥皮小刀，流星兵团 短，RGX 11z Pr，紫金爪刀，剥皮小刀，},]', 9, 1, 90, 'juh988976', '1243213432', 'T', '2025-03-27 14:28:44');
INSERT INTO `games` VALUES (21, '无畏契约', '失败尽常态', '测试2', ' 222', '【当前段位：铂金I】可排，紫金爪，显卡爪，剥皮，龙刀，混沌紫金弃王掠夺娘炮龙ak，奇点正义鬼魅，天界侦察假面幻影24标配', '[{英雄,奇乐，黑梦，星礈，斯凯，奇乐，黑梦，星礈，斯凯，奇乐，黑梦，星礈，斯凯，奇乐，黑梦，星礈，斯凯，奇乐，黑梦，星礈，斯凯，},{随身武器,艺高胆大 ，珍珠棘贝 ，星云暗涌，艺高胆大 ，珍珠棘贝 ，星云暗涌，艺高胆大 ，珍珠棘贝 ，星云暗涌，艺高胆大 ，珍珠棘贝 ，星云暗涌，艺高胆大 ，珍珠棘贝 ，星云暗涌，艺高胆大 ，珍珠棘贝 ，星云暗涌，艺高胆大 ，珍珠棘贝 ，星云暗涌，},{冲锋枪,深海水族 蜂，战术小伙伴，流星兵团 骇，深海水族 蜂，战术小伙伴，流星兵团 骇，深海水族 蜂，战术小伙伴，流星兵团 骇，深海水族 蜂，战术小伙伴，流星兵团 骇，深海水族 蜂，战术小伙伴，流星兵团 骇，深海水族 蜂，战术小伙伴，流星兵团 骇，深海水族 蜂，战术小伙伴，流星兵团 骇，深海水族 蜂，战术小伙伴，流星兵团 骇，},{霰弹枪,模拟赫兹 ，复古浪潮 ，萌彩剪影，纳米打击 判，},{狙击步枪,纤云弄巧 ，荒土机兵 冥，模拟赫兹 ，纤云弄巧 ，荒土机兵 冥，模拟赫兹 ，纤云弄巧 ，荒土机兵 冥，模拟赫兹 ，纤云弄巧 ，荒土机兵 冥，模拟赫兹 ，纤云弄巧 ，荒土机兵 冥，模拟赫兹 ，},{近战武器,流星兵团 短，RGX 11z Pr，紫金爪刀，剥皮小刀，流星兵团 短，RGX 11z Pr，紫金爪刀，剥皮小刀，流星兵团 短，RGX 11z Pr，紫金爪刀，剥皮小刀，流星兵团 短，RGX 11z Pr，紫金爪刀，剥皮小刀，流星兵团 短，RGX 11z Pr，紫金爪刀，剥皮小刀，流星兵团 短，RGX 11z Pr，紫金爪刀，剥皮小刀，流星兵团 短，RGX 11z Pr，紫金爪刀，剥皮小刀，},]', 9, 1, 90, '123123', '1243213432', 'T', '2025-03-27 14:28:44');
INSERT INTO `games` VALUES (22, '穿越火线', ' 闭嘴吧你ω', '测试2', '102', '【允许排位】允许打字，4传说，女帝幻神音效皮白虎音效皮qbz音效皮王者星神2冠特1匕首副武器满配6烈6盘蝴蝶炼狱', '[{主武器,M200-幻神，传说暗裔，M200-幻神，传说暗裔，M200-幻神，传说暗裔，M200-幻神，传说暗裔，M200-幻神，传说暗裔，M200-幻神，传说暗裔，},{副武器,沙鹰-修罗，沙鹰-修罗，沙鹰-修罗，沙鹰-修罗，沙鹰-修罗，沙鹰-修罗，沙鹰-修罗，沙鹰-修罗，沙鹰-修罗，},]', 8, 1, 4, 'sasdf1864', '1234567890', NULL, '2025-03-27 14:28:44');
INSERT INTO `games` VALUES (23, '星际争霸', 'sdas', '合作', '90', '魔兽争霸标题', '[{道具,魔兽争霸，魔兽争霸，魔兽争霸，魔兽争霸，魔兽争霸，魔兽争霸，魔兽争霸，魔兽争霸，魔兽争霸，魔兽争霸，魔兽争霸，},]', 10, 1, 3, 'kaisdasd', '2143efdv23', NULL, '2025-04-08 14:45:25');
INSERT INTO `games` VALUES (25, '原神', '唧唧歪歪', '探索', '满命满精雷神🌪️满命基尼奇二命芙宁娜🐉一命龙王二命夜兰', '满命满精雷神🌪️满命基尼奇二命芙宁娜🐉一命龙王二命夜兰', '[{详细,满命满精雷神，满命基尼奇二命芙宁娜，一命龙王二命夜兰，},]', 10, 1, 7, 'fasdfasdf', 'fasdfasdfasdf', NULL, '2025-04-09 17:21:02');
INSERT INTO `games` VALUES (26, '原神', '原神最好玩', '探索', ' 60', '360金27满命😶满命娜维娅🌨️满命芙宁娜🌊满命那维莱特满命夜阑满命雷神2+1希诺宁', '[{详细,360金27满命，满命娜维娅，满命芙宁娜，满命那维莱特满命夜阑满命雷神2+1希诺宁},]', 10, 0, 10, 'dsfadghjk', 'FASDFASDFGVSADGF', NULL, '2025-04-09 17:33:35');
INSERT INTO `games` VALUES (27, '三角洲:行动', '三角洲霸主', '射击', '30', '400W纯币░P90(18W）░四甲四头五甲░金弹挂░总资产4.7M 祝老板绝密爽沙', '[{详细,400W纯币},]', 10, 1, 10, 'asfdasdg', 'dfsafasgasdgf', NULL, '2025-04-09 17:36:10');
INSERT INTO `games` VALUES (28, '黑神话：悟空', '齐天大圣', '动作', '100', '100%上号🔥黑神话悟空🔥wegame豪华版🔥全DLC🔥到时不下线', '[{详细描述,黑神话·悟空🔥wegame328豪华版🔥全DLC🔥国产3A大作🔥到时不下线🔥,},]', 3, 1, 10, 'sadgfsefgb', 'asdfasdfasdgf', NULL, '2025-04-09 17:38:33');
INSERT INTO `games` VALUES (30, '英雄联盟', ' 2025一切顺利', '合作', '2760', '🌟5000皮130神话🌟圣堂金克丝🌟莲花套🌟冠军全套🌟冰原女警🌟KDA套🌟心之钢套🌟', '[{英雄与皮肤,暗黑元首，冰晶凤凰，亡灵战神，未来守护者，瘟疫之源，无极剑圣，方块王后 辛德拉，刀锋女王 丽桑卓，暗黑元首，冰晶凤凰，亡灵战神，未来守护者，瘟疫之源，无极剑圣，方块王后 辛德拉，刀锋女王 丽桑卓，暗黑元首，冰晶凤凰，亡灵战神，未来守护者，瘟疫之源，无极剑圣，方块王后 辛德拉，刀锋女王 丽桑卓，暗黑元首，冰晶凤凰，亡灵战神，未来守护者，瘟疫之源，无极剑圣，方块王后 辛德拉，刀锋女王 丽桑卓，},]', 12, 1, 3, 'dasdasdasdwqeq', 'qwerqwerqwer', NULL, '2025-04-18 10:24:13');
INSERT INTO `games` VALUES (31, 'QQ飞车', '夏娃', '竞技', ' 201', '龙玉双年限⭐影龙烛龙⭐玉麒麟⭐极光10阶⭐乾坤10阶⭐爆天甲刘备⭐风之使者兰⭐众神吕布', '[{T3机甲,创世之神 x 1，圣域大天使 x 1，终极幻影 x 1，六道神尊，},{T2机甲,幻音兔女郎 x 1，白龙马 x 1，暗黑牛魔王 x 1，黄金射手座 x 1，镇魔天王 x 1},{S车,至尊-神·奥丁 x 1，暗影龙骑 x 1，至尊-神·奥丁 x 1，暗影龙骑 x 1，至尊-神·奥丁 x 1，暗影龙骑 x 1，至尊-神·奥丁 x 1，暗影龙骑 x 1，至尊-神·奥丁 x 1，暗影龙骑 x 1，至尊-神·奥丁 x 1，暗影龙骑 x 1，},{座椅,双人座椅 深念相思座椅 x 1，极品双人座椅 樱之魔法阵 x 1，极品四人座椅 银魂猎鹰 x 1，双人座椅 深念相思座椅 x 1，极品双人座椅 樱之魔法阵 x 1，极品四人座椅 银魂猎鹰 x 1，},]', 10, 0, 3, 'abishdfiahg', 'jhasdfkhsklhdflk', NULL, '2025-05-24 14:45:31');
INSERT INTO `games` VALUES (32, '只狼：影逝二度', '只狼-1', '动作', '1', '《只狼：影逝二度》终极版+预购奖励！顶级体验！', '[{详细,进入由打造了《黑暗之魂》系列的知名开发商FromSoftware倾力制作的全新冒险，用智慧和力量斩开复仇之路。 决死复仇，夺回荣誉，智杀强敌。},]', 3, 1, 10, '3v45n790t', '5v279n08 3', NULL, '2025-05-24 14:54:24');
INSERT INTO `games` VALUES (33, '暗区突围', '非常强', '射击', '88', '暗区突围无限端游租号，上号随便造能不能造完就看你本事了', '[{详细描述,30级打任何模式，仓库总价值52M，号上还有一千多万科恩币},]', 14, 1, 3, '1341234', 'dafadfasdfas', NULL, '2025-06-04 17:04:42');
INSERT INTO `games` VALUES (35, '原神', '回收', '探索', '90', '满命满精阿蕾奇诺/满命仆人🔥满命夜兰满命达达利亚💨二命茜特菈莉', '[{描述,满命满精阿蕾奇诺/满命仆人🔥满命夜兰满命达达利亚💨二命茜特菈莉，},]', 14, 0, 3, 'zaqwsxcde', 'qazxswedc', NULL, '2025-06-04 21:39:34');
INSERT INTO `games` VALUES (36, '三角洲:行动', '捡垃圾', '射击', '12', '推荐包天✅满12级✅可打机密大坝✅可打长弓✅可加好友✅开黑已过新手✅金币随便用', '[{描述,推荐包天✅满12级✅可打机密大坝✅可打长弓✅可加好友✅开黑已过新手✅金币随便用},]', 14, 0, 8, 'zxcdewq', 'zxcdewqa', NULL, '2025-06-04 21:47:29');
INSERT INTO `games` VALUES (37, '三角洲:行动', '无名', '射击', '10', '41级6000W纯币 4级体 低DK 带妹护航首选，不撤单 租完可下架 时间不限', '[{描述,推荐包天✅满12级✅可打机密大坝✅可打长弓✅可加好友✅开黑已过新手✅金币随便用},]', 14, 0, 12, 'nkoijb', 'pl,mkoijn', NULL, '2025-06-04 21:50:53');
INSERT INTO `games` VALUES (38, '英雄联盟', '1212', '合作', '212', '22h1hiuhihd', '[{xaing,111111，2222222},]', 15, 0, 7, 'mkoijn', 'ijnubjijdia', NULL, '2025-06-05 18:27:08');

-- ----------------------------
-- Table structure for leaseorder
-- ----------------------------
DROP TABLE IF EXISTS `leaseorder`;
CREATE TABLE `leaseorder`  (
  `leaseorder_id` int NOT NULL AUTO_INCREMENT COMMENT 'leaseorderID',
  `games_id` int NULL DEFAULT NULL COMMENT '订单ID',
  `users_id` bigint NULL DEFAULT NULL COMMENT '用户ID',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `leaseorder_money` double NULL DEFAULT NULL COMMENT '租号花了多少钱',
  `end_time` timestamp NOT NULL,
  `leaseorder_status` int NULL DEFAULT NULL COMMENT '订单状态',
  PRIMARY KEY (`leaseorder_id`) USING BTREE,
  INDEX `games_id`(`games_id` ASC) USING BTREE,
  INDEX `users_id`(`users_id` ASC) USING BTREE,
  CONSTRAINT `leaseorder_ibfk_1` FOREIGN KEY (`games_id`) REFERENCES `games` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `leaseorder_ibfk_2` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of leaseorder
-- ----------------------------
INSERT INTO `leaseorder` VALUES (1, 6, 10, '2025-04-29 12:12:06', 5, '2025-04-29 13:12:07', 1);
INSERT INTO `leaseorder` VALUES (2, 22, 3, '2025-05-20 18:06:37', 4, '2025-05-20 19:06:38', 1);

-- ----------------------------
-- Table structure for moneyall
-- ----------------------------
DROP TABLE IF EXISTS `moneyall`;
CREATE TABLE `moneyall`  (
  `moneyall` int NOT NULL AUTO_INCREMENT COMMENT 'moneyallID',
  `users_id` bigint NULL DEFAULT NULL COMMENT '用户ID',
  `users_money` double NULL DEFAULT NULL COMMENT '用户的钱',
  PRIMARY KEY (`moneyall`) USING BTREE,
  INDEX `users_id`(`users_id` ASC) USING BTREE,
  CONSTRAINT `moneyall_ibfk_1` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of moneyall
-- ----------------------------
INSERT INTO `moneyall` VALUES (1, 3, 100);
INSERT INTO `moneyall` VALUES (3, 8, 109);
INSERT INTO `moneyall` VALUES (4, 9, 100);
INSERT INTO `moneyall` VALUES (5, 14, 100);
INSERT INTO `moneyall` VALUES (6, 10, 100);
INSERT INTO `moneyall` VALUES (7, 11, 100);
INSERT INTO `moneyall` VALUES (8, 12, 100);
INSERT INTO `moneyall` VALUES (9, 15, 0);

-- ----------------------------
-- Table structure for rentingorder
-- ----------------------------
DROP TABLE IF EXISTS `rentingorder`;
CREATE TABLE `rentingorder`  (
  `rentingorder` int NOT NULL AUTO_INCREMENT COMMENT 'rentingorderID',
  `games_id` int NULL DEFAULT NULL COMMENT '订单ID',
  `users_id` bigint NULL DEFAULT NULL COMMENT '用户ID',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `rentingorder_money` double NULL DEFAULT NULL COMMENT '出租赚了多少钱',
  PRIMARY KEY (`rentingorder`) USING BTREE,
  INDEX `games_id`(`games_id` ASC) USING BTREE,
  INDEX `fk_rentingorder_user`(`users_id` ASC) USING BTREE,
  CONSTRAINT `fk_rentingorder_user` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `rentingorder_ibfk_1` FOREIGN KEY (`games_id`) REFERENCES `games` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rentingorder
-- ----------------------------
INSERT INTO `rentingorder` VALUES (1, 32, 3, '2025-05-24 14:54:25', 10);
INSERT INTO `rentingorder` VALUES (2, 6, 8, '2025-05-28 17:21:54', 5);
INSERT INTO `rentingorder` VALUES (3, 1, 8, '2025-05-28 18:17:29', 6);
INSERT INTO `rentingorder` VALUES (4, 2, 8, '2025-05-28 18:18:05', 100);
INSERT INTO `rentingorder` VALUES (5, 4, 8, '2025-05-28 18:18:29', 200);
INSERT INTO `rentingorder` VALUES (6, 5, 8, '2025-05-28 18:18:57', 999);
INSERT INTO `rentingorder` VALUES (8, 7, 3, '2025-05-28 18:19:45', 3);
INSERT INTO `rentingorder` VALUES (9, 9, 9, '2025-05-28 18:20:15', 99);
INSERT INTO `rentingorder` VALUES (10, 10, 8, '2025-05-28 18:20:39', 1);
INSERT INTO `rentingorder` VALUES (11, 11, 8, '2025-05-28 18:20:58', 3);
INSERT INTO `rentingorder` VALUES (12, 12, 8, '2025-05-28 18:21:24', 3);
INSERT INTO `rentingorder` VALUES (13, 13, 8, '2025-05-28 18:21:46', 3);
INSERT INTO `rentingorder` VALUES (14, 14, 8, '2025-05-28 18:22:10', 3);
INSERT INTO `rentingorder` VALUES (15, 15, 8, '2025-05-28 18:22:34', 3);
INSERT INTO `rentingorder` VALUES (16, 16, 8, '2025-05-28 18:23:07', 4);
INSERT INTO `rentingorder` VALUES (17, 17, 8, '2025-05-28 18:23:29', 4);
INSERT INTO `rentingorder` VALUES (18, 18, 8, '2025-05-28 18:24:11', 4);
INSERT INTO `rentingorder` VALUES (19, 19, 9, '2025-05-28 18:24:53', 99);
INSERT INTO `rentingorder` VALUES (20, 20, 9, '2025-05-28 18:25:18', 90);
INSERT INTO `rentingorder` VALUES (21, 21, 9, '2025-05-28 18:25:39', 90);
INSERT INTO `rentingorder` VALUES (22, 22, 8, '2025-05-28 18:26:01', 4);
INSERT INTO `rentingorder` VALUES (23, 23, 10, '2025-05-28 18:26:41', 3);
INSERT INTO `rentingorder` VALUES (24, 25, 10, '2025-05-28 18:27:38', 7);
INSERT INTO `rentingorder` VALUES (25, 27, 10, '2025-05-28 18:28:31', 10);
INSERT INTO `rentingorder` VALUES (26, 28, 3, '2025-05-28 18:29:09', 10);
INSERT INTO `rentingorder` VALUES (27, 30, 12, '2025-05-28 18:29:35', 3);
INSERT INTO `rentingorder` VALUES (28, 33, 14, '2025-06-04 17:04:43', 3);
INSERT INTO `rentingorder` VALUES (30, 35, 14, '2025-06-04 21:39:36', 3);
INSERT INTO `rentingorder` VALUES (31, 36, 14, '2025-06-04 21:47:30', 8);
INSERT INTO `rentingorder` VALUES (32, 37, 14, '2025-06-04 21:50:53', 12);
INSERT INTO `rentingorder` VALUES (33, 38, 15, '2025-06-05 18:27:10', 7);

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色描述',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES (1, 'admin', '管理员', '2025-03-12 16:52:49');
INSERT INTO `roles` VALUES (2, 'consumer', '普通用户', '2025-04-07 16:24:01');
INSERT INTO `roles` VALUES (3, 'illegal', '违规', '2025-04-07 16:24:38');

-- ----------------------------
-- Table structure for user_roles
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `role_id`(`role_id` ASC) USING BTREE,
  CONSTRAINT `user_roles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `user_roles_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_roles
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `Userdescription` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` tinyint NULL DEFAULT 1 COMMENT '用户状态（1-正常，0-禁用）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (3, 'test', '098f6bcd4621d373cade4e832627b4f6', '假', 0, '2025-03-12 16:36:53', '2045272344@qq.com', '12345678900', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/uploads/1741769645357_0cddfc55-3f81-4d6e-ad6d-a4802624b4d1.png');
INSERT INTO `users` VALUES (8, 'admin', '0192023a7bbd73250516f069df18b500', '真', 0, '2025-03-12 16:52:49', '2045272344@qq.com', '12345678900', 'https://used-car-sales-system.oss-cn-guangzhou.aliyuncs.com/uploads/1742207879371_ed95c490-04d9-40a7-a7b5-27c2942b6560.png');
INSERT INTO `users` VALUES (9, 'aaa', 'd9f6e636e369552839e7bb8057aeb8da', '封了', 2, '2025-03-13 18:50:47', '2045272344@qq.com', '132123123', NULL);
INSERT INTO `users` VALUES (10, 'user', '6ad14ba9986e3615423dfca256d04e3f', '', 1, '2025-04-07 17:03:12', '2045272344@qq.com', '12345678900', NULL);
INSERT INTO `users` VALUES (11, 'login', '2446d54c2e68d221db9cff653b01a0eb', '', 1, '2025-04-10 15:56:11', '2045272344@qq.com', '12345678900', NULL);
INSERT INTO `users` VALUES (12, 'NBCF', '36dbc61f0ea88bac93e2d1f9fc41091e', NULL, 1, '2025-04-10 16:37:40', '2045272344@qq.com', '12345678900', NULL);
INSERT INTO `users` VALUES (14, 'test100', 'cc03e747a6afbbcbf8be7668acfebee5', NULL, 1, '2025-04-10 16:45:04', '2045272344@qq.com', '12345678900', NULL);
INSERT INTO `users` VALUES (15, 'bbb', '003d81296cbed643579b24d1cf6c907c', NULL, 1, '2025-06-05 12:48:46', '2045272344@qq.com', '1008611', NULL);

SET FOREIGN_KEY_CHECKS = 1;
