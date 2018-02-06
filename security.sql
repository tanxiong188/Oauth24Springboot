
-- ----------------------------
-- Table structure for sys_authority
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_authority";
CREATE TABLE "public"."sys_authority" (
  "id" int8 NOT NULL DEFAULT NULL,
  "name" varchar(50) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL,
  "code" varchar(100) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL
)
;

-- ----------------------------
-- Records of sys_authority
-- ----------------------------
INSERT INTO "public"."sys_authority" VALUES (1, '我的信息', '/user/me');
INSERT INTO "public"."sys_authority" VALUES (2, '用户列表', '/user/list');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_role";
CREATE TABLE "public"."sys_role" (
  "id" int8 NOT NULL DEFAULT NULL,
  "name" varchar(20) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "code" varchar(100) COLLATE "pg_catalog"."default" DEFAULT NULL
)
;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO "public"."sys_role" VALUES (1, '普通用户', 'ROLE_USER');
INSERT INTO "public"."sys_role" VALUES (2, '管理员', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for sys_role_authority
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_role_authority";
CREATE TABLE "public"."sys_role_authority" (
  "id" int8 NOT NULL DEFAULT NULL,
  "role_id" int8 DEFAULT NULL,
  "authority_id" int8 DEFAULT NULL
)
;

-- ----------------------------
-- Records of sys_role_authority
-- ----------------------------
INSERT INTO "public"."sys_role_authority" VALUES (1, 1, 1);
INSERT INTO "public"."sys_role_authority" VALUES (2, 2, 1);
INSERT INTO "public"."sys_role_authority" VALUES (3, 2, 2);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_user";
CREATE TABLE "public"."sys_user" (
  "id" int8 NOT NULL DEFAULT NULL,
  "name" varchar(20) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "username" varchar(20) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "password" varchar(255) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "tel_no" varchar(20) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying
)
;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO "public"."sys_user" VALUES (1, '张三', 'user', '123', '137');
INSERT INTO "public"."sys_user" VALUES (2, '李四', 'admin', '123', '138');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_user_role";
CREATE TABLE "public"."sys_user_role" (
  "id" int8 NOT NULL DEFAULT NULL,
  "user_id" int8 DEFAULT NULL,
  "role_id" int8 DEFAULT NULL
)
;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO "public"."sys_user_role" VALUES (1, 1, 1);
INSERT INTO "public"."sys_user_role" VALUES (2, 2, 2);

-- ----------------------------
-- Primary Key structure for table oauth_client_details
-- ----------------------------
ALTER TABLE "public"."oauth_client_details" ADD CONSTRAINT "oauth_client_details_pkey" PRIMARY KEY ("client_id");

-- ----------------------------
-- Indexes structure for table sys_authority
-- ----------------------------
CREATE INDEX "ix_auth_username" ON "public"."sys_authority" USING btree (
  "name" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST,
  "code" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table sys_authority
-- ----------------------------
ALTER TABLE "public"."sys_authority" ADD CONSTRAINT "authority_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_role
-- ----------------------------
ALTER TABLE "public"."sys_role" ADD CONSTRAINT "role_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_role_authority
-- ----------------------------
ALTER TABLE "public"."sys_role_authority" ADD CONSTRAINT "role_authority_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_user_role
-- ----------------------------
ALTER TABLE "public"."sys_user_role" ADD CONSTRAINT "user_role_pkey" PRIMARY KEY ("id");
