/*
Navicat MySQL Data Transfer

Source Server         : server
Source Server Version : 50710
Source Host           : 192.168.1.106:3306
Source Database       : food_safety

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2016-07-21 10:22:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_role_function_mapping
-- ----------------------------
DROP TABLE IF EXISTS `t_role_function_mapping`;
CREATE TABLE `t_role_function_mapping` (
  `id` varchar(36) NOT NULL,
  `function` varchar(36) DEFAULT NULL,
  `role_id` varchar(36) DEFAULT NULL,
  `ct` datetime DEFAULT NULL,
  `ut` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_role_function_mapping
-- ----------------------------
INSERT INTO `t_role_function_mapping` VALUES ('00333faa-1301-4ee1-8485-7cb86f64d7fe', 'userManage', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('00f987ec-e7ee-4acb-85c7-5db02d9f9876', 'algorithmManage', '61808c74-6e9a-425b-8a4f-0d535ba3df6c', '2016-05-16 16:14:27', null);
INSERT INTO `t_role_function_mapping` VALUES ('06122e10-ff6a-4c6c-ac9d-cf6da02cf283', 'dataManagePreprocess', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('077d3bfd-2bb3-4b00-8b22-4f3ee837fcbe', 'projectManageEdit', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('084c86d0-1973-48e9-97d1-8f920d70b26f', 'sampleTypeLv2ManageAdd', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('0ba7ec22-5e32-42cd-9dcb-20bcd7f0a89d', 'sampleTypeLv2Manage', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('0cf5b3d5-6fde-4945-ad37-0efc29d61489', 'userManageDel', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('11b5a8ac-24bf-430e-95b0-c8c7e42354b6', 'projectManageReprot', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('121e8b05-10c9-4b75-9335-7c3788dcf2a5', 'sampleManage', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('16060f16-b283-4879-b629-5163c22e3c0a', 'algorithmManageDown', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('1642e428-1065-4bd3-a675-d16fc5de6cb0', 'sampleManageView', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('184b0808-905b-42c5-9191-7909cc0bc2f4', 'projectManageReport', 'c42d72de-2adf-46da-8591-faddbd54e008', '2016-05-20 11:28:09', null);
INSERT INTO `t_role_function_mapping` VALUES ('1875b704-a19b-4462-9aa5-a55442b0277e', 'projectManageDel', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('18eee85b-8af6-4f85-8c0b-e5a9f34ad848', 'roleManage', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('19a20048-e27a-4863-8d80-f23b4d47f0db', 'instrumentManageAdd', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('1b3227da-04cf-4451-9dc9-bd3256756e12', 'serviceTypeManageAdd', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('201ff525-8e4b-42fd-b175-b080008eb3a4', 'sampleManageDel', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('20f4dd99-957d-487b-8fac-71f5f0dbc742', 'projectManageStart', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('237d5592-b05f-46c7-9b26-5da440b5a74d', 'dataManageImport', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('23e31f53-bb6d-4875-a718-63c061317102', 'instrumentManageDownload', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('27cf7c64-a7bb-49d0-96c3-19672dbb961e', 'sampleTypeLv2Manage', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('2b881b35-c9a7-4a9c-8f50-f4776e257a87', 'dataManageImport', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('2edf3d5b-2eee-4dbe-a71c-102e1e1d1247', 'serviceTypeManage', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('31a05e4c-6817-4915-8f3a-a3fba6bf17a3', 'reportManage', '61808c74-6e9a-425b-8a4f-0d535ba3df6c', '2016-05-16 16:14:27', null);
INSERT INTO `t_role_function_mapping` VALUES ('31dfaa8a-49ab-4d2e-8e2b-6708bf2e8949', 'projectManageEnd', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('3249c809-3133-43b3-b010-77e6c78eee3e', 'reportManage', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('337de3e5-077c-4f56-81d6-ce167b0fa83f', 'dataManageExport', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('36763dc5-6398-4657-ad0d-1d7f5b797938', 'projectManageEdit', 'c42d72de-2adf-46da-8591-faddbd54e008', '2016-05-20 11:28:09', null);
INSERT INTO `t_role_function_mapping` VALUES ('36ae5114-46d5-4dd3-80bd-ddd6c6e86531', 'projectManageDel', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('3a0653b5-52b9-488d-b15c-71ab33c4acf0', 'projectManageStart', 'c42d72de-2adf-46da-8591-faddbd54e008', '2016-05-20 11:28:09', null);
INSERT INTO `t_role_function_mapping` VALUES ('3bd47917-0d3b-4eed-8bcb-e2c876c6298b', 'projectManage', 'c42d72de-2adf-46da-8591-faddbd54e008', '2016-05-20 11:28:09', null);
INSERT INTO `t_role_function_mapping` VALUES ('3bdf98f4-b0d8-466c-b2e1-fa729e35c64c', 'analysisManageView', 'c42d72de-2adf-46da-8591-faddbd54e008', '2016-05-20 11:28:09', null);
INSERT INTO `t_role_function_mapping` VALUES ('40f0cd5c-89df-409f-b681-b1a0131da32a', 'instrumentManage', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('428cbd98-5546-4d81-9fc4-838d0ec380e6', 'algorithmManageAdd', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('43d8be38-5b75-4b51-8eff-ae9829cfe150', 'analysisManageView', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('45e30d26-c9cf-40d7-b0da-92952e00e71f', 'dataManage', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('482c427c-06a8-45cd-ab61-f0b3e01fb97b', 'algorithmManage', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('48fde6a7-9cbb-4483-8a47-40d9e54fcec5', 'dataManageView', 'c42d72de-2adf-46da-8591-faddbd54e008', '2016-05-20 11:28:09', null);
INSERT INTO `t_role_function_mapping` VALUES ('4d11127d-eda6-49bb-90ab-9182cebe16ae', 'instrumentManage', '61808c74-6e9a-425b-8a4f-0d535ba3df6c', '2016-05-16 16:14:27', null);
INSERT INTO `t_role_function_mapping` VALUES ('4fe497cb-06a9-47d4-8c76-e449ce104c0a', 'sampleTypeLv2ManageDel', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('527024d5-7135-470a-b1bb-6879e9c9c662', 'standardsManageEdit', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('531f10ea-c972-4bbd-9209-bc16412834e6', 'projectManageEdit', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('548cf53a-e9e4-4a22-8d77-6fd0f1d25c8d', 'roleManageAdd', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('5570ef6d-51be-462c-9853-039e5513fa00', 'standardsManageAdd', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('560ce8bb-5492-4bf1-a793-e5045d01f959', 'sampleTypeLv2ManageEdit', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('5858847c-2a06-4add-b27e-e777bae2e8f4', 'roleManageDel', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('58dcb658-2020-4e6b-a091-40af53911656', 'projectManage', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('59b563a7-3d2d-4322-914c-4693846fbb3c', 'analysisManage', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('5a37dd34-cfcb-4b82-844f-18adc727cdcc', 'serviceTypeManageEdit', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('5b38d383-e8b1-41d5-aa37-a51ed2860791', 'standardsManage', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('5d1411d5-b5e0-4e76-b626-f252a207917f', 'sampleTypeLv1Manage', '61808c74-6e9a-425b-8a4f-0d535ba3df6c', '2016-05-16 16:14:27', null);
INSERT INTO `t_role_function_mapping` VALUES ('5ff5b539-f8ed-48a5-9430-c3c62ee4142d', 'instrumentManageEdit', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('62b7a604-97ed-438f-9e1d-9478ad907561', 'sampleTypeLv1Manage', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('654067f7-90b6-41df-8912-8b2175f897dc', 'dataManageDel', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('66847ecb-f5b7-45a2-9a52-992df456467f', 'serviceTypeManageDel', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('68343405-7a2c-44c7-9ebe-73d1f21be935', 'projectManageReport', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('6842d89c-599b-4ca7-9824-c4e50c101250', 'analysisManageComment', 'c42d72de-2adf-46da-8591-faddbd54e008', '2016-05-20 11:28:09', null);
INSERT INTO `t_role_function_mapping` VALUES ('6b35eca8-e881-485a-b923-8ed6c25c865a', 'basicInformationManage', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('6c0015d2-a68a-4218-a8d5-ae0a5592325c', 'sampleManageEdit', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('6ce26632-8229-4dac-8ff0-de9805bad83b', 'sampleTypeLv2ManageAdd', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('71dae290-653d-4b0b-a9b3-7c5a7d8856bd', 'instrumentManageDel', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('72812560-b1be-4a51-b565-482b2f473bc8', 'dataManageImport', 'c42d72de-2adf-46da-8591-faddbd54e008', '2016-05-20 11:28:09', null);
INSERT INTO `t_role_function_mapping` VALUES ('72aaf348-f281-42a1-96c2-faeed24a91d4', 'standardsManageView', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('73574ac0-0dc6-4db3-85f8-5d2bd0a6a0b5', 'instrumentManageView', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('738cca12-e86a-4238-ae03-24ea0b5d491f', 'standardsManageDel', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('73d7567b-dc56-4e70-8529-f65a0203cd56', 'roleManageEdit', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('75775941-0725-4824-994d-fd476502a086', 'userManageAdd', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('75aae51b-8a60-471f-a156-df62964a9f5a', 'projectManageEnd', 'c42d72de-2adf-46da-8591-faddbd54e008', '2016-05-20 11:28:09', null);
INSERT INTO `t_role_function_mapping` VALUES ('779f86dd-4986-48e0-9b43-16a4183f5aa4', 'dataManageDel', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('78542c94-e5b9-480b-a9f3-eaf041f2673d', 'dataManageExport', 'c42d72de-2adf-46da-8591-faddbd54e008', '2016-05-20 11:28:09', null);
INSERT INTO `t_role_function_mapping` VALUES ('78a4306a-1d9f-46eb-a7c9-28832d9a0e52', 'serviceTypeManageDel', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('7cf8ac2e-7737-410b-86d4-9ca990451fda', 'userManageEdit', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('7dd4fca7-0636-451b-bcb1-80758260d498', 'roleManageAuth', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('7defc4ba-d6d8-4513-9785-152fda2db31c', 'basicInformationManage', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('81ea7b68-7866-4854-884b-f0c445f57efc', 'roleManageAuth', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('8204449c-0ce5-4749-bf89-7c19da0aca53', 'analysisManage', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('865606c6-876a-43d7-9e24-96b2f6ade8d9', 'instrumentManageServer', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('877bb7de-39f4-4cef-b8c8-90a169fc17d5', 'sampleTypeLv1ManageDel', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('8beb5c42-6bde-4e4d-9052-479a2822c196', 'algorithmManageStop', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('8d45a313-60b7-458b-b00b-708b821f2257', 'sampleManageAdd', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('8d48ae0f-e0fa-4ebf-b150-df1b4dca7821', 'serviceTypeManage', '61808c74-6e9a-425b-8a4f-0d535ba3df6c', '2016-05-16 16:14:27', null);
INSERT INTO `t_role_function_mapping` VALUES ('8e108803-c402-4073-bdb3-e75122d3946e', 'sampleTypeLv1ManageAdd', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('8fff03f6-8721-4d07-a15a-d586075b98d7', 'reportManage', '82533bf3-9448-403c-880e-7577169a1e4a', '2016-05-16 15:02:02', null);
INSERT INTO `t_role_function_mapping` VALUES ('91557d93-6d6f-4577-9d51-b74a974938b7', 'standardsManageAdd', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('95e138e1-72bf-4a34-b114-32c944e1152e', 'roleManageAdd', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('95e8ebae-7de0-4b7f-9e2b-4ea6db0809c6', 'sampleTypeLv2Manage', '61808c74-6e9a-425b-8a4f-0d535ba3df6c', '2016-05-16 16:14:27', null);
INSERT INTO `t_role_function_mapping` VALUES ('97253720-416c-4a7f-ab3d-443f93586ab2', 'standardsManage', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('97deeaa7-e6d9-494f-8407-d3149e4e50d4', 'standardsManageDel', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('9db0c044-ced4-4029-ad00-84a1a3e3df78', 'sampleManage', '61808c74-6e9a-425b-8a4f-0d535ba3df6c', '2016-05-16 16:14:27', null);
INSERT INTO `t_role_function_mapping` VALUES ('a058c67b-4348-4ae4-a342-22a0f51f84f8', 'algorithmManageDown', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('a1513fc2-5229-48bf-9018-3db50637eac0', 'dataManagePreprocess', 'c42d72de-2adf-46da-8591-faddbd54e008', '2016-05-20 11:28:09', null);
INSERT INTO `t_role_function_mapping` VALUES ('ace31edf-3625-45ec-97f6-208684472268', 'basicInformationManage', '61808c74-6e9a-425b-8a4f-0d535ba3df6c', '2016-05-16 16:14:27', null);
INSERT INTO `t_role_function_mapping` VALUES ('b0b9482d-b378-49f8-af36-c0a018354717', 'userManageDel', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('b0cef7de-91ec-4124-a8b8-feea1d63bbac', 'instrumentManageView', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('b3603b08-82e3-47cd-af0a-a8318c1ed04f', 'projectManageView', 'c42d72de-2adf-46da-8591-faddbd54e008', '2016-05-20 11:28:09', null);
INSERT INTO `t_role_function_mapping` VALUES ('b5f30421-a643-4acb-809f-d7f65b489740', 'sampleManageView', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('b9437007-e36a-4760-aafb-822b8431c37f', 'standardsManageDownload', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('bca2ab42-1711-4284-9d57-839ce2751bf9', 'projectManageView', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('bf37f885-1b92-4d80-9643-738fef3a0912', 'instrumentManageAdd', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('c001e9ef-9f1f-4690-9607-1e8cd72ab061', 'sampleTypeLv1ManageEdit', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('c063a06d-d975-422d-b48a-6c72123c2411', 'roleManageDel', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('c367bafe-d5b8-479b-b760-d677347a151c', 'analysisManage', 'c42d72de-2adf-46da-8591-faddbd54e008', '2016-05-20 11:28:09', null);
INSERT INTO `t_role_function_mapping` VALUES ('c6f180c3-c74c-4981-936d-36f252dbe714', 'analysisManageComment', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('c9cb04f1-b905-441a-a8ac-e4326413e3fd', 'dataManage', 'c42d72de-2adf-46da-8591-faddbd54e008', '2016-05-20 11:28:09', null);
INSERT INTO `t_role_function_mapping` VALUES ('caaf42e3-2ece-406a-a565-f383500dcac4', 'approvalUsers', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('ce250931-7cee-4e16-bfdc-8c674859afe7', 'dataManage', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('d0f65fa0-9b0f-41c2-b56d-33c506fb9d83', 'serviceTypeManage', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('d2dd4ca7-610a-47db-b00a-f56914a3e713', 'standardsManage', '61808c74-6e9a-425b-8a4f-0d535ba3df6c', '2016-05-16 16:14:27', null);
INSERT INTO `t_role_function_mapping` VALUES ('d6d26471-9ef9-458a-adc4-98f6600b798c', 'sampleTypeLv1ManageDel', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('d6f08c3f-3c05-409a-a06a-3ca9b30f8851', 'algorithmManageView', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('d717fc45-40e2-4eff-b98e-a8d9d0e5226d', 'dataManageView', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('d8321a67-dbf6-48b5-b6b3-9040cd06422a', 'instrumentManageDel', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('db2b2cf8-7deb-4caa-9ead-b653fa26b058', 'sampleTypeLv1ManageAdd', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('dd0e7aa5-ed8c-4b0f-8414-8a7a684c203f', 'instrumentManage', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('de0157d0-5148-4228-9d41-63f4a3bfa298', 'roleManageEdit', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('e270b435-aa8e-4eae-8ccc-74c07ab23ef9', 'roleManage', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('e2bbae03-3510-4cd5-8557-5fdf12960acb', 'projectManageDel', 'c42d72de-2adf-46da-8591-faddbd54e008', '2016-05-20 11:28:09', null);
INSERT INTO `t_role_function_mapping` VALUES ('e3664043-bbcf-4643-9301-efcadfd9977f', 'instrumentManageServer', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('e6d803cb-a2ec-4580-9ef1-ad50ae95189d', 'algorithmManageEdit', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('e939d5bc-f600-4506-bf9d-803fa2db2038', 'dataManageDel', 'c42d72de-2adf-46da-8591-faddbd54e008', '2016-05-20 11:28:09', null);
INSERT INTO `t_role_function_mapping` VALUES ('e9b49e68-cbea-4308-8ff4-9fd092f0c0a4', 'serviceTypeManageEdit', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('ec3c2273-9622-4293-9b3a-e09ec952cdce', 'projectManageAdd', 'c42d72de-2adf-46da-8591-faddbd54e008', '2016-05-20 11:28:09', null);
INSERT INTO `t_role_function_mapping` VALUES ('f025491f-c14c-4acf-8989-c34fa9806e53', 'dataManageExport', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('fb1c2051-2eed-4e86-a943-22c46229e483', 'algorithmManage', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('fbe1bc95-9886-405f-a8e6-6bb399ff458d', 'projectManage', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
INSERT INTO `t_role_function_mapping` VALUES ('fc4d17ae-945e-4734-ae38-8609a514557b', 'projectManageAdd', 'a43419a9-cef0-444c-9edf-643dff455479', '2016-07-12 17:34:59', null);
